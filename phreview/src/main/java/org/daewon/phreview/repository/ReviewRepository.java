package org.daewon.phreview.repository;

import org.daewon.phreview.domain.LikeForReview;
import org.daewon.phreview.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r WHERE r.pharmacy.phId = :phId ORDER BY r.reviewId DESC")
    List<Review> listOfPharmacy(@Param("phId") Long phId); // 최신순

    @Query("SELECT r FROM Review r WHERE r.pharmacy.phId = :phId ORDER BY r.likeIndex DESC")
    List<Review> listOfPharmacyOrderByLikeIndex(@Param("phId") Long phId); // 좋아요순

    // 리뷰 작성자의 userId를 반환하도록 하는 메서드
    @Query("select r.users.userId from Review r where r.reviewId = :reviewId")
    Optional<Long> findAuthorUserIdById(Long reviewId);

    // 사용자 ID로 작성한 글을 조회하는 메서드
    @Query("select r from Review r where r.users.userId = :userId")
    List<Review> findByUserId(Long userId);

    int countByPharmacyPhId(Long phId); // 리뷰 작성한 수




}