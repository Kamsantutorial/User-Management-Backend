package com.backend.internal.usermanagement.vo.user.request;

import java.util.List;
import lombok.Data;

@Data
public class UserUpdateRequestVO {
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
