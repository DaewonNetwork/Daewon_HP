package org.daewon.phreview.service;

import org.daewon.phreview.dto.ReviewDTO;

public interface ReviewService {
    Long register(ReviewDTO reviewDTO);

    ReviewDTO read(Long reviewId);

    void modify(ReviewDTO reviewDTO);

    void remove(Long reviewId);
}
