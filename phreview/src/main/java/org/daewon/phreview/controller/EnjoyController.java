package org.daewon.phreview.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.PharmacyEnjoy;
import org.daewon.phreview.dto.pharmacy.PharmacyEnjoyRankListDTO;
import org.daewon.phreview.repository.EnjoyRepository;
import org.daewon.phreview.repository.Pharmacy.PharmacyEnjoyRepository;
import org.daewon.phreview.service.EnjoyService;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final EnjoyRepository enjoyRepository;


    @Operation(summary = "즐겨찾기")
    @PreAuthorize("hasRole('USER')")
    @GetMapping()
    public boolean enjoy(@RequestParam Long phId) {
        enjoyService.enjoyPharmacy(phId);
        PharmacyEnjoy pharmacyEnjoy = pharmacyEnjoyRepository.findByPhId(phId)
                .orElse(null); // 객체가 없을 경우 null 반환
        return pharmacyEnjoy != null; 
    }



    @Operation(summary = "자신이 즐겨찾기한 약국 목록")
    @GetMapping("/list") // 자신이 즐겨찾기한 약국 목록 (즐겨찾기한 순 정렬)
    public List<PharmacyEnjoyRankListDTO> enjoyedPharmaciesList(){ //
        List<PharmacyEnjoyRankListDTO> Pharmacylist = enjoyService.enjoyedPharmaciesListByUser();
        return Pharmacylist;
    }

}
