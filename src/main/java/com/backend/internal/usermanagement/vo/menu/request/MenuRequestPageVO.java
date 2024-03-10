package com.backend.internal.usermanagement.vo.menu.request;

import com.backend.internal.usermanagement.vo.RequestPageableVO;

import lombok.Data;

@Data
public class MenuRequestPageVO extends RequestPageableVO {
	 private String searchKeyword;
}
