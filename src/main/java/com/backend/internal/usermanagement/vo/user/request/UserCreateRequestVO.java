package com.backend.internal.usermanagement.vo.user.request;

import java.sql.Timestamp;
import java.util.List;

import com.backend.internal.usermanagement.dto.branch.BranchDTO;
import com.backend.internal.usermanagement.dto.role.RoleDTO;

import lombok.Data;

@Data
public class UserCreateRequestVO {
    private String username;
    private String fullname;
    private String phoneNumber;
    private String email;
    private String password;
    private boolean locked;
    private Long branchId;
    private List<Long> roleIds;
    private Boolean isActive;
}
