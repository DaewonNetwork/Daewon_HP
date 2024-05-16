package org.daewon.phreview.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.daewon.phreview.dto.PharmacyDTO;
import org.daewon.phreview.service.PharmacyService;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/map")
public class mapController {

    private final PharmacyService pharmacyService

    @GetMapping("/categoryResult")
    public void categoryResult() {
    }


    @GetMapping(value = "/categoryResult2?city={city}")
    public PharmacyDTO getPharmacyDTO(@PathVariable("city") String city){
        PharmacyDTO pharmacyDTO = pharmacyService.cityCategorySearch(city);
        return pharmacyDTO;
    }
    public void categoryResult2() {


    }

    @GetMapping("/nearResult")
    public void nearResult()     {

    }

    @GetMapping("/searchResult")
    public void searchResult() {
    }

    @GetMapping("/home")
    public void home() {
    }

}
