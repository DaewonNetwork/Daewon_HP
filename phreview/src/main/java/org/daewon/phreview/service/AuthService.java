package org.daewon.phreview.service;

import org.daewon.phreview.domain.Users;
import org.daewon.phreview.dto.AuthSignupDTO;

public interface AuthService {
    // 이메일 중복 검사 예외 Exception
    static class UserEmailExistException extends Exception {

        public UserEmailExistException() {}
        public UserEmailExistException(String msg) {
            super(msg);
        }
    }

    Users signup(AuthSignupDTO authSignupDTO) throws UserEmailExistException;

    Users signin(final String email, final String password);

}