package com.backend.internal.usermanagement.vo.user.request;

import java.util.List;
import lombok.Data;

@Data
public class UserCreateRequestVO {
    private String username;
    private String fullname;
    private String phoneNumber;
    private String email;
    private String password;
    private Boolean locked;
    private Long branchId;
    private List<Long> roleIds;
    private Boolean isActive;
}
