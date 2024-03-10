package com.backend.internal.usermanagement.service.primary;

import java.util.List;

import com.backend.internal.usermanagement.entity.primary.UserRoleEntity;

/**
 * 
 * @author SETC01
 *
 */
public interface UserRoleService {
	List<UserRoleEntity> findByUserId(Long userId, int isDeleted);
	List<UserRoleEntity> findByRoleId(Long roleId, int isDeleted);
	UserRoleEntity findByUserIdAndRoleId(Long userId, Long roleId);
	boolean save(UserRoleEntity userRole);
}
