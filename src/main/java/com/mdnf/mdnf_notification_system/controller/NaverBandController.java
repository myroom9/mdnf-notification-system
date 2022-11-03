package com.mdnf.mdnf_notification_system.controller;


import com.mdnf.mdnf_notification_system.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/naver-band")
@RequiredArgsConstructor
public class NaverBandController {
    @PostMapping("/board-content")
    public ApiResponse createBoardContent() {
        return ApiResponse.success();
    }
}
