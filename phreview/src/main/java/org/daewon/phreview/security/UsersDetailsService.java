package org.daewon.phreview.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.Users;
import org.daewon.phreview.dto.AuthSigninDTO;
import org.daewon.phreview.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class UsersDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("loadUserByEmail : " + email);

        Optional<Users> result = userRepository.findByEmail(email);
        log.info("result : " + result);

        if (!result.isPresent()) {
            throw new UsernameNotFoundException("Cannot find user with Email : " + email);
        }

        Users users = result.get();

        log.info("User found: " + users);

        log.info("UsersDetailsService----------------------------------------------");

        // ROLE 역할이 비어있는지 확인
        if (users.getRoleSet().isEmpty()) {
            log.error("User has no roles assigned");
            throw new UsernameNotFoundException("User has no roles assigned");
        }

        // 권한을 SimpleGrantedAuthority로 변환
        List<SimpleGrantedAuthority> authorities;
        try {
            authorities = users.getRoleSet().stream()
                    .peek(role -> log.info("유저한테 할당된 권한: ROLE_" +role))
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Error converting roles to authorities", e);
            throw new UsernameNotFoundException("Cannot convert roles to authorities", e);
        }

        return new AuthSigninDTO(
                        users.getUserName(),
                        users.getPassword(),
                        users.getEmail(),
                        authorities,
                        users.getUserId()
                );
    }
}