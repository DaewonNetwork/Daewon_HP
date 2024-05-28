package org.daewon.phreview.repository;

import org.daewon.phreview.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from Review r where r.pharmacy.phId = :phId")
    List<Review> listOfPharmacy(Long phId);
}
