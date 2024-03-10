package com.backend.internal.usermanagement.entity.primary;

import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.WhereJoinTable;
import com.backend.internal.usermanagement.entity.base.BaseEntity;
import javax.persistence.JoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor
public class UserEntity extends BaseEntity<Long> {
	/** */
	private static final long serialVersionUID = 8636971907023656602L;

	@Column(name = "branch_id")
	private Long branchId;

	@Column(name = "username")
	private String username;

	@Column(name = "fullname")
	private String fullname;

	@Column(name = "staff_id")
	private String staffId;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "email")
	private String email;

	@Column(name = "failed_attempt")
	private int failedAttempt;

	@Column(name = "locked")
	private boolean locked;

	@Column(name = "last_login")
	private Timestamp lastLogin;

	private String password;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "branch_id", referencedColumnName = "id", insertable = false, updatable = false)
	private BranchEntity branch;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	@WhereJoinTable(clause = "is_active = true ")
	private Set<RoleEntity> roles;

	public UserEntity(Long branchId, String username, String fullname, String staffId, String phoneNumber, String email,
			int failedAttempt, boolean locked, boolean isActive, Timestamp createdAt, String createdBy,
			boolean isDeleted) {
		super();
		this.branchId = branchId;
		this.username = username;
		this.fullname = fullname;
		this.staffId = staffId;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.failedAttempt = failedAttempt;
		this.locked = locked;
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
