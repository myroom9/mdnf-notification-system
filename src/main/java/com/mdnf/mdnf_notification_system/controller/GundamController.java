package com.mdnf.mdnf_notification_system.controller;

import com.mdnf.mdnf_notification_system.domain.Gundam;
import com.mdnf.mdnf_notification_system.service.GundamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GundamController {

    private final GundamService gundamService;

    @GetMapping("/gundam")
    public String gundamAlarmRegisterForm() {
        log.info("건담 알람 입고 등록 접근");
        return "gundam/gundam-alarm-register-form";
    }

    @GetMapping("/gundam/list")
    public String gundamAlarmList(Model model) {
        List<Gundam> gundamList = gundamService.getGundamAlarmList();
        log.info("건담 알람 목록 리스트: {}", gundamList);

        model.addAttribute("list", gundamList);

        return "gundam/gundam-alarm-list";
    }
}
