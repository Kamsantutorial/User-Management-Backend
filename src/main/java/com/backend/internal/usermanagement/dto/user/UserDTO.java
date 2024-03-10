package com.backend.internal.usermanagement.dto.user;

import java.sql.Timestamp;
import java.util.List;
import com.backend.internal.usermanagement.dto.BaseDTO;
import com.backend.internal.usermanagement.dto.branch.BranchDTO;
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
	private boolean locked;
	private Timestamp lastLogin;
	private BranchDTO branch;
	private List<RoleDTO> roles;
	private Long branchId;
	private String searchKeyword;
	private List<Long> roleIds;

	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDTO(Long id, String username, String fullname, String staffId, String phoneNumber, String email,
			String roleName,
			int failedAttempt, boolean locked, boolean isActive, Timestamp createdAt) {
		super();
		this.id = id;
		this.username = username;
		this.fullname = fullname;
		this.staffId = staffId;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.roleName = roleName;
		this.failedAttempt = failedAttempt;
		this.locked = locked;
		this.setIsActive(isActive);
		this.setCreatedAt(createdAt);
	}
}
