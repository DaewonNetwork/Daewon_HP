package org.daewon.phreview.repository;

import org.daewon.phreview.domain.EnjoyPh;
import org.daewon.phreview.domain.Pharmacy;
import org.daewon.phreview.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnjoyRepository extends JpaRepository<EnjoyPh, Long> {

    EnjoyPh findByPharmacyAndUser(Pharmacy pharmacy, Users user);
    EnjoyPh findEnjoyPhByPharmacy(Pharmacy pharmacy);
}
