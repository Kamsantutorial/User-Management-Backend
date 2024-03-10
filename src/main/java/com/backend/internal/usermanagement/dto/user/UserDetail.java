package com.backend.internal.usermanagement.dto.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.backend.internal.usermanagement.entity.base.BaseEntitySetting;
import com.backend.internal.usermanagement.entity.primary.RoleEntity;


public class UserDetail extends BaseEntitySetting implements UserDetails, Serializable {
	/** */
	private static final long serialVersionUID = 6912973547583844054L;
	
	private Long id;
	private String username;
	private String fullname;
	private String phoneNumber;
	private String email;
	private List<String> permissions;
	private List<RoleEntity> roles;
	
	public UserDetail(Long id, String username, String fullname, String phoneNumber, String email, List<String> permissions,
			List<RoleEntity> roles) {
		super();
		this.id = id;
		this.username = username;
		this.fullname = fullname;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.permissions = permissions;
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		for (String permission : permissions) {
			list.add(()->(permission));
		}
		return list;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
