package com.backend.internal.usermanagement.vo.menu.request;

import lombok.Data;

@Data
public class MenuCreateRequestVO {
	private String menuName;
	private Float orderBy;
	private String url;
	private String icon;
	private String parentId;
}
