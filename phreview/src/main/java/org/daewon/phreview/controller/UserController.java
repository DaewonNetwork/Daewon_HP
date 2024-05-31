package org.daewon.phreview.controller;

import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.daewon.phreview.dto.Reply.ReplyDTO;
import org.daewon.phreview.dto.ReviewDTO;
import org.daewon.phreview.dto.ReviewReadDTO;
import org.daewon.phreview.repository.RefreshTokenRepository;
import org.daewon.phreview.service.ReplyService;
import org.daewon.phreview.service.ReviewService;
import org.daewon.phreview.utils.JWTUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JWTUtil jwtUtil;
    private final ReviewService reviewService;
    private final ReplyService replyService;

    // 클라이언트가 accessToken을 서버에 보내면
    // 서버에서 해당 토큰을 검증하고 payload 전체를 JSON 형식으로 클라이언트에게 반환이 가능
    // 테스트할때 Authorization에 Bearer (accessToken Value) - 이렇게 보내면 됨
    @GetMapping()
    public ResponseEntity<?> readUser(@RequestHeader("Authorization") String token) {
        try {
            // Bearer 토큰에서 "Bearer " 부분 제거
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            // 토큰에서 payload 추출
            Map<String, Object> claims = jwtUtil.validateToken(token);

            log.info("payload값 서버 -> 클라이언트 : " + claims);
            // payload 반환
            return ResponseEntity.ok(claims);
        } catch (JwtException e) {
            // 토큰이 유효하지 않으면 401 상태 코드 반환
            return ResponseEntity.status(401).body("Invalid token");
        }
    }
    // @GetMapping()
    // public Object readUser(@RequestHeader("Authorization") String token) {
    // try {
    // // Bearer 토큰에서 "Bearer " 부분 제거
    // if (token.startsWith("Bearer ")) {
    // token = token.substring(7);
    // }

    // // 토큰에서 payload 추출
    // Map<String, Object> claims = jwtUtil.validateToken(token);

    // log.info("payload값 서버 -> 클라이언트 : " + claims);

    // // payload 반환
    // return claims;
    // } catch (JwtException e) {
    // // 토큰이 유효하지 않으면 401 상태 코드 반환
    // return "Invalid token";
    // }
    // }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token) {
        log.info("logout token(successToken) : " + token);
        // "Bearer " 부분 제거 - 토큰 값이 "Bearer ${accessToken}" 이 방식으로 들어가기 때문
        String refreshToken = token.substring(7);
        log.info("refreshToken : " + refreshToken);
        // Refresh Token 삭제
        refreshTokenRepository.deleteByToken(refreshToken);
        // SecurityContextHolder 초기화
        SecurityContextHolder.clearContext();

        return ResponseEntity.ok("Logout successful");
    }

    @GetMapping("/reivew")
    public ResponseEntity<?> getUserReviews(@RequestHeader("Authorization") String token) {
        try {
            // Bearer 토큰에서 "Bearer " 부분 제거
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            // 토큰 payload에서 userId 값 추출
            Map<String, Object> claims = jwtUtil.validateToken(token);
            // payload에서 userId 추출
            Long userId = Long.parseLong(claims.get("userId").toString());

            log.info("유저 ID: " + userId);

            // 해당 사용자가 작성한 Review 가져오기
            List<ReviewReadDTO> userReviews = reviewService.getReviewsByUserId(userId);

            log.info("리뷰 목록: " + userReviews);

            // 리뷰 목록 반환
            return ResponseEntity.ok(userReviews);
        } catch (JwtException e) {
            // 토큰이 유효하지 않으면 401 상태 코드 반환
            return ResponseEntity.status(401).body("Invalid token");
        }
    }

    // 특정 사용자가 작성한 Reply 목록 조회
    // 로그인한 사용자의 Reply를 가져오는 엔드포인트
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/reply")
    public ResponseEntity<?> getUserReplys(@RequestHeader("Authorization") String token) {
        try {
            // Bearer 토큰에서 "Bearer " 부분 제거
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            // 토큰에서 payload 추출
            Map<String, Object> claims = jwtUtil.validateToken(token);
            // payload에서 userId 추출
            Long userId = Long.parseLong(claims.get("userId").toString());

            log.info("유저 ID: " + userId);

            // 해당 사용자가 작성한 Reply 가져오기
            List<ReplyDTO> replies = replyService.getRepliesByUserId(userId);

            // 댓글 목록 반환
            return ResponseEntity.ok(replies);
        } catch (JwtException e) {
            // 토큰이 유효하지 않으면 401 상태 코드 반환
            return ResponseEntity.status(401).body("Invalid token");
        }
    }
}