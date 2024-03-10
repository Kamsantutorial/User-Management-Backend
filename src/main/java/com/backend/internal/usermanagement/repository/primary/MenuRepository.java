package com.backend.internal.usermanagement.repository.primary;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.internal.usermanagement.entity.primary.MenuEntity;
import com.backend.internal.usermanagement.repository.base.BaseRepository;

@Repository
public interface MenuRepository extends BaseRepository<MenuEntity, Long> {

	public static String queryAllMenus = "SELECT m.id, m.menu_name, p.menu_name AS parent_id, m.order_by, m.url, m.icon, m.is_active, m.created_at, m.created_by, m.updated_at, m.updated_by, m.is_deleted, m.deleted_at, m.deleted_by\r\n"
			+
			"FROM menus m\r\n" +
			"LEFT JOIN menus p ON p.id = m.parent_id\r\n" +
			"WHERE m.is_deleted = 0 \r\n" +
			"ORDER BY m.order_by, m.menu_name\r\n" +
			"LIMIT :start, :length";

	public static String queryCountAllMenus = "SELECT COUNT(id) FROM menus WHERE is_deleted = 0;";

	public static String queryAllParentMenu = "SELECT *\r\n" +
			"FROM menus\r\n" +
			"WHERE is_deleted = 0 AND parent_id IS NULL\r\n" +
			"ORDER BY menu_name";

	public static String queryAllMenuByParentId = "SELECT *\r\n" +
			"FROM menus\r\n" +
			"WHERE parent_id = :parentId\r\n" +
			"AND is_deleted = :isDeleted";

	public static String queryExistByMenuName = "SELECT * \r\n" +
			"FROM menus\r\n" +
			"WHERE TRIM(LOWER(menu_name)) = :menuName AND is_deleted = :isDeleted LIMIT 1";

	public static String queryExistByMenuUrl = "SELECT COUNT(id)\r\n" +
			"FROM menus\r\n" +
			"WHERE TRIM(LOWER(url)) = :menuUrl AND is_deleted = 0";

	public static String queryExistDeletdMenuName = "SELECT *\r\n" +
			"FROM menus\r\n" +
			"WHERE TRIM(LOWER(menu_name)) = :menuName AND is_deleted = 1 LIMIT 1";

	public static String queryAllAtiveMenus = "SELECT * \r\n" +
			"FROM menus \r\n" +
			"WHERE is_deleted = 0\r\n" +
			"ORDER BY menu_name";

	Optional<MenuEntity> findById(Long id);

	@Query(value = queryAllMenus, nativeQuery = true)
	List<MenuEntity> getAllMenus(@Param("length") int length, @Param("start") int start);

	@Query(value = queryCountAllMenus, nativeQuery = true)
	int countAllMenus();

	@Query(value = queryAllParentMenu, nativeQuery = true)
	List<MenuEntity> getAllParentMenu();

	@Query(value = queryExistByMenuName, nativeQuery = true)
	MenuEntity existsByMenuName(@Param("menuName") String menuName, @Param("isDeleted") int isDeleted);

	@Query(value = queryExistByMenuUrl, nativeQuery = true)
	int existsByMenuUrl(@Param("menuUrl") String url);

	@Query(value = queryExistDeletdMenuName, nativeQuery = true)
	MenuEntity existsDeletedByMenuName(@Param("menuName") String menuName);

	@Query(value = queryAllAtiveMenus, nativeQuery = true)
	List<MenuEntity> getAllMenus();

	@Query(value = queryAllMenuByParentId, nativeQuery = true)
	List<MenuEntity> getAllMenuByParentId(@Param("parentId") Long parentId, @Param("isDeleted") int isDeleted);
}
