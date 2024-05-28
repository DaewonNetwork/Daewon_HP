package org.daewon.phreview.utils;

import lombok.RequiredArgsConstructor;
import org.daewon.phreview.service.ReviewService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

// 커스텀 보안 표현식을 추가하여 작성자가 맞는지 확인
@Component
@RequiredArgsConstructor
public class ReviewAndReplySecurity {

    private final ReviewService reviewService;

    public boolean hasPermissionToModifyOrDelete(Long reviewId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        return reviewService.isReviewOwner(reviewId, currentUserName);
    }
}
