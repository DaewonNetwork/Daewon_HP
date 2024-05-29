package org.daewon.phreview.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.PharmacyStar;
import org.daewon.phreview.dto.PharmacyStarDTO;
import org.daewon.phreview.dto.ReviewDTO;
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

    @GetMapping(value = "/")
    public List<ReviewDTO> readReview(
            @RequestParam(name = "phId") Long phId) {
        List<ReviewDTO> reviewList = reviewService.readReview(phId);
        log.info("dto:" + reviewList);
        return reviewList;
    }

    @GetMapping("/list")
    public List<PharmacyStarDTO> starRankList(){ // 즐겨찾기 수가 높은 병원 내림차순
        List<PharmacyStarDTO> list = reviewService.getPharmaciesByStarAvgDesc();
        return list;
    }
}
