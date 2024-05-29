package org.daewon.phreview.repository;

import org.daewon.phreview.domain.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MapRepository extends JpaRepository<Pharmacy, Long> {



    @Query("select p from Pharmacy p where p.phAdd like concat('%',:city,'%')")
    List<Pharmacy> findByCity(String city);

    @Query("select p from Pharmacy p where (6371 * acos(cos(radians(:lat)) * cos(radians(p.phY)) * cos(radians(p.phX) - radians(:lng)) + sin(radians(:lat)) * sin(radians(p.phY)))) <= 0.5")
    List<Pharmacy> findByLoc(double lat, double lng);

    @Query("select p from Pharmacy p where p.phAdd like concat('%',:keyword,'%') or p.phName like concat('%',:keyword,'%')")
    List<Pharmacy> findAddOrNameByKeyword(String keyword);


    @Query("select p from Pharmacy p where p.phName like concat('%',:keyword,'%') AND  p.phAdd like concat('%',:city,'%')")
    List<Pharmacy> findNameByCityAndKeyword(String city, String keyword);

}