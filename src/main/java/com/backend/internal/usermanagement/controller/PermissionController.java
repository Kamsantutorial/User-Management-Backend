package com.backend.internal.usermanagement.controller;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.internal.usermanagement.dto.base.RequestPageableDTO;
import com.backend.internal.usermanagement.dto.permission.PermissionDTO;
import com.backend.internal.usermanagement.exception.ServerException;
import com.backend.internal.usermanagement.mapper.PermissionMapper;
import com.backend.internal.usermanagement.service.primary.MenuService;
import com.backend.internal.usermanagement.service.primary.PermissionService;
import com.backend.internal.usermanagement.vo.BaseResponse;
import com.backend.internal.usermanagement.vo.ResponsePageableVO;
import com.backend.internal.usermanagement.vo.permission.request.PermissionCreateRequestVO;
import com.backend.internal.usermanagement.vo.permission.request.PermissionRequestPageVO;
import com.backend.internal.usermanagement.vo.permission.response.PermissionResponseVO;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Permission Management", description = "Expose Permission Rest API")
@RestController
@Transactional
@RequestMapping("/setting/permission")
public class PermissionController {
	private static final Logger logger = LogManager.getLogger(String.class);

	@Autowired
	private PermissionService permissionService;
	@Autowired
	private MenuService menuService;

	@GetMapping("/find-with-page")
	public BaseResponse<ResponsePageableVO<PermissionResponseVO>> list(PermissionRequestPageVO permissionRequestPageVO)
			throws ServerException {
		PermissionDTO permissionDTO = new PermissionDTO();
		PermissionMapper.INSTANCE.copyRequestPageVoDto(permissionRequestPageVO, permissionDTO);
		RequestPageableDTO requestPageableDTO = new RequestPageableDTO();
		PermissionMapper.INSTANCE.copyRequestPageVoToRequestPageDto(permissionRequestPageVO, requestPageableDTO);
		Page<PermissionDTO> page = permissionService.findWithPage(permissionDTO, requestPageableDTO);

		List<PermissionResponseVO> responses = new ArrayList<>();
		PermissionMapper.INSTANCE.copyListDtoToListResponseItemVo(page.getContent(), responses);

		ResponsePageableVO<PermissionResponseVO> response = new ResponsePageableVO<PermissionResponseVO>(responses,
				page.getTotalElements(), page.getTotalPages(),
				permissionRequestPageVO);

		return new BaseResponse<ResponsePageableVO<PermissionResponseVO>>()
				.body(response)
				.success();
	}

	@PostMapping("/create")
	public BaseResponse<PermissionResponseVO> create(@RequestBody PermissionCreateRequestVO createRequestVO)
			throws ServerException {
		PermissionDTO permissionDTO = new PermissionDTO();
		PermissionMapper.INSTANCE.copyCreateRequestVoToDto(createRequestVO, permissionDTO);
		permissionService.create(permissionDTO);
		PermissionResponseVO response = new PermissionResponseVO();
		return new BaseResponse<PermissionResponseVO>()
				.body(response)
				.success();
	}
}
