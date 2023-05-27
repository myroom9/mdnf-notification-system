package com.mdnf.mdnf_notification_system.api;

import com.mdnf.mdnf_notification_system.dto.ApiResponse;
import com.mdnf.mdnf_notification_system.dto.gundam.request.RegisterGundamSite;
import com.mdnf.mdnf_notification_system.service.GundamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GundamApiController {

    private final GundamService gundamService;

    @PostMapping("/gundam")
    public ApiResponse registerGundamAlarm(RegisterGundamSite request) {
        log.info("건담 입고 체크 사이트 등록 요청: {}", request);
        gundamService.registerGundamSite(request);
        log.info("건담 입고 체크 사이트 등록 완료");
        return ApiResponse.success();
    }

}
