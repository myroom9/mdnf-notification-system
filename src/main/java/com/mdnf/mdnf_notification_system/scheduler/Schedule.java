package com.mdnf.mdnf_notification_system.scheduler;

import com.mdnf.mdnf_notification_system.domain.Alarm;
import com.mdnf.mdnf_notification_system.domain.User;
import com.mdnf.mdnf_notification_system.service.AlarmService;
import com.mdnf.mdnf_notification_system.service.FcmService;
import com.mdnf.mdnf_notification_system.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class Schedule {

    private final AlarmService alarmService;
    private final FcmService fcmService;
    private final UserService userService;

    // 매분 체크
    // 현재 시간으로 2분 전 ~ 현재 시간 / 발송되지 않은 알람 조건으로 조회
    // 발송
    @Scheduled(fixedDelay = 5000)
    // @Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}") // 문자열 milliseconds 사용 시
    public void sendAlarmSchedule() {
        log.info("돌고있어!");
        LocalDateTime startTime = LocalDateTime.now().minusMinutes(2);
        LocalDateTime endTime = startTime.plusMinutes(12);

        List<User> users = userService.getAllUsers();
        List<Alarm> alarms = alarmService.getAllNotSentAlarm(startTime, endTime);

        fcmService.sendAllAlarm(alarms, users);
    }
}
