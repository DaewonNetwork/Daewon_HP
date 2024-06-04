package org.daewon.phreview.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.EnjoyPh;
import org.daewon.phreview.domain.Pharmacy;
import org.daewon.phreview.domain.PharmacyEnjoy;
import org.daewon.phreview.domain.Users;
import org.daewon.phreview.dto.pharmacy.PharmacyEnjoyRankListDTO;
import org.daewon.phreview.repository.EnjoyRepository;
import org.daewon.phreview.repository.Pharmacy.PharmacyEnjoyRepository;
import org.daewon.phreview.repository.Pharmacy.PharmacyRepository;
import org.daewon.phreview.repository.UserRepository;
import org.daewon.phreview.security.exception.PharmacyNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class EnjoyServiceImpl implements EnjoyService {

    private final UserRepository userRepository;
    private final EnjoyRepository enjoyRepository;
    private final PharmacyEnjoyRepository pharmacyEnjoyRepository;
    private final PharmacyRepository pharmacyRepository;

    @Override
    public void enjoyPharmacy(Long phId) { // 즐겨찾기 기능

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentUserName = authentication.getName();
        log.info("이름:"+currentUserName);
        Users users = userRepository.findByEmail(currentUserName)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없음"));

        Long userId = users.getUserId();

        Pharmacy pharmacy = pharmacyRepository.findById(phId)
                .orElseThrow(() -> new PharmacyNotFoundException(phId)); // phId를 통해 약국 찾기

        PharmacyEnjoy pharmacyEnjoy = pharmacyEnjoyRepository.findByPhId(phId)
                .orElseGet(() -> { // phId를 가지고 즐겨찾기된 약국 찾기, 해당하는 phId가 없을경우 새로 생성
                    PharmacyEnjoy newPharmacyEnjoy = PharmacyEnjoy.builder()
                            .pharmacy(pharmacy)
                            .build();
                    return pharmacyEnjoyRepository.save(newPharmacyEnjoy);
                });

        if (enjoyRepository.findByPharmacyAndUsers(phId, userId) == null) { // 즐겨찾기 안 했을 때 즐겨찾기 기능
            pharmacyEnjoy.setEnjoyIndex(pharmacyEnjoy.getEnjoyIndex() + 1); // EnjoyIndex()가 + 1 추가됨
            pharmacyEnjoyRepository.save(pharmacyEnjoy);
            EnjoyPh enjoyPh = new EnjoyPh(pharmacyEnjoy, users);
            enjoyRepository.save(enjoyPh);
            log.info(pharmacy);
            log.info(enjoyPh);
            log.info("즐겨찾기");
        } else { // 즐겨찾기를 이미 했을 때 즐겨찾기 취소
            EnjoyPh enjoyPh = enjoyRepository.findByPharmacyAndUsers(phId, userId); // EnjoyPh 객체 생성
            enjoyRepository.delete(enjoyPh); // 레코드 삭제
            pharmacyEnjoy.setEnjoyIndex(pharmacyEnjoy.getEnjoyIndex() - 1); // 즐겨찾기 수 -1
            if(pharmacyEnjoy.getEnjoyIndex() == 0){ // 즐겨찾기 수가 0일경우 삭제
                pharmacyEnjoyRepository.delete(pharmacyEnjoy);
            } else {    // 약국의 즐겨찾기수가 1이상이면 save
                pharmacyEnjoyRepository.save(pharmacyEnjoy);
            }
            log.info(pharmacy);
            log.info(enjoyPh);
            log.info("즐겨찾기 취소");
        }

    }

    @Override
    public List<PharmacyEnjoyRankListDTO> enjoyedPharmaciesListByUser() { // 자신이 즐겨찾기한 약국

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentUserName = authentication.getName();
        log.info("이름:"+currentUserName);
        Users users = userRepository.findByEmail(currentUserName)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없음"));

        Long userId = users.getUserId();

        List<EnjoyPh> list = enjoyRepository.findByUsersUserIdOrderByEnjoyIdDesc(userId); // userId를 통해 검색후 EnjoyId가 높은순 내림차순(최신순)

        return list.stream()
                .map(e -> {
                    PharmacyEnjoyRankListDTO dto = new PharmacyEnjoyRankListDTO();
                    dto.setPhName(e.getPharmacyEnjoy().getPharmacy().getPhName());
                    dto.setPhAdd(e.getPharmacyEnjoy().getPharmacy().getPhAdd());
                    dto.setPhTel(e.getPharmacyEnjoy().getPharmacy().getPhTel());
                    dto.setEnjoyIndex(e.getPharmacyEnjoy().getEnjoyIndex());
                    return dto;
                })
                .collect(Collectors.toList());
    }

}
