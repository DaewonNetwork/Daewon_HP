package org.daewon.phreview.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.dto.PageRequestDTO;
import org.daewon.phreview.dto.PageResponseDTO;
import org.daewon.phreview.dto.ReviewDTO;
import org.daewon.phreview.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // ROLE_USER 권한을 가지고 있는 유저만 접근 가능
    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Long createReview(@RequestBody ReviewDTO reviewDTO) {
        log.info(reviewDTO);
        Long reviewId;
        try {
            reviewId = reviewService.createReview(reviewDTO);
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request", e);
        }
        return reviewId;
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

    // 특정 사용자가 작성한 리뷰 목록 조회
    @GetMapping(value = "/user/{userId}")
    public List<ReviewDTO> getUserReviews(@PathVariable(name = "userId") Long userId) {

        return reviewService.getReivewsByUserId(userId);
    }
}