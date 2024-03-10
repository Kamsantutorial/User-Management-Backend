package com.backend.internal.usermanagement.service.primary.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.internal.usermanagement.entity.primary.RolePermissionEntity;
import com.backend.internal.usermanagement.repository.primary.RolePermissionRepository;
import com.backend.internal.usermanagement.service.primary.RolePermissionService;


@Service
public class RolePermissionServiceImpl implements RolePermissionService {

	@Autowired
	RolePermissionRepository rolePermissionRepository;

	@Override
	public boolean save(RolePermissionEntity roleGroupDetail) {
		rolePermissionRepository.save(roleGroupDetail);
		return true;
	}

	@Override
	public RolePermissionEntity findExistingRolePermission(Long roleId, Long permissionId, int isDeleted) {
		return rolePermissionRepository.findExistingRolePermission(roleId, permissionId, isDeleted);
	}

	@Override
	public List<RolePermissionEntity> findByPermissionId(Long permissionId) {
		return rolePermissionRepository.findByPermissionId(permissionId);
	}

	@Override
	public List<RolePermissionEntity> findByRoleId(Long roleId) {
		return rolePermissionRepository.findAllByRoleId(roleId);
	}

}
