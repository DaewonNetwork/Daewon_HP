package org.daewon.phreview.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.UserRole;
import org.daewon.phreview.domain.Users;
import org.daewon.phreview.dto.AuthSignupDTO;
import org.daewon.phreview.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signup(AuthSignupDTO memberSignupDTO) throws MidExistException {
        if(userRepository.existsByEmail(memberSignupDTO.getEmail())) {
            throw new MidExistException();
        }

        Users users = modelMapper.map(memberSignupDTO, Users.class);
        
        users.setPassword(passwordEncoder.encode(memberSignupDTO.getPassword()));
        users.addRole(UserRole.USER);

        log.info("================================");
        log.info(users);
        log.info(users.getRoleSet());

        userRepository.save(users);
    }
}