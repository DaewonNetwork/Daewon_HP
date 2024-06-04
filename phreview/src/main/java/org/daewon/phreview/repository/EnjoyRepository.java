package org.daewon.phreview.repository;

import org.daewon.phreview.domain.EnjoyPh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// 약국 유저 사이에 관계
public interface EnjoyRepository extends JpaRepository<EnjoyPh, Long> {
    @Query(value = "SELECT * from enjoy_ph where ph_id=:phId and user_id=:userId",nativeQuery = true)
    EnjoyPh findByPharmacyAndUsers(Long phId, Long userId); // phId, userId를 통해 EnjoyPh(약국 즐겨찾기) 내 검색

    List<EnjoyPh> findByUsersUserIdOrderByEnjoyIdDesc(Long userId); // userId를 통해 검색후 EnjoyId가 높은순 내림차순(최신순)
}