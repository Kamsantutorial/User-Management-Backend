package com.backend.internal.usermanagement.dto.role;

import java.util.List;

import com.backend.internal.usermanagement.dto.BaseDTO;
import com.backend.internal.usermanagement.dto.permission.PermissionDTO;

import lombok.Data;

/**
 * 
 * @author SETC01
 *
 */
@Data
public class RoleDTO extends BaseDTO {
	private Long id;
	private String roleName;
	private List<PermissionDTO> permissions;
	private String searchKeyword;
}
