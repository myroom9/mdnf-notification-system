package com.mdnf.mdnf_notification_system.config;

import feign.Logger;
import feign.codec.Decoder;
import feign.gson.GsonDecoder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(basePackages = "com.mdnf.mdnf_notification_system.feign")
@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }

    @Bean
    Decoder feignDecoder() {
        return new GsonDecoder();
    }
}
