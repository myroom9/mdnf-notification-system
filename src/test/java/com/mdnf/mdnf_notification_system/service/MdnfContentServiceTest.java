package com.mdnf.mdnf_notification_system.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class MdnfContentServiceTest {
    @Test
    @DisplayName("정기점검 타이틀 매칭 테스트")
    void titleRegMatchTest() {
        Pattern pattern = Pattern.compile("^.*(정기 점검).*");
        String title = "11/8(화) 정기 점검 안내 (10:00)";
        String title2 = "넥슨 지스타2022 참가 소식 안내";

        Assertions.assertThat(pattern.matcher(title).matches()).isTrue();
        Assertions.assertThat(pattern.matcher(title2).matches()).isFalse();
    }
}