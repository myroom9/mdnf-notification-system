package com.mdnf.mdnf_notification_system.controller;

import com.mdnf.mdnf_notification_system.service.FcmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class FcmController {

    private final FcmService fcmService;

    @ResponseBody
    @GetMapping("/test")
    public String test() {
        fcmService.sendMessage("test");
        return "success";
    }

    @GetMapping("/test-page")
    public String testPage() {
        // fcmService.sendMessage("test");
        return "fcm-test";
    }
}
