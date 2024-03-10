package com.backend.internal.usermanagement.vo.permission.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class PermissionLoginResponseVO implements Serializable {
	private Long id;
	private String permissionName;
	private String type;
}
