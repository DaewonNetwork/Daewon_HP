package org.daewon.phreview.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.dto.page.PageRequestDTO;
import org.daewon.phreview.dto.page.PageResponseDTO;
import org.daewon.phreview.dto.pharmacy.PharmacyDTO;
import org.daewon.phreview.dto.pharmacy.PharmacyEnjoyRankListDTO;
import org.daewon.phreview.dto.pharmacy.PharmacyInfoDTO;
import org.daewon.phreview.dto.pharmacy.PharmacyStarRankListDTO;
import org.daewon.phreview.service.PharmacyService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/pharmacy")
public class PharmacyController {
    private final PharmacyService pharmacyService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/region") // 지역별 검색
    public PageResponseDTO<PharmacyDTO> searchRegionCategory(@RequestParam String city, PageRequestDTO pageRequestDTO){ // 지역 별 검색
        PageResponseDTO<PharmacyDTO> responseDTO = pharmacyService.regionCategorySearch(city,pageRequestDTO);
        return responseDTO;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/near") // 근처 검색
    public PageResponseDTO<PharmacyDTO> searchNear(@RequestParam double lat, @RequestParam double lng, PageRequestDTO pageRequestDTO){ // 내 위치 반경 1.0km 가까운 약국 검색
        PageResponseDTO<PharmacyDTO> responseDTO = pharmacyService.nearSearch(lat,lng,pageRequestDTO);
        return responseDTO;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/region/search") // 지역 내 약국 검색
    public PageResponseDTO<PharmacyDTO> searchNameInCity(@RequestParam String city, @RequestParam String keyword, PageRequestDTO pageRequestDTO) { // 지역 내 병원 이름
        PageResponseDTO<PharmacyDTO> responseDTO = pharmacyService.nameSearchInCity(city,keyword,pageRequestDTO);
        return responseDTO;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/search") // 키워드 약국 검색
    public PageResponseDTO<PharmacyDTO> searchNameOrAdd(@RequestParam String keyword, PageRequestDTO pageRequestDTO) { // 병원 이름이랑 주소 둘다
        PageResponseDTO<PharmacyDTO> responseDTO = pharmacyService.nameOrAddSearch(keyword,pageRequestDTO);
        return responseDTO;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/all") // 전체 약국 검색
    public PageResponseDTO<PharmacyDTO> searchAll(PageRequestDTO pageRequestDTO) {
        PageResponseDTO<PharmacyDTO> responseDTO = pharmacyService.allSearch(pageRequestDTO);

        log.info(responseDTO);
        return responseDTO;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/read") // 약국 정보 가져오기
    public PharmacyInfoDTO readPharmacy(@RequestParam Long phId) {
        PharmacyInfoDTO pharmacyInfoDTO = pharmacyService.getPharmacyInfo(phId);

        return pharmacyInfoDTO;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/rank/enjoy")
    public List<PharmacyEnjoyRankListDTO> PharmaciesListByEnjoyInexDesc() { // 즐겨찾기 수가 높은 병원 내림차순
        List<PharmacyEnjoyRankListDTO> list = pharmacyService.pharmaciesListByEnjoyIndexDesc();
        return list;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/rank/star")
    public List<PharmacyStarRankListDTO> PharmaciesListByStarAvgDesc() { // 즐겨찾기 수가 높은 병원 내림차순
        List<PharmacyStarRankListDTO> list = pharmacyService.reviewsListByStarAvgDesc();
        return list;
    }
}
