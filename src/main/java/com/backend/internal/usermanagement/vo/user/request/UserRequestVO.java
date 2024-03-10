package com.backend.internal.usermanagement.vo.user.request;

import java.util.List;

import lombok.Data;

@Data
public class UserRequestVO {
	private String username;
	private String phoneNumber;
	private Boolean locked = false;
	private List<Long> roles;
	private Long branchId;
	private Boolean isActive;

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
}
