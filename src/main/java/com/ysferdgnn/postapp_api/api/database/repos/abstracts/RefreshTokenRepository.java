package com.ysferdgnn.postapp_api.api.database.repos.abstracts;

import com.ysferdgnn.postapp_api.api.database.models.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long > {
    Optional<RefreshToken> findByUserId(Long id);
}
