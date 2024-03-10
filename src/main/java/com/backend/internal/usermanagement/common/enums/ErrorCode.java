package com.backend.internal.usermanagement.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    S000("200", "Success"),
    E001("E001", "Field must be not null"),
    E002("E002", "Record Not found"),
    E003("E003", "Internal server error"),
    E004("E004", "RequestMessage is mandatory"),
    E005("E005", "ServiceHeader is mandatory"),
    E006("E006", "Third Party is mandatory"),
    E007("E007", "Token id invalid"),
    E008("E008", "Error Occur"),
    E009("E009", "KHQR Generate Failed");

    private String code;
    private String desc;
}
