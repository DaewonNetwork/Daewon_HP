package org.daewon.phreview.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.dto.PharmacyDTO;
import org.daewon.phreview.service.PharmacyService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/map")
public class MapController {

    private final PharmacyService pharmacyService;

    @GetMapping("/search/category")
    public ResponseEntity<List<PharmacyDTO>> searchCategory(String city){ // 지역 별 검색
        List<PharmacyDTO> pharmacyDTO = pharmacyService.cityCategorySearch(city);
        log.info(pharmacyDTO);
        return ResponseEntity.ok(pharmacyDTO);
    }

    @GetMapping("/search/near")
    public ResponseEntity<List<PharmacyDTO>> searchNear(double lat, double lng){ // 내 위치 반경 500m 가까운 약국 검색
        log.info("좌표값 :"+lat+","+lng);
        List<PharmacyDTO> pharmacyDTO = pharmacyService.nearSearch(lat,lng);
        log.info(pharmacyDTO);

        return ResponseEntity.ok(pharmacyDTO);
    }

    @GetMapping("/search/keyword")
    public ResponseEntity<List<PharmacyDTO>> searchKeyword(String keyword) { // 병원 이름만
        log.info("키워드:"+keyword);
            List<PharmacyDTO> pharmacyDTO = pharmacyService.NameSearch(keyword);
            log.info(pharmacyDTO);

        return ResponseEntity.ok(pharmacyDTO);
    }

    @GetMapping("/search/city")
    public ResponseEntity<List<PharmacyDTO>>  searchCity(String city, String keyword) { // 지역 내 병원 이름
        List<PharmacyDTO> pharmacyDTO = pharmacyService.NameSearchInCity(keyword,city);
        log.info(pharmacyDTO);
        return ResponseEntity.ok(pharmacyDTO);
    }

    @GetMapping("/search/or")
    public ResponseEntity<List<PharmacyDTO>> searchOr(String keyword) { // 병원 이름이랑 주소 둘다
        List<PharmacyDTO> pharmacyDTO = pharmacyService.NameOrAddSearch(keyword);
        log.info(pharmacyDTO);
        return ResponseEntity.ok(pharmacyDTO);
    }
    
}