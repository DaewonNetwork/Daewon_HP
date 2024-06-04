package org.daewon.phreview.security.exception;

import jakarta.persistence.EntityNotFoundException;

public class PharmacyNotFoundException extends EntityNotFoundException {
    public PharmacyNotFoundException(Long phId) {
        super("약국을 찾을 수 없습니다. ID: " + phId);
    }
}