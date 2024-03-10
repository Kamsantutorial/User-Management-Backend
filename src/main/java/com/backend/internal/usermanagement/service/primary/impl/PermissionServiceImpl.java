package com.backend.internal.usermanagement.service.primary.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.backend.internal.usermanagement.common.enums.ErrorCode;
import com.backend.internal.usermanagement.dto.base.RequestPageableDTO;
import com.backend.internal.usermanagement.dto.permission.PermissionDTO;
import com.backend.internal.usermanagement.entity.primary.PermissionEntity;
import com.backend.internal.usermanagement.exception.ServerException;
import com.backend.internal.usermanagement.mapper.PermissionMapper;
import com.backend.internal.usermanagement.repository.base.BaseCriteria;
import com.backend.internal.usermanagement.repository.primary.PermissionRepository;
import com.backend.internal.usermanagement.service.primary.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public Page<PermissionDTO> findWithPage(PermissionDTO permissionDTO, RequestPageableDTO pageableDTO) {
		BaseCriteria<PermissionRepository> criteria = new BaseCriteria<>(permissionRepository);
		criteria.or(criteria.or(criteria.like("permissionName", permissionDTO.getSearchKeyword()),
				criteria.or(criteria.like("type", permissionDTO.getSearchKeyword()),
						criteria.equal("id", permissionDTO.getSearchKeyword()))));
		criteria.orderBy("type", Direction.DESC);
		criteria.size(pageableDTO.getSize());
		criteria.setPage(pageableDTO.getPageOffset());

		Page<PermissionEntity> permissions = permissionRepository.findAllWithPage(criteria);
		List<PermissionDTO> permissionDTOs = new ArrayList<>();
		PermissionMapper.INSTANCE.copyListEntityToListDto(permissions.getContent(), permissionDTOs);
		return new PageImpl<>(permissionDTOs, permissions.getPageable(), permissions.getTotalElements());
	}

	@Override
	public void create(PermissionDTO dto) throws ServerException {
		PermissionEntity permissionEntity = new PermissionEntity();
		PermissionMapper.INSTANCE.copyDtoToEntity(dto, permissionEntity);
		permissionRepository.save(permissionEntity);
		PermissionMapper.INSTANCE.copyEntityToDto(permissionEntity, dto);
	}

	@Override
	public void update(PermissionDTO dto, Long id) throws ServerException {
		PermissionDTO permissionDTO = this.findOne(id);

		if (Objects.isNull(permissionDTO)) {
			throw new ServerException(ErrorCode.E002.name(), ErrorCode.E002.getDesc());
		}
		PermissionEntity permissionEntity = new PermissionEntity();
		PermissionMapper.INSTANCE.copyDtoToEntity(dto, permissionEntity);
		permissionRepository.save(permissionEntity);
		PermissionMapper.INSTANCE.copyEntityToDto(permissionEntity, dto);
	}

	@Override
	public void delete(Long id) throws ServerException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'delete'");
	}

	@Override
	public PermissionDTO findOne(Long id) throws ServerException {
		BaseCriteria<PermissionRepository> criteria = new BaseCriteria<>(permissionRepository);
		criteria.equal("id", id);
		PermissionEntity permissionEntity = permissionRepository.findOneWithCriteria(criteria).orElse(null);
		PermissionDTO dto = new PermissionDTO();
		PermissionMapper.INSTANCE.copyEntityToDto(permissionEntity, dto);
		return dto;
	}

	@Override
	public List<PermissionDTO> findAll() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'findAll'");
	}
}
