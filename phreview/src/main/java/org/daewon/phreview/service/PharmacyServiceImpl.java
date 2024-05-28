package org.daewon.phreview.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.EnjoyPh;
import org.daewon.phreview.domain.Pharmacy;
import org.daewon.phreview.domain.Reply;
import org.daewon.phreview.domain.Users;
import org.daewon.phreview.dto.EnjoyPhDTO;
import org.daewon.phreview.dto.PharmacyDTO;
import org.daewon.phreview.repository.EnjoyRepository;
import org.daewon.phreview.repository.PharmacyRepository;
import org.daewon.phreview.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class PharmacyServiceImpl implements PharmacyService {

    private final PharmacyRepository pharmacyRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final EnjoyRepository enjoyRepository;


    public List<PharmacyDTO> regionCategorySearch(String city) {
        List<Pharmacy> result = pharmacyRepository.findByCity(city);
        List<PharmacyDTO> pharmacyDTOList = new ArrayList<>();
        for(Pharmacy p : result){
            PharmacyDTO dto = PharmacyDTO.builder()
                    .phId(p.getPhId())
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
            pharmacyDTOList.add(dto);

        }
        return pharmacyDTOList;
    }

    @Override
    public List<PharmacyDTO> nearSearch(double lat, double lng) {
        List<Pharmacy> result = pharmacyRepository.findByLoc(lat,lng);

        List<PharmacyDTO> pharmacyDTOList = new ArrayList<>();
        for(Pharmacy p : result){
            PharmacyDTO dto = PharmacyDTO.builder()
                    .phId(p.getPhId())
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
            pharmacyDTOList.add(dto);
        }


        return pharmacyDTOList;
    }



//    @Override
//    public List<PharmacyDTO> NameSearch(String keyword) {
//        List<Pharmacy> result = pharmacyRepository.findNameByKeyword(keyword);
//        log.info("name");
//        List<PharmacyDTO> pharmacyDTOList = new ArrayList<>();
//        for(Pharmacy p : result){
//            PharmacyDTO dto = PharmacyDTO.builder()
//                    .phId(p.getPhId())
//                    .phName(p.getPhName())
//                    .phAdd(p.getPhAdd())
//                    .phTel(p.getPhTel())
//                    .phX(p.getPhX())
//                    .phY(p.getPhY())
//                    .enjoyIndex(p.getEnjoyIndex())
//                    .starIndex(p.getStarIndex())
//                    .timeHoliEndDate(p.getTimeHoliEndDate())
//                    .timeHoliStartDate(p.getTimeHoliStartDate())
//                    .timeSatEndDate(p.getTimeSatEndDate())
//                    .timeSatStartDate(p.getTimeSatStartDate())
//                    .timeWeekEndDate(p.getTimeWeekEndDate())
//                    .timeWeekStartDate(p.getTimeWeekStartDate())
//                    .phPageIndex(p.getPhPageIndex())
//                    .phPageTotal(p.getPhPageTotal())
//                    .build();
//            pharmacyDTOList.add(dto);
//        }
//
//
//        return pharmacyDTOList;
//    }

    @Override
    public List<PharmacyDTO> NameOrAddSearch(String keyword) {
        List<Pharmacy> result = pharmacyRepository.findAddOrNameByKeyword(keyword);
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
                    .enjoyIndex(p.getEnjoyIndex())
                    .starIndex(p.getStarIndex())
                    .timeHoliEndDate(p.getTimeHoliEndDate())
                    .timeHoliStartDate(p.getTimeHoliStartDate())
                    .timeSatEndDate(p.getTimeSatEndDate())
                    .timeSatStartDate(p.getTimeSatStartDate())
                    .timeWeekEndDate(p.getTimeWeekEndDate())
                    .timeWeekStartDate(p.getTimeWeekStartDate())
                    .build();
            pharmacyDTOList.add(dto);
        }


        return pharmacyDTOList;
    }

    @Override
    public List<PharmacyDTO> NameSearchInCity(String city,String keyword) {
        log.info("nameInCity");
        List<Pharmacy> result = pharmacyRepository.findNameByCityAndKeyword(keyword, city);
        List<PharmacyDTO> pharmacyDTOList = new ArrayList<>();
        for(Pharmacy p : result){
            PharmacyDTO dto = PharmacyDTO.builder()
                    .phId(p.getPhId())
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


        return pharmacyDTOList;
    }

    @Override
    public PharmacyDTO getPharmacyInfo(Long phId) {

        Optional<Pharmacy> result = pharmacyRepository.findById(phId);
        Pharmacy pharmacy = result.orElseThrow(() -> new EntityNotFoundException("약국을 찾을 수 없습니다. ID: " + phId));

        PharmacyDTO pharmacyDTO = modelMapper.map(pharmacy, PharmacyDTO.class);

        return pharmacyDTO;
    }


    @Override
    public void enjoyPharmacy(Long phId, Long userId) {

        Pharmacy pharmacy = pharmacyRepository.findById(phId).orElseThrow();
        Users users = userRepository.findById(userId).orElseThrow();

        if(enjoyRepository.findByPharmacyAndUsers(phId,userId) == null){
            pharmacy.setEnjoyIndex(pharmacy.getEnjoyIndex()+1);
            pharmacyRepository.save(pharmacy);
            EnjoyPh enjoyPh = new EnjoyPh(pharmacy,users);
            enjoyRepository.save(enjoyPh);
            log.info(pharmacy);
            log.info(enjoyPh);
            log.info("즐겨찾기");
        } else{
            EnjoyPh enjoyPh = enjoyRepository.findByPharmacyAndUsers(phId,userId);
            pharmacy.setEnjoyIndex(pharmacy.getEnjoyIndex()-1);
            pharmacyRepository.save(pharmacy);
            enjoyPh.unEnjoyPh(pharmacy);
            enjoyRepository.delete(enjoyPh);
            log.info(pharmacy);
            log.info(enjoyPh);

            log.info("즐겨찾기 취소");
        }


    }
}