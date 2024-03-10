package com.backend.internal.usermanagement.vo.role.request;

import java.util.List;

import lombok.Data;

@Data
public class RoleRequestVO {
	private String roleName;
	private List<Long> permissions;
}
