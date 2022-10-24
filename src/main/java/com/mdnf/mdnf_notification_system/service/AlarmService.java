package com.mdnf.mdnf_notification_system.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.gson.JsonParseException;
import com.mdnf.mdnf_notification_system.domain.Alarm;
import com.mdnf.mdnf_notification_system.domain.User;
import com.mdnf.mdnf_notification_system.dto.FcmMessage;
import com.mdnf.mdnf_notification_system.repository.AlarmRepository;
import com.mdnf.mdnf_notification_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlarmService {

    private final AlarmRepository alarmRepository;


    public void saveAlarm(String alarmTitle, String alarmContent, LocalDateTime alarmSchedule) {
        Alarm alarm = Alarm.builder().title(alarmTitle).content(alarmContent).alarmSchedule(alarmSchedule).build();
        alarmRepository.save(alarm);
    }

    public List<Alarm> getAllNotSentAlarm(LocalDateTime startTime, LocalDateTime endTime) {
        List<Alarm> alarms = alarmRepository.findAllNotSentAlarmByTime(startTime, endTime);
        log.info("발송하지 않았던 알람 조회: {}", alarms);
        return alarms;
    }

    @Transactional
    public void updateAlarmSendFlagToCompleted(Alarm alarm) {
        alarm.updateAlarmSendFlagToCompleted();
        alarmRepository.save(alarm);
    }

}
