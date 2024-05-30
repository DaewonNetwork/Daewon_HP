package org.daewon.phreview.service;

import org.daewon.phreview.dto.ReviewLikeDTO;
import java.util.List;

public interface LikeService {
    void likeReview (Long reviewId);
    List<ReviewLikeDTO> getReviewsByLikeIndexDesc();
}
