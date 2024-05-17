package org.daewon.phreview.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.dto.PharmacyDTO;
import org.daewon.phreview.service.PharmacyService;
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

    @GetMapping("/searchResult")
    public void searchResult(String city, String keyword, Model model) { // 검색
        log.info("지역명:"+city);
        log.info("키워드:"+keyword);
        if(city!=null) { // 키워드로 지역 내 병원 검색
            List<PharmacyDTO> pharmacyDTO = pharmacyService.NameSearchInCity(keyword,city);
            log.info(pharmacyDTO);
            model.addAttribute("pharmacyDTO", pharmacyDTO);
        } else if (keyword.length()==1) { // 키워드가 한 글자일 경우 병원 주소 제외 병원 이름만 검색
            List<PharmacyDTO> pharmacyDTO = pharmacyService.NameSearch(keyword);
            log.info(pharmacyDTO);
            model.addAttribute("pharmacyDTO", pharmacyDTO);
        } else { // 키워드로 병원 주소, 이름 검색
            List<PharmacyDTO> pharmacyDTO = pharmacyService.NameOrAddSearch(keyword);
            log.info(pharmacyDTO);
            model.addAttribute("pharmacyDTO", pharmacyDTO);
        }

    }



}
