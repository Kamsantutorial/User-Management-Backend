package com.backend.internal.usermanagement.service.primary.impl;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backend.internal.usermanagement.repository.primary.AccessTokenRepository;
import com.backend.internal.usermanagement.service.primary.AccessTokenService;
import com.backend.internal.usermanagement.entity.primary.AccessTokenEntity;
import com.backend.internal.usermanagement.repository.base.BaseCriteria;
import com.backend.internal.usermanagement.repository.primary.UserRoleRepository;
import java.util.stream.Collectors;

@Service
public class AccessTokenServiceImpl implements AccessTokenService {

    @Autowired
    private AccessTokenRepository accessTokenRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public void removeAccessTokenByRoleId(Long roleId) {

        BaseCriteria<UserRoleRepository> criteria = new BaseCriteria<>(userRoleRepository);
        criteria.equal("roleId", roleId);

        List<Long> userIds = userRoleRepository.findAllWithCriteria(criteria).stream().map(user -> user.getUserId())
                .collect(Collectors.toList());

        BaseCriteria<AccessTokenRepository> criteriaAccessToken = new BaseCriteria<>(accessTokenRepository);
        criteriaAccessToken.in("userId", userIds);
        List<AccessTokenEntity> list = accessTokenRepository.findAllWithCriteria(criteriaAccessToken);

        accessTokenRepository.deleteAll(list);
    }

    @Override
    public void removeAccessTokenByUserId(Long userId) {
        BaseCriteria<AccessTokenRepository> criteriaAccessToken = new BaseCriteria<>(accessTokenRepository);
        criteriaAccessToken.equal("userId", userId);
        AccessTokenEntity entity = accessTokenRepository.findOneWithCriteria(criteriaAccessToken).orElse(null);

        if (Objects.nonNull(entity))
            accessTokenRepository.delete(entity);
    }

}
