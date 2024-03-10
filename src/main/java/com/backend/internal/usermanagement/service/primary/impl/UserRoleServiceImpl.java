package com.backend.internal.usermanagement.service.primary.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.internal.usermanagement.entity.primary.UserRoleEntity;
import com.backend.internal.usermanagement.repository.primary.UserRoleRepository;
import com.backend.internal.usermanagement.service.primary.UserRoleService;


@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	UserRoleRepository userRoleRepository;

	@Override
	public List<UserRoleEntity> findByUserId(Long userId, int isDeleted) {
		return userRoleRepository.findByUserIdAndIsDeleted(userId, isDeleted);
	}

	@Override
	public List<UserRoleEntity> findByRoleId(Long roleId, int isDeleted) {
		return userRoleRepository.findByRoleId(roleId, isDeleted);
	}

	@Override
	public boolean save(UserRoleEntity userGroupDetail) {
		userRoleRepository.save(userGroupDetail);
		return true;
	}

	@Override
	public UserRoleEntity findByUserIdAndRoleId(Long userId, Long roleId) {
		return userRoleRepository.findByUserIdAndRoleId(userId, roleId);
	}
}
