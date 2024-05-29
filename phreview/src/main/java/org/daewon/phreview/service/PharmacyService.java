package org.daewon.phreview.service;

import org.daewon.phreview.domain.EnjoyPh;
import org.daewon.phreview.dto.*;


public interface PharmacyService {

     PageResponseDTO<PharmacyDTO> regionCategorySearch(String city, PageRequestDTO pageRequestDTO);
     PageResponseDTO<PharmacyDTO> nearSearch(double lat, double lng, PageRequestDTO pageRequestDTO);
//   PageResponseDTO<PharmacyDTO> NameSearch(String keyword);
     PageResponseDTO<PharmacyDTO> NameOrAddSearch(String keyword, PageRequestDTO pageRequestDTO);
     PageResponseDTO<PharmacyDTO> NameSearchInCity(String keyword, String city, PageRequestDTO pageRequestDTO);
  
    PharmacyDTO getPharmacyInfo(Long phId);

}