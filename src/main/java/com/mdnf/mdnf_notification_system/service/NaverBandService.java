package com.mdnf.mdnf_notification_system.service;

import com.mdnf.mdnf_notification_system.domain.MdnfNotice;
import com.mdnf.mdnf_notification_system.domain.NaverBand;
import com.mdnf.mdnf_notification_system.feign.NaverBandFeignClient;
import com.mdnf.mdnf_notification_system.feign.dto.NaverBandWriteBoardRequest;
import com.mdnf.mdnf_notification_system.feign.dto.NaverBandWriteBoardResponse;
import com.mdnf.mdnf_notification_system.repository.NaverBandRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NaverBandService {
    private final NaverBandRepository naverBandRepository;
    private final NaverBandFeignClient naverBandFeignClient;

    public NaverBand getNaverBandSecret() {
        return naverBandRepository.findById(1L).orElseThrow(IllegalArgumentException::new);
    }

    public void writeNaverBandBoardContent(List<MdnfNotice> newNotices) {
        NaverBand naverBandSecret = getNaverBandSecret();

        newNotices.forEach(o -> {
            NaverBandWriteBoardRequest request = NaverBandWriteBoardRequest.builder()
                    .access_token(naverBandSecret.getAccessToken())
                    .band_key(naverBandSecret.getBandKey())
                    .content("\uD83E\uDD20 NEW 공지사항 발생 \uD83E\uDD20\n제목: " + o.getTitle() + "\nURL: https://dnfm.nexon.com/News/Notice/View/" + o.getThreadId())
                    .do_push(true).build();

            // TODO: response 안됨
            NaverBandWriteBoardResponse.WrapperData response = naverBandFeignClient.getNoticeContents(request);
            System.out.println("response = " + response);

            // band api가 쿨타임이 필요함
            try {
                Thread.sleep(12000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });


    }

    public void writeNaverBandBoardContentDevNote(List<MdnfNotice> newNotices) {
        NaverBand naverBandSecret = getNaverBandSecret();

        newNotices.forEach(o -> {
            NaverBandWriteBoardRequest request = NaverBandWriteBoardRequest.builder()
                    .access_token(naverBandSecret.getAccessToken())
                    .band_key(naverBandSecret.getBandKey())
                    .content("\uD83E\uDD20 NEW 개발자노트 발생 \uD83E\uDD20\n제목: " + o.getTitle() + "\nURL: https://dnfm.nexon.com/News/Devnote/View/" + o.getThreadId())
                    .do_push(true).build();

            // TODO: response 안됨
            NaverBandWriteBoardResponse.WrapperData response = naverBandFeignClient.getNoticeContents(request);
            System.out.println("response = " + response);

            // band api가 쿨타임이 필요함
            try {
                Thread.sleep(12000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });


    }
}
