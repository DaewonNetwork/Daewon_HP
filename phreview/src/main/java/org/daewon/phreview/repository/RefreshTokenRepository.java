package org.daewon.phreview.repository;

import org.daewon.phreview.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    void deleteByToken(String token);
}
