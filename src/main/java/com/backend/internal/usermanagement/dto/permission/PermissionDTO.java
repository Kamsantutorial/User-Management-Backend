package com.backend.internal.usermanagement.dto.permission;

import com.backend.internal.usermanagement.dto.BaseDTO;
import com.backend.internal.usermanagement.dto.menu.MenuDTO;
import lombok.Data;


@Data
public class PermissionDTO extends BaseDTO {
	private Long id;
	private String permissionName;
	private String type;
	private Long menuId;
	private MenuDTO menu;
	private String searchKeyword;
}
