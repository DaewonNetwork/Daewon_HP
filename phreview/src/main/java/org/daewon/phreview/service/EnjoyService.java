package org.daewon.phreview.service;

import org.daewon.phreview.domain.EnjoyPh;
import org.daewon.phreview.dto.EnjoyPhDTO;
import org.daewon.phreview.dto.PharmacyEnjoyDTO;

import java.util.List;

public interface EnjoyService {
    void enjoyPharmacy(Long phId);
    List<PharmacyEnjoyDTO> getPharmaciesByEnjoyIndexDesc();
    List<EnjoyPhDTO> getUserEnjoyedPharmacies ();
}
