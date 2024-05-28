package org.daewon.phreview.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.Users;
import org.daewon.phreview.dto.AuthSigninDTO;
import org.daewon.phreview.repository.ReviewRepository;
import org.daewon.phreview.service.ReviewService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

// 커스텀 보안 표현식을 추가하여 작성자가 맞는지 확인
@Component
@RequiredArgsConstructor
@Log4j2
public class ReviewAndReplySecurity {

    private final ReviewRepository reviewRepository;

    // 현재 사용자가 리뷰의 작성자인지 확인하는 메서드
    public boolean isReviewOwner(Long reviewId) {
        // 현재 인증된 사용자의 정보를 가져옴
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // UserId값을 빼옴
        AuthSigninDTO authSigninDTO = (AuthSigninDTO) authentication.getPrincipal();
        log.info("authSigninDTO :" + authSigninDTO);
        Long userId = authSigninDTO.getUserId();

        log.info("userId :"+userId);

        Optional<Long> authorUserIdOptional = reviewRepository.findAuthorUserIdById(reviewId);
        log.info("authorUserIdOptional :"+authorUserIdOptional);

        if (authorUserIdOptional.isEmpty()) {
            return false;
        }

        // 리뷰작성한 사용자의 id값을 가져옴
        Long reviewAuthorId = authorUserIdOptional.get();

        // SecurityContextHolder에 등록된 userId랑 리뷰작성한 사용자의 id 값이 맞는지 확인. 맞으면 - true, 안 맞으면 - false
        return userId.equals(reviewAuthorId);
    }
}
