package org.daewon.phreview.service;

import org.daewon.phreview.dto.ReviewDTO;
import org.daewon.phreview.dto.ReviewReadDTO;

import java.util.List;

public interface LikeService {
    void likeReview (Long reviewId);

}
