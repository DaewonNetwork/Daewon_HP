package org.daewon.phreview.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import net.coobird.thumbnailator.Thumbnailator;
import org.daewon.phreview.dto.*;

import org.daewon.phreview.domain.PharmacyEnjoy;
import org.daewon.phreview.domain.Review;
import org.daewon.phreview.dto.AuthSigninDTO;
import org.daewon.phreview.dto.PageRequestDTO;
import org.daewon.phreview.dto.PageResponseDTO;
import org.daewon.phreview.dto.ReviewDTO;
import org.daewon.phreview.repository.ReviewRepository;
import org.daewon.phreview.security.exception.PharmacyNotFoundException;
import org.daewon.phreview.security.exception.ReviewNotFoundException;
import org.daewon.phreview.service.LikeService;

import org.daewon.phreview.service.ReviewService;
import org.daewon.phreview.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    // 파일과 리뷰 데이터를 받는 엔드포인트
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadReviewData(
            @RequestPart("reviewDTO") String reviewDTOStr,
            @RequestPart("files") List<MultipartFile> files) {
        log.info("Review DTO String: " + reviewDTOStr);

        ReviewDTO reviewDTO;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            reviewDTO = objectMapper.readValue(reviewDTOStr, ReviewDTO.class);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid JSON format", e);
        }

        Long reviewId;
        try {
            reviewId = reviewService.createReview(reviewDTO, files, uploadPath);
            return ResponseEntity.ok().body(Map.of("reviewId", reviewId));
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request", e);
        }
    }
    
//    @GetMapping(value = "/")
//    public List<ReviewDTO> readReview(
//            @RequestParam(name = "phId") Long phId) {
//        List<ReviewDTO> reviewList = reviewService.readReview(phId);
//        log.info("dto:" + reviewList);
//        return reviewList;
//    }

    // 리뷰 작성한 유저만 삭제 가능
    @PreAuthorize("@reviewAndReplySecurity.isReviewOwner(#reviewId)")
    @DeleteMapping(value = "/")
    public Map<String, String> deleteReview(@RequestParam(name = "reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);
        return Map.of("result", "success");
    }

    // 리뷰 작성한 유저만 수정 가능
    @PreAuthorize("@reviewAndReplySecurity.isReviewOwner(#reviewId)")
    @PutMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> updateReview(@RequestParam(name = "reviewId") Long reviewId,
                                            @RequestBody ReviewDTO reviewDTO) {
        reviewDTO.setReviewId(reviewId);
        reviewService.updateReview(reviewDTO);
        return Map.of("result", "success");
    }

//    // 특정 사용자가 작성한 리뷰 목록 조회
//    // 로그인한 사용자의 리뷰를 가져오는 엔드포인트
//    @PreAuthorize("hasRole('USER')")
//    @GetMapping(value = "/user/{userId}")
//    public List<ReviewDTO> getUserReviews(@PathVariable(name = "userId") Long userId) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        AuthSigninDTO authSigninDTO = (AuthSigninDTO) authentication.getPrincipal();
//        Long userId = authSigninDTO.getUserId();
//
//
//        return reviewService.getReviewsByUserId(userId);
//    }

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