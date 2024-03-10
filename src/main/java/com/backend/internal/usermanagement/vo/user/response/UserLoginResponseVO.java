package com.backend.internal.usermanagement.vo.user.response;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import lombok.Data;

@Data
public class UserLoginResponseVO implements Serializable {
	private Long id;
	private String username;
	private String fullname;
	private String staffId;
	private String phoneNumber;
	private String email;
	private int failedAttempt;
	private Boolean locked;
	private Timestamp lastLogin;
	private BranchLoginResponseVO branch;
	private List<RoleLoginResponseVO> roles;
}
