package org.daewon.phreview.service;

import org.daewon.phreview.dto.PageRequestDTO;
import org.daewon.phreview.dto.PageResponseDTO;
import org.daewon.phreview.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
    Long createReview(ReviewDTO reviewDTO);

    List<ReviewDTO> readReview(Long phId);

    void updateReview(ReviewDTO reviewDTO);

    void deleteReview(Long reviewId);

    void addStar(Long reviewId, int rating);

    void updateStar(Long reviewId, int rating);

    void getStar(Long reviewId);

}