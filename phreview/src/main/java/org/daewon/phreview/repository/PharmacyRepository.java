package org.daewon.phreview.repository;

import org.daewon.phreview.domain.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Integer> {

    @Query("select p from Pharmacy p where p.phAdd like concat('%',:city,'%')")
    Optional<Pharmacy> findByCity(String city);

}
