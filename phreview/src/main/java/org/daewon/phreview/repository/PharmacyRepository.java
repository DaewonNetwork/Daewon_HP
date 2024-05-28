package org.daewon.phreview.repository;

import org.daewon.phreview.domain.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {



    @Query(value = "SELECT ph_id,enjoy_index,ph_add,ph_name,ph_tel,phx,phy,star_index,time_holi_end_date,time_holi_start_date,time_sat_end_date,time_sat_start_date,time_week_end_date,time_week_start_date,CEIL((SELECT COUNT(*) FROM pharmacy p2 WHERE p2.ph_id <= p1.ph_id AND p2.ph_add LIKE concat('%',:city,'%'))/10.0) AS ph_page_index, CEIL((SELECT COUNT(*) FROM pharmacy p2 WHERE p2.ph_add LIKE concat('%',:city,'%'))/10) AS ph_page_total FROM pharmacy p1 WHERE ph_add LIKE concat('%',:city,'%')",nativeQuery = true)
    List<Pharmacy> findByCity(String city);

//    @Query("select p, (6371 * acos(cos(radians(:lat)) * cos(radians(p.phY)) * cos(radians(p.phX) - radians(:lng)) + sin(radians(:lat)) * sin(radians(p.phY)))) from Pharmacy p where (6371 * acos(cos(radians(:lat)) * cos(radians(p.phY)) * cos(radians(p.phX) - radians(:lng)) + sin(radians(:lat)) * sin(radians(p.phY)))) <= 1")
//    List<Pharmacy> findByLoc(double lat, double lng);

    @Query(value = "SELECT ph_id,enjoy_index,ph_add,ph_name,ph_tel,phx,phy,star_index,time_holi_end_date,time_holi_start_date,time_sat_end_date ,time_sat_start_date,time_week_end_date,time_week_start_date, CEIL((SELECT COUNT(*) FROM pharmacy p2 WHERE p2.ph_id <= p1.ph_id AND (6371 * acos(cos(radians(:lat)) * cos(radians(phY)) * cos(radians(phX) - radians(:lng)) + sin(radians(:lat)) * sin(radians(phY)))<=0.5))/10.0) as ph_page_index, CEIL((SELECT COUNT(*) FROM pharmacy p2 WHERE (6371 * acos(cos(radians(:lat)) * cos(radians(phY)) * cos(radians(phX) - radians(:lng)) + sin(radians(:lat)) * sin(radians(phY)))<=0.5))/10) as ph_page_total, (6371*acos(cos(radians(:lat))*cos(radians(phY))*cos(radians(phX)-radians(:lng))+sin(radians(:lat))*sin(radians(phY)))) AS distance FROM pharmacy p1 HAVING distance <= 0.5",nativeQuery = true)
    List<Pharmacy> findByLoc(double lat, double lng);

    @Query(value = "SELECT ph_id,enjoy_index,ph_add,ph_name,ph_tel,phx,phy,star_index,time_holi_end_date,time_holi_start_date,time_sat_end_date,time_sat_start_date,time_week_end_date,time_week_start_date,CEIL((SELECT COUNT(*) FROM pharmacy p2 WHERE p2.ph_id <= p1.ph_id AND ph_name like concat('%',:keyword,'%'))/10.0) AS ph_page_index, CEIL((SELECT COUNT(*) FROM pharmacy p2 WHERE ph_name like concat('%',:keyword,'%'))/10) AS ph_page_total FROM pharmacy p1 where ph_name like concat('%',:keyword,'%')",nativeQuery = true)
    List<Pharmacy> findNameByKeyword(String keyword);

    @Query(value = "SELECT ph_id,enjoy_index,ph_add,ph_name,ph_tel,phx,phy,star_index,time_holi_end_date,time_holi_start_date,time_sat_end_date,time_sat_start_date,time_week_end_date,time_week_start_date,CEIL((SELECT COUNT(*) FROM pharmacy p2 WHERE p2.ph_id <= p1.ph_id AND p2.ph_add LIKE concat('%',:keyword,'%') or ph_name like concat('%',:keyword,'%'))/10.0) AS ph_page_index, CEIL((SELECT COUNT(*) FROM pharmacy p2 WHERE ph_add like concat('%',:keyword,'%') or ph_name like concat('%',:keyword,'%'))/10) AS ph_page_total FROM pharmacy p1 where ph_add like concat('%',:keyword,'%') or ph_name like concat('%',:keyword,'%')",nativeQuery = true)
    List<Pharmacy> findAddOrNameByKeyword(String keyword);

    @Query(value = "SELECT ph_id,enjoy_index,ph_add,ph_name,ph_tel,phx,phy,star_index,time_holi_end_date,time_holi_start_date,time_sat_end_date,time_sat_start_date,time_week_end_date,time_week_start_date,CEIL((SELECT COUNT(*) FROM pharmacy p2 WHERE p2.ph_id <= p1.ph_id AND p2.ph_name LIKE concat('%',:keyword,'%') AND ph_add like concat('%',:city,'%'))/10.0) AS ph_page_index, CEIL((SELECT COUNT(*) FROM pharmacy p2 WHERE p2.ph_name LIKE concat('%',:keyword,'%') AND ph_add like concat('%',:city,'%'))/10.0) AS ph_page_total FROM pharmacy p1 where ph_name like concat('%',:keyword,'%') AND ph_add like concat('%',:city,'%')",nativeQuery = true)
    List<Pharmacy> findNameByCityAndKeyword(String city, String keyword);

}