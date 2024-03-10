package com.backend.internal.usermanagement.vo.user.response;

import java.util.List;

import com.backend.internal.usermanagement.vo.permission.response.PermissionResponseVO;

import lombok.Data;

/**
 * 
 * @author SETC01
 *
 */
@Data
public class RoleVO {
	private Long id;
	private String roleName;
	private List<PermissionResponseVO> permissions;
}
