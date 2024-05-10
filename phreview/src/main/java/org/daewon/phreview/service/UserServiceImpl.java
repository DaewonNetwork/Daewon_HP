package org.daewon.phreview.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.Users;
import org.daewon.phreview.dto.UserJoinDTO;
import org.daewon.phreview.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void join(UserJoinDTO userJoinDTO) throws MidExistException {
        Long userId = userJoinDTO.getUserId();

        boolean exist = userRepository.existsById(userId);

        if(exist){
            throw new MidExistException();
        }
        Users user = modelMapper.map(userJoinDTO, Users.class);

        log.info(user);

        userRepository.save(user);

    }
}
