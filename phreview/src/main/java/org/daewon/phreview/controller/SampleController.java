package org.daewon.phreview.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.daewon.phreview.dto.PharmacyDTO;

import org.daewon.phreview.service.PharmacyService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/sample")
public class SampleController {

    private final PharmacyService pharmacyService;


    @GetMapping("/home")
    public void home() {
    }

    @GetMapping("/category")
    public void category() {
    }

    @GetMapping("/pharmacyInfo")
    public void pharmacyInfo(Long phId, Model model) {
        PharmacyDTO pharmacyDTO = pharmacyService.getPharmacyInfo(phId);
        log.info("1번째인포:"+pharmacyDTO);

        model.addAttribute("pharmacyDTO", pharmacyDTO);
    }

    @GetMapping("/pharmacyInfo/enjoy")
    public void favoriteBoard(@RequestParam Long phId) {
        pharmacyService.enjoyPharmacy(phId);
    }

}