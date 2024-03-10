package com.backend.internal.usermanagement.vo;

import java.util.Date;
import java.util.Objects;
import com.backend.internal.usermanagement.common.enums.ErrorCode;
import lombok.Data;

@Data
public class BaseResponse<T> {
    private long timestamp;
    private boolean success;
    private String message;
    private String code;
    private T body;

    public BaseResponse<T> body(T response) {
        this.body = response;
        return this;
    }

    public BaseResponse<T> success() {
        if (Objects.isNull(this.code)) {
            this.setTimestamp(new Date().getTime());
            this.setMessage("SUCCESS");
            this.setCode(ErrorCode.S000.getCode());
            this.setSuccess(true);
        }
        return this;
    }

    public BaseResponse<T> error(String code, String message) {
        if (Objects.nonNull(code) && message != null) {
            this.setTimestamp(new Date().getTime());
            this.setMessage(message);
            this.setCode(code);
            this.setSuccess(false);
        }
        return this;
    }

}
