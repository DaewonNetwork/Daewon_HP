package org.daewon.phreview.service;

import org.daewon.phreview.domain.EnjoyPh;
import org.daewon.phreview.dto.EnjoyPhDTO;
import org.daewon.phreview.dto.PharmacyDTO;
import org.daewon.phreview.dto.ReplyDTO;

import java.util.List;
import java.util.Optional;

public interface PharmacyService {

    List<PharmacyDTO> regionCategorySearch(String phAdd);
    List<PharmacyDTO> nearSearch(double lat, double lng);
//    List<PharmacyDTO> NameSearch(String keyword);
    List<PharmacyDTO> NameOrAddSearch(String keyword);
    List<PharmacyDTO> NameSearchInCity(String keyword, String city);

    PharmacyDTO getPharmacyInfo(Long phId);

    void enjoyPharmacy(Long phId, Long userId);
}