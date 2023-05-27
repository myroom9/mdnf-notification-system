package com.mdnf.mdnf_notification_system.scheduler;

import com.mdnf.mdnf_notification_system.service.AlarmService;
import com.mdnf.mdnf_notification_system.service.GundamService;
import com.mdnf.mdnf_notification_system.service.NaverBandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GundamScheduler {

    private final GundamService gundamService;
    private final NaverBandService naverBandService;

    @Scheduled(fixedDelay = 300000)
    public void checkSoldOutSite() {
        gundamService.checkSoldOutSite();
    }

    @Scheduled(fixedDelay = 300000)
    public void sendAlarm() {
        naverBandService.sendGundamAlarm();
    }
}
