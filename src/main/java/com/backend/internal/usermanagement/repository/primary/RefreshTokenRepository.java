package com.backend.internal.usermanagement.repository.primary;

import java.util.Optional;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.internal.usermanagement.entity.primary.RefreshTokenEntity;
import com.backend.internal.usermanagement.repository.base.BaseRepository;

@Repository
public interface RefreshTokenRepository extends BaseRepository<RefreshTokenEntity, Long> {

    Optional<RefreshTokenEntity> findByTokenId(@Param("tokenId") String tokenId);

}
