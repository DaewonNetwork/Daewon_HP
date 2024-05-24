package org.daewon.phreview.service;

import org.daewon.phreview.dto.ReviewDTO;
import org.daewon.phreview.dto.ReviewImageDTO;
import org.springframework.data.domain.Page;

public interface ReviewService {
    Long register(ReviewDTO reviewDTO);

    ReviewDTO read(Long reviewId);

    void modify(ReviewDTO reviewDTO);

    void remove(Long reviewId);

    Page<ReviewImageDTO> getImagesByReviewIdPaginated(Long reviewId, int page, int size);

}
