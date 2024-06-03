package org.daewon.phreview.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.ReviewImage;
import org.daewon.phreview.dto.review.ReviewImageDTO;
import org.daewon.phreview.dto.review.ReviewReadDTO;

import org.daewon.phreview.repository.ReviewImageRepository;
import org.daewon.phreview.service.LikeService;
import org.daewon.phreview.service.ReviewService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewPublicController {

    private final ReviewService reviewService;
    private final ReviewImageRepository reviewImageRepository;


    @GetMapping("/read")
    public ReviewReadDTO readReview(@RequestParam(name = "reviewId") Long reviewId) {
        ReviewReadDTO review = reviewService.readReview(reviewId); // 리뷰 최신순
        return review;
    }

    @GetMapping("/list")
    public List<ReviewReadDTO> readReviews(@RequestParam(name = "phId") Long phId) {
        List<ReviewReadDTO> reviewList = reviewService.readReviews(phId); // 리뷰 최신순
        return reviewList;
    }

    @Operation(summary = "좋아요 순")
    @GetMapping("/list/like")
    public List<ReviewReadDTO> readReviewsByLikeIndexDesc(@RequestParam(name = "phId") Long phId){ // 리뷰 좋아요순
        List<ReviewReadDTO> reviewlist = reviewService.readReviewsByLikeIndexDesc(phId);
        return reviewlist;
    }

    @Operation(summary = "모든 리뷰")
    @GetMapping("/AllList")
    public List<ReviewReadDTO> readAllReviews(){ // 리뷰 전체
        List<ReviewReadDTO> reviewlist =  reviewService.readAllReviews();
        return reviewlist;
    }

    @Operation(summary = "모든 리뷰 좋아요순")
    @GetMapping("/AllList/like")
    public List<ReviewReadDTO> readAllReviewsByLikeIndexDesc(){ // 리뷰 전체
        List<ReviewReadDTO> reviewlist =  reviewService.readAllReviewsByLikeIndexDesc();
        return reviewlist;
    }

   
    @Operation(summary = "이미지")
    @GetMapping("/read/image")
    public ReviewImageDTO readReviewImage(Long reviewId){
        ReviewImage reviewImage = reviewImageRepository.findByReviewId(reviewId);
        log.info("Review Image: " + reviewImage);
        // ReviewImage를 ReviewImageDTO로 변환하여 반환
        ReviewImageDTO reviewImageDTO = new ReviewImageDTO();
        reviewImageDTO.setUuid(reviewImage.getUuid());
        // ReviewImage의 다른 필드를 ReviewImageDTO에 설정
        reviewImageDTO.setOrd(reviewImage.getOrd());
        reviewImageDTO.setFileName(reviewImage.getFileName());
        // 필요한 경우 더 많은 속성을 설정할 수 있습니다.
        return reviewImageDTO;
    }


}
