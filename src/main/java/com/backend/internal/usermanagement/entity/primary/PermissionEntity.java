package com.backend.internal.usermanagement.entity.primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.backend.internal.usermanagement.entity.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "permissions")
public class PermissionEntity extends BaseEntity<Long> {
	/** */
	private static final long serialVersionUID = -2055967364242882798L;

	@Column(name = "permission_name")
	private String permissionName;

	@Column(name = "type")
	private String type;

	@Column(name = "menu_id")
	private Long menuId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "menu_id", referencedColumnName = "id", insertable = false, updatable = false)
	private MenuEntity menu;

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
