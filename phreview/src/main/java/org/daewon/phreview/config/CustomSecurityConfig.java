package org.daewon.phreview.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
<<<<<<< HEAD
import org.daewon.phreview.security.CustomUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
=======
>>>>>>> main
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
<<<<<<< HEAD
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
=======
import org.springframework.security.core.userdetails.UserDetailsService;
>>>>>>> main
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@Log4j2
@RequiredArgsConstructor
@EnableMethodSecurity
@EnableWebSecurity
public class CustomSecurityConfig {

    private final DataSource dataSource;
<<<<<<< HEAD
    private final CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());

        // 세션 비활성화
        http.sessionManagement(httpSecuritySessionManagementConfigurer ->
                httpSecuritySessionManagementConfigurer.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS
                ));

        http.rememberMe(httpSecurityRememberMeConfigurer -> {
            httpSecurityRememberMeConfigurer.key("12345678")
                    .tokenRepository(persistentTokenRepository())
                    .userDetailsService(userDetailsService)
                    .tokenValiditySeconds(60*60*24*30); // 토큰 유효기간 설정
                });

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {      // 특정부분 시큐리티 검증 제외 설정
        log.info("---------------------web configure--------------------");
        // 정적 리소스 필터링 제외
        return (web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations()));    // staticResources가 있는 부분은 무시해라 resources - static 폴더 안에 있는 파일은 로그인창을 띄우지 않음
    }


    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }
}
=======
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("Security configuration initialized.");

        http.formLogin(form -> {
            form.loginPage("/users/login");
        });

        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());

        return http.build();
    }

}
>>>>>>> main
