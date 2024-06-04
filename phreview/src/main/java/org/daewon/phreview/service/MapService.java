package org.daewon.phreview.service;
import org.daewon.phreview.dto.pharmacy.PharmacyDTO;

import java.util.List;

public interface MapService {

    List<PharmacyDTO> regionCategorySearch(String phAdd);
    List<PharmacyDTO> nearSearch(double lat, double lng);
    List<PharmacyDTO> NameOrAddSearch(String keyword);
    List<PharmacyDTO> NameSearchInCity(String keyword, String city);
    List<PharmacyDTO> AllSearch();
}
