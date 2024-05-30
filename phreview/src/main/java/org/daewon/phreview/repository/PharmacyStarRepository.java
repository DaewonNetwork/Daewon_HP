package org.daewon.phreview.repository;
import org.daewon.phreview.domain.PharmacyEnjoy;
import org.daewon.phreview.domain.PharmacyStar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PharmacyStarRepository extends JpaRepository<PharmacyStar, Long> {

    List<PharmacyStar> findAllByOrderByStarAvgDesc();


    @Query("select p from PharmacyStar p where p.pharmacy.phId=:phId")
    Optional<PharmacyStar> findByPhId(Long phId);
}
