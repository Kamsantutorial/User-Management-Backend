package com.backend.internal.usermanagement.vo.menu.response;

import java.sql.Timestamp;

import lombok.Data;

/**
 * 
 * @author SETC01
 *
 */
@Data
public class MenuResponseVO {
    private Long id;
    private String menuName;
    private String orderBy;
    private String url;
    private String icon;
    private ParentMenuVO parent;
    private Boolean isActive;
    private String createdBy;
    private Timestamp createdAt;
    private String updatedBy;
    private Timestamp updatedAt;
    private Boolean isDeleted;
    private String deletedBy;
    private Timestamp deletedAt;
}
