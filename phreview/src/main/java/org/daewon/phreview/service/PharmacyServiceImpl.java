package org.daewon.phreview.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.*;
import org.daewon.phreview.dto.PageRequestDTO;
import org.daewon.phreview.dto.PageResponseDTO;
import org.daewon.phreview.dto.PharmacyDTO;
import org.daewon.phreview.dto.PharmacyInfoDTO;
import org.daewon.phreview.repository.*;
import org.daewon.phreview.security.exception.PharmacyNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class PharmacyServiceImpl implements PharmacyService {

    private final PharmacyRepository pharmacyRepository;
    private final PharmacyEnjoyRepository pharmacyEnjoyRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final EnjoyRepository enjoyRepository;
    private final PharmacyStarRepository pharmacyStarRepository;
    private final ReviewRepository reviewRepository;

    public PageResponseDTO<PharmacyDTO> regionCategorySearch(String city, PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPageIndex() <= 0 ? 0 : pageRequestDTO.getPageIndex() - 1,
                pageRequestDTO.getSize(),
                Sort.by("phId").ascending());
        Page<Pharmacy> result = pharmacyRepository.findByCity(city, pageable);
        log.info(result);

        List<PharmacyDTO> phList = result.getContent().stream().map(pharmacy -> {
            PharmacyDTO pharmacyDTO = modelMapper.map(pharmacy, PharmacyDTO.class);
            return pharmacyDTO;
        }).collect(Collectors.toList());
        log.info(phList);
        return PageResponseDTO.<PharmacyDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .phList(phList)
                .totalIndex((int) result.getTotalElements())
                .build();
    }

    // @Override
    // public PageResponseDTO<PharmacyDTO> nearSearch(double lat, double lng,
    // PageRequestDTO pageRequestDTO) {
    // Pageable pageable = PageRequest.of(
    // pageRequestDTO.getPage() <= 0 ? 0: pageRequestDTO.getPage() - 1,
    // pageRequestDTO.getSize(),
    // Sort.by("phId").ascending());
    // Page<PharmacyDTO> result = pharmacyRepository.findByLoc(lat,lng,pageable);
    // // log.info(result); // 필요한 경우에만 로그를 남기는 것이 좋습니다.
    //
    // return PageResponseDTO.<PharmacyDTO>withAll()
    // .pageRequestDTO(pageRequestDTO)
    // .dtoList(result.getContent()) // 변경된 부분
    // .total((int)result.getTotalElements())
    // .build();
    // }

    @Override
    public PageResponseDTO<PharmacyDTO> nearSearch(double lat, double lng, PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPageIndex() <= 0 ? 0 : pageRequestDTO.getPageIndex() - 1,
                pageRequestDTO.getSize(),
                Sort.by("phId").ascending());
        Page<Pharmacy> result = pharmacyRepository.findByLoc(lat, lng, pageable); // 변경된 부분
        log.info(result);
        List<PharmacyDTO> phList = result.getContent().stream().map(pharmacy -> {
            PharmacyDTO pharmacyDTO = modelMapper.map(pharmacy, PharmacyDTO.class);
            return pharmacyDTO;
        }).collect(Collectors.toList());
        log.info(phList);
        return PageResponseDTO.<PharmacyDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .phList(phList)
                .totalIndex((int) result.getTotalElements())
                .build();
    }

    // @Override
    // public PageResponseDTO<PharmacyDTO> NameSearch(String keyword) {
    // List<Pharmacy> result = pharmacyRepository.findNameByKeyword(keyword);
    // log.info("name");
    // PageResponseDTO<PharmacyDTO> pharmacyDTOList = new ArrayList<>();
    // for(Pharmacy p : result){
    // PharmacyDTO dto = PharmacyDTO.builder()
    // .phId(p.getPhId())
    // .phName(p.getPhName())
    // .phAdd(p.getPhAdd())
    // .phTel(p.getPhTel())
    // .phX(p.getPhX())
    // .phY(p.getPhY())
    // .enjoyIndex(p.getEnjoyIndex())
    // .starIndex(p.getStarIndex())
    // .timeHoliEndDate(p.getTimeHoliEndDate())
    // .timeHoliStartDate(p.getTimeHoliStartDate())
    // .timeSatEndDate(p.getTimeSatEndDate())
    // .timeSatStartDate(p.getTimeSatStartDate())
    // .timeWeekEndDate(p.getTimeWeekEndDate())
    // .timeWeekStartDate(p.getTimeWeekStartDate())
    // .phPageIndex(p.getPhPageIndex())
    // .phPageTotal(p.getPhPageTotal())
    // .build();
    // pharmacyDTOList.add(dto);
    // }
    //
    //
    // return pharmacyDTOList;
    // }

    @Override
    public PageResponseDTO<PharmacyDTO> nameOrAddSearch(String keyword, PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPageIndex() <= 0 ? 0 : pageRequestDTO.getPageIndex() - 1,
                pageRequestDTO.getSize(),
                Sort.by("phId").ascending());
        Page<Pharmacy> result = pharmacyRepository.findAddOrNameByKeyword(keyword, pageable);
        log.info(result);

        List<PharmacyDTO> phList = result.getContent().stream().map(pharmacy -> {
            PharmacyDTO pharmacyDTO = modelMapper.map(pharmacy, PharmacyDTO.class);
            return pharmacyDTO;
        }).collect(Collectors.toList());
        log.info(phList);
        return PageResponseDTO.<PharmacyDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .phList(phList)
                .totalIndex((int) result.getTotalElements())
                .build();
    }

    @Override
    public PageResponseDTO<PharmacyDTO> nameSearchInCity(String city, String keyword, PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPageIndex() <= 0 ? 0 : pageRequestDTO.getPageIndex() - 1,
                pageRequestDTO.getSize(),
                Sort.by("phId").ascending());
        Page<Pharmacy> result = pharmacyRepository.findNameByCityAndKeyword(city, keyword, pageable);
        log.info(result);

        List<PharmacyDTO> phList = result.getContent().stream().map(pharmacy -> {
            PharmacyDTO pharmacyDTO = modelMapper.map(pharmacy, PharmacyDTO.class);
            return pharmacyDTO;
        }).collect(Collectors.toList());
        log.info(phList);
        return PageResponseDTO.<PharmacyDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .phList(phList)
                .totalIndex((int) result.getTotalElements())
                .build();
    }

    @Override
    public PageResponseDTO<PharmacyDTO> allSearch(PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPageIndex() <= 0 ? 0 : pageRequestDTO.getPageIndex() - 1,
                pageRequestDTO.getSize(),
                Sort.by("phId").ascending());
        Page<Pharmacy> result = pharmacyRepository.findAll(pageable);
        log.info(result);

        List<PharmacyDTO> dtoList = result.getContent().stream().map(pharmacy -> {
            PharmacyDTO pharmacyDTO = modelMapper.map(pharmacy, PharmacyDTO.class);
            return pharmacyDTO;
        }).collect(Collectors.toList());
        log.info(dtoList);
        return PageResponseDTO.<PharmacyDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .phList(dtoList)
                .totalIndex((int) result.getTotalElements())
                .build();
    }

    @Override
    public PharmacyInfoDTO getPharmacyInfo(Long phId) {

        Optional<Pharmacy> result = pharmacyRepository.findById(phId);
        Pharmacy pharmacy = result.orElseThrow(() -> new PharmacyNotFoundException(phId));

        PharmacyInfoDTO pharmacyInfoDTO = modelMapper.map(pharmacy, PharmacyInfoDTO.class);


        PharmacyStar pharmacyStar = pharmacyStarRepository.findByPhId(phId).orElse(null);

        pharmacyInfoDTO.setStarAvg(pharmacyStar != null ? pharmacyStar.getStarAvg() : 0);

        PharmacyEnjoy pharmacyEnjoy = pharmacyEnjoyRepository.findByPhId(phId).orElse(null);
        pharmacyInfoDTO.setEnjoyIndex(pharmacyEnjoy != null ? pharmacyEnjoy.getEnjoyIndex() : 0);
        pharmacyInfoDTO.setReviewIndex(reviewRepository.countByPharmacyPhId(phId) != 0 ? reviewRepository.countByPharmacyPhId(phId) : 0);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentUserName = authentication.getName();
        log.info("이름:"+currentUserName);
        Users users = userRepository.findByEmail(currentUserName)
                .orElse(null);
        if(users != null) {
            Long userId = users.getUserId();
            EnjoyPh enjoyPh = enjoyRepository.findByPharmacyAndUsers(phId, userId);
            pharmacyInfoDTO.setEnjoyPh(enjoyPh);
        } else{
            pharmacyInfoDTO.setEnjoyPh(null);
        }
        return pharmacyInfoDTO;
    }


}