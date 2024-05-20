package org.daewon.phreview.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.Users;
import org.daewon.phreview.dto.UserSecurityDTO;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername : " + username);

        Optional<Users> userOptional = userRepository.findById(username);
        Users user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Cannot find email"));

        String email = userOptional.get().getEmail();
        Optional<Users> result = userRepository.getWithRoles(email);

        Users users = result.get();

        UserSecurityDTO usersSecurityDTO = new UserSecurityDTO(
                users.getEmail(),
                users.getPassword(),
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
