package org.daewon.phreview.repository;


import org.daewon.phreview.domain.ReviewImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {

    @Query("SELECT r FROM ReviewImage r WHERE r.review.reviewId = :reviewId")
    Optional<ReviewImage> findByReviewId(@Param("reviewId") Long reviewId);


    @Transactional
    @Modifying
    @Query("DELETE FROM ReviewImage r WHERE r.review.reviewId = :reviewId")
    void deleteByReviewId(Long reviewId);

}
