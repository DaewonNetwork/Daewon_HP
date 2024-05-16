package org.daewon.phreview.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.Pharmacy;
import org.daewon.phreview.dto.PharmacyDTO;
import org.daewon.phreview.repository.PharmacyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class PharmacySerivceImpl implements PharmacyService {

    private final PharmacyRepository pharmacyRepository;

    private final ModelMapper modelMapper;


    public List<PharmacyDTO> cityCategorySearch(String city) {
        List<Pharmacy> result = pharmacyRepository.findByCity(city);
        log.info("result:"+result);
        List<PharmacyDTO> pharmacyDTOList = new ArrayList<>();
        for(Pharmacy p : result){ // 3
            PharmacyDTO dto = PharmacyDTO.builder()
                    .phID(p.getPhID())
                    .phName(p.getPhName())
                    .phAdd(p.getPhAdd())
                    .phTel(p.getPhTel())
                    .phX(p.getPhX())
                    .phY(p.getPhY())
                    .enjoyIndex(p.getEnjoyIndex())
                    .starIndex(p.getStarIndex())
                    .timeHoliEndDate(p.getTimeHoliEndDate())
                    .timeHoliStartDate(p.getTimeHoliStartDate())
                    .timeSatEndDate(p.getTimeSatEndDate())
                    .timeSatStartDate(p.getTimeSatStartDate())
                    .timeWeekEndDate(p.getTimeWeekEndDate())
                    .timeWeekStartDate(p.getTimeWeekStartDate())
                    .build();
            pharmacyDTOList.add(dto); // 4
        }
        log.info(pharmacyDTOList);

        return pharmacyDTOList;
    }

    @Override
    public List<PharmacyDTO> nearSearch(double lat, double lng) {
        List<Pharmacy> result = pharmacyRepository.findByLoc(lat,lng);
        log.info("result:"+result);
        log.info("nearSearch");
        List<PharmacyDTO> pharmacyDTOList = new ArrayList<>();
        for(Pharmacy p : result){ // 3
            PharmacyDTO dto = PharmacyDTO.builder()
                    .phID(p.getPhID())
                    .phName(p.getPhName())
                    .phAdd(p.getPhAdd())
                    .phTel(p.getPhTel())
                    .phX(p.getPhX())
                    .phY(p.getPhY())
                    .enjoyIndex(p.getEnjoyIndex())
                    .starIndex(p.getStarIndex())
                    .timeHoliEndDate(p.getTimeHoliEndDate())
                    .timeHoliStartDate(p.getTimeHoliStartDate())
                    .timeSatEndDate(p.getTimeSatEndDate())
                    .timeSatStartDate(p.getTimeSatStartDate())
                    .timeWeekEndDate(p.getTimeWeekEndDate())
                    .timeWeekStartDate(p.getTimeWeekStartDate())
                    .build();
            pharmacyDTOList.add(dto); // 4
        }
        log.info(pharmacyDTOList);

        return pharmacyDTOList;
    }



    @Override
    public List<PharmacyDTO> NameSearch(String keyword) {
        List<Pharmacy> result = pharmacyRepository.findNameByKeyword(keyword);
        log.info("result:"+result);
        log.info("NameSearch");
        List<PharmacyDTO> pharmacyDTOList = new ArrayList<>();
        for(Pharmacy p : result){ // 3
            PharmacyDTO dto = PharmacyDTO.builder()
                    .phID(p.getPhID())
                    .phName(p.getPhName())
                    .phAdd(p.getPhAdd())
                    .phTel(p.getPhTel())
                    .phX(p.getPhX())
                    .phY(p.getPhY())
                    .enjoyIndex(p.getEnjoyIndex())
                    .starIndex(p.getStarIndex())
                    .timeHoliEndDate(p.getTimeHoliEndDate())
                    .timeHoliStartDate(p.getTimeHoliStartDate())
                    .timeSatEndDate(p.getTimeSatEndDate())
                    .timeSatStartDate(p.getTimeSatStartDate())
                    .timeWeekEndDate(p.getTimeWeekEndDate())
                    .timeWeekStartDate(p.getTimeWeekStartDate())
                    .build();
            pharmacyDTOList.add(dto); // 4
        }
        log.info(pharmacyDTOList);

        return pharmacyDTOList;
    }

    @Override
    public List<PharmacyDTO> NameOrAddSearch(String keyword) {
        List<Pharmacy> result = pharmacyRepository.findAddOrNameByKeyword(keyword);
        log.info("result:"+result);
        log.info("NameOrAddSearch");
        List<PharmacyDTO> pharmacyDTOList = new ArrayList<>();
        for(Pharmacy p : result){ // 3
            PharmacyDTO dto = PharmacyDTO.builder()
                    .phID(p.getPhID())
                    .phName(p.getPhName())
                    .phAdd(p.getPhAdd())
                    .phTel(p.getPhTel())
                    .phX(p.getPhX())
                    .phY(p.getPhY())
                    .enjoyIndex(p.getEnjoyIndex())
                    .starIndex(p.getStarIndex())
                    .timeHoliEndDate(p.getTimeHoliEndDate())
                    .timeHoliStartDate(p.getTimeHoliStartDate())
                    .timeSatEndDate(p.getTimeSatEndDate())
                    .timeSatStartDate(p.getTimeSatStartDate())
                    .timeWeekEndDate(p.getTimeWeekEndDate())
                    .timeWeekStartDate(p.getTimeWeekStartDate())
                    .build();
            pharmacyDTOList.add(dto); // 4
        }
        log.info(pharmacyDTOList);

        return pharmacyDTOList;
    }

    @Override
    public List<PharmacyDTO> NameSearchInCity(String keyword, String city) {

        List<Pharmacy> result = pharmacyRepository.findNameByCityAndKeyword(keyword, city);
        log.info("result:"+result);
        log.info("NameSearchInCity");
        List<PharmacyDTO> pharmacyDTOList = new ArrayList<>();
        for(Pharmacy p : result){ // 3
            PharmacyDTO dto = PharmacyDTO.builder()
                    .phID(p.getPhID())
                    .phName(p.getPhName())
                    .phAdd(p.getPhAdd())
                    .phTel(p.getPhTel())
                    .phX(p.getPhX())
                    .phY(p.getPhY())
                    .enjoyIndex(p.getEnjoyIndex())
                    .starIndex(p.getStarIndex())
                    .timeHoliEndDate(p.getTimeHoliEndDate())
                    .timeHoliStartDate(p.getTimeHoliStartDate())
                    .timeSatEndDate(p.getTimeSatEndDate())
                    .timeSatStartDate(p.getTimeSatStartDate())
                    .timeWeekEndDate(p.getTimeWeekEndDate())
                    .timeWeekStartDate(p.getTimeWeekStartDate())
                    .build();
            pharmacyDTOList.add(dto); // 4
        }
        log.info(pharmacyDTOList);

        return pharmacyDTOList;
    }

    @Override
    public PharmacyDTO getPharmacyInfo(Long phID) {

        Optional<Pharmacy> result = pharmacyRepository.findById(phID);
        log.info("결과:"+result);

        Pharmacy pharmacy = result.orElseThrow();

        PharmacyDTO pharmacyDTO = modelMapper.map(pharmacy, PharmacyDTO.class);

        return pharmacyDTO;
    }
}
