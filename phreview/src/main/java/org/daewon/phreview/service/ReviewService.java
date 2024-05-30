package org.daewon.phreview.service;

import org.daewon.phreview.dto.PharmacyStarDTO;
import org.daewon.phreview.dto.ReviewDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    Long createReview(ReviewDTO reviewDTO, MultipartFile file, String uploadPath);

    List<ReviewDTO> readReview(Long phId);

    void updateReview(ReviewDTO reviewDTO);

    void deleteReview(Long reviewId);

    // 특정 사용자가 작성한 리뷰 목록에 가져오는 메서드
    List<ReviewDTO> getReviewsByUserId(Long userId);

    List<PharmacyStarDTO> getPharmaciesByStarAvgDesc(); // 리뷰 별점 평균
}