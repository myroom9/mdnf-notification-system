package com.mdnf.mdnf_notification_system.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FcmController {



    @GetMapping("/test")
    public String test() {
        return "success";
    }
}
