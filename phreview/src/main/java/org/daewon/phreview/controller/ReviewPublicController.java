package org.daewon.phreview.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.PharmacyStar;
import org.daewon.phreview.dto.PharmacyStarDTO;
import org.daewon.phreview.dto.ReviewDTO;

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
    @GetMapping(value = "/")
    public List<ReviewDTO> readReviews(@RequestParam(name = "phId") Long phId) {
        List<ReviewDTO> reviewList = reviewService.readReview(phId);
        return reviewList;
    }

    @GetMapping("/")
    public List<ReviewDTO> readReviewsByLikeIndexDesc(@RequestParam(name = "phId") Long phId){ // 좋아요 수가 높은 리뷰 내림차순
        List<ReviewDTO> reviewlist = likeService.getReviewsByLikeIndexDesc(phId);
        return reviewlist;
    }
}
