package com.backend.internal.usermanagement.entity.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "system_config")
public class SystemConfigEntity extends BaseEntity<Long> {

    @Column(name = "\"type\"")
    private String type;
    @Column(name = "\"key\"", unique = true)
    private String key;
    private String value;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
