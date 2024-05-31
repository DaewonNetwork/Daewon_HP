package org.daewon.phreview.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.dto.Page.PageRequestDTO;
import org.daewon.phreview.dto.Page.PageResponseDTO;
import org.daewon.phreview.dto.Pharmacy.PharmacyDTO;
import org.daewon.phreview.dto.Pharmacy.PharmacyEnjoyRankListDTO;
import org.daewon.phreview.dto.Pharmacy.PharmacyInfoDTO;
import org.daewon.phreview.dto.Pharmacy.PharmacyStarRankListDTO;
import org.daewon.phreview.repository.*;
import org.daewon.phreview.repository.Pharmacy.PharmacyEnjoyRepository;
import org.daewon.phreview.repository.Pharmacy.PharmacyStarRepository;
import org.daewon.phreview.service.PharmacyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/pharmacy")
public class PharmacyController {

    private final PharmacyService pharmacyService;
    private final PharmacyStarRepository pharmacyStarRepository;
    private final PharmacyEnjoyRepository pharmacyEnjoyRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final EnjoyRepository enjoyRepository;

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
    public PharmacyInfoDTO readPharmacy(@RequestParam Long phId) {
        PharmacyInfoDTO pharmacyInfoDTO = pharmacyService.getPharmacyInfo(phId);

        return pharmacyInfoDTO;
    }

    @GetMapping("/list/EnjoyIndex")
    public List<PharmacyEnjoyRankListDTO> PharmaciesListByEnjoyInexDesc() { // 즐겨찾기 수가 높은 병원 내림차순
        List<PharmacyEnjoyRankListDTO> list = pharmacyService.pharmaciesListByEnjoyIndexDesc();
        return list;
    }

    @GetMapping("/list/StarAvg")
    public List<PharmacyStarRankListDTO> PharmaciesListByStarAvgDesc() { // 즐겨찾기 수가 높은 병원 내림차순
        List<PharmacyStarRankListDTO> list = pharmacyService.reviewsListByStarAvgDesc();
        return list;
    }
}