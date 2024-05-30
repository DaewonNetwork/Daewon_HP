package org.daewon.phreview.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.PharmacyEnjoy;
import org.daewon.phreview.domain.PharmacyStar;
import org.daewon.phreview.dto.PageRequestDTO;
import org.daewon.phreview.dto.PageResponseDTO;
import org.daewon.phreview.dto.PharmacyDTO;
import org.daewon.phreview.repository.PharmacyEnjoyRepository;
import org.daewon.phreview.repository.PharmacyStarRepository;
import org.daewon.phreview.service.EnjoyService;
import org.daewon.phreview.service.PharmacyService;
import org.daewon.phreview.service.ReviewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/pharmacy")
public class PharmacyController {

    private final PharmacyService pharmacyService;
    private final PharmacyStarRepository pharmacyStarRepository;
    private final PharmacyEnjoyRepository pharmacyEnjoyRepository;

    @GetMapping("/region")
    public PageResponseDTO<PharmacyDTO> searchRegionCategory(@RequestParam String city, PageRequestDTO pageRequestDTO){ // 지역 별 검색
        PageResponseDTO<PharmacyDTO> responseDTO = pharmacyService.regionCategorySearch(city,pageRequestDTO);
        return responseDTO;
    }

    @GetMapping("/near")
    public PageResponseDTO<PharmacyDTO> searchNear(@RequestParam double lat, @RequestParam double lng, PageRequestDTO pageRequestDTO){ // 내 위치 반경 500m 가까운 약국 검색
        PageResponseDTO<PharmacyDTO> responseDTO = pharmacyService.nearSearch(lat,lng,pageRequestDTO);
        return responseDTO;
    }

    @GetMapping("/region/search")
    public PageResponseDTO<PharmacyDTO> searchNameInCity(@RequestParam String city, @RequestParam String keyword, PageRequestDTO pageRequestDTO) { // 지역 내 병원 이름
        PageResponseDTO<PharmacyDTO> responseDTO = pharmacyService.nameSearchInCity(city,keyword,pageRequestDTO);
        return responseDTO;
    }

    @GetMapping("/search")
    public PageResponseDTO<PharmacyDTO> searchNameOrAdd(@RequestParam String keyword, PageRequestDTO pageRequestDTO) { // 병원 이름이랑 주소 둘다
        PageResponseDTO<PharmacyDTO> responseDTO = pharmacyService.nameOrAddSearch(keyword,pageRequestDTO);
        return responseDTO;
    }

    @GetMapping("/all")
    public PageResponseDTO<PharmacyDTO> searchAll(PageRequestDTO pageRequestDTO) { // 병원 이름이랑 주소 둘다
        PageResponseDTO<PharmacyDTO> responseDTO = pharmacyService.allSearch(pageRequestDTO);
        return responseDTO;
    }

    @GetMapping("/read")
    public PharmacyDTO readPharmacy(@RequestParam Long phId) {
        PharmacyDTO pharmacyDTO = pharmacyService.getPharmacyInfo(phId);
        Optional<PharmacyStar> pharmacyStar = pharmacyStarRepository.findByPhId(phId);
        Optional<PharmacyEnjoy> pharmacyEnjoy = pharmacyEnjoyRepository.findByPhId(phId);
        pharmacyDTO.setEnjoyIndex(pharmacyEnjoy.get().getEnjoyIndex());
        pharmacyDTO.setStarAvg(pharmacyStar.get().getStarAvg());
        return pharmacyDTO;
    }
}