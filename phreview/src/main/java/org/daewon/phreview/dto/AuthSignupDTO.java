package org.daewon.phreview.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AuthSignupDTO {
    private String password;
    private String userName;
    //@Column(name = "users_email")
    private String email;
//    private boolean social;
}
