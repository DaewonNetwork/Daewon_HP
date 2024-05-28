package org.daewon.phreview.service;

import org.daewon.phreview.domain.Users;
import org.daewon.phreview.dto.AuthSignupDTO;

import java.awt.*;

public interface AuthService {
    static class MidExistException extends Exception {

        public MidExistException() {}
        public MidExistException(String msg) {
            super(msg);
        }
    }

    Users signup(AuthSignupDTO authSignupDTO) throws MidExistException;

    // 이미지 업로드 메서드
    void uploadImage(String userId, byte[] imageData) throws ImageUploadException;

    static class ImageUploadException extends Exception {
        public ImageUploadException() {}
        public ImageUploadException(String msg) {
            super(msg);
        }
    }

}