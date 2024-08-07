package org.daewon.phreview.service;

import org.daewon.phreview.dto.page.PageRequestDTO;
import org.daewon.phreview.dto.page.PageResponseDTO;
import org.daewon.phreview.dto.pharmacy.PharmacyDTO;
import org.daewon.phreview.dto.pharmacy.PharmacyEnjoyRankListDTO;
import org.daewon.phreview.dto.pharmacy.PharmacyInfoDTO;
import org.daewon.phreview.dto.pharmacy.PharmacyStarRankListDTO;

import java.util.List;


public interface PharmacyService {

     PageResponseDTO<PharmacyDTO> regionCategorySearch(String city, PageRequestDTO pageRequestDTO);
     PageResponseDTO<PharmacyDTO> nearSearch(double lat, double lng, PageRequestDTO pageRequestDTO);
//   PageResponseDTO<PharmacyDTO> NameSearch(String keyword);
     PageResponseDTO<PharmacyDTO> nameOrAddSearch(String keyword, PageRequestDTO pageRequestDTO);
     PageResponseDTO<PharmacyDTO> nameSearchInCity(String keyword, String city, PageRequestDTO pageRequestDTO);
     PageResponseDTO<PharmacyDTO> allSearch(PageRequestDTO pageRequestDTO);

     PharmacyInfoDTO getPharmacyInfo(Long phId);

     List<PharmacyEnjoyRankListDTO> pharmaciesListByEnjoyIndexDesc();
     List<PharmacyStarRankListDTO> reviewsListByStarAvgDesc(); // 리뷰 별점 평균
}