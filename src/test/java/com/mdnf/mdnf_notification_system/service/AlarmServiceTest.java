package com.mdnf.mdnf_notification_system.service;

import com.mdnf.mdnf_notification_system.domain.Alarm;
import com.mdnf.mdnf_notification_system.repository.AlarmRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AlarmServiceTest {

    @Autowired
    AlarmRepository alarmRepository;

    @Autowired
    AlarmService alarmService;

    @Test
    @DisplayName("알람 발송 플래그 갱신 테스트")
    void updateAlarmSendFlagToCompletedTest() {
        Alarm alarm = Alarm.builder().title("test").content("test").build();
        alarmRepository.saveAndFlush(alarm);

        alarmService.updateAlarmSendFlagToCompleted(alarm);
    }

}