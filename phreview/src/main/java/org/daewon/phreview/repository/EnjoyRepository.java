package org.daewon.phreview.repository;

import org.daewon.phreview.domain.EnjoyPh;
import org.daewon.phreview.domain.Pharmacy;
import org.daewon.phreview.domain.PharmacyEnjoy;
import org.daewon.phreview.domain.Users;
import org.daewon.phreview.dto.EnjoyPhDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EnjoyRepository extends JpaRepository<EnjoyPh, Long> {
    @Query(value = "SELECT * from enjoy_ph where ph_id=:phId and user_id=:userId",nativeQuery = true)
    EnjoyPh findByPharmacyAndUsers(Long phId, Long userId);

    List<EnjoyPh> findByUsersUserIdOrderByEnjoyIdDesc(Long userId);

    @Query(value = "SELECT is_enjoy from enjoy_ph where ph_id=:phId and user_id=:userId",nativeQuery = true)
    EnjoyPhDTO findIsEnjoyByPharmacyAndUsers(Long phId, Long userId);
}