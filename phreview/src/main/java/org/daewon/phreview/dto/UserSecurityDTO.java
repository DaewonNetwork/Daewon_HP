package org.daewon.phreview.dto;

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
public class UserSecurityDTO extends User implements OAuth2User {

    private String password;
    private String userName;
    private String email;
    private boolean social;

    private Map<String, Object> props;

    public UserSecurityDTO(String username, String password, boolean social,
                           Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);

        this.password = password;
        this.email = username;
        this.social = social;
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
