package com.mdnf.mdnf_notification_system.controller;

import com.mdnf.mdnf_notification_system.domain.Alarm;
import com.mdnf.mdnf_notification_system.domain.Etc;
import com.mdnf.mdnf_notification_system.dto.ApiResponse;
import com.mdnf.mdnf_notification_system.dto.request.AlarmRegisterRequestDto;
import com.mdnf.mdnf_notification_system.service.AlarmSendControlFlagService;
import com.mdnf.mdnf_notification_system.service.AlarmService;
import com.mdnf.mdnf_notification_system.service.EtcService;
import com.mdnf.mdnf_notification_system.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AlarmController {

    private final AlarmService alarmService;

    private final AlarmSendControlFlagService alarmSendControlFlagService;

    private final EtcService etcService;

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

    @GetMapping("/alarm/password")
    public String alarmPasswordPage() {
        return "alarm-password";
    }

    @GetMapping("/alarm/register")
    public String scheduleRegisterForm(@RequestParam("password") String password,
                                       HttpServletResponse response) {
        // TODO: 배포할 떄 db 값 넣어야되는거 잊지마
        Etc alarmRegisterSecretKey = etcService.getEtc("alarmRegisterSecretKey");
        if (password.equals(alarmRegisterSecretKey.getExtra1())) {
            String jwt = CookieUtil.createJwtCookie(alarmRegisterSecretKey.getExtra1());

            Cookie cookie = CookieUtil.createCookie("alarm-register", jwt, 60 * 60 * 100);
            response.addCookie(cookie);

            return "alarm-register-form";
        }

        throw new IllegalArgumentException("알람 등록 패스워드가 일치하지 않습니다.");
    }

    @ResponseBody
    @PostMapping("/alarm/register")
    public String scheduleRegister(AlarmRegisterRequestDto request,
                                   @CookieValue("alarm-register") Cookie cookie) {
        log.info("알람 등록 요청: {}", request);
        log.info("알람 등록 요청: {}", cookie);
        Etc alarmRegisterSecretKey = etcService.getEtc("alarmRegisterSecretKey");

        boolean b = (boolean) CookieUtil.verifyCookieAndGetDataByKey(cookie.getValue(), alarmRegisterSecretKey.getExtra1(), "is_password_checked");
        if (b) {
            if (request.getNotificationWay().equals("SINGLE")) {
                alarmService.saveAlarm(request.getAlarmTitle(), request.getAlarmContent(), request.getSingleAlarmTime());
            } else if (request.getNotificationWay().equals("ROTATE")){
                alarmService.saveRotateAlarm(request);
            }
            return "success";
        }

        throw new IllegalArgumentException("알람 등록 가능 쿠기가 존재하지 않습니다.");
    }

    @GetMapping("/alarm/option")
    public String alarmOptionPage() {

        return "alarm-option";
    }

    @ResponseBody
    @GetMapping("/alarm/option/change")
    public String alarmOptionPage(@RequestParam("flag") boolean sendFlag,
                                  @RequestParam("type") String type,
                                  @CookieValue("alarm-register") Cookie cookie) {
        Etc alarmRegisterSecretKey = etcService.getEtc("alarmRegisterSecretKey");

        boolean b = (boolean) CookieUtil.verifyCookieAndGetDataByKey(cookie.getValue(), alarmRegisterSecretKey.getExtra1(), "is_password_checked");

        if (b) {
            alarmSendControlFlagService.changeAlarmSendOption(sendFlag, type);

            return "success";
        }

        throw new IllegalArgumentException("알람 등록 가능 쿠기가 존재하지 않습니다.");
    }
}