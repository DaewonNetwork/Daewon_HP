package org.daewon.phreview.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.dto.Pharmacy.PharmacyDTO;
import org.daewon.phreview.service.MapService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/map")
public class MapController {

    private final MapService mapService;
    @GetMapping("/region")
    public List<PharmacyDTO> searchRegionCategory(String city){ // 지역 별 검색
        List<PharmacyDTO> pharmacyDTO = mapService.regionCategorySearch(city);
        log.info(pharmacyDTO);
        return pharmacyDTO;
    }

    @GetMapping("/near")
    public List<PharmacyDTO> searchNear(double lat, double lng){ // 내 위치 반경 500m 가까운 약국 검색
        log.info("좌표값 :"+lat+","+lng);
        List<PharmacyDTO> pharmacyDTO = mapService.nearSearch(lat,lng);
        log.info(pharmacyDTO);
        return pharmacyDTO;
    }

    @GetMapping("/region/search")
    public List<PharmacyDTO> searchNameInCity(String city, String keyword) { // 지역 내 병원 이름
        List<PharmacyDTO> pharmacyDTO = mapService.NameSearchInCity(keyword,city);
        log.info(pharmacyDTO);
        return pharmacyDTO;
    }

    @GetMapping("/search")
    public List<PharmacyDTO> searchNameOrAdd(String keyword) { // 병원 이름이랑 주소 둘다
        List<PharmacyDTO> pharmacyDTO = mapService.NameOrAddSearch(keyword);
        log.info(pharmacyDTO);
        return pharmacyDTO;
    }

    @GetMapping("/all")
    public List<PharmacyDTO> searchAll() {
        List<PharmacyDTO> pharmacyDTO = mapService.AllSearch();
        log.info(pharmacyDTO);
        return pharmacyDTO;
    }

}