package com.backend.internal.usermanagement.repository.primary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.internal.usermanagement.entity.primary.UserEntity;
import com.backend.internal.usermanagement.repository.base.BaseRepository;

/**
 * 
 * @author SETC01
 *
 */
@Repository
public interface UserRepository extends BaseRepository<UserEntity, Long> {

	static String stringSearch = "AND u.username LIKE %:search% OR u.fullname LIKE %:search% OR u.staff_id LIKE %:search% OR u.email LIKE %:search%\r\n";

	static String queryAllUsers = "SELECT u.*, GROUP_CONCAT(g.role_name) AS 'roles'\r\n" +
			"FROM users u\r\n" +
			"LEFT JOIN user_roles ugd ON ugd.user_id = u.id\r\n" +
			"LEFT JOIN `roles` g ON g.id = ugd.role_id\r\n" +
			"WHERE u.is_deleted = 0\r\n" +
			stringSearch +
			"GROUP BY u.username\r\n" +
			"ORDER BY u.fullname \r\n" +
			"LIMIT :start, :length";

	static String queryUsers = "SELECT * \r\n" +
			"FROM users \r\n" +
			"WHERE is_deleted = :isDeleted \r\n" +
			"ORDER BY fullname ASC";
	static String queryUsersByBranch = "SELECT * FROM users WHERE branch_id = :branchId";

	static String queryCountAllUsers = "SELECT COUNT(id) FROM users WHERE is_deleted = 0 and username LIKE %:search%";

	static String queryUser = "SELECT u.*, GROUP_CONCAT(g.role_name) AS 'roles'\r\n" +
			"FROM users u\r\n" +
			"LEFT JOIN user_roles ugd ON ugd.user_id = u.id\r\n" +
			"LEFT JOIN `roles` g ON g.id = ugd.role_id\r\n" +
			"WHERE u.is_deleted = 0\r\n" +
			stringSearch +
			"GROUP BY u.username\r\n" +
			"ORDER BY u.fullname";

	UserEntity findByUsername(String username);

	Optional<UserEntity> findById(Long id);

	@Query(value = queryUsers, nativeQuery = true)
	List<UserEntity> findUsers(@Param("isDeleted") int isDeleted);

	@Query(value = queryUsersByBranch, nativeQuery = true)
	List<UserEntity> findUsersByBranch(@Param("branchId") Long branchId);

	@Query(value = queryAllUsers, nativeQuery = true)
	List<UserEntity> findAllUsers(@Param("start") int start, @Param("length") int length,
			@Param("search") String search);

	@Query(value = queryCountAllUsers, nativeQuery = true)
	int countAllUsers(@Param("search") String search);

	@Query(value = queryUser, nativeQuery = true)
	List<UserEntity> findAllUser(@Param("search") String search);
}
