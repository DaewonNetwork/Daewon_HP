package org.daewon.phreview.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.daewon.phreview.domain.Review;
import org.daewon.phreview.dto.ReviewDTO;
import org.daewon.phreview.repository.ReviewRepository;
import org.daewon.phreview.security.exception.ReviewNotFoundException;
import org.daewon.phreview.service.LikeService;

import org.daewon.phreview.service.ReviewService;
import org.daewon.phreview.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@RestController
@Log4j2
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final LikeService likeService;
    private final JWTUtil jwtUtil;
    private final ReviewRepository reviewRepository;

    @Value("${org.daewon.upload.path}")
    private String uploadPath;

    // ROLE_USER 권한을 가지고 있는 유저만 접근 가능
    @PreAuthorize("hasRole('USER')")

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createReview(
            @RequestPart("reviewDTO") String reviewDTOStr,
            @RequestPart("files") List<MultipartFile> files) {
        log.info("Review DTO String: " + reviewDTOStr);

        ReviewDTO reviewDTO;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long createReview(@RequestBody ReviewDTO reviewDTO) {
        log.info(reviewDTO);
        Long reviewId;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // 여기에서 인코딩을 UTF-8로 변환
            String decodedReviewDTO = new String(reviewDTOStr.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            reviewDTO = objectMapper.readValue(decodedReviewDTO, ReviewDTO.class);
        } catch (IOException e) {
            log.error("Error parsing reviewDTO: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid JSON format", e);
        }

        try {
            Long reviewId = reviewService.createReview(reviewDTO, files, uploadPath);
            return ResponseEntity.ok(reviewId);
        } catch (RuntimeException e) {
            log.error("Error creating review: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request", e);
        }
    }


    // 리뷰 작성한 유저만 삭제 가능
    @PreAuthorize("@reviewAndReplySecurity.isReviewOwner(#reviewId)")
    @DeleteMapping()
    public Map<String, String> deleteReview(@RequestParam(name = "reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);
        return Map.of("result", "success");
    }

    // 리뷰 작성한 유저만 수정 가능
    @PreAuthorize("@reviewAndReplySecurity.isReviewOwner(#reviewId)")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> updateReview(@RequestParam(name = "reviewId") Long reviewId,
                                            @RequestBody ReviewDTO reviewDTO) {
        reviewDTO.setReviewId(reviewId);
        reviewService.updateReview(reviewDTO);
        return Map.of("result", "success");
    }

    //  특정 사용자가 작성한 리뷰 목록 조회
    // 로그인한 사용자의 리뷰를 가져오는 엔드포인트
    @GetMapping("/user")
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
            List<ReviewDTO> userReviews = reviewService.getReviewsByUserId(userId);

            log.info("리뷰 목록: " + userReviews);

            // 리뷰 목록 반환
            return ResponseEntity.ok(userReviews);
        } catch (JwtException e) {
            // 토큰이 유효하지 않으면 401 상태 코드 반환
            return ResponseEntity.status(401).body("Invalid token");
        }
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/like")
    public int like(@RequestParam Long reviewId){ // 좋아요
        likeService.likeReview(reviewId);
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException(reviewId));
        return review.getLikeIndex(); // 좋아요 수 반환
    }

}