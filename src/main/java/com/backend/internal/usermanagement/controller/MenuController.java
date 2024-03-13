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
import com.backend.internal.usermanagement.dto.menu.MenuDTO;
import com.backend.internal.usermanagement.dto.user.UserDTO;
import com.backend.internal.usermanagement.entity.primary.MenuEntity;
import com.backend.internal.usermanagement.exception.ServerException;
import com.backend.internal.usermanagement.mapper.MenuMapper;
import com.backend.internal.usermanagement.mapper.UserMapper;
import com.backend.internal.usermanagement.repository.primary.UserRepository;
import com.backend.internal.usermanagement.service.primary.MenuService;
import com.backend.internal.usermanagement.service.primary.PermissionService;
import com.backend.internal.usermanagement.service.primary.RolePermissionService;
import com.backend.internal.usermanagement.vo.BaseResponse;
import com.backend.internal.usermanagement.vo.ResponsePageableVO;
import com.backend.internal.usermanagement.vo.menu.request.MenuCreateRequestVO;
import com.backend.internal.usermanagement.vo.menu.request.MenuRequestPageVO;
import com.backend.internal.usermanagement.vo.menu.request.MenuUpdateRequestVO;
import com.backend.internal.usermanagement.vo.menu.response.MenuResponseVO;
import com.backend.internal.usermanagement.vo.permission.response.MenuTypeResponseVO;
import com.backend.internal.usermanagement.vo.user.request.UserCreateRequestVO;
import com.backend.internal.usermanagement.vo.user.request.UserUpdateRequestVO;
import com.backend.internal.usermanagement.vo.user.response.UserResponseVO;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Menu Management", description = "Expose Menu Rest API")
@RequestMapping(value = "/setting/menu")
@RestController
@Transactional
public class MenuController {
	private static final Logger logger = LogManager.getLogger(String.class);

	@Autowired
	private MenuService menuService;
	@Autowired
	private PermissionService permissionService;
	@Autowired
	private RolePermissionService rolePermissionService;
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/find-with-page")
	public BaseResponse<ResponsePageableVO<MenuResponseVO>> list(MenuRequestPageVO menuRequestPageVO)
			throws ServerException {
		MenuDTO menuDTO = new MenuDTO();
		MenuMapper.INSTANCE.copyRequestPageVoDto(menuRequestPageVO, menuDTO);

		RequestPageableDTO requestPageableDTO = new RequestPageableDTO();
		MenuMapper.INSTANCE.copyRequestPageVoToRequestPageDto(menuRequestPageVO, requestPageableDTO);

		Page<MenuDTO> page = menuService.findWithPage(menuDTO, requestPageableDTO);

		List<MenuResponseVO> responses = new ArrayList<>();
		MenuMapper.INSTANCE.copyListDtoToListResponseItemVo(page.getContent(), responses);

		ResponsePageableVO<MenuResponseVO> response = new ResponsePageableVO<MenuResponseVO>(responses,
				page.getTotalElements(), page.getTotalPages(),
				menuRequestPageVO);

		return new BaseResponse<ResponsePageableVO<MenuResponseVO>>()
				.body(response)
				.success();
	}

	@GetMapping("/menu-types")
	public BaseResponse<List<MenuTypeResponseVO>> menuTypes() {
		List<MenuDTO> menus = menuService.findAllTypeMenu();
		List<MenuTypeResponseVO> responseVO = new ArrayList<>();
		MenuMapper.INSTANCE.copyListEntityToResponseTypeVo(menus, responseVO);
		return new BaseResponse<List<MenuTypeResponseVO>>()
				.body(responseVO)
				.success();
	}

	@GetMapping("/all-menus")
	public BaseResponse<List<MenuResponseVO>> allMenus() {
		List<MenuDTO> menus = menuService.getAllMenus();
		List<MenuResponseVO> responseVO = new ArrayList<>();
		MenuMapper.INSTANCE.copyListDtoToResponseVo(menus, responseVO);
		return new BaseResponse<List<MenuResponseVO>>()
				.body(responseVO)
				.success();
	}

	@GetMapping("/parents")
	public BaseResponse<List<MenuResponseVO>> parents() {
		List<MenuDTO> menus = menuService.getParentMenus();
		List<MenuResponseVO> responseVO = new ArrayList<>();
		MenuMapper.INSTANCE.copyListDtoToResponseVo(menus, responseVO);
		return new BaseResponse<List<MenuResponseVO>>()
				.body(responseVO)
				.success();
	}



	@PostMapping("/create")
	public BaseResponse<MenuResponseVO> create(@RequestBody MenuCreateRequestVO createRequestVO)
			throws ServerException {
		MenuDTO dto = new MenuDTO();
		MenuMapper.INSTANCE.copyCreateRequestVoToDto(createRequestVO, dto);
		menuService.create(dto);
		MenuResponseVO response = new MenuResponseVO();
		return new BaseResponse<MenuResponseVO>()
				.body(response)
				.success();
	}

	@PostMapping("/update/{id}")
	public BaseResponse<MenuResponseVO> update(@PathVariable Long id, @RequestBody MenuUpdateRequestVO requestVO)
			throws ServerException {
		MenuDTO dto = new MenuDTO();
		MenuMapper.INSTANCE.copyUpdateRequestVoToDto(requestVO, dto);
		menuService.update(dto, id);
		MenuResponseVO response = new MenuResponseVO();
		return new BaseResponse<MenuResponseVO>()
				.body(response)
				.success();
	}

}
