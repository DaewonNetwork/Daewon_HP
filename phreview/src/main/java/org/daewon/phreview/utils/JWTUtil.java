package org.daewon.phreview.utils;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

// JWT를 검증하고 생성하는 클래스
@Log4j2
@Component
public class JWTUtil {

    @Value("${spring.jwt.secret}")
    private String key;

    // 토큰 생성 메서드
    public String generateToken(Map<String, Object> valueMap, int days) {

        log.info("generateKey....."+key);

        // 헤더 부분
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ","JWT");
        headers.put("alg","HS256");

        // payload 부분
        Map<String, Object> payloads = new HashMap<>();
        payloads.putAll(valueMap);

        // 토큰 생성 시간 설정...
        int time = (60 * 24) * days;   // 시간설정 변경... 1day로 ...

        String jwtStr = Jwts.builder()
                .setHeader(headers)
                .setClaims(payloads)
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(time).toInstant()))    // 분 단위로 처리 나중에 plusDays()로 변경 해줘야 함
                .signWith(SignatureAlgorithm.HS256, key.getBytes())
                .compact();


        return jwtStr;
    }

    // 토큰 검증 메서드
    public Map<String, Object> validateToken(String token) throws JwtException {
        Map<String, Object> claim = null;

        claim = Jwts.parser()
                .setSigningKey(key.getBytes()).build()
                .parseSignedClaims(token)
                .getBody();
        return claim;
    }
}

