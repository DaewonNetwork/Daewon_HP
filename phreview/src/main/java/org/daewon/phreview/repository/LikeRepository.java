package org.daewon.phreview.repository;

import org.daewon.phreview.domain.EnjoyPh;
import org.daewon.phreview.domain.LikeForReview;
import org.daewon.phreview.domain.PharmacyEnjoy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LikeRepository extends JpaRepository<LikeForReview,Long> {
    @Query(value = "SELECT * from like_for_review where review_id=:reviewId and user_id=:userId",nativeQuery = true)
    LikeForReview findByReviewAndUsers(Long reviewId, Long userId);

}
