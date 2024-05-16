package org.daewon.phreview.repository;

import lombok.extern.log4j.Log4j2;
import org.daewon.phreview.domain.UserRole;
import org.daewon.phreview.domain.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertMembers() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Users user = Users.builder()
                    .userName("member" + i)
                    .password(passwordEncoder.encode("1111"))
                    .email("email" + i + "@test.com")
                    .social(false)
                    .build();
            user.addRole(UserRole.USER);

            if(i >= 90) {
                user.addRole(UserRole.ADMIN);
            }

            userRepository.save(user);
        });
    }

    @Test
    public void deleteUser() {
        userRepository.deleteAll();
    }

}