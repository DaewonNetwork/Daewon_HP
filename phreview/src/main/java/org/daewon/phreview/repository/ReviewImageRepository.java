package org.daewon.phreview.repository;

import org.daewon.phreview.domain.Review;
import org.daewon.phreview.domain.ReviewImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, String> {

    @Query("SELECT r FROM ReviewImage r WHERE r.review.reviewId = :reviewId")
    List<ReviewImage> findByReviewId(Long reviewId);
}
