package com.mdnf.mdnf_notification_system.controller;

import com.mdnf.mdnf_notification_system.dto.request.AlarmRegisterRequestDto;
import com.mdnf.mdnf_notification_system.service.AlarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AlarmController {

    private final AlarmService alarmService;

    @GetMapping("/alarm/schedule")
    public String schedule() {
        return "alarm-schedule";
    }

    @GetMapping("/alarm/register")
    public String scheduleRegisterForm() {
        return "alarm-register-form";
    }

    @ResponseBody
    @PostMapping("/alarm/register")
    public String scheduleRegister(AlarmRegisterRequestDto request) {
        log.info("알람 등록 요청: {}", request);

        alarmService.saveAlarm(request.getAlarmTitle(), request.getAlarmSchedule());

        return "success";
    }
}
