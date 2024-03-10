package com.backend.internal.usermanagement.controller;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.internal.usermanagement.dto.base.RequestPageableDTO;
import com.backend.internal.usermanagement.dto.branch.BranchDTO;
import com.backend.internal.usermanagement.exception.ServerException;
import com.backend.internal.usermanagement.mapper.BranchMapper;
import com.backend.internal.usermanagement.repository.primary.UserRepository;
import com.backend.internal.usermanagement.service.primary.BranchService;
import com.backend.internal.usermanagement.vo.BaseResponse;
import com.backend.internal.usermanagement.vo.ResponsePageableVO;
import com.backend.internal.usermanagement.vo.branch.request.BranchRequestPageVO;
import com.backend.internal.usermanagement.vo.branch.response.BranchResponseVO;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Branch Management", description = "Expose Branch Rest API")
@RestController
@RequestMapping("/setting/branch")
@Transactional
public class BranchController {
	private static final Logger logger = LogManager.getLogger(BranchController.class);

	@Autowired
	private BranchService branchService;
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/find-with-page")
	public BaseResponse<ResponsePageableVO<BranchResponseVO>> list(BranchRequestPageVO branchRequestPageVO)
			throws ServerException {
		BranchDTO branchDTO = new BranchDTO();
		BranchMapper.INSTANCE.copyRequestPageVoDto(branchRequestPageVO, branchDTO);
		RequestPageableDTO requestPageableDTO = new RequestPageableDTO();
		BranchMapper.INSTANCE.copyRequestPageVoToRequestPageDto(branchRequestPageVO, requestPageableDTO);
		Page<BranchDTO> page = branchService.findWithPage(branchDTO, requestPageableDTO);

		List<BranchResponseVO> list = new ArrayList<>();
		BranchMapper.INSTANCE.copyListDtoToListResponseItemVo(page.getContent(), list);

		ResponsePageableVO<BranchResponseVO> response = new ResponsePageableVO<BranchResponseVO>(list,
				page.getTotalElements(), page.getTotalPages(),
				branchRequestPageVO);

		return new BaseResponse<ResponsePageableVO<BranchResponseVO>>()
				.body(response)
				.success();
	}

}
