package org.daewon.phreview.repository.Pharmacy;

import org.daewon.phreview.domain.Pharmacy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {

    // 도시명을 기준으로 약국을 검색
    @Query("select p from Pharmacy p where p.phAdd like concat('%',:city,'%')")
    Page<Pharmacy> findByCity(String city, Pageable pageable);

    // 위도(lat), 경도(lng), 페이징 정보를 매개변수로 받아, 해당 조건에 맞는 약국 목록을 페이지 단위로 반환하는 메서드
    @Query("select p from Pharmacy p where (6371 * acos(cos(radians(:lat)) * cos(radians(p.phY)) * cos(radians(p.phX) - radians(:lng)) + sin(radians(:lat)) * sin(radians(p.phY)))) <= 0.5")
    Page<Pharmacy> findByLoc(double lat, double lng, Pageable pageable);

    // 약국명 또는 주소로 검색
    @Query("select p from Pharmacy p where p.phAdd like concat('%',:keyword,'%') or p.phName like concat('%',:keyword,'%')")
    Page<Pharmacy> findAddOrNameByKeyword(String keyword, Pageable pageable);

    // 지역구 선택하고 그 안에서 약국명 검색
    @Query("select p from Pharmacy p where p.phName like concat('%',:keyword,'%') AND  p.phAdd like concat('%',:city,'%')")
    Page<Pharmacy> findNameByCityAndKeyword(String city, String keyword, Pageable pageable);

}