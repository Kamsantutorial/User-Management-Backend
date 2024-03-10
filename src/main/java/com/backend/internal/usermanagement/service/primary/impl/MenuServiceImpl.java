package com.backend.internal.usermanagement.service.primary.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.criteria.JoinType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.backend.internal.usermanagement.dto.base.RequestPageableDTO;
import com.backend.internal.usermanagement.dto.menu.MenuDTO;
import com.backend.internal.usermanagement.entity.primary.MenuEntity;
import com.backend.internal.usermanagement.entity.primary.RoleEntity;
import com.backend.internal.usermanagement.entity.primary.UserEntity;
import com.backend.internal.usermanagement.mapper.MenuMapper;
import com.backend.internal.usermanagement.repository.base.BaseCriteria;
import com.backend.internal.usermanagement.repository.primary.MenuRepository;
import com.backend.internal.usermanagement.service.primary.MenuService;


@Service
public class MenuServiceImpl implements MenuService {

	private static final Logger logger = LogManager.getLogger(MenuServiceImpl.class);

	@Autowired
	private MenuRepository menuRepository;

	@Override
	public MenuEntity findById(Long id) {
		return menuRepository.findById(id).orElse(null);
	}

	@Override
	public List<MenuEntity> getMenus(int length, int start) {
		return menuRepository.getAllMenus(length, start);
	}

	@Override
	public int countAllMenus() {
		return menuRepository.countAllMenus();
	}

	@Override
	public List<MenuEntity> getParentMenus() {
		return menuRepository.getAllParentMenu();
	}

	@Override
	public boolean save(MenuEntity menu) {
		try {
			menuRepository.save(menu);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<MenuEntity> getAllMenus() {
		return menuRepository.getAllMenus();
	}

	@Override
	public int existByMenuUrl(String url) {
		return menuRepository.existsByMenuUrl(url.toLowerCase());
	}

	@Override
	public MenuEntity existByMenuName(String menuName, int isDeleted) {
		return menuRepository.existsByMenuName(menuName.toLowerCase(), isDeleted);
	}

	@Override
	public List<MenuEntity> findAllByParentId(Long parentId, int isDeleted) {
		return menuRepository.getAllMenuByParentId(parentId, isDeleted);
	}

	@Override
	public Page<MenuDTO> findWithPage(MenuDTO menuDTO, RequestPageableDTO pageableDTO) {
		BaseCriteria<MenuRepository> criteria = new BaseCriteria<>(menuRepository);
		criteria.or(criteria.or(criteria.like("menuName", menuDTO.getSearchKeyword()),
				criteria.equal("id", menuDTO.getSearchKeyword())));
		criteria.setDistinct(true);
		criteria.size(pageableDTO.getSize());
		criteria.setPage(pageableDTO.getPageOffset());

		Page<MenuEntity> listEntity = menuRepository.findAllWithPage(criteria);
		List<MenuDTO> listDto = new ArrayList<>();
		MenuMapper.INSTANCE.copyListEntityToListDto(listEntity.getContent(), listDto);
		return new PageImpl<>(listDto, listEntity.getPageable(), listEntity.getTotalElements());
	}

	@Override
	public List<MenuDTO> findAllTypeMenu() {
		BaseCriteria<MenuRepository> parentCriteria = new BaseCriteria<>(menuRepository);
		parentCriteria.isNotNull("parentId");

		List<Long> listIds = menuRepository.findAllWithCriteria(parentCriteria)
				.stream().map(m -> m.getParentId()).distinct()
				.collect(Collectors.toList());

		BaseCriteria<MenuRepository> criteria = new BaseCriteria<>(menuRepository);
		criteria.join("permissions", JoinType.LEFT);
		criteria.orderBy("id", Direction.ASC);
		criteria.setDistinct(true);
		criteria.notIn("id", listIds);
		List<MenuEntity> listEntity = menuRepository.findAllWithCriteria(criteria);

		List<MenuDTO> listDto = new ArrayList<>();
		MenuMapper.INSTANCE.copyListEntityToListDto(listEntity, listDto);
		return listDto;
	}

	@Override
	public List<MenuDTO> findAllByUser(UserEntity userEntity) {

		Set<RoleEntity> roleList = userEntity.getRoles();
		List<Long> permissionIds = new ArrayList<>();
		roleList.forEach(role -> {
			if (Objects.nonNull(role.getPermissions()))
				permissionIds.addAll(role.getPermissions().stream().map(p -> p.getId()).collect(Collectors.toList()));
		});
		logger.info("permissionIds {}", permissionIds.size());

		if (permissionIds.isEmpty())
			return Collections.emptyList();

		logger.info("permissionIds {}", permissionIds);

		BaseCriteria<MenuRepository> criteria = new BaseCriteria<>(menuRepository);
		criteria.join("permissions", JoinType.LEFT);
		criteria.join("parent", JoinType.LEFT);
		criteria.in("permissions.id", permissionIds);
		criteria.orderBy("id", Direction.ASC);

		criteria.setDistinct(true);
		List<MenuEntity> listEntity = menuRepository.findAllWithCriteria(criteria);

		Set<MenuEntity> parentOther = new HashSet<>(
				listEntity.stream().filter(m -> Objects.nonNull(m.getParent())).map(m -> m.getParent())
						.collect(Collectors.toList()));
		listEntity.addAll(new ArrayList<>(parentOther));

		List<MenuDTO> listDto = new ArrayList<>();
		MenuMapper.INSTANCE.copyListEntityToListDto(listEntity, listDto);

		List<MenuDTO> listDtoParent = listDto.stream().filter(menu -> Objects.isNull(menu.getParent()))
				.collect(Collectors.toList());
		listDtoParent.forEach(parent -> {
			List<MenuDTO> children = listDto.stream()
					.filter(ch -> Objects.nonNull(ch.getParent()) && ch.getParent().getId().equals(parent.getId()))
					.collect(Collectors.toList());
			logger.info("children {}", permissionIds);

			parent.setChildren(children);
		});

		removeIfNotExist(permissionIds, listDtoParent);

		return listDtoParent;
	}

	private void removeIfNotExist(List<Long> permissionIds, List<MenuDTO> menus) {
		menus.forEach(menu -> {
			menu.getPermissions().removeIf(p -> permissionIds.stream().noneMatch(pid -> pid.equals(p.getId())));
			logger.info("menu.getPermissions() {}", menu.getPermissions());
			if (!menu.getChildren().isEmpty()) {
				removeIfNotExist(permissionIds, menu.getChildren());
			}
		});
	}
}
