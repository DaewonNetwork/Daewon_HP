package org.daewon.phreview.repository;

import org.daewon.phreview.domain.EnjoyPh;
import org.daewon.phreview.dto.pharmacy.EnjoyPhDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// 약국 유저 사이에 관계
public interface EnjoyRepository extends JpaRepository<EnjoyPh, Long> {
    @Query(value = "SELECT * from enjoy_ph where ph_id=:phId and user_id=:userId",nativeQuery = true)
    EnjoyPh findByPharmacyAndUsers(Long phId, Long userId);

    List<EnjoyPh> findByUsersUserIdOrderByEnjoyIdDesc(Long userId);
}