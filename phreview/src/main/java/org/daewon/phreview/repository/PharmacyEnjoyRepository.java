package org.daewon.phreview.repository;

import org.daewon.phreview.domain.PharmacyEnjoy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PharmacyEnjoyRepository extends JpaRepository<PharmacyEnjoy, Long> {
    List<PharmacyEnjoy> findAllByOrderByEnjoyIndexDesc();
}
