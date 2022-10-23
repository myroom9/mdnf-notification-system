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
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlarmService {

    private final AlarmRepository alarmRepository;


    public void saveAlarm(String alarmTitle, LocalDateTime alarmSchedule) {
        Alarm alarm = Alarm.builder().alarmTitle(alarmTitle).alarmSchedule(alarmSchedule).build();
        alarmRepository.save(alarm);
    }

}
