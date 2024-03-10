package com.backend.internal.usermanagement.vo.permission.response;

import java.sql.Timestamp;

import com.backend.internal.usermanagement.vo.menu.response.MenuVO;

import lombok.Data;

@Data
public class PermissionResponseVO {
	private Long id;
	private String permissionName;
	private String type;
	private MenuVO menu;
	private Boolean isActive;
	private String createdBy;
	private Timestamp createdAt;
	private String updatedBy;
	private Timestamp updatedAt;
	private Boolean isDeleted;
	private String deletedBy;
	private Timestamp deletedAt;
}
