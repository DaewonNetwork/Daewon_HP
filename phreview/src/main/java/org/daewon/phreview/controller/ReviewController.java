package org.daewon.phreview.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.Review;
import org.daewon.phreview.dto.review.ReviewDTO;
import org.daewon.phreview.dto.review.ReviewUpdateDTO;
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
import java.util.List;
import java.util.Map;

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
    // Content-Type : multipart/form-data, Accept : application/json 형태 이어야함
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createReview(
            // @RequestPart 어노테이션은 요청의 일부인 특정 파트를 가져오는 데 사용됨.
            // 클라이언트로부터 전달된 'reviewDTO'라는 이름의 JSON 데이터를 문자열 형식으로 받음
            @RequestPart("reviewDTO") String reviewDTOStr,
            // 클라이언트로부터 전달된 파일 리스트를 받음.
            // required = false 로 설정하여 파일이 없어도 요청이 처리됨
            @RequestPart(name = "files", required = false) List<MultipartFile> files) {
        log.info("Review DTO String: " + reviewDTOStr);
        log.info("Files: " + files);
        
        ReviewDTO reviewDTO;
        try {
            // JSON 문자열을 ReviewDTO 객체로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            // @RequestPart 부분에서 한글 처리하는데 문제가 생겨서 강제로 UTF-8로 변환해 줌
            String decodedReviewDTO = new String(reviewDTOStr.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            reviewDTO = objectMapper.readValue(decodedReviewDTO, ReviewDTO.class);
        } catch (IOException e) {
            // JSON 변환 중 오류가 발생하면 로그를 남기고 예외 발생
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid JSON format", e);
        }

        Long reviewId;
        try {
            // 리뷰 생성 메서드 호출
            reviewId = reviewService.createReview(reviewDTO, files != null && !files.isEmpty() ? files.get(0) : null, uploadPath);

            return ResponseEntity.ok(reviewId);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
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
            @RequestBody ReviewUpdateDTO reviewUpdateDTO) {
        reviewService.updateReview(reviewUpdateDTO,reviewId);
        return Map.of("result", "success");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/like")
    public int like(@RequestParam Long reviewId){ // 좋아요
        likeService.likeReview(reviewId);
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ReviewNotFoundException(reviewId));
        return review.getLikeIndex(); // 좋아요 수 반환
    }

}