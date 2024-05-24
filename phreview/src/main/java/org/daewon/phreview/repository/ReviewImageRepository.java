package org.daewon.phreview.repository;

import org.daewon.phreview.domain.Review;
import org.daewon.phreview.domain.ReviewImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, String> {
    Page<ReviewImage> findByReview(Review review, Pageable pageable);
}
