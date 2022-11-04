package com.mdnf.mdnf_notification_system.scheduler;

import com.mdnf.mdnf_notification_system.domain.MdnfNotice;
import com.mdnf.mdnf_notification_system.feign.dto.MdnfResponse;
import com.mdnf.mdnf_notification_system.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class Schedule {

    private final AlarmService alarmService;
    private final FcmService fcmService;
    private final UserService userService;

    private final MdnfContentService mdnfContentService;
    private final NaverBandService naverBandService;

    // 매분 체크
    // 현재 시간으로 2분 전 ~ 현재 시간 / 발송되지 않은 알람 조건으로 조회
    // 발송
    @Scheduled(fixedDelay = 5000)
    // @Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}") // 문자열 milliseconds 사용 시
    public void sendAlarmSchedule() {
        log.info("돌고있어!");
        /*LocalDateTime startTime = LocalDateTime.now().minusMinutes(2);
        LocalDateTime endTime = startTime.plusMinutes(12);

        List<User> users = userService.getAllUsers();
        List<Alarm> alarms = alarmService.getAllNotSentAlarm(startTime, endTime);

        fcmService.sendAllAlarm(alarms, users);*/
    }

    @Scheduled(fixedDelay = 30000)
    public void watchNoticeContents() {
        MdnfResponse.Notice noticeContents = mdnfContentService.getNoticeContents();
        System.out.println("noticeContents = " + noticeContents);
        List<MdnfNotice> mdnfNotices = MdnfNotice.mdnfNoticeMapper(noticeContents);
        System.out.println("mdnfNotices = " + mdnfNotices);

        List<MdnfNotice> newMdnfNotices = mdnfContentService.checkNewNoticeAndRenewNotice(mdnfNotices);

        if (!ObjectUtils.isEmpty(newMdnfNotices)) {
            naverBandService.writeNaverBandBoardContent(newMdnfNotices);
        }
    }

    @Scheduled(fixedDelay = 30000)
    public void watchDevNoteContents() {
        MdnfResponse.Notice noticeContents = mdnfContentService.getDevNoteContents();
        System.out.println("devnotes = " + noticeContents);
        List<MdnfNotice> mdnfNotices = MdnfNotice.mdnfDevNoteMapper(noticeContents);
        System.out.println("mdnfdevnote = " + mdnfNotices);

        List<MdnfNotice> newMdnfNotices = mdnfContentService.checkNewDevNoteAndRenewDevNote(mdnfNotices);

        if (!ObjectUtils.isEmpty(newMdnfNotices)) {
            naverBandService.writeNaverBandBoardContentDevNote(newMdnfNotices);
        }
    }
}
