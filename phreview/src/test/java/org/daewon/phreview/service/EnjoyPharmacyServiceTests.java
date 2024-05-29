package org.daewon.phreview.service;

import lombok.extern.log4j.Log4j2;

import org.daewon.phreview.domain.Pharmacy;
import org.daewon.phreview.domain.PharmacyEnjoy;
import org.daewon.phreview.domain.Users;
import org.daewon.phreview.dto.EnjoyPhDTO;
import org.daewon.phreview.dto.PharmacyEnjoyDTO;
import org.daewon.phreview.repository.EnjoyRepository;
import org.daewon.phreview.repository.PharmacyRepository;
import org.daewon.phreview.repository.UserRepository;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


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
    private EnjoyService enjoyService;


    @Test
    public void testEnjoyPharmacy(){

    }


    @Test
    public void testGetEnjoyPharmacyList(){

       List<PharmacyEnjoyDTO> list = enjoyService.getPharmaciesByEnjoyIndexDesc();
       log.info(list);

    }

    @Test
    public void testGetPharmacyEnjoyList(){
        List<EnjoyPhDTO> list = enjoyService.getUserEnjoyedPharmacies();
        log.info(list);

    }

}
