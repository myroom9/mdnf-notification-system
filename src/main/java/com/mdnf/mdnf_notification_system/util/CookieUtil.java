package com.mdnf.mdnf_notification_system.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.Cookie;
import java.util.Date;

public class CookieUtil {

    public static Cookie createCookie(String cookieKey, String data, int age) {
        Cookie cookie = new Cookie(cookieKey, data);
        cookie.setPath("/");
        cookie.setMaxAge(age);
        // cookie.setSecure(true);
        // cookie.setHttpOnly(true);

        return cookie;
    }

    public static String createJwtCookie(String secretKey) {
        // Claims을 생성
        var claims = Jwts.claims();
        // Payload 데이터 추가
        claims.put("is_password_checked", true);
        // 현재 시간
        Date now = new Date();
        // JWT 토큰을 만드는데, Payload 정보와 생성시간, 만료시간, 알고리즘 종류와 암호화 키를 넣어 암호화 함.
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + 1000L * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public static Object verifyCookieAndGetDataByKey(String cookie, String secretKey, String key) {
        // 데이터 취득
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(cookie)
                .getBody().get(key);
    }
}
