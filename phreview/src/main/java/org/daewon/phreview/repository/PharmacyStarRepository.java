package org.daewon.phreview.repository;
import org.daewon.phreview.domain.PharmacyStar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PharmacyStarRepository extends JpaRepository<PharmacyStar, Long> {

    List<PharmacyStar> findAllByOrderByStarAvgDesc();


}
