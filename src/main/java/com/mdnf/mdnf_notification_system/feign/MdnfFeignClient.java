package com.mdnf.mdnf_notification_system.feign;

import com.mdnf.mdnf_notification_system.config.FeignConfig;
import com.mdnf.mdnf_notification_system.feign.dto.MdnfResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="mdnf", url = "${feign.request-url.mdnf}", configuration = FeignConfig.class)
public interface MdnfFeignClient {
    @GetMapping(value = "/News/Notice/GetThreads", consumes = MediaType.APPLICATION_JSON_VALUE)
    MdnfResponse.Notice getNoticeContents(@RequestParam(value = "PageNo", defaultValue = "1") int pageNo,
                                          @RequestParam(value = "blockSize", defaultValue = "10") int blockSize);

    @GetMapping(value = "/News/Devnote/GetThreads", consumes = MediaType.APPLICATION_JSON_VALUE)
    MdnfResponse.Notice getDevNoteContents(@RequestParam(value = "PageNo", defaultValue = "1") int pageNo,
                                          @RequestParam(value = "blockSize", defaultValue = "10") int blockSize);

    @GetMapping(value = "/News/Update/GetThreads", consumes = MediaType.APPLICATION_JSON_VALUE)
    MdnfResponse.Notice getUpdateContents(@RequestParam(value = "PageNo", defaultValue = "1") int pageNo,
                                          @RequestParam(value = "blockSize", defaultValue = "10") int blockSize);
}
