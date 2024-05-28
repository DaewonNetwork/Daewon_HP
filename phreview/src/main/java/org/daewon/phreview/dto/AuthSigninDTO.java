package org.daewon.phreview.dto;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@ToString
@Data
public class AuthSigninDTO implements UserDetails {

    private String userName;
    private String password;
    @Column(unique = true)
    private String email;
    private Collection<? extends GrantedAuthority> authorities;

    public AuthSigninDTO(String userName, String password, String email,
                         Collection<? extends GrantedAuthority> authorities) {

        this.userName = userName;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email; // 또는 userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
