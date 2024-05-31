package org.daewon.phreview.service;

import org.daewon.phreview.dto.Pharmacy.EnjoyPhDTO;
import org.daewon.phreview.dto.Pharmacy.PharmacyEnjoyRankListDTO;

import java.util.List;

public interface EnjoyService {
    void enjoyPharmacy(Long phId);

    List<PharmacyEnjoyRankListDTO> enjoyedPharmaciesListByUser();
}
