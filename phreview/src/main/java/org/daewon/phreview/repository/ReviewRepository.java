package org.daewon.phreview.repository;

import org.daewon.phreview.domain.Pharmacy;
import org.daewon.phreview.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from Review r where r.pharmacy.phId = :phId")
    Page<Review> listOfPharmacy(Long phId, Pageable pageable);
}
