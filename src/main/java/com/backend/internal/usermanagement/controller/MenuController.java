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
import com.backend.internal.usermanagement.dto.menu.MenuDTO;
import com.backend.internal.usermanagement.entity.primary.MenuEntity;
import com.backend.internal.usermanagement.exception.ServerException;
import com.backend.internal.usermanagement.mapper.MenuMapper;
import com.backend.internal.usermanagement.repository.primary.UserRepository;
import com.backend.internal.usermanagement.service.primary.MenuService;
import com.backend.internal.usermanagement.service.primary.PermissionService;
import com.backend.internal.usermanagement.service.primary.RolePermissionService;
import com.backend.internal.usermanagement.vo.BaseResponse;
import com.backend.internal.usermanagement.vo.ResponsePageableVO;
import com.backend.internal.usermanagement.vo.menu.request.MenuRequestPageVO;
import com.backend.internal.usermanagement.vo.menu.response.MenuResponseVO;
import com.backend.internal.usermanagement.vo.permission.response.MenuTypeResponseVO;

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
		List<MenuEntity> menus = menuService.getAllMenus();
		List<MenuResponseVO> responseVO = new ArrayList<>();
		MenuMapper.INSTANCE.copyListEntityToResponseVo(menus, responseVO);
		return new BaseResponse<List<MenuResponseVO>>()
				.body(responseVO)
				.success();
	}

}
