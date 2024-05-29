package org.daewon.phreview.service;

import org.daewon.phreview.dto.PharmacyEnjoyDTO;

import java.util.List;

public interface EnjoyService {
    void enjoyPharmacy(Long phId, Long userId);
    List<PharmacyEnjoyDTO> pharmacyEnjoyRank();
}
