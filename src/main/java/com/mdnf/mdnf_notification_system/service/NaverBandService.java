package com.mdnf.mdnf_notification_system.service;

import com.mdnf.mdnf_notification_system.domain.*;
import com.mdnf.mdnf_notification_system.feign.NaverBandFeignClient;
import com.mdnf.mdnf_notification_system.feign.dto.NaverBandWriteBoardRequest;
import com.mdnf.mdnf_notification_system.feign.dto.NaverBandWriteBoardResponse;
import com.mdnf.mdnf_notification_system.repository.GundamRepository;
import com.mdnf.mdnf_notification_system.repository.NaverBandRepository;
import com.mdnf.mdnf_notification_system.type.BoardType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class NaverBandService {
    private final AlarmService alarmService;
    private final AlarmSendControlFlagService alarmSendControlFlagService;
    private final NaverBandRepository naverBandRepository;
    private final GundamRepository gundamRepository;
    private final NaverBandFeignClient naverBandFeignClient;

    public NaverBand getNaverBandSecret() {
        return naverBandRepository.findById(1L).orElseThrow(IllegalArgumentException::new);
    }

    public void writeNaverBandManualAlarm(List<Alarm> alarms) {
        NaverBand naverBandSecret = getNaverBandSecret();

        AlarmSendOption alarmSendOption = alarmSendControlFlagService.getAlarmSendOption("manual");

        if (alarmSendOption.isSendFlag()) {
            alarms.forEach(o -> {

                NaverBandWriteBoardRequest request = NaverBandWriteBoardRequest.builder()
                        .access_token(naverBandSecret.getAccessToken())
                        .band_key(naverBandSecret.getBandKey())
                        .content(o.getContent())
                        .do_push(true).build();

                log.info("네이버 밴드 수동 알람 발송 데이터: {}", request);

                // TODO: response 안됨
                NaverBandWriteBoardResponse.WrapperData response = naverBandFeignClient.getNoticeContents(request);

                alarmService.updateAlarmSendFlagToCompleted(o);

                // band api가 쿨타임이 필요함
                try {
                    Thread.sleep(12000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    public void writeNaverBandBoardContent(List<MdnfNotice> newNotices, BoardType boardType) {
        NaverBand naverBandSecret = getNaverBandSecret();
        AtomicBoolean sendFlag = new AtomicBoolean(true);

        IntStream.range(0, newNotices.size())
                 .forEach(i -> {

                     MdnfNotice o = newNotices.get(i);

                     if (i == 0) {
                         AlarmSendOption alarmSendOption = alarmSendControlFlagService.getAlarmSendOption(o.getBoardType().getBoardType());
                         sendFlag.set(alarmSendOption.isSendFlag());
                     }

                     if (Boolean.FALSE.equals(sendFlag.get())) {
                         return ;
                     }

                     String content = String.format(boardType.getContent(), o.getTitle(), o.getThreadId());

                     NaverBandWriteBoardRequest request = NaverBandWriteBoardRequest.builder()
                             .access_token(naverBandSecret.getAccessToken())
                             .band_key(naverBandSecret.getBandKey())
                             .content(content)
                             .do_push(true).build();

                     log.info("네이버 밴드 알람 발송 데이터: {}", request);

                     // TODO: response 안됨
                     NaverBandWriteBoardResponse.WrapperData response = naverBandFeignClient.getNoticeContents(request);

                     // band api가 쿨타임이 필요함
                     try {
                         Thread.sleep(12000);
                     } catch (InterruptedException e) {
                         throw new RuntimeException(e);
                     }
                 });
    }

    @Transactional
    public void sendGundamAlarm() {
        NaverBand naverBandSecret = getNaverBandSecret();
        List<Gundam> gundamList = gundamRepository.findAllByDeletedAtIsNullAndSendAlarmIsFalse();

        for(Gundam o : gundamList) {

            String soldOutStatus = o.isSoldOut() ? "품절" : "입고완료";

            String content = o.getTitle() + "(" + o.getNickname() + ")의 상품 상태가 변경되었습니다.\n" +
                    "상품상태: " + soldOutStatus  + "\n" +
                    "상품URL: " + o.getUrl();

            NaverBandWriteBoardRequest request = NaverBandWriteBoardRequest.builder()
                    .access_token(naverBandSecret.getAccessToken())
                    .band_key(naverBandSecret.getBandKey())
                    .content(content)
                    .do_push(true).build();

            log.info("네이버 밴드 알람 발송 데이터: {}", request);

            NaverBandWriteBoardResponse.WrapperData response = naverBandFeignClient.getNoticeContents(request);

            o.setSendAlarmComplete();
            gundamRepository.saveAndFlush(o);

            // band api가 쿨타임이 필요함
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
