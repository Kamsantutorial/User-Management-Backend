package com.backend.internal.usermanagement.repository.primary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.internal.usermanagement.entity.primary.RolePermissionEntity;
import com.backend.internal.usermanagement.repository.base.BaseRepository;

/**
 * 
 * @author SETC01
 *
 */
@Repository
public interface RolePermissionRepository
		extends JpaRepository<RolePermissionEntity, Long>, BaseRepository<RolePermissionEntity, Long> {
	static String queryExistingRolePermission = "SELECT *\r\n" +
			"FROM role_permissions\r\n" +
			"WHERE role_id = :roleId\r\n" +
			" AND permission_id = :permissionId\r\n" +
			" AND is_deleted = :isDeleted LIMIT 1";

	static String queryRolePermissionByPermissionId = "SELECT * \r\n" +
			"FROM role_permissions \r\n" +
			"WHERE permission_id = :permissionId AND is_deleted = 0";

	static String queryRolePermissionByRoleId = "SELECT * \r\n" +
			"FROM role_permissions \r\n" +
			"WHERE role_id = :roleId AND is_deleted = 0";

	@Query(value = queryExistingRolePermission, nativeQuery = true)
	RolePermissionEntity findExistingRolePermission(@Param("roleId") Long roleId,
			@Param("permissionId") Long permissionId, @Param("isDeleted") int isDeleted);

	@Query(value = queryRolePermissionByPermissionId, nativeQuery = true)
	List<RolePermissionEntity> findByPermissionId(@Param("permissionId") Long permissionId);

	@Query(value = queryRolePermissionByRoleId, nativeQuery = true)
	List<RolePermissionEntity> findAllByRoleId(@Param("roleId") Long roleId);
}
