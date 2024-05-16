package org.daewon.phreview.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserJoinDTO {

    private String password;
    private String userName;
    private String email;
    private boolean social;

}
