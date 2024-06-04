package org.daewon.phreview.repository.Pharmacy;

import org.daewon.phreview.domain.PharmacyEnjoy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

// 즐겨찾기
public interface PharmacyEnjoyRepository extends JpaRepository<PharmacyEnjoy, Long> {
    List<PharmacyEnjoy> findAllByOrderByEnjoyIndexDesc();

    @Query("select p from PharmacyEnjoy p where p.pharmacy.phId=:phId")
    Optional<PharmacyEnjoy> findByPhId(Long phId);
}
