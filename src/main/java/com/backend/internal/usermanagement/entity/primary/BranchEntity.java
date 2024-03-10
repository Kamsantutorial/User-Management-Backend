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
@NoArgsConstructor
@Table(name = "branches")
public class BranchEntity extends BaseEntity<Long> {
	/** */
	private static final long serialVersionUID = 7171827009471676787L;

	@Column(name = "branch_name")
	private String branchName;

	@Column(name = "branch_code")
	private String branchCode;

	public BranchEntity(String branchName, String branchCode, boolean isActive, Timestamp createdAt, String createdBy,
			boolean isDeleted) {
		super();
		this.branchName = branchName;
		this.branchCode = branchCode;
		this.setIsActive(isActive);
		this.setCreatedAt(createdAt);
		this.setCreatedBy(createdBy);
		this.setIsDeleted(isDeleted);
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