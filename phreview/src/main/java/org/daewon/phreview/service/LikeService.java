package org.daewon.phreview.service;

import org.daewon.phreview.dto.ReviewDTO;
import java.util.List;

public interface LikeService {
    void likeReview (Long reviewId);
    List<ReviewDTO> getReviewsByLikeIndexDesc(Long phId);
}
