package com.mdnf.mdnf_notification_system.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CookieUtilTest {
    @Test
    @DisplayName("jwt 쿠키 생성 테스트")
    void createCookieTest() {
        String test = CookieUtil.createJwtCookie("test");
        System.out.println("test = " + test);
    }

    @Test
    @DisplayName("jwt 쿠키 검증 테스트")
    void verifyCookie() {
        String cookie = CookieUtil.createJwtCookie("test");
        Boolean data = (Boolean) CookieUtil.verifyCookieAndGetDataByKey(cookie, "test", "is_password_checked");
        System.out.println("data = " + data);
    }
}