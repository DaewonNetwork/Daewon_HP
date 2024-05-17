package org.daewon.phreview.service;

import org.daewon.phreview.dto.AuthSignupDTO;

public interface AuthService {
    static class MidExistException extends Exception {

        public MidExistException() {}
        public MidExistException(String msg) {
            super(msg);
        }
    }

    void signup(AuthSignupDTO userSignupDTO) throws MidExistException;
}