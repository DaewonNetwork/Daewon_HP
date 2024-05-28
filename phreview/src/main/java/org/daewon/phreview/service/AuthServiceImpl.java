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

import java.util.Optional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Users signup(AuthSignupDTO authSignupDTO) throws UserEmailExistException {
        if(userRepository.existsByEmail(authSignupDTO.getEmail())) {
            throw new UserEmailExistException();
        }

        Users users = modelMapper.map(authSignupDTO, Users.class);

        users.setPassword(passwordEncoder.encode(authSignupDTO.getPassword()));
        users.addRole(UserRole.USER);

        log.info("================================");
        log.info(users);
        log.info(users.getRoleSet());

        userRepository.save(users);
        return users;
    }

    @Override
    public Users signin(String email, String password) {
        // log.info("클라이언트 이메일 : " + email);

        Optional<Users> optionalUsers = userRepository.findByEmail(email);
        // 클라이언트한테 받은 password값과 db에 있는 password값 대조
        // DB에 저장되어 있는 password값이 인코딩 되어서 저장되어 있으므로
        // 클라이언트한테 받은 password값을 인코딩 시켜 대조 시켜야 함
        // 앞에 password는 클라이언트한테 받은 패스워드, 뒤에는 db에 있는 패스워드 값
        if (optionalUsers.isPresent()) {
            if (passwordEncoder.matches(password, optionalUsers.get().getPassword()) && email.equals(optionalUsers.get().getEmail())) {
                log.info("클라이언트 이메일 :" + email);
                log.info("클라이언트 패스워드 : " + password);
                log.info("login success");
                return optionalUsers.get();
            } else {
                log.info("클라이언트 패스워드 : " + password);
                log.info("login fail");
                log.info("클라이언트가 입력한 패스워드" + password +"을 DB에서 찾지 못했습니다.");
                return null;
            }
        } else {
            log.info("클라이언트 이메일 :" + email);
            log.info("login fail");
            log.info("클라이언트가 입력한 이메일" + email + "을 DB에서 찾지 못했습니다");
            return null;
        }
    }

    @Override
    public void uploadImage(String userId, byte[] imageData) throws ImageUploadException {
        try {
            Path imagePath = Paths.get("images/" + userId + ".jpg");
            Files.write(imagePath, imageData);
            log.info(userId + "의 이미지가 업로드됨");
        } catch (IOException e) {
            log.error(userId + "의 이미지 업로드 실패", e);
            throw new ImageUploadException("이미지 업로드 실패", e);
        }
    }
}