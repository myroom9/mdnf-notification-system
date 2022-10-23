package com.mdnf.mdnf_notification_system.controller;

import com.mdnf.mdnf_notification_system.dto.request.UserSignUpRequestDto;
import com.mdnf.mdnf_notification_system.service.FcmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FcmController {

    private final FcmService fcmService;

    // https://phantagram.cafe24.com/test?title="whahn"&message="오늘은 몽환하는날!"&token=fD6MwUGBnc4:APA91bEcIS9R1MbY06Qcw-_nkiSnF3kiQzx7TX5pVr4MxqNNLwi16EvB9fSJ7XbGLGTQaeii4965buM6UIREIBjsplDUrDAGhS4cCPpCOuGqZNzZT-D_lcCTht3WLz2IhPOCnUI3BU_v
    // https://phantagram.cafe24.com/test?title="whahn"&message="test"&token=c1UkpWO9LQI:APA91bHWwIkiNHAVn9cdjAyBKTxLvA2JKlSJt4C0vt-CoIhxQ6b-vAB2Uqs4RTA-h6h1kAMeGamqYrhy6oj4mYu_gQs-mjr0yaM4AkEPqBALkhwdRpMHksfihEGAfPjDBvfAv4CoH1ji
    @ResponseBody
    @GetMapping("/test")
    public String test(@RequestParam("title") String title, @RequestParam("message") String message,
                       @RequestParam("token") String token) throws IOException {
        fcmService.sendMessage(title, message, token);
        return "success";
    }

    /**
     * fcm 토큰 저장
     */
    @ResponseBody
    @PostMapping("/fcm/token")
    public String test(@RequestParam("mdnfUserId") String mdnfUserId, @RequestParam("token") String token) {
        fcmService.saveUser(mdnfUserId, token);
        return "success";
    }

    /**
     * 메인페이지
     */
    @GetMapping("/fcm/sign-up")
    public String signUpPage() {
        return "sign-up";
    }

    /**
     * 사용자등록
     */
    @ResponseBody
    @PostMapping("/fcm/sign-up")
    public String signUp(UserSignUpRequestDto request) {

        log.info("가입 요청 정보: {}", request);

        fcmService.saveUser(request.getMdnfUserId(), request.getFcmToken());

        return "success";
    }

    @GetMapping("/test-page")
    public String testPage() {
        // fcmService.sendMessage("test");
        return "fcm-test";
    }
}
