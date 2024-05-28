package org.daewon.phreview.service;

import lombok.extern.log4j.Log4j2;

import org.daewon.phreview.domain.Pharmacy;
import org.daewon.phreview.domain.Users;
import org.daewon.phreview.repository.EnjoyRepository;
import org.daewon.phreview.repository.PharmacyRepository;
import org.daewon.phreview.repository.UserRepository;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Log4j2
public class EnjoyPharmacyServiceTests {

    @Autowired
    private PharmacyRepository pharmacyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EnjoyRepository enjoyRepository;

    @Autowired
    private PharmacyService pharmacyService;


    @Test
    public void testEnjoyPharmacy(){
        pharmacyService.enjoyPharmacy(1L,1L);

    }


}
