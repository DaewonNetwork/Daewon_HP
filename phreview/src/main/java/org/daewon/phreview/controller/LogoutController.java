package org.daewon.phreview.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.repository.RefreshTokenRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LogoutController {

    private final RefreshTokenRepository refreshTokenRepository;

    @PostMapping("/logout")
    public String logout(@RequestHeader("Authorization") String token) {
        log.info("logout token(successToken) : "+ token);
        // "Bearer " 부분 제거 - 토큰 값이 "Bearer ${accessToken}" 이 방식으로 들어가기 때문
        String refreshToken = token.substring(7);
        log.info("refreshToken : " + refreshToken);
        // Refresh Token 삭제
        refreshTokenRepository.deleteByToken(refreshToken);
        // SecurityContextHolder 초기화
        SecurityContextHolder.clearContext();

        return "Logged out successfully";
    }
}
