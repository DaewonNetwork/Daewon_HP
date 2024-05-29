package org.daewon.phreview.repository;

import jakarta.persistence.Entity;
import org.daewon.phreview.domain.Review;
import org.daewon.phreview.repository.search.ReviewSearch;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReviewImageRepository extends JpaRepository<Review, Long>, ReviewSearch {

    @EntityGraph(attributePaths = {"imageSet"})
    @Query("select r from Review r where r.reviewId = :reviewId")
    Optional<Review> findByIdWithImages(Long reviewId); // revviewId로 리뷰를 조회하고, 연관된 이미지들도 함께 로드합니다.

}
