package com.backend.internal.usermanagement.vo;

import java.util.List;
import lombok.Data;

@Data
public class ResponsePageableVO<T> {
    // list item response
    private List<T> items;
    private long totalPages;
    private long totalRecords;
    // current page
    private long page;
    // total of page
    private long pageSize;

    public ResponsePageableVO(List<T> items, long totalRecords, long totalPages, RequestPageableVO requestPageableVO) {
        this.items = items;
        this.pageSize = requestPageableVO.getSize();
        this.totalPages = totalPages;
        this.page = requestPageableVO.getPage();
        this.totalRecords = totalRecords;

    }

}
