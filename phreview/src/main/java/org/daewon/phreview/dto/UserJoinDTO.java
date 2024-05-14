package org.daewon.phreview.dto;

import lombok.Data;

@Data
public class UserJoinDTO {

    private Long userId;
    private String password;
    private String userName;
    private String email;
    private boolean social;

}
