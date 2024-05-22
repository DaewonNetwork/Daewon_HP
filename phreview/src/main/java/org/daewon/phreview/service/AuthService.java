package org.daewon.phreview.service;

import org.daewon.phreview.domain.Users;
import org.daewon.phreview.dto.AuthSignupDTO;

public interface AuthService {
    static class MidExistException extends Exception {

        public MidExistException() {}
        public MidExistException(String msg) {
            super(msg);
        }
    }

    Users signup(AuthSignupDTO authSignupDTO) throws MidExistException;

    

}