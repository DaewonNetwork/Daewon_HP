package org.daewon.phreview.repository;

import org.daewon.phreview.domain.Review;
import org.daewon.phreview.domain.ReviewImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, String> {

    @Query("SELECT r FROM ReviewImage r WHERE r.review.reviewId = :reviewId")
    ReviewImage findByReviewId(@Param("reviewId") Long reviewId);

    List<ReviewImage> findByReviewReviewId(Long reviewId);
    @Transactional
    @Modifying
    @Query("DELETE FROM ReviewImage r WHERE r.review.reviewId = :reviewId")
    void deleteByReviewId(Long reviewId);

}
