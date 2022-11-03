package com.mdnf.mdnf_notification_system.feign.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NaverBandWriteBoardRequest {
    private String access_token;
    private String band_key;
    private String content;
    private boolean do_push;
}
