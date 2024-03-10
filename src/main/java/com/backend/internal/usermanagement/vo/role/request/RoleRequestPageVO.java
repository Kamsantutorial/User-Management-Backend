package com.backend.internal.usermanagement.vo.role.request;

import com.backend.internal.usermanagement.vo.RequestPageableVO;

import lombok.Data;

@Data
public class RoleRequestPageVO extends RequestPageableVO {
    private String searchKeyword;
}
