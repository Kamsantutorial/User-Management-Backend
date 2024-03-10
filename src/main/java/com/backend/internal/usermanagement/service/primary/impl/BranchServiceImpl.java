package com.backend.internal.usermanagement.service.primary.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import com.backend.internal.usermanagement.repository.primary.BranchRepository;
import com.backend.internal.usermanagement.dto.base.RequestPageableDTO;
import com.backend.internal.usermanagement.dto.branch.BranchDTO;
import com.backend.internal.usermanagement.entity.primary.BranchEntity;
import com.backend.internal.usermanagement.mapper.BranchMapper;
import com.backend.internal.usermanagement.repository.base.BaseCriteria;
import com.backend.internal.usermanagement.service.primary.BranchService;

/**
 * 
 * @author SETC01
 *
 */
@Service
public class BranchServiceImpl implements BranchService {

	@Autowired BranchRepository branchRepository;
	
	@Override
	public boolean save(BranchEntity branch) {
		try {
			branchRepository.save(branch);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public BranchEntity findBranchById(Long id) {
		return branchRepository.findById(id).orElse(null);
	}

	@Override
	public BranchEntity findExistBranchByBranchName(String branchName) {
		return branchRepository.findBranchByBranchName(branchName.trim().toLowerCase());
	}

	@Override
	public List<BranchEntity> findDatatableAllBranches(int start, int length,String search) {
		return branchRepository.findDatatableAllBranches(start, length,search);
	}

	@Override
	public List<BranchEntity> findAllBranches(int isDeleted) {
		return branchRepository.findAllBranches(isDeleted);
	}

	@Override
	public int countAllBranch(String search) {
		return branchRepository.countAllBranches(search);
	}
	
	@Override
	public Page<BranchDTO> findWithPage(BranchDTO dto, RequestPageableDTO pageableDTO) {
		BaseCriteria<BranchRepository> criteria = new BaseCriteria<>(branchRepository);
		criteria.or(criteria.or(criteria.like("branchName", dto.getSearchKeyword()),
				criteria.equal("id", dto.getSearchKeyword())));
        criteria.size(pageableDTO.getSize());
        criteria.setPage(pageableDTO.getPageOffset());

        Page<BranchEntity> listEntity = branchRepository.findAllWithPage(criteria);
        List<BranchDTO> listDto = new ArrayList<>();
        BranchMapper.INSTANCE.copyListEntityToListDto(listEntity.getContent(), listDto);
        return new PageImpl<>(listDto, listEntity.getPageable(), listEntity.getTotalElements());
	}
}
