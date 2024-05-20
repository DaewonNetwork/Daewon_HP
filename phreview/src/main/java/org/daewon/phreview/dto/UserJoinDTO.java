package org.daewon.phreview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserJoinDTO {

    private String password;
    private String userName;
    private String email;
    private boolean social;

}
