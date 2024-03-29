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
@Table(name = "user_roles")
@NoArgsConstructor
public class UserRoleEntity extends BaseEntity<Long> {
	/** */
	private static final long serialVersionUID = -4577634787515614075L;

	@Column(name = "user_id")
	private Long userId;
	@Column(name = "role_id")
	private Long roleId;

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
