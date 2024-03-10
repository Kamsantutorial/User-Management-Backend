package com.backend.internal.usermanagement.vo.permission.request;

import lombok.Data;

@Data
public class PermissionCreateRequestVO {
	private String permissionName;
	private String type;
	private Long menuId;
	private Boolean isActive;
}
