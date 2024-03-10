package com.backend.internal.usermanagement.service.primary;

import com.backend.internal.usermanagement.dto.base.RequestPageableDTO;
import com.backend.internal.usermanagement.dto.user.UserDTO;
import com.backend.internal.usermanagement.entity.primary.UserEntity;
import com.backend.internal.usermanagement.service.base.BaseService;

public interface UserService extends BaseService<UserDTO, RequestPageableDTO> {
	UserEntity findByUsername(String username);
}
