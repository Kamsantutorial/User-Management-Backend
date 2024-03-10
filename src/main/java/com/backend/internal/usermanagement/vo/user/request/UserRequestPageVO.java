package com.backend.internal.usermanagement.vo.user.request;

import com.backend.internal.usermanagement.vo.RequestPageableVO;

import lombok.Data;

@Data
public class UserRequestPageVO extends RequestPageableVO {
	
    private String searchKeyword;
}
