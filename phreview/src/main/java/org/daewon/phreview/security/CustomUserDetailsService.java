package org.daewon.phreview.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.Users;
import org.daewon.phreview.dto.AuthSecurityDTO;
import org.daewon.phreview.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("loadUserByUsername : " + userName);

        Optional<Users> userOptional = userRepository.findByUserName(userName);

        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found with userName: " + userName);
        }

        Long userId = userOptional.get().getUserId();
        Optional<Users> result = userRepository.getWithRoles(userId);

        Users users = result.get();

        AuthSecurityDTO usersSecurityDTO = new AuthSecurityDTO(
                users.getUserId(),
                users.getPassword(),
                users.getUserName(),
                users.getEmail(),
                false,
                users.getRoleSet().stream().map(userRole ->
                                new SimpleGrantedAuthority("ROLE_" + userRole.name()))
                        .collect(Collectors.toList())
        );

        log.info("usersSecurityDTO");
        log.info(usersSecurityDTO);

        return usersSecurityDTO;
    }
}
