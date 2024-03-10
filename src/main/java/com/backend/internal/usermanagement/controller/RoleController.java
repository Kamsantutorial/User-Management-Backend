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
import com.backend.internal.usermanagement.dto.role.RoleDTO;
import com.backend.internal.usermanagement.entity.primary.RoleEntity;
import com.backend.internal.usermanagement.exception.ServerException;
import com.backend.internal.usermanagement.mapper.RoleMapper;
import com.backend.internal.usermanagement.repository.primary.RolePermissionRepository;
import com.backend.internal.usermanagement.service.primary.AccessTokenService;
import com.backend.internal.usermanagement.service.primary.RolePermissionService;
import com.backend.internal.usermanagement.service.primary.RoleService;
import com.backend.internal.usermanagement.service.primary.UserRoleService;
import com.backend.internal.usermanagement.vo.BaseResponse;
import com.backend.internal.usermanagement.vo.ResponsePageableVO;
import com.backend.internal.usermanagement.vo.role.request.RoleRequestPageVO;
import com.backend.internal.usermanagement.vo.role.response.RoleResponseVO;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Role Management", description = "Expose Role Rest API")
@RestController
@RequestMapping("/setting/role")
@Transactional
public class RoleController {
	private static final Logger logger = LogManager.getLogger(String.class);

	@Autowired
	private RoleService roleService;
	@Autowired
	private RolePermissionService rolePermissionService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private RolePermissionRepository rolePermissionRepository;
	@Autowired
	private AccessTokenService accessTokenService;

	@GetMapping("/find-with-page")
	public BaseResponse<ResponsePageableVO<RoleResponseVO>> findWithPage(
			RoleRequestPageVO roleRequestPageVO)
			throws ServerException {

		RoleDTO roleDTO = new RoleDTO();
		RoleMapper.INSTANCE.copyRequestPageVoDto(roleRequestPageVO, roleDTO);
		RequestPageableDTO requestPageableDTO = new RequestPageableDTO();
		RoleMapper.INSTANCE.copyRequestPageVoToRequestPageDto(roleRequestPageVO, requestPageableDTO);
		Page<RoleDTO> page = roleService.findWithPage(roleDTO, requestPageableDTO);

		List<RoleResponseVO> list = new ArrayList<>();
		RoleMapper.INSTANCE.copyListDtoToListResponseItemVo(page.getContent(), list);
		ResponsePageableVO<RoleResponseVO> response = new ResponsePageableVO<RoleResponseVO>(list,
				page.getTotalElements(), page.getTotalPages(),
				roleRequestPageVO);

		return new BaseResponse<ResponsePageableVO<RoleResponseVO>>()
				.body(response)
				.success();

	}

	@GetMapping("/all-roles")
	public BaseResponse<List<RoleResponseVO>> getAllRoles() {
		List<RoleDTO> roleDTOs = roleService.findAll();
		List<RoleResponseVO> response = new ArrayList<>();
		RoleMapper.INSTANCE.copyListDtoToResponseVo(roleDTOs, response);
		return new BaseResponse<List<RoleResponseVO>>()
				.body(response)
				.success();
	}

}
