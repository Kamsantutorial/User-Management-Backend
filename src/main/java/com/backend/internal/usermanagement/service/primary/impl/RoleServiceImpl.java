package com.backend.internal.usermanagement.service.primary.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import com.backend.internal.usermanagement.repository.primary.RoleRepository;
import com.backend.internal.usermanagement.common.enums.ErrorCode;
import com.backend.internal.usermanagement.dto.base.RequestPageableDTO;
import com.backend.internal.usermanagement.dto.role.RoleDTO;
import com.backend.internal.usermanagement.entity.primary.RoleEntity;
import com.backend.internal.usermanagement.exception.ServerException;
import com.backend.internal.usermanagement.mapper.RoleMapper;
import com.backend.internal.usermanagement.repository.base.BaseCriteria;
import com.backend.internal.usermanagement.service.primary.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public Page<RoleDTO> findWithPage(RoleDTO roleDTO, RequestPageableDTO pageableDTO) {
		BaseCriteria<RoleRepository> criteria = new BaseCriteria<>(roleRepository);
		criteria.or(criteria.or(criteria.like("roleName", roleDTO.getSearchKeyword()),
				criteria.equal("id", roleDTO.getSearchKeyword())));
		criteria.size(pageableDTO.getSize());
		criteria.setPage(pageableDTO.getPageOffset());

		Page<RoleEntity> roles = roleRepository.findAllWithPage(criteria);
		List<RoleDTO> roleDTOs = new ArrayList<>();
		RoleMapper.INSTANCE.copyListEntityToListDto(roles.getContent(), roleDTOs);
		return new PageImpl<>(roleDTOs, roles.getPageable(), roles.getTotalElements());
	}

	@Override
	public void create(RoleDTO dto) throws ServerException {
		RoleEntity roleEntity = new RoleEntity();
		RoleMapper.INSTANCE.copyDtoToEntity(dto, roleEntity);
		roleRepository.save(roleEntity);
		RoleMapper.INSTANCE.copyEntityToDto(roleEntity, dto);
	}

	@Override
	public void update(RoleDTO dto, Long id) throws ServerException {
		RoleDTO roleDTO = this.findOne(id);

		if (Objects.isNull(roleDTO)) {
			throw new ServerException(ErrorCode.E002.name(), ErrorCode.E002.getDesc());
		}
		RoleEntity roleEntity = new RoleEntity();
		RoleMapper.INSTANCE.copyDtoToEntity(dto, roleEntity);
		roleRepository.save(roleEntity);
		RoleMapper.INSTANCE.copyEntityToDto(roleEntity, dto);
	}

	@Override
	public void delete(Long id) throws ServerException {
		RoleDTO roleDTO = this.findOne(id);

		if (Objects.isNull(roleDTO)) {
			throw new ServerException(ErrorCode.E002.name(), ErrorCode.E002.getDesc());
		}
		RoleEntity roleEntity = new RoleEntity();
		RoleMapper.INSTANCE.copyDtoToEntity(roleDTO, roleEntity);
		roleEntity.setIsDeleted(true);
		roleRepository.save(roleEntity);
	}

	@Override
	public RoleDTO findOne(Long id) throws ServerException {
		BaseCriteria<RoleRepository> criteria = new BaseCriteria<>(roleRepository);
		criteria.equal("id", id);
		RoleEntity roleEntity = roleRepository.findOneWithCriteria(criteria).orElse(null);
		RoleDTO dto = new RoleDTO();
		RoleMapper.INSTANCE.copyEntityToDto(roleEntity, dto);
		return dto;
	}

	@Override
	public List<RoleDTO> findAll() {
		BaseCriteria<RoleRepository> criteria = new BaseCriteria<>(roleRepository);
		criteria.equal("isActive", true);
		List<RoleEntity> entities = roleRepository.findAllWithCriteria(criteria);
		List<RoleDTO> items = new ArrayList<>();
		RoleMapper.INSTANCE.copyListEntityToListDto(entities, items);
		return items;
	}
}
