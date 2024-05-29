package org.daewon.phreview.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.PharmacyEnjoy;
import org.daewon.phreview.dto.PharmacyEnjoyDTO;
import org.daewon.phreview.repository.PharmacyEnjoyRepository;
import org.daewon.phreview.security.exception.PharmacyNotFoundException;
import org.daewon.phreview.service.EnjoyService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/api/enjoy")
@RequiredArgsConstructor
public class EnjoyController {

    private final EnjoyService enjoyService;
    private final PharmacyEnjoyRepository pharmacyEnjoyRepository;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/")
    public int enjoy(@RequestParam Long phId,@RequestParam Long userId){
        enjoyService.enjoyPharmacy(phId,userId);
        PharmacyEnjoy pharmacyEnjoy = pharmacyEnjoyRepository.findById(phId).orElseThrow(() -> new PharmacyNotFoundException(phId));
        log.info(pharmacyEnjoy.getEnjoyIndex());
        return pharmacyEnjoy.getEnjoyIndex();
    }

    @GetMapping("/list")
    public List<PharmacyEnjoyDTO> enjoyRank(){
        List<PharmacyEnjoyDTO> list = enjoyService.pharmacyEnjoyRank();
        return list;
    }

}
