package com.mdnf.mdnf_notification_system.feign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class NaverBandWriteBoardResponse {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WrapperData {
        @JsonProperty("result_code")
        private int resultCode;
        @JsonProperty("result_data")
        private DetailData resultData;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DetailData {
        @JsonProperty("post_key")
        private String postKey;
        @JsonProperty("band_key")
        private String bandKey;
    }

}
