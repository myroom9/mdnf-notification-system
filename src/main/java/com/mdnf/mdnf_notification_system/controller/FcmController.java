package com.mdnf.mdnf_notification_system.controller;

import com.mdnf.mdnf_notification_system.domain.User;
import com.mdnf.mdnf_notification_system.dto.ApiResponse;
import com.mdnf.mdnf_notification_system.dto.request.UserSignUpRequestDto;
import com.mdnf.mdnf_notification_system.service.FcmService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FcmController {

    private final FcmService fcmService;

    @GetMapping("/")
    public String main() {
        return "redirect:/sign-up";
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
    @GetMapping("/sign-up")
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

        User user = fcmService.getUser(request.getFcmToken()).orElse(null);

        if (ObjectUtils.isEmpty(user)) {
            fcmService.saveUser(request.getMdnfUserId(), request.getFcmToken());
        }

        return "success";
    }

    @ResponseBody
    @DeleteMapping("/fcm/sign-out")
    public ApiResponse fcmSignOut(@RequestParam("fcmToken") String fcmToken) {
        fcmService.deleteUser(fcmToken);
        return ApiResponse.success();
    }

    @GetMapping("/test-page")
    public String testPage() {
        // fcmService.sendMessage("test");
        return "fcm-test";
    }
}
