package com.mdnf.mdnf_notification_system.feign;

import com.mdnf.mdnf_notification_system.config.FeignConfig;
import com.mdnf.mdnf_notification_system.feign.dto.MdnfResponse;
import com.mdnf.mdnf_notification_system.feign.dto.NaverBandWriteBoardRequest;
import com.mdnf.mdnf_notification_system.feign.dto.NaverBandWriteBoardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//https://openapi.band.us/v2.2/band/post/create
@FeignClient(name="naverBand", url = "${feign.request-url.naver-band}", configuration = FeignConfig.class)
public interface NaverBandFeignClient {
    @PostMapping(value = "/v2.2/band/post/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    NaverBandWriteBoardResponse.WrapperData getNoticeContents(NaverBandWriteBoardRequest request);
}
