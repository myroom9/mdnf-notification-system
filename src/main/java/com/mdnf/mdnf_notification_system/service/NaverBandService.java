package com.mdnf.mdnf_notification_system.service;

import com.mdnf.mdnf_notification_system.domain.MdnfNotice;
import com.mdnf.mdnf_notification_system.domain.NaverBand;
import com.mdnf.mdnf_notification_system.feign.NaverBandFeignClient;
import com.mdnf.mdnf_notification_system.feign.dto.NaverBandWriteBoardRequest;
import com.mdnf.mdnf_notification_system.feign.dto.NaverBandWriteBoardResponse;
import com.mdnf.mdnf_notification_system.repository.NaverBandRepository;
import com.mdnf.mdnf_notification_system.type.BoardType;
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
            String content;
            if (o.getBoardType().equals(BoardType.NOTICE.getBoardType())) {
                content = String.format(BoardType.NOTICE.getContent(), o.getTitle(), o.getThreadId());
            } else {
                content = String.format(BoardType.DEV_NOTE.getContent(), o.getTitle(), o.getThreadId());
            }

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
}
