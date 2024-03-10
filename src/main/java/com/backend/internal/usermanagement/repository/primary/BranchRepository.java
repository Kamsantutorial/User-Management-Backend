package com.backend.internal.usermanagement.repository.primary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.internal.usermanagement.entity.primary.BranchEntity;
import com.backend.internal.usermanagement.repository.base.BaseRepository;

/**
 * 
 * @author SETC01
 *
 */
@Repository
public interface BranchRepository extends BaseRepository<BranchEntity, Long> {

	static String queryDatatableAllBranches = "SELECT * \r\n" +
			"FROM branches \r\n" +
			"WHERE is_deleted = 0\r\n" +
			"AND branch_name LIKE %:search%\r\n" +
			"ORDER BY branch_name\r\n" +
			"LIMIT :start, :length";

	static String queryCountAllBranches = "SELECT COUNT(id)\r\n" +
			"FROM branches \r\n" +
			"WHERE is_deleted = 0 \r\n" +
			"AND branch_name LIKE %:search%";
	static String queryBranchByBranchName = "SELECT * \r\n" +
			"FROM branches \r\n" +
			"WHERE TRIM(LOWER(branch_name)) = :branchName\r\n" +
			"LIMIT 1";
	static String queryAllBranches = "SELECT *\r\n" +
			"FROM branches\r\n" +
			"WHERE is_deleted = :isDeleted\r\n" +
			"ORDER BY branch_name";

	Optional<BranchEntity> findById(Long id);

	@Query(value = queryBranchByBranchName, nativeQuery = true)
	BranchEntity findBranchByBranchName(@Param("branchName") String branchName);

	@Query(value = queryDatatableAllBranches, nativeQuery = true)
	List<BranchEntity> findDatatableAllBranches(@Param("start") int start, @Param("length") int length,
			@Param("search") String search);

	@Query(value = queryAllBranches, nativeQuery = true)
	List<BranchEntity> findAllBranches(@Param("isDeleted") int isDeleted);

	@Query(value = queryCountAllBranches, nativeQuery = true)
	int countAllBranches(@Param("search") String search);
}