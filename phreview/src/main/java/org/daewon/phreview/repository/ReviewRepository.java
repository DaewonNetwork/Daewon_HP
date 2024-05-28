package org.daewon.phreview.repository;

import org.daewon.phreview.domain.Pharmacy;
import org.daewon.phreview.domain.Review;
import org.daewon.phreview.domain.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from Review r where r.pharmacy.phId = :phId")
    Page<Review> listOfPharmacy(Long phId, Pageable pageable);

    @Query("select r.users.userId from Review r where r.reviewId = :reviewId")
    Optional<Long> findAuthorUserIdById(Long reviewId);
}
