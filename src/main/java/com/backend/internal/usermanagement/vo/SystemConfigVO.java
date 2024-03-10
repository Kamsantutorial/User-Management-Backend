package com.backend.internal.usermanagement.vo;

import lombok.Data;

@Data
public class SystemConfigVO {
    private Long id;
    private String type;
    private String key;
    private String value;
}
