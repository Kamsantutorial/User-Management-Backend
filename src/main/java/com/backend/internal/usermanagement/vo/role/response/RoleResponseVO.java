package com.backend.internal.usermanagement.vo.role.response;

import java.sql.Timestamp;
import java.util.List;

import com.backend.internal.usermanagement.vo.permission.response.PermissionVO;

import lombok.Data;

@Data
public class RoleResponseVO {
	private Long id;
	private String roleName;
	private List<PermissionVO> permissions;
	private Boolean isActive;
	private String createdBy;
	private Timestamp createdAt;
	private String updatedBy;
	private Timestamp updatedAt;
	private Boolean isDeleted;
	private String deletedBy;
	private Timestamp deletedAt;
}
