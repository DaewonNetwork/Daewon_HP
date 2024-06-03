package org.daewon.phreview.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.dto.pharmacy.PharmacyInfoDTO;
import org.daewon.phreview.service.PharmacyService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/pharmacy")
public class PharmacyController {
    private final PharmacyService pharmacyService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/read")
    public PharmacyInfoDTO readPharmacy(@RequestParam Long phId) {
        PharmacyInfoDTO pharmacyInfoDTO = pharmacyService.getPharmacyInfo(phId);

        return pharmacyInfoDTO;
    }
}
