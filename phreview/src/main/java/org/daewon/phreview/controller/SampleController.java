package org.daewon.phreview.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.dto.ReviewDTO;
import org.daewon.phreview.service.PharmacyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}