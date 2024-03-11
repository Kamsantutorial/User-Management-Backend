package com.backend.internal.usermanagement.vo.user.response;

import java.io.Serializable;
import java.util.List;
import com.backend.internal.usermanagement.vo.permission.response.PermissionLoginResponseVO;
import lombok.Data;

@Data
public class RoleLoginResponseVO implements Serializable {
	private Long id;
	private String roleName;
	private List<PermissionLoginResponseVO> permissions;
}
