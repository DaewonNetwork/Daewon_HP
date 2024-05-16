package org.daewon.phreview.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.Pharmacy;
import org.daewon.phreview.dto.PharmacyDTO;
import org.daewon.phreview.repository.PharmacyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class PharmacySerivceImpl implements PharmacyService {

    private final PharmacyRepository pharmacyRepository;

    private final ModelMapper modelMapper;

    @Override
    public PharmacyDTO cityCategorySearch(String city) {

        Optional<Pharmacy> result = pharmacyRepository.findByCity(city);

        Pharmacy pharmacy = result.orElseThrow();

        PharmacyDTO pharmacyDTO = modelMapper.map(pharmacy, PharmacyDTO.class);

        return pharmacyDTO;
    }
}
