package com.backend.internal.usermanagement.vo.menu.request;

import lombok.Data;

@Data
public class MenuRequestVO {
	private String menuName;
	private Float orderBy;
	private String url;
	private String icon;
	private String parentId;
}
