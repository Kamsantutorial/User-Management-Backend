package com.backend.internal.usermanagement.entity.primary;

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
@NoArgsConstructor
@Table(name = "branches")
public class BranchEntity extends BaseEntity<Long> {
	/** */
	private static final long serialVersionUID = 7171827009471676787L;

	@Column(name = "branch_name")
	private String branchName;

	@Column(name = "branch_code")
	private String branchCode;

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
