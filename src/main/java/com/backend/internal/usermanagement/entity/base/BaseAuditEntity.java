package com.backend.internal.usermanagement.entity.base;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseAuditEntity<T extends Serializable> implements Serializable {

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    protected T createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    protected Date createdAt;

    @LastModifiedBy
    @Column(name = "updated_by")
    protected T updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "updated_at")
    protected Date updatedAt;

    @Column(name = "deleted_by")
    protected T deletedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "deleted_at")
    protected Date deletedAt;

}
