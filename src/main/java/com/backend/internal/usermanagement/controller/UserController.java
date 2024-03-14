package com.backend.internal.usermanagement.controller;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backend.internal.usermanagement.dto.base.RequestPageableDTO;
import com.backend.internal.usermanagement.dto.user.UserDTO;
import com.backend.internal.usermanagement.exception.ServerException;
import com.backend.internal.usermanagement.mapper.UserMapper;
import com.backend.internal.usermanagement.service.primary.AccessTokenService;
import com.backend.internal.usermanagement.service.primary.RoleService;
import com.backend.internal.usermanagement.service.primary.UserRoleService;
import com.backend.internal.usermanagement.service.primary.UserService;
import com.backend.internal.usermanagement.vo.BaseResponse;
import com.backend.internal.usermanagement.vo.ResponsePageableVO;
import com.backend.internal.usermanagement.vo.dashboard.ResponseDashboardVO;
import com.backend.internal.usermanagement.vo.user.request.UserCreateRequestVO;
import com.backend.internal.usermanagement.vo.user.request.UserRequestPageVO;
import com.backend.internal.usermanagement.vo.user.request.UserUpdateRequestVO;
import com.backend.internal.usermanagement.vo.user.response.UserResponseVO;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User Management", description = "Expose User Rest API")
@RestController
@RequestMapping("/setting/user")
@Transactional
public class UserController {
	private static final Logger logger = LogManager.getLogger(String.class);

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserRoleService userRoleService;

	private AccessTokenService accessTokenService;

	@GetMapping("/find-with-page")
	public BaseResponse<ResponsePageableVO<UserResponseVO>> list(UserRequestPageVO userRequestPageVO)
			throws ServerException {
		UserDTO userDTO = new UserDTO();
		UserMapper.INSTANCE.copyRequestPageVoDto(userRequestPageVO, userDTO);
		RequestPageableDTO requestPageableDTO = new RequestPageableDTO();
		UserMapper.INSTANCE.copyRequestPageVoToRequestPageDto(userRequestPageVO, requestPageableDTO);
		Page<UserDTO> page = userService.findWithPage(userDTO, requestPageableDTO);

		List<UserResponseVO> responses = new ArrayList<>();
		UserMapper.INSTANCE.copyListDtoToListResponseItemVo(page.getContent(), responses);

		ResponsePageableVO<UserResponseVO> response = new ResponsePageableVO<UserResponseVO>(responses,
				page.getTotalElements(), page.getTotalPages(),
				userRequestPageVO);

		return new BaseResponse<ResponsePageableVO<UserResponseVO>>()
				.body(response)
				.success();
	}

	@PostMapping("/create")
	public BaseResponse<UserResponseVO> create(@RequestBody UserCreateRequestVO createRequestVO)
			throws ServerException {
		UserDTO userDTO = new UserDTO();
		UserMapper.INSTANCE.copyCreateRequestVoToDto(createRequestVO, userDTO);
		userService.create(userDTO);
		UserResponseVO response = new UserResponseVO();
		return new BaseResponse<UserResponseVO>()
				.body(response)
				.success();
	}

	@PostMapping("/update/{id}")
	public BaseResponse<UserResponseVO> update(@PathVariable Long id, @RequestBody UserUpdateRequestVO requestVO)
			throws ServerException {
		UserDTO userDTO = new UserDTO();
		UserMapper.INSTANCE.copyUpdateRequestVoToDto(requestVO, userDTO);
		userService.update(userDTO, id);
		UserResponseVO response = new UserResponseVO();
		return new BaseResponse<UserResponseVO>()
				.body(response)
				.success();
	}

	@PostMapping("/delete/{id}")
	public BaseResponse<Void> delete(@PathVariable Long id)
			throws ServerException {
		userService.delete(id);
		return new BaseResponse<Void>()
				.body(null)
				.success();
	}

	@GetMapping("/count")
	public BaseResponse<ResponseDashboardVO> count()
			throws ServerException {
		Long total = userService.countUser();
		return new BaseResponse<ResponseDashboardVO>()
				.body(new ResponseDashboardVO(total))
				.success();
	}

}
