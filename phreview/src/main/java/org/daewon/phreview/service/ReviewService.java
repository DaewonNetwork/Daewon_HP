package org.daewon.phreview.service;

import org.daewon.phreview.dto.PageRequestDTO;
import org.daewon.phreview.dto.PageResponseDTO;
import org.daewon.phreview.dto.PharmacyStarDTO;
import org.daewon.phreview.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    Long createReview(ReviewDTO reviewDTO);

    List<ReviewDTO> readReview(Long phId);

    void updateReview(ReviewDTO reviewDTO);

    void deleteReview(Long reviewId);

    // 특정 사용자가 작성한 리뷰 목록에 가져오는 메서드
    List<ReviewDTO> getReviewsByUserId(Long userId);

    List<PharmacyStarDTO> getPharmaciesByStarAvgDesc(); // 리뷰 별점 평균
}