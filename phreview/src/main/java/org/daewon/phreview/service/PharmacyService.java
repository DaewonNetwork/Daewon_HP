package org.daewon.phreview.service;

import org.daewon.phreview.dto.Page.PageRequestDTO;
import org.daewon.phreview.dto.Page.PageResponseDTO;
import org.daewon.phreview.dto.Pharmacy.PharmacyDTO;
import org.daewon.phreview.dto.Pharmacy.PharmacyEnjoyRankListDTO;
import org.daewon.phreview.dto.Pharmacy.PharmacyInfoDTO;
import org.daewon.phreview.dto.Pharmacy.PharmacyStarRankListDTO;

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