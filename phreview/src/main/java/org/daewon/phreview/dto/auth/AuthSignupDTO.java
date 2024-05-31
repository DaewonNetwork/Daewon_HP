package org.daewon.phreview.dto.auth;

import lombok.Data;

import java.util.Date;

@Data
public class AuthSignupDTO {
    private String password;
    private String userName;
    //@Column(name = "users_email")
    private String email;
//    private boolean social;

    private Date createAt;

}