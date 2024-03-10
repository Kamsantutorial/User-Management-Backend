package com.backend.internal.usermanagement.vo.menu.response;

import java.io.Serializable;
import java.util.List;

import com.backend.internal.usermanagement.vo.permission.response.PermissionLoginResponseVO;

import lombok.Data;

/**
 * 
 * @author SETC01
 *
 */
@Data
public class MenuLoginResponseVO implements Serializable {
    private Long id;
    private String name;
    private String orderBy;
    private String path;
    private String icon;
    private Long parentId;
    private String redirect;
    private String component;
    private List<PermissionLoginResponseVO> permissions;
    private List<MenuLoginResponseVO> children;
}
