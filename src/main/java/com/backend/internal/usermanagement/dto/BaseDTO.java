package com.backend.internal.usermanagement.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class BaseDTO implements Serializable {
    private Boolean isActive = true;
    private String createdBy;
    private Date createdAt = new Date();
    private String updatedBy;
    private Date updatedAt;
    private boolean isDeleted;
    private String deletedBy;
    private Date deletedAt;
}
