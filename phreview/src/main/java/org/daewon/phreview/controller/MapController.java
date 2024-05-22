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

import java.util.List;


@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/map")
public class MapController {

    private final PharmacyService pharmacyService;

    @GetMapping("/categoryResult")
    public void getPharmacyInfoByCity(String city, Model model){ // 지역 별 검색
        List<PharmacyDTO> pharmacyDTO = pharmacyService.cityCategorySearch(city);
        log.info(pharmacyDTO);
        model.addAttribute("pharmacyDTO", pharmacyDTO);
    }

    @GetMapping("/nearResult")
    public void nearResult(double lat, double lng, Model model){ // 내 위치 반경 1km 가까운 약국 검색
        log.info("좌표값 :"+lat+","+lng);
        List<PharmacyDTO> pharmacyDTO = pharmacyService.nearSearch(lat,lng);
        log.info(pharmacyDTO);
        model.addAttribute("pharmacyDTO", pharmacyDTO);
    }

    @GetMapping("/search/keyword")
    public ResponseEntity<List<PharmacyDTO>> keywordSearch(String keyword, Model model) { // 병원 이름만
        log.info("키워드:"+keyword);
            List<PharmacyDTO> pharmacyDTO = pharmacyService.NameSearch(keyword);
            log.info(pharmacyDTO);
            model.addAttribute("pharmacyDTO", pharmacyDTO);
        return ResponseEntity.ok(pharmacyDTO);
    }

    @GetMapping("/search/city")
    public ResponseEntity<List<PharmacyDTO>>  citySearch(String city, String keyword, Model model) { // 지역 내 병원 이름
        List<PharmacyDTO> pharmacyDTO = pharmacyService.NameSearchInCity(keyword,city);
        log.info(pharmacyDTO);
        model.addAttribute("pharmacyDTO", pharmacyDTO);
        return ResponseEntity.ok(pharmacyDTO);
    }

    @GetMapping("/search/and")
    public ResponseEntity<List<PharmacyDTO>>  andSearch(String keyword, Model model) { // 병원 이름이랑 주소 둘다
        List<PharmacyDTO> pharmacyDTO = pharmacyService.NameOrAddSearch(keyword);
        log.info(pharmacyDTO);
        model.addAttribute("pharmacyDTO", pharmacyDTO);
        return ResponseEntity.ok(pharmacyDTO);
    }
    
}