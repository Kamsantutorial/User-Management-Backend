package com.backend.internal.usermanagement.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusType {
    INACTIVE(0),
    ACTIVE(1);

    private Integer value;
}
