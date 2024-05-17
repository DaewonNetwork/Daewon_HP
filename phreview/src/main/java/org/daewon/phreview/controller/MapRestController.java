package org.daewon.phreview.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.dto.PharmacyDTO;
import org.daewon.phreview.service.PharmacyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
public class MapRestController {
    private final PharmacyService pharmacyService;

    @GetMapping("/api/map/category")
    public ResponseEntity<List<PharmacyDTO>> getPharmacyInfoByCity(@RequestParam String city) {
        List<PharmacyDTO> pharmacyDTO = pharmacyService.cityCategorySearch(city);
        log.info(pharmacyDTO);
        return new ResponseEntity<>(pharmacyDTO, HttpStatus.OK);
    }

    @GetMapping("/api/map/near")
    public ResponseEntity<List<PharmacyDTO>> nearResult(@RequestParam double lat, @RequestParam double lng) {
        log.info("좌표값 :" + lat + "," + lng);
        List<PharmacyDTO> pharmacyDTO = pharmacyService.nearSearch(lat, lng);
        log.info(pharmacyDTO);
        return new ResponseEntity<>(pharmacyDTO, HttpStatus.OK);
    }

    @GetMapping("/api/map/search")
    public ResponseEntity<List<PharmacyDTO>> searchResult(@RequestParam(required = false) String city,
            @RequestParam String keyword) {
        log.info("지역명:" + city);
        log.info("키워드:" + keyword);
        List<PharmacyDTO> pharmacyDTO;
        if (city != null) {
            pharmacyDTO = pharmacyService.NameSearchInCity(keyword, city);
        } else if (keyword.length() == 1) {
            pharmacyDTO = pharmacyService.NameSearch(keyword);
        } else {
            pharmacyDTO = pharmacyService.NameOrAddSearch(keyword);
        }
        log.info(pharmacyDTO);
        return new ResponseEntity<>(pharmacyDTO, HttpStatus.OK);
    }
}