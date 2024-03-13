package com.backend.internal.usermanagement.dto.user;

import java.sql.Timestamp;
import java.util.List;
import com.backend.internal.usermanagement.dto.BaseDTO;
import com.backend.internal.usermanagement.dto.role.RoleDTO;
import lombok.Data;

@Data
public class UserDTO extends BaseDTO {
	private Long id;
	private String username;
	private String fullname;
	private String staffId;
	private String phoneNumber;
	private String email;
	private String password;
	private String roleName;
	private int failedAttempt;
	private Boolean locked;
	private Timestamp lastLogin;
	private List<RoleDTO> roles;
	private Long branchId;
	private String searchKeyword;
	private List<Long> roleIds;
}
