package com.backend.internal.usermanagement.dto.menu;

import java.util.List;
import com.backend.internal.usermanagement.dto.BaseDTO;
import com.backend.internal.usermanagement.vo.menu.response.ParentMenuVO;
import com.backend.internal.usermanagement.vo.permission.response.PermissionVO;
import lombok.Data;


@Data
public class MenuDTO extends BaseDTO {
	private Long id;
	private String menuName;
	private Float orderBy;
	private String url;
	private String icon;
	private Long parentId;
	private ParentMenuVO parent;
	private String redirect;
	private String component;
	private List<PermissionVO> permissions;
	private String searchKeyword;
	private List<MenuDTO> children;
}
