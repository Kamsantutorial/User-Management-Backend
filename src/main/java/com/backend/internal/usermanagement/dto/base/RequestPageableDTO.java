package com.backend.internal.usermanagement.dto.base;

import lombok.Data;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Data
public class RequestPageableDTO {
    private int page;
    private int size;
    private String[] orderBy;
    private Sort.Direction direction;

    public int getPageOffset() {
        return (int) PageRequest.of(this.page - 1, this.size).getOffset();
    }
}
