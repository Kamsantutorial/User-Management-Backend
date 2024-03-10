package com.backend.internal.usermanagement.dto.user;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.Data;

@Data
public class UserDetailDTO implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String username;
	private String fullname;
	private String phoneNumber;
	private String email;
	private List<GrantedAuthority> roles;

	public UserDetailDTO(Long id, String username, String fullname, String phoneNumber, String email,
			List<GrantedAuthority> roles) {
		super();
		this.id = id;
		this.username = username;
		this.fullname = fullname;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.roles = roles;
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

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.getRoles();
	}

}
