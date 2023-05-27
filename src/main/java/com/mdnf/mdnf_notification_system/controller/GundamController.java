package com.mdnf.mdnf_notification_system.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GundamController {

    @GetMapping("/gundam")
    public String gundamAlarmRegisterForm() {
        log.info("건담 알람 입고 등록 접근");
        return "gundam/alarm-register-form";
    }

}
