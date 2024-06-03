package org.daewon.phreview.dto.auth;

import lombok.Data;

import java.util.Date;

@Data
public class AuthSignupDTO {
    private String password;
    private String userName;
    private String email;

    private Date createAt;

}