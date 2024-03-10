package com.backend.internal.usermanagement.repository.primary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.internal.usermanagement.entity.primary.RoleEntity;
import com.backend.internal.usermanagement.entity.primary.RolePermissionEntity;
import com.backend.internal.usermanagement.repository.base.BaseRepository;

/**
 * 
 * @author SETC01
 *
 */
@Repository
public interface RoleRepository extends BaseRepository<RoleEntity, Long> {
	static String queryRolesByUserId = "SELECT g.*\r\n" + 
										"FROM user_roles ugd\r\n" + 
										"INNER JOIN `roles` g ON g.id = ugd.role_id\r\n" + 
										"WHERE ugd.is_deleted = 0 AND ugd.user_id = :userId";
	static String queryRolesByUsername = "SELECT g.*\r\n" + 
										"FROM user_roles ugd\r\n" + 
										"INNER JOIN `roles` g ON g.id = ugd.role_id\r\n" +
										"INNER JOIN `users` u ON u.id = ugd.user_id\r\n" +
										"WHERE ugd.is_deleted = 0 AND g.is_deleted = 0 AND u.username = :username";
	static String queryAllRoles = "SELECT *\r\n" + 
									"FROM `roles`\r\n" + 
									"WHERE is_deleted = 0\r\n" + 
									"LIMIT :start, :length";
	static String queryCountAllRoles = "SELECT COUNT(id)\r\n" + 
										"FROM `roles`\r\n" + 
										"WHERE is_deleted = 0 ";

	static String queryExistRole = "SELECT *\r\n" + 
									"FROM `roles`\r\n" + 
									"WHERE TRIM(LOWER(role_name)) = :roleName\r\n" + 
									"AND is_deleted = :isDeleted\r\n" + 
									"LIMIT 1";
	static String queryFindConcatRole = "SELECT GROUP_CONCAT(g.role_name) AS 'role_name' \r\n" + 
										"FROM `roles` g\r\n" + 
										"LEFT JOIN user_roles ugd ON ugd.role_id = g.id\r\n" + 
										"LEFT JOIN users u ON u.id = ugd.user_id\r\n" + 
										"WHERE g.is_deleted = 0 AND ugd.is_deleted = 0 AND u.username = :username";

	static String queryFindAllRoles = "SELECT * \r\n" + 
										"FROM `roles`\r\n" + 
										"WHERE is_deleted = 0";
	
	static String queryPermissionsByRole = "SELECT * FROM role_permissions WHERE role_id = :roleId AND is_deleted = 0";
	
	Optional<RoleEntity> findById(Long id);
	
	@Query(value = queryRolesByUserId, nativeQuery = true)
	List<RoleEntity> findRoleByUserId(@Param("userId") Long userId);
	
	@Query(value = queryRolesByUsername, nativeQuery = true)
	List<RoleEntity> findRoleByUsername(@Param("username" ) String username);
	
	@Query(value = queryAllRoles, nativeQuery = true)
	List<RoleEntity> getAllRoles(@Param("start") int start, @Param("length") int length);
	
	@Query(value = queryCountAllRoles, nativeQuery = true)
	int countAllRoles();
	
	@Query(value = queryExistRole, nativeQuery =  true)
	RoleEntity findExistRoleByRoleName(@Param("roleName") String roleName, @Param("isDeleted") int isDeleted);
	
	@Query(value = queryFindConcatRole, nativeQuery = true)
	String findConcatRoleByUsername(@Param("username") String username);
	
	@Query(value = queryFindAllRoles, nativeQuery = true)
	List<RoleEntity> findAllRoles();
	
	@Query(value = queryPermissionsByRole, nativeQuery = true)
	List<RolePermissionEntity> findPermissionsByRole(@Param("roleId") Long roleId);
}