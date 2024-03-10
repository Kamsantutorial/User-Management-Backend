package com.backend.internal.usermanagement.entity.primary;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.backend.internal.usermanagement.entity.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "role_permissions")
@NoArgsConstructor
public class RolePermissionEntity extends BaseEntity<Long> {
	/** */
	private static final long serialVersionUID = -7604013748858948371L;

	@Column(name = "role_id")
	private Long roleId;

	@Column(name = "permission_id")
	private Long permissionId;

	public RolePermissionEntity(Long roleId, Long permissionId, Timestamp createdAt, String createdBy) {
		super();
		this.roleId = roleId;
		this.permissionId = permissionId;
		this.setCreatedAt(createdAt);
		this.setCreatedBy(createdBy);
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
