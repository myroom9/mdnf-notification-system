package com.mdnf.mdnf_notification_system.service;

import com.mdnf.mdnf_notification_system.domain.Alarm;
import com.mdnf.mdnf_notification_system.dto.request.AlarmRegisterRequestDto;
import com.mdnf.mdnf_notification_system.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlarmService {

    private final AlarmRepository alarmRepository;

    public List<Alarm> getAllAlarms(LocalDate currentDate) {
        LocalDateTime startDateTime = LocalDateTime.of(currentDate.getYear(), currentDate.getMonth(), 1, 0, 0, 0);
        LocalDateTime endDateTime = LocalDateTime.of(currentDate.getYear(), currentDate.getMonth(), currentDate.getMonth().length(currentDate.isLeapYear()), 23, 59, 59);
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

    public void saveRotateAlarm(AlarmRegisterRequestDto request) {
        LocalDateTime requestScheduleTime = request.getRotateAlarmTime();
        LocalDateTime now = LocalDateTime.now();
        List<DayOfWeek> days = Arrays.stream(request.getCheckedDays())
                .mapToObj(DayOfWeek::of)
                .collect(Collectors.toList());
        LocalDateTime firstOfNextMonth = requestScheduleTime.with(TemporalAdjusters.firstDayOfNextMonth());
        ArrayList<Alarm> alarms = new ArrayList<>();

        days.forEach(o -> {
            LocalDateTime eachDaysScheduleTime = requestScheduleTime;
            DayOfWeek nowDay = now.getDayOfWeek();

            // 조건에 부합하면, nextWeek부터 진행
            if (o.compareTo(nowDay) > 0) {
                eachDaysScheduleTime = eachDaysScheduleTime.with(TemporalAdjusters.next(o));
            }

            if (eachDaysScheduleTime.isBefore(now)) {
                eachDaysScheduleTime = eachDaysScheduleTime.with(TemporalAdjusters.next(o));
            }

            while(eachDaysScheduleTime.isBefore(firstOfNextMonth)) {
                // alarmSendTime
                alarms.add(
                        Alarm.builder()
                                .title(request.getAlarmTitle())
                                .content(request.getAlarmContent())
                                .alarmSchedule(eachDaysScheduleTime).build()
                );

                eachDaysScheduleTime = eachDaysScheduleTime.with(TemporalAdjusters.next(o));
            }
        });
        log.info("복수 알람 등록 데이터: {}", alarms);
        alarmRepository.saveAll(alarms);
    }

}
