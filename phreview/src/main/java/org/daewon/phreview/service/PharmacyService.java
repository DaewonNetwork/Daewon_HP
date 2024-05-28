package org.daewon.phreview.service;

import org.daewon.phreview.dto.PharmacyDTO;

import java.util.List;

public interface PharmacyService {

    List<PharmacyDTO> cityCategorySearch(String phAdd);
    PharmacyDTO getPharmacyInfo(Long phId);
    List<PharmacyDTO> nearSearch(double lat, double lng);


    List<PharmacyDTO> NameSearch(String keyword);
    List<PharmacyDTO> NameOrAddSearch(String keyword);
    List<PharmacyDTO> NameSearchInCity(String keyword, String city);
}