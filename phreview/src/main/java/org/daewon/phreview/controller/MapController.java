package org.daewon.phreview.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.dto.PharmacyDTO;
import org.daewon.phreview.service.PharmacyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/map")
public class MapController {

    private final PharmacyService pharmacyService;

    @GetMapping("/region")
    public void searchRegionCategory(String city,Model model){ // 지역 별 검색
        List<PharmacyDTO> pharmacyDTO = pharmacyService.regionCategorySearch(city);
        log.info(pharmacyDTO);
        model.addAttribute("pharmacyDTO", pharmacyDTO);
    }

    @GetMapping("/near")
    public void searchNear(double lat, double lng,Model model){ // 내 위치 반경 500m 가까운 약국 검색
        log.info("좌표값 :"+lat+","+lng);
        List<PharmacyDTO> pharmacyDTO = pharmacyService.nearSearch(lat,lng);
        log.info(pharmacyDTO);
        model.addAttribute("pharmacyDTO", pharmacyDTO);
    }

    @GetMapping("/region/search")
    public void searchNameInCity(String city, String keyword,Model model) { // 지역 내 병원 이름
        List<PharmacyDTO> pharmacyDTO = pharmacyService.NameSearchInCity(keyword,city);
        log.info(pharmacyDTO);
        model.addAttribute("pharmacyDTO", pharmacyDTO);
    }

    @GetMapping("/search")
    public void searchNameOrAdd(String keyword,Model model) { // 병원 이름이랑 주소 둘다
        List<PharmacyDTO> pharmacyDTO = pharmacyService.NameOrAddSearch(keyword);
        log.info(pharmacyDTO);
        model.addAttribute("pharmacyDTO", pharmacyDTO);
    }
    
            return ResponseEntity.ok(pharmacyDTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 내 위치 반경 500m 가까운 약국 검색
    @GetMapping("/search/near")
    public ResponseEntity<List<PharmacyDTO>> nearSearch(@RequestParam double lat, @RequestParam double lng){
        try {
            List<PharmacyDTO> pharmacyDTO = pharmacyService.nearSearch(lat,lng);
            log.info(pharmacyDTO);
    
            return ResponseEntity.ok(pharmacyDTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 지역 내 병원 이름
    @GetMapping("/search/city")
    public ResponseEntity<List<PharmacyDTO>> citySearch(@RequestParam String city, @RequestParam String keyword) {
        try {
            List<PharmacyDTO> pharmacyDTO = pharmacyService.NameSearchInCity(keyword,city);
            log.info(pharmacyDTO);
            
            return ResponseEntity.ok(pharmacyDTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 병원 이름 이거나 주소
    @GetMapping("/search/or")
    public ResponseEntity<List<PharmacyDTO>> searchOr(@RequestParam String keyword) {
        try {
            List<PharmacyDTO> pharmacyDTO = pharmacyService.NameOrAddSearch(keyword);
            log.info(pharmacyDTO);
    
            return ResponseEntity.ok(pharmacyDTO);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}