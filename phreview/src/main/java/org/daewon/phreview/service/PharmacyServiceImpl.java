package org.daewon.phreview.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.*;
import org.daewon.phreview.dto.page.PageRequestDTO;
import org.daewon.phreview.dto.page.PageResponseDTO;
import org.daewon.phreview.dto.pharmacy.*;
import org.daewon.phreview.repository.*;
import org.daewon.phreview.repository.Pharmacy.PharmacyEnjoyRepository;
import org.daewon.phreview.repository.Pharmacy.PharmacyRepository;
import org.daewon.phreview.repository.Pharmacy.PharmacyStarRepository;
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

    // 지역별 검색 카테고리 메서드
    public PageResponseDTO<PharmacyDTO> regionCategorySearch(String city, PageRequestDTO pageRequestDTO) {
        // 페이지 요청 객체 생성
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPageIndex() <= 0 ? 0 : pageRequestDTO.getPageIndex() - 1,
                pageRequestDTO.getSize(),
                Sort.by("phId").ascending()); // pageable 객체 생성
        // 도시명을 기준으로 약국을 검색
        Page<Pharmacy> result = pharmacyRepository.findByCity(city, pageable); // city 스트링을 phAdd에 대입하여 검색
        log.info(result);

        // 현재 인증된 사용자의 정보 가져오기
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        log.info("이름:" + currentUserName);

        // 사용자 정보 가져오기
        Users users = userRepository.findByEmail(currentUserName)
                .orElse(null); // 유저 객체 생성

        // 검색 결과를 DTO로 변환
        List<PharmacyDTO> phList = result.getContent().stream().map(pharmacy -> {
            PharmacyDTO pharmacyDTO = modelMapper.map(pharmacy, PharmacyDTO.class);
            if (users != null) { // 유저가 있을경우
                Long userId = users.getUserId();
                EnjoyPh enjoyPh = enjoyRepository.findByPharmacyAndUsers(pharmacyDTO.getPhId(), userId); // EnjoyPh 객체
                pharmacyDTO.setEnjoy(enjoyPh != null ? enjoyPh.isEnjoy() : false); // 약국 목록 가져올 때 즐겨찾기 여부
            } else {
                pharmacyDTO.setEnjoy(false); // 로그인 안한경우 무조건 false
            }
            return pharmacyDTO;
        }).collect(Collectors.toList());
        log.info(phList);

        // 페이지 응답 객체 생성 및 반환
        return PageResponseDTO.<PharmacyDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .phList(phList)
                .totalIndex((int) result.getTotalElements())
                .build();
    }

    // 위도와 경도를 기준으로 근처 약국을 검색하는 메서드
    @Override
    public PageResponseDTO<PharmacyDTO> nearSearch(double lat, double lng, PageRequestDTO pageRequestDTO) {
        // PageRequestDTO를 사용하여 페이지 요청 정보를 기반으로 Pageable 객체를 생성
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPageIndex() <= 0 ? 0 : pageRequestDTO.getPageIndex() - 1,
                pageRequestDTO.getSize(),
                Sort.by("phId").ascending()); // phID를 기준으로 오름차순 정렬합니다.

        // 주어진 위도와 경도를 기준으로 근처 약국을 검색하여 결과를 page<pharmacy>로 반환
        Page<Pharmacy> result = pharmacyRepository.findByLoc(lat, lng, pageable); // 좌표 가지고 근처 약국 검색
        log.info(result);

        // 현재 인증된 사용자의 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        log.info("이름:" + currentUserName);

        // 사용자 이메일을 통해 사용자 정보를 찾음, 없을 경우 null 반환
        Users users = userRepository.findByEmail(currentUserName)
                .orElse(null);

        // 검색 결과를 PharmacyDTO 리스트로 변환
        List<PharmacyDTO> phList = result.getContent().stream().map(pharmacy -> {
            PharmacyDTO pharmacyDTO = modelMapper.map(pharmacy, PharmacyDTO.class);

            if (users != null) { // 사용자가 존재하는 경우
                Long userId = users.getUserId(); // 사용자의 ID 가져옴
                // 현재 사용자가 즐겨찾기한 약국인지 확인
                EnjoyPh enjoyPh = enjoyRepository.findByPharmacyAndUsers(pharmacyDTO.getPhId(), userId);
                // 즐겨찾기 여부 설정
                pharmacyDTO.setEnjoy(enjoyPh != null ? enjoyPh.isEnjoy() : false);
            } else {
                // 사용자가 존재하지 않는 경우 즐겨찾기 여부를 false로 설정
                pharmacyDTO.setEnjoy(false);
            }
            return pharmacyDTO;
        }).collect(Collectors.toList());

        log.info(phList);

        // PageResponseDTO를 생성하여 반환
        return PageResponseDTO.<PharmacyDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .phList(phList)
                .totalIndex((int) result.getTotalElements())
                .build();
    }

    @Override
    public PageResponseDTO<PharmacyDTO> nameOrAddSearch(String keyword, PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPageIndex() <= 0 ? 0 : pageRequestDTO.getPageIndex() - 1,
                pageRequestDTO.getSize(),
                Sort.by("phId").ascending());
        Page<Pharmacy> result = pharmacyRepository.findAddOrNameByKeyword(keyword, pageable);
        log.info(result);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        log.info("이름:" + currentUserName);
        Users users = userRepository.findByEmail(currentUserName)
                .orElse(null);

        List<PharmacyDTO> phList = result.getContent().stream().map(pharmacy -> {
            PharmacyDTO pharmacyDTO = modelMapper.map(pharmacy, PharmacyDTO.class);
            if (users != null) {
                Long userId = users.getUserId();
                EnjoyPh enjoyPh = enjoyRepository.findByPharmacyAndUsers(pharmacyDTO.getPhId(), userId);
                pharmacyDTO.setEnjoy(enjoyPh != null ? enjoyPh.isEnjoy() : false);
            } else {
                pharmacyDTO.setEnjoy(false);
            }
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        log.info("이름:" + currentUserName);
        Users users = userRepository.findByEmail(currentUserName)
                .orElse(null);

        List<PharmacyDTO> phList = result.getContent().stream().map(pharmacy -> {
            PharmacyDTO pharmacyDTO = modelMapper.map(pharmacy, PharmacyDTO.class);
            if (users != null) {
                Long userId = users.getUserId();
                EnjoyPh enjoyPh = enjoyRepository.findByPharmacyAndUsers(pharmacyDTO.getPhId(), userId);
                pharmacyDTO.setEnjoy(enjoyPh != null ? enjoyPh.isEnjoy() : false);
            } else {
                pharmacyDTO.setEnjoy(false);
            }
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        log.info("이름:" + currentUserName);
        Users users = userRepository.findByEmail(currentUserName)
                .orElse(null);

        List<PharmacyDTO> dtoList = result.getContent().stream().map(pharmacy -> {
            PharmacyDTO pharmacyDTO = modelMapper.map(pharmacy, PharmacyDTO.class);
            if (users != null) {
                Long userId = users.getUserId();
                EnjoyPh enjoyPh = enjoyRepository.findByPharmacyAndUsers(pharmacyDTO.getPhId(), userId);
                pharmacyDTO.setEnjoy(enjoyPh != null ? enjoyPh.isEnjoy() : false);
            } else {
                pharmacyDTO.setEnjoy(false);
            }
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
        Pharmacy pharmacy = result.orElseThrow(() -> new PharmacyNotFoundException()); // pharmacy 객체 생성

        PharmacyInfoDTO pharmacyInfoDTO = modelMapper.map(pharmacy, PharmacyInfoDTO.class); // dto 매핑

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        log.info("이름:" + currentUserName);
        Users users = userRepository.findByEmail(currentUserName)
                .orElse(null); // 유저 객체 생성
        if (users != null) { // 로그인한 경우
            Long userId = users.getUserId();
            EnjoyPh enjoyPh = enjoyRepository.findByPharmacyAndUsers(phId, userId);
            pharmacyInfoDTO.setEnjoy(enjoyPh != null ? enjoyPh.isEnjoy() : false); // 즐겨찾기 여부
        } else {
            pharmacyInfoDTO.setEnjoy(false); // 로그인 안한경우 무조건 false
        }
        // 별점 매긴 약국 찾기
        PharmacyStar pharmacyStar = pharmacyStarRepository.findByPhId(phId).orElse(null);
        // 결과가 null이 아니라면 그 약국의 별점 평균, null이라면 0
        pharmacyInfoDTO.setStarAvg(pharmacyStar != null ? pharmacyStar.getStarAvg() : 0);

        // 즐겨찾기한 약국 찾기
        PharmacyEnjoy pharmacyEnjoy = pharmacyEnjoyRepository.findByPhId(phId).orElse(null);
        // 결과가 null이 아니라면 그 약국의 즐겨찾기 수, null이라면 0
        pharmacyInfoDTO.setEnjoyIndex(pharmacyEnjoy != null ? pharmacyEnjoy.getEnjoyIndex() : 0);

        // 약국정보의 총 리뷰 수를 Review 테이블에서 phId를 통해 Select, Count 반환, 없을경우 0
        pharmacyInfoDTO.setReviewIndex(
                reviewRepository.countByPharmacyPhId(phId) != 0 ? reviewRepository.countByPharmacyPhId(phId) : 0);

        return pharmacyInfoDTO;
    }

    @Override
    public List<PharmacyEnjoyRankListDTO> pharmaciesListByEnjoyIndexDesc() { // 병원 즐겨찾기가 많은 순부터 내림차순 정렬

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Users users = userRepository.findByEmail(currentUserName).orElse(null);

        List<PharmacyEnjoy> list = pharmacyEnjoyRepository.findAllByOrderByEnjoyIndexDesc();

        if (users != null) {
            Long userId = users.getUserId();
            return list.stream()
                    .map(p -> {
                        PharmacyEnjoyRankListDTO dto = new PharmacyEnjoyRankListDTO();
                        EnjoyPh enjoyPh = enjoyRepository.findByPharmacyAndUsers(p.getPharmacy().getPhId(), userId);

                        dto.setPhId(p.getPharmacy().getPhId());
                        dto.setPhAdd(p.getPharmacy().getPhAdd());
                        dto.setPhTel(p.getPharmacy().getPhTel());
                        dto.setEnjoyIndex(p.getEnjoyIndex());
                        dto.setPhName(p.getPharmacy().getPhName());
                        dto.setEnjoy(enjoyPh != null ? enjoyPh.isEnjoy() : false);
                        return dto;
                    })
                    .collect(Collectors.toList());
        } else {
            return list.stream()
                    .map(p -> {
                        PharmacyEnjoyRankListDTO dto = new PharmacyEnjoyRankListDTO();
                        dto.setPhId(p.getPharmacy().getPhId());
                        dto.setPhAdd(p.getPharmacy().getPhAdd());
                        dto.setPhTel(p.getPharmacy().getPhTel());
                        dto.setEnjoyIndex(p.getEnjoyIndex());
                        dto.setPhName(p.getPharmacy().getPhName());
                        dto.setEnjoy(false);
                        return dto;
                    })
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<PharmacyStarRankListDTO> reviewsListByStarAvgDesc() { // 병원 평점 평균 많은 순부터 내림차순 정렬
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Users users = userRepository.findByEmail(currentUserName).orElse(null);
        List<PharmacyStar> list = pharmacyStarRepository.findAllByOrderByStarAvgDesc();

        if (users != null) {
            Long userId = users.getUserId();
            return list.stream()
                    .map(p -> {
                        PharmacyStarRankListDTO dto = new PharmacyStarRankListDTO();
                        EnjoyPh enjoyPh = enjoyRepository.findByPharmacyAndUsers(p.getPharmacy().getPhId(), userId);

                        dto.setPhId(p.getPharmacy().getPhId());
                        dto.setPhAdd(p.getPharmacy().getPhAdd());
                        dto.setPhTel(p.getPharmacy().getPhTel());
                        dto.setStarAvg(p.getStarAvg());
                        dto.setPhName(p.getPharmacy().getPhName());
                        dto.setEnjoy(enjoyPh != null ? enjoyPh.isEnjoy() : false);
                        return dto;
                    })
                    .collect(Collectors.toList());
        } else {
            return list.stream()
                    .map(p -> {
                        PharmacyStarRankListDTO dto = new PharmacyStarRankListDTO();
                        dto.setPhId(p.getPharmacy().getPhId());
                        dto.setPhAdd(p.getPharmacy().getPhAdd());
                        dto.setPhTel(p.getPharmacy().getPhTel());
                        dto.setStarAvg(p.getStarAvg());
                        dto.setPhName(p.getPharmacy().getPhName());
                        dto.setEnjoy(false);
                        return dto;
                    })
                    .collect(Collectors.toList());
        }
    }

}