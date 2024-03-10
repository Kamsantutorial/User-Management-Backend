package com.backend.internal.usermanagement.vo.permission.request;

import com.backend.internal.usermanagement.vo.RequestPageableVO;

import lombok.Data;

@Data
public class PermissionRequestPageVO extends RequestPageableVO {
	 private String searchKeyword;
}
