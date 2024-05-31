package org.daewon.phreview.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.PharmacyStar;
import org.daewon.phreview.dto.PharmacyStarDTO;
import org.daewon.phreview.dto.ReviewDTO;

import org.daewon.phreview.dto.ReviewReadDTO;
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
    @GetMapping()
    public List<ReviewReadDTO> readReviews(@RequestParam(name = "phId") Long phId) {
        List<ReviewReadDTO> reviewList = reviewService.readReview(phId);
        return reviewList;
    }

    @Operation(summary = "좋아요 순")
    @GetMapping("/list")
    public List<ReviewReadDTO> readReviewsByLikeIndexDesc(@RequestParam(name = "phId") Long phId){ // 좋아요 수가 높은 리뷰 내림차순
        List<ReviewReadDTO> reviewlist = likeService.getReviewsByLikeIndexDesc(phId);
        return reviewlist;
    }
}
