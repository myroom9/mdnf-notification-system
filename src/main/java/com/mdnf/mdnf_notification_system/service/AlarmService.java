package com.mdnf.mdnf_notification_system.service;

import com.mdnf.mdnf_notification_system.domain.Alarm;
import com.mdnf.mdnf_notification_system.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlarmService {

    private final AlarmRepository alarmRepository;

    public List<Alarm> getAllAlarms(LocalDate currentDate) {
        LocalDateTime startDateTime = LocalDateTime.of(currentDate.getYear(), currentDate.getMonth(), 1, 0, 0, 0);
        LocalDateTime endDateTime = LocalDateTime.of(currentDate.getYear(), currentDate.getMonth(), currentDate.getMonth().length(currentDate.isLeapYear()), 0, 0, 0);
        return alarmRepository.findAllAlarmsByTime(startDateTime, endDateTime);
    }

    public void saveAlarm(String alarmTitle, String alarmContent, LocalDateTime alarmSchedule) {
        Alarm alarm = Alarm.builder().title(alarmTitle).content(alarmContent).alarmSchedule(alarmSchedule).build();
        alarmRepository.save(alarm);
    }

    public List<Alarm> getAllNotSentAlarm(LocalDateTime startTime, LocalDateTime endTime) {
        List<Alarm> alarms = alarmRepository.findAllNotSentAlarmsByTime(startTime, endTime);
        log.info("발송하지 않았던 알람 조회: {}", alarms);
        return alarms;
    }

    @Transactional
    public void updateAlarmSendFlagToCompleted(Alarm alarm) {
        alarm.updateAlarmSendFlagToCompleted();
        alarmRepository.save(alarm);
    }

}
