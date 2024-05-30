package org.daewon.phreview.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.dto.PharmacyEnjoyDTO;
import org.daewon.phreview.service.EnjoyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@Log4j2
@RequestMapping("/enjoy")
@RequiredArgsConstructor
public class EnjoyPublicController {

    private final EnjoyService enjoyService;

    @GetMapping()
    public List<PharmacyEnjoyDTO> PharmaciesByEnjoyCountDesc() { // 즐겨찾기 수가 높은 병원 내림차순
        List<PharmacyEnjoyDTO> list = enjoyService.getPharmaciesByEnjoyIndexDesc();
        return list;
    }

}
