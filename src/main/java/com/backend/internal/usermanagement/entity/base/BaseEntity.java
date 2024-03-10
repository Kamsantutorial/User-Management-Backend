package com.backend.internal.usermanagement.entity.base;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity<ID extends Serializable> extends BaseAuditEntity<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

    @Column(name = "is_active")
    private Boolean isActive = true;
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @PreUpdate
    public void beforeUpdate() {
        if (Objects.nonNull(this.getIsDeleted()) && this.getIsDeleted()) {
            this.setDeletedAt(new Date());
            this.setIsActive(false);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseEntity<ID> other = (BaseEntity<ID>) obj;
        return id != null && Objects.equals(id, other.getId());
    }

    @Override
    public String toString() {
        return "BaseEntity [id=" + id + ", isActive=" + isActive + ", isDeleted=" + isDeleted + "]";
    }

}
