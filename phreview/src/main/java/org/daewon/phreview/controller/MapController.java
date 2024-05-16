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
    public void getPharmacyInfoByCity(String city, Model model){
        List<PharmacyDTO> pharmacyDTO = pharmacyService.cityCategorySearch(city);
        log.info(pharmacyDTO);
        model.addAttribute("pharmacyDTO", pharmacyDTO);
    }

    @GetMapping("/nearResult")
    public void nearResult(double lat, double lng, Model model){
        log.info("좌표값 :"+lat+","+lng);
        List<PharmacyDTO> pharmacyDTO = pharmacyService.nearSearch(lat,lng);
        log.info(pharmacyDTO);
        model.addAttribute("pharmacyDTO", pharmacyDTO);
    }

    @GetMapping("/searchResult2")
    public void searchResult(String keyword, String city, Model model) {
        log.info("지역명:"+city);
        log.info("키워드:"+keyword);

        if(city!=null) {
            List<PharmacyDTO> pharmacyDTO = pharmacyService.NameSearchInCity(keyword,city);
            log.info(pharmacyDTO);
            model.addAttribute("pharmacyDTO", pharmacyDTO);
        } else if (keyword.length()==1) {
            List<PharmacyDTO> pharmacyDTO = pharmacyService.NameSearch(keyword);
            log.info(pharmacyDTO);
            model.addAttribute("pharmacyDTO", pharmacyDTO);
        } else {
            List<PharmacyDTO> pharmacyDTO = pharmacyService.NameOrAddSearch(keyword);
            log.info(pharmacyDTO);
            model.addAttribute("pharmacyDTO", pharmacyDTO);
        }

    }



}
