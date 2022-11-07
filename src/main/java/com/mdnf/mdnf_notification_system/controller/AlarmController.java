package com.mdnf.mdnf_notification_system.controller;

import com.mdnf.mdnf_notification_system.domain.Alarm;
import com.mdnf.mdnf_notification_system.dto.ApiResponse;
import com.mdnf.mdnf_notification_system.dto.request.AlarmRegisterRequestDto;
import com.mdnf.mdnf_notification_system.service.AlarmSendControlFlagService;
import com.mdnf.mdnf_notification_system.service.AlarmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AlarmController {

    private final AlarmService alarmService;

    private final AlarmSendControlFlagService alarmSendControlFlagService;

    @GetMapping("/alarm/schedule")
    public String schedule() {
        // alarmService.getAllAlarms();

        return "alarm-schedule";
    }

    @ResponseBody
    @GetMapping("/alarm/schedule-data")
    public ApiResponse getScheduleData(@RequestParam(value = "currentDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate currentDate) {
        log.info("알람 조회 요청 {}", currentDate);

        List<Alarm> alarms = alarmService.getAllAlarms(currentDate);

        return ApiResponse.success(alarms);
    }

    @GetMapping("/alarm/register")
    public String scheduleRegisterForm() {
        return "alarm-register-form";
    }

    @ResponseBody
    @PostMapping("/alarm/register")
    public String scheduleRegister(AlarmRegisterRequestDto request) {
        log.info("알람 등록 요청: {}", request);

        // TODO : 일단 단건 알람만 저장 / 복수 알람 추가해야됨
        alarmService.saveAlarm(request.getAlarmTitle(), request.getAlarmContent(), request.getSingleAlarmTime());

        return "success";
    }

    @GetMapping("/alarm/option")
    public String alarmOptionPage() {
        return "alarm-option";
    }

    @ResponseBody
    @GetMapping("/alarm/option/change")
    public String alarmOptionPage(@RequestParam("flag") boolean sendFlag,
                                  @RequestParam("type") String type) {

        alarmSendControlFlagService.changeAlarmSendOption(sendFlag, type);

        return "success";
    }
}