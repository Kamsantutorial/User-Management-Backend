package com.backend.internal.usermanagement.service.primary;

import java.util.List;
import com.backend.internal.usermanagement.entity.primary.RolePermissionEntity;

public interface RolePermissionService {
	boolean save(RolePermissionEntity rolePermissionEntity);
	
	RolePermissionEntity findExistingRolePermission(Long roleId, Long permissionId, int isDeleted);
	
	List<RolePermissionEntity> findByPermissionId(Long permissionId);
	List<RolePermissionEntity> findByRoleId(Long roleId);
}
