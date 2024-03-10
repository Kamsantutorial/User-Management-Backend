package com.backend.internal.usermanagement.repository.primary;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.internal.usermanagement.entity.primary.UserRoleEntity;
import com.backend.internal.usermanagement.repository.base.BaseRepository;

/**
 * 
 * @author SETC01
 *
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Integer>, BaseRepository<UserRoleEntity, Integer> {

	static String queryByUserId = "SELECT *\r\n" +
			"FROM user_roles\r\n" +
			"WHERE user_id = :userId\r\n" +
			"AND is_deleted = :isDeleted";
	static String queryByRoleId = "SELECT *\r\n" +
			"FROM user_roles\r\n" +
			"WHERE role_id = :roleId\r\n" +
			"AND is_deleted = :isDeleted";
	static String queryByUserIdAndRoleId = "SELECT *\r\n" +
			"FROM user_roles\r\n" +
			"WHERE user_id = :userId AND role_id = :roleId";

	@Query(value = queryByUserId, nativeQuery = true)
	List<UserRoleEntity> findByUserIdAndIsDeleted(@Param("userId") Long userId, @Param("isDeleted") int isDeleted);

	@Query(value = queryByRoleId, nativeQuery = true)
	List<UserRoleEntity> findByRoleId(@Param("roleId") Long roleId, @Param("isDeleted") int isDeleted);

	@Query(value = queryByUserIdAndRoleId, nativeQuery = true)
	UserRoleEntity findByUserIdAndRoleId(@Param("userId") Long userId, @Param("roleId") Long roleId);
}
