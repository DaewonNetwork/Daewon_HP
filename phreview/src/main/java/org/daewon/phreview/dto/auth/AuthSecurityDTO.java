package org.daewon.phreview.dto.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
@Setter
@ToString
public class AuthSecurityDTO extends User implements OAuth2User {

    private Long userId;
    private String password;
    private String userName;
    private String email;
//    private boolean social;

    private Map<String, Object> props;

    public AuthSecurityDTO(Long userId, String userName, String password, String email, boolean social,
            Collection<? extends GrantedAuthority> authorities) {
        super(userName, password, authorities);

        this.userName = userName;
        this.password = password;
        this.email = email;
//        this.social = social;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return props;
    }

    @Override
    public String getName() {
        return this.userName;
    }
}
