package org.daewon.phreview.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.dto.review.ReviewReadDTO;

import org.daewon.phreview.service.LikeService;
import org.daewon.phreview.service.ReviewService;
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
    private final LikeService likeService;

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



}
