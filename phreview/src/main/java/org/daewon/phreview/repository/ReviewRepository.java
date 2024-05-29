package org.daewon.phreview.repository;

import org.daewon.phreview.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from Review r where r.pharmacy.phId = :phId")
    List<Review> listOfPharmacy(Long phId);

    // 리뷰 작성자의 userId를 반환하도록 하는 메서드
    @Query("select r.users.userId from Review r where r.reviewId = :reviewId")
    Optional<Long> findAuthorUserIdById(Long reviewId);
}