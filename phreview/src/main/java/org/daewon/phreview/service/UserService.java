package org.daewon.phreview.service;

import org.daewon.phreview.dto.UserJoinDTO;

public interface UserService {
    static class MidExistException extends Exception {

        public MidExistException() {}
        public MidExistException(String msg) {

            super(msg);
        }
    }

    void join(UserJoinDTO  userJoinDTO) throws MidExistException;


}
