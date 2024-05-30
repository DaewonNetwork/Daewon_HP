package org.daewon.phreview.service;

import org.daewon.phreview.dto.*;


public interface PharmacyService {

     PageResponseDTO<PharmacyDTO> regionCategorySearch(String city, PageRequestDTO pageRequestDTO);
     PageResponseDTO<PharmacyDTO> nearSearch(double lat, double lng, PageRequestDTO pageRequestDTO);
//   PageResponseDTO<PharmacyDTO> NameSearch(String keyword);
     PageResponseDTO<PharmacyDTO> nameOrAddSearch(String keyword, PageRequestDTO pageRequestDTO);
     PageResponseDTO<PharmacyDTO> nameSearchInCity(String keyword, String city, PageRequestDTO pageRequestDTO);
     PageResponseDTO<PharmacyDTO> allSearch(PageRequestDTO pageRequestDTO);

    PharmacyInfoDTO getPharmacyInfo(Long phId);

}