package org.daewon.phreview.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.Pharmacy;
import org.daewon.phreview.dto.pharmacy.PharmacyDTO;
import org.daewon.phreview.repository.MapRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class MapServiceImpl implements MapService {

    private final MapRepository mapRepository;



    private final ModelMapper modelMapper;

    public List<PharmacyDTO> regionCategorySearch(String city) {
        List<Pharmacy> result = mapRepository.findByCity(city);
        List<PharmacyDTO> pharmacyDTOList = new ArrayList<>();
        for(Pharmacy p : result){
            PharmacyDTO dto = PharmacyDTO.builder()
                    .phId(p.getPhId())
                    .phName(p.getPhName())
                    .phAdd(p.getPhAdd())
                    .phTel(p.getPhTel())
                    .phX(p.getPhX())
                    .phY(p.getPhY())
                    .build();
            pharmacyDTOList.add(dto);

        }
        return pharmacyDTOList;
    }

    @Override
    public List<PharmacyDTO> nearSearch(double lat, double lng) {
        List<Pharmacy> result = mapRepository.findByLoc(lat,lng);

        List<PharmacyDTO> pharmacyDTOList = new ArrayList<>();
        for(Pharmacy p : result){
            PharmacyDTO dto = PharmacyDTO.builder()
                    .phId(p.getPhId())
                    .phName(p.getPhName())
                    .phAdd(p.getPhAdd())
                    .phTel(p.getPhTel())
                    .phX(p.getPhX())
                    .phY(p.getPhY())
                    .build();
            pharmacyDTOList.add(dto);
        }


        return pharmacyDTOList;
    }

    @Override
    public List<PharmacyDTO> NameOrAddSearch(String keyword) {
        List<Pharmacy> result = mapRepository.findAddOrNameByKeyword(keyword);
        log.info("nameOrAdd");
        List<PharmacyDTO> pharmacyDTOList = new ArrayList<>();
        for(Pharmacy p : result){
            PharmacyDTO dto = PharmacyDTO.builder()
                    .phId(p.getPhId())
                    .phName(p.getPhName())
                    .phAdd(p.getPhAdd())
                    .phTel(p.getPhTel())
                    .phX(p.getPhX())
                    .phY(p.getPhY())
                    .build();
            pharmacyDTOList.add(dto);
        }


        return pharmacyDTOList;
    }

    @Override
    public List<PharmacyDTO> NameSearchInCity(String city,String keyword) {
        log.info("nameInCity");
        List<Pharmacy> result = mapRepository.findNameByCityAndKeyword(keyword, city);
        List<PharmacyDTO> pharmacyDTOList = new ArrayList<>();
        for(Pharmacy p : result){
            PharmacyDTO dto = PharmacyDTO.builder()
                    .phId(p.getPhId())
                    .phName(p.getPhName())
                    .phAdd(p.getPhAdd())
                    .phTel(p.getPhTel())
                    .phX(p.getPhX())
                    .phY(p.getPhY())
                    .build();
            pharmacyDTOList.add(dto); // 4
        }


        return pharmacyDTOList;
    }


    @Override
    public List<PharmacyDTO> AllSearch(){

        List<Pharmacy> result = mapRepository.findAll();
        log.info(result);
        List<PharmacyDTO> pharmacyDTOList = new ArrayList<>();
        for(Pharmacy p : result){
            PharmacyDTO dto = PharmacyDTO.builder()
                    .phId(p.getPhId())
                    .phName(p.getPhName())
                    .phAdd(p.getPhAdd())
                    .phTel(p.getPhTel())
                    .phX(p.getPhX())
                    .phY(p.getPhY())
                    .build();
            pharmacyDTOList.add(dto); // 4
        }
        return pharmacyDTOList;
    }

}