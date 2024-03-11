package com.backend.internal.usermanagement.entity.primary;

import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.WhereJoinTable;
import com.backend.internal.usermanagement.entity.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "roles")
@NoArgsConstructor
public class RoleEntity extends BaseEntity<Long> {
	/** */
	private static final long serialVersionUID = -1670847308322273155L;

	@Column(name = "role_name")
	private String roleName;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "role_permissions", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
	@WhereJoinTable(clause = "is_deleted = false ")
	@BatchSize(size = 10)
	private Set<PermissionEntity> permissions;

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
