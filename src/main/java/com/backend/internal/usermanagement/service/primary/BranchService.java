package com.backend.internal.usermanagement.service.primary;

import java.util.List;
import org.springframework.data.domain.Page;
import com.backend.internal.usermanagement.dto.base.RequestPageableDTO;
import com.backend.internal.usermanagement.dto.branch.BranchDTO;
import com.backend.internal.usermanagement.entity.primary.BranchEntity;


public interface BranchService {

	boolean save(BranchEntity branch);
	BranchEntity findBranchById(Long id);
	BranchEntity findExistBranchByBranchName(String branchName);
	List<BranchEntity> findDatatableAllBranches(int start, int length,String search);
	List<BranchEntity> findAllBranches(int isDeleted);
	int countAllBranch(String search);
	Page<BranchDTO> findWithPage(BranchDTO dto, RequestPageableDTO pageableDTO);
}
