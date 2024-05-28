package org.daewon.phreview.service;

import org.daewon.phreview.dto.PageRequestDTO;
import org.daewon.phreview.dto.PageResponseDTO;
import org.daewon.phreview.dto.ReviewDTO;
import org.daewon.phreview.dto.ReviewImageDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReviewService {
    Long createReview(ReviewDTO reviewDTO);

    List<ReviewDTO> readReview(Long phId);

    void updateReview(ReviewDTO reviewDTO);

    void deleteReview(Long reviewId);



}
