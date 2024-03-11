package com.backend.internal.usermanagement.vo.user.response;

import java.sql.Timestamp;
import java.util.List;
import lombok.Data;

@Data
public class UserResponseVO {
	private Long id;
	private String username;
	private String fullname;
	private String staffId;
	private String phoneNumber;
	private String email;
	private int failedAttempt;
	private Boolean locked;
	private Timestamp lastLogin;
	private BranchVO branch;
	private List<RoleVO> roles;
	private Boolean isActive;
	private String createdBy;
	private Timestamp createdAt;
	private String updatedBy;
	private Timestamp updatedAt;
	private Boolean isDeleted;
	private String deletedBy;
	private Timestamp deletedAt;
}
