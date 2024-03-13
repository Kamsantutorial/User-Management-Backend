package com.backend.internal.usermanagement.vo.role.request;

import java.util.List;
import lombok.Data;

@Data
public class RoleUpdateRequestVO {
	private String roleName;
	private List<Long> permissionIds;
}
