package com.mdnf.mdnf_notification_system.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class AlarmRepositoryTest {
    @Autowired
    AlarmRepository alarmRepository;

    @Test
    @DisplayName("아직 보내지 않은 알람 조회")
    void findAllNotSentAlarmByTimeTest() {
        LocalDateTime startTime = LocalDateTime.now().minusMinutes(2);
        LocalDateTime endTime = LocalDateTime.now().plusMinutes(10);
        alarmRepository.findAllNotSentAlarmsByTime(startTime, endTime);
    }
}