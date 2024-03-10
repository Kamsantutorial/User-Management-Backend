package com.backend.internal.usermanagement.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.backend.internal.usermanagement.repository.primary.PermissionRepository;
import com.backend.internal.usermanagement.repository.primary.RoleRepository;
import com.backend.internal.usermanagement.dto.user.UserDetail;
import com.backend.internal.usermanagement.entity.primary.PermissionEntity;
import com.backend.internal.usermanagement.entity.primary.RoleEntity;
import com.backend.internal.usermanagement.entity.primary.UserEntity;
import com.backend.internal.usermanagement.repository.primary.UserRepository;


@Service
public class UserDetailService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findByUsername(username);
		List<String> listPermissions = new ArrayList<String>();
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		List<RoleEntity> roles = roleRepository.findRoleByUserId(user.getId());
		boolean superAdmin = roles.stream().anyMatch(g -> g.getRoleName().equals("Super Admin"));
		if (superAdmin) {
			List<PermissionEntity> allPermissions = permissionRepository.getAllPermissions();
			for (PermissionEntity permission : allPermissions) {
				listPermissions.add(permission.getPermissionName());
			}
		} else {
			for (RoleEntity role : roles) {
				List<PermissionEntity> permissions = permissionRepository.findPermissionByRoleId(role.getId());
				for (PermissionEntity permission : permissions) {
					listPermissions.add(permission.getPermissionName());
				}
			}
		}
		return new UserDetail((long) user.getId(), user.getUsername(), user.getFullname(), user.getPhoneNumber(),
				user.getEmail(), listPermissions, roles);
	}
}