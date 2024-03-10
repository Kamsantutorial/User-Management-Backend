package com.backend.internal.usermanagement.repository.primary;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.backend.internal.usermanagement.entity.primary.PermissionEntity;
import com.backend.internal.usermanagement.repository.base.BaseRepository;

@Repository
public interface PermissionRepository extends BaseRepository<PermissionEntity, Long> {
	
	static String queryPermissionByUsername = "SELECT r.permission_name \r\n" + 
										"FROM role_permissions rgd\r\n" + 
										"INNER JOIN permissions r ON r.id = rgd.permission_id\r\n" + 
										"INNER JOIN `roles` g ON g.id = rgd.role_id\r\n" + 
										"INNER JOIN user_roles ugd ON ugd.role_id = g.id\r\n" + 
										"INNER JOIN users u ON u.id = ugd.user_id\r\n" + 
										"WHERE rgd.is_deleted = 0 " +
										"AND g.is_deleted = 0 " +
										"AND ugd.is_deleted = 0 " +
										"AND r.is_active = 1 " +
										"AND u.username = :username\r\n" + 
										"GROUP BY r.permission_name";
	
	static String queryPermissionMenuIdByUsername = "SELECT r.menu_id\r\n" + 
								 		"FROM role_permissions rgd\r\n" + 
								 		"INNER JOIN permissions r ON r.id = rgd.permission_id\r\n" + 
								 		"INNER JOIN menus m ON m.id = r.menu_id\r\n" + 
								 		"INNER JOIN `permissions` g ON g.id = rgd.permission_id\r\n" + 
								 		"INNER JOIN user_roles ugd ON ugd.role_id = g.id\r\n" + 
								 		"INNER JOIN users u ON u.id = ugd.user_id\r\n" + 
								 		"WHERE rgd.is_deleted = 0\r\n" + 
								 		"AND g.is_deleted = 0 \r\n" + 
								 		"AND ugd.is_deleted = 0 \r\n" + 
								 		"AND r.is_active = 1 \r\n" + 
								 		"AND u.username = :username\r\n" + 
								 		"GROUP BY r.permission_name\r\n" + 
								 		"ORDER BY m.order_by";
	
	static String queryFindPermissionByRoleId = "SELECT r.*\r\n" + 
										"FROM role_permissions rgd\r\n" + 
										"INNER JOIN permissions r ON r.id = rgd.permission_id\r\n" + 
										"WHERE rgd.is_deleted = 0 AND r.is_active = 1 AND rgd.role_id = :roleId";
	
	static String queryFindPermissionByMenuId = "SELECT *\r\n" + 
										"FROM permissions\r\n" + 
										"WHERE menu_id = :menuId\r\n" + 
										"AND is_deleted = :isDeleted\r\n" + 
										"LIMIT 1";
	
	static String queryGetPermissions = "SELECT m.menu_name AS menu_id, r.*\r\n" + 
									"FROM permissions r\r\n" + 
									"LEFT JOIN menus m ON m.id = r.menu_id\r\n" + 
									"WHERE r.is_deleted = 0 \r\n" + 
									"ORDER BY r.permission_name\r\n" + 
									"LIMIT :start, :length";
	static String queryCountAllPermissions = "SELECT COUNT(id)\r\n" + 
									"FROM permissions\r\n" + 
									"WHERE is_deleted = 0";
	
	static String queryFindByPermissionName = "SELECT *\r\n" + 
									"FROM permissions\r\n" + 
									"WHERE permission_name = :permissionName";
	
	static String queryGetAllPermissions = "SELECT * "
									+ "FROM permissions "
									+ "WHERE is_active = 1 "
									+ "AND is_deleted = 0 "
									+ "ORDER BY permission_name";
	
	static String queryAllPermissionTypes = "SELECT `type` FROM permissions GROUP BY `type` ORDER BY `type`";
	
	public Optional<PermissionEntity> findById(Long id);
	
	@Query(value = queryFindPermissionByMenuId, nativeQuery = true)
	public PermissionEntity findByMenuId(@Param("menuId") Long menuId, @Param("isDeleted") int isDeleted);
	
	@Query(value = queryFindByPermissionName, nativeQuery = true)
	PermissionEntity findByPermissionName(@Param("permissionName") String permissionName);
	
	@Query(value = queryPermissionByUsername, nativeQuery = true)
	public List<String> findPermissionByUsername(@Param("username") String username);
	
	@Query(value = queryPermissionMenuIdByUsername, nativeQuery = true)
	public List<Integer> findPermissionMenuIdByUsername(@Param("username") String username);
	
	@Query(value = queryAllPermissionTypes, nativeQuery = true)
	public List<String> getAllPermissionTypes();
	
	@Query(value = queryFindPermissionByRoleId, nativeQuery = true)
	public List<PermissionEntity> findPermissionByRoleId(@Param("roleId") Long roleId);
	
	@Query(value = queryCountAllPermissions, nativeQuery = true)
	public int countAllPermissions();
	
	@Query(value = queryGetPermissions, nativeQuery = true)
	public List<PermissionEntity> getPermissions(@Param("start") int start, @Param("length") int length);
	
	@Query(value = queryGetAllPermissions, nativeQuery = true)
	public List<PermissionEntity> getAllPermissions();

}
