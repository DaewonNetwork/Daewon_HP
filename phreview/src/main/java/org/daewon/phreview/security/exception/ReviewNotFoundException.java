package org.daewon.phreview.security.exception;

import jakarta.persistence.EntityNotFoundException;

public class ReviewNotFoundException extends EntityNotFoundException {
    public ReviewNotFoundException(Long reviewId) {
        super("리뷰를 찾을 수 없습니다. ID: " + reviewId);
    }
}