package org.daewon.phreview.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.EnjoyPh;
import org.daewon.phreview.domain.Pharmacy;
import org.daewon.phreview.domain.PharmacyEnjoy;
import org.daewon.phreview.domain.Users;
import org.daewon.phreview.dto.Pharmacy.EnjoyPhDTO;
import org.daewon.phreview.dto.Pharmacy.PharmacyEnjoyRankListDTO;
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
                .orElseThrow(() -> new PharmacyNotFoundException(phId));

        PharmacyEnjoy pharmacyEnjoy = pharmacyEnjoyRepository.findByPhId(phId)
                .orElseGet(() -> { // 해당하는 phId가 없을경우 새로 생성
                    PharmacyEnjoy newPharmacyEnjoy = PharmacyEnjoy.builder()
                            .pharmacy(pharmacy)
                            .build();
                    return pharmacyEnjoyRepository.save(newPharmacyEnjoy);
                });

        if (enjoyRepository.findByPharmacyAndUsers(phId, userId) == null) {
            pharmacyEnjoy.setEnjoyIndex(pharmacyEnjoy.getEnjoyIndex() + 1);
            pharmacyEnjoyRepository.save(pharmacyEnjoy);
            EnjoyPh enjoyPh = new EnjoyPh(pharmacyEnjoy, users);
            enjoyRepository.save(enjoyPh);
            log.info(pharmacy);
            log.info(enjoyPh);
            log.info("즐겨찾기");
        } else {
            EnjoyPh enjoyPh = enjoyRepository.findByPharmacyAndUsers(phId, userId);
            enjoyPh.unEnjoyPh(pharmacyEnjoy);
            enjoyRepository.delete(enjoyPh);
            pharmacyEnjoy.setEnjoyIndex(pharmacyEnjoy.getEnjoyIndex() - 1);
            if(pharmacyEnjoy.getEnjoyIndex() == 0){
                pharmacyEnjoyRepository.delete(pharmacyEnjoy);
            } else {
                pharmacyEnjoyRepository.save(pharmacyEnjoy);
            }
            log.info(pharmacy);
            log.info(enjoyPh);
            log.info("즐겨찾기 취소");
        }

    }


    @Override
    public List<PharmacyEnjoyRankListDTO> enjoyedPharmaciesListByUser() { // 자신이 즐겨찾기한 병원

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String currentUserName = authentication.getName();
        log.info("이름:"+currentUserName);
        Users users = userRepository.findByEmail(currentUserName)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없음"));

        Long userId = users.getUserId();

        List<EnjoyPh> list = enjoyRepository.findByUsersUserIdOrderByEnjoyIdDesc(userId);

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
