package org.daewon.phreview.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.EnjoyPh;
import org.daewon.phreview.domain.Pharmacy;
import org.daewon.phreview.domain.Users;
import org.daewon.phreview.dto.PageRequestDTO;
import org.daewon.phreview.dto.PageResponseDTO;
import org.daewon.phreview.dto.PharmacyDTO;
import org.daewon.phreview.repository.EnjoyRepository;
import org.daewon.phreview.repository.PharmacyRepository;
import org.daewon.phreview.repository.UserRepository;
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
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final EnjoyRepository enjoyRepository;

    public PageResponseDTO<PharmacyDTO> regionCategorySearch(String city, PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPageIndex() <= 0 ? 0 : pageRequestDTO.getPageIndex() - 1,
                pageRequestDTO.getSize(),
                Sort.by("phId").ascending());
        Page<Pharmacy> result = pharmacyRepository.findByCity(city, pageable);
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
    public PageResponseDTO<PharmacyDTO> NameOrAddSearch(String keyword, PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPageIndex() <= 0 ? 0 : pageRequestDTO.getPageIndex() - 1,
                pageRequestDTO.getSize(),
                Sort.by("phId").ascending());
        Page<Pharmacy> result = pharmacyRepository.findAddOrNameByKeyword(keyword, pageable);
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
    public PageResponseDTO<PharmacyDTO> NameSearchInCity(String city, String keyword, PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPageIndex() <= 0 ? 0 : pageRequestDTO.getPageIndex() - 1,
                pageRequestDTO.getSize(),
                Sort.by("phId").ascending());
        Page<Pharmacy> result = pharmacyRepository.findNameByCityAndKeyword(city, keyword, pageable);
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
    public PharmacyDTO getPharmacyInfo(Long phId) {

        Optional<Pharmacy> result = pharmacyRepository.findById(phId);
        Pharmacy pharmacy = result.orElseThrow(() -> new EntityNotFoundException("약국을 찾을 수 없습니다. ID: " + phId));

        PharmacyDTO pharmacyDTO = modelMapper.map(pharmacy, PharmacyDTO.class);

        return pharmacyDTO;
    }

    @Override
    public void enjoyPharmacy(Long phId) {
        Pharmacy pharmacy = pharmacyRepository.findById(phId)
                .orElseThrow(() -> new EntityNotFoundException("약국을 찾을 수 없습니다. ID: " + phId));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users users = userRepository.findByUserName(authentication.getName())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없음"));
        Long userId = users.getUserId();

        if (enjoyRepository.findByPharmacyAndUsers(phId, userId) == null) {
            pharmacy.setEnjoyIndex(pharmacy.getEnjoyIndex() + 1);
            pharmacyRepository.save(pharmacy);
            EnjoyPh enjoyPh = new EnjoyPh(pharmacy, users);
            enjoyRepository.save(enjoyPh);
            log.info(pharmacy);
            log.info(enjoyPh);
            log.info("즐겨찾기");
        } else {
            EnjoyPh enjoyPh = enjoyRepository.findByPharmacyAndUsers(phId, userId);
            pharmacy.setEnjoyIndex(pharmacy.getEnjoyIndex() - 1);
            pharmacyRepository.save(pharmacy);
            enjoyPh.unEnjoyPh(pharmacy);
            enjoyRepository.delete(enjoyPh);
            log.info(pharmacy);
            log.info(enjoyPh);
            log.info("즐겨찾기 취소");
        }

    }
}