package org.daewon.phreview.repository.Pharmacy;

import org.daewon.phreview.domain.Pharmacy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
    @Query("select p from Pharmacy p where p.phAdd like concat('%',:city,'%')")
    Page<Pharmacy> findByCity(String city, Pageable pageable);

    @Query("select p from Pharmacy p where (6371 * acos(cos(radians(:lat)) * cos(radians(p.phY)) * cos(radians(p.phX) - radians(:lng)) + sin(radians(:lat)) * sin(radians(p.phY)))) <= 0.5")
    Page<Pharmacy> findByLoc(double lat, double lng, Pageable pageable);


    @Query("select p from Pharmacy p where p.phAdd like concat('%',:keyword,'%') or p.phName like concat('%',:keyword,'%')")
    Page<Pharmacy> findAddOrNameByKeyword(String keyword, Pageable pageable);

    @Query("select p from Pharmacy p where p.phName like concat('%',:keyword,'%') AND  p.phAdd like concat('%',:city,'%')")
    Page<Pharmacy> findNameByCityAndKeyword(String city, String keyword, Pageable pageable);

}