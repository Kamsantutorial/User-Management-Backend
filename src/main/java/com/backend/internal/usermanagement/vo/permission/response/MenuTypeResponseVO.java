package com.backend.internal.usermanagement.vo.permission.response;

import java.util.List;
import lombok.Data;

@Data
public class MenuTypeResponseVO {
	private Long id;
	private String menuName;
	private List<PermissionVO> permissions;
}
