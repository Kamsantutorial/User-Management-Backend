package com.backend.internal.usermanagement.dto.role;

import java.util.List;

import com.backend.internal.usermanagement.dto.BaseDTO;
import com.backend.internal.usermanagement.dto.permission.PermissionDTO;
import lombok.Data;

@Data
public class RoleDTO extends BaseDTO {
	private Long id;
	private String roleName;
	private List<PermissionDTO> permissions;
	private List<Long> permissionIds;
	private String searchKeyword;
}
