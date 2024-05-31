package org.daewon.phreview.service;


import org.daewon.phreview.dto.Pharmacy.PharmacyEnjoyRankListDTO;
import org.daewon.phreview.dto.Review.ReviewReadDTO;

import java.util.List;

public interface LikeService {
    void likeReview (Long reviewId);
    List<ReviewReadDTO> likedReviewsListByUser();
}
