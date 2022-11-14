package com.mdnf.mdnf_notification_system.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Slf4j
@Profile("local")
@RestController
public class MockApiController {

    @GetMapping("/News/Notice/GetThreads")
    public String mdnfNoticesMockApi() throws IOException {
        ClassPathResource resource = new ClassPathResource("mock-data/mdnf-notice-data.json");
        InputStreamReader is = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);

        try (BufferedReader reader = new BufferedReader(is)) {
            String data = reader.lines().collect(Collectors.joining("\n"));

            return data;
        }
    }

    @GetMapping("/News/Devnote/GetThreads")
    public String mdnfDevNoteMockApi() throws IOException {
        ClassPathResource resource = new ClassPathResource("mock-data/mdnf-devnote-data.json");
        InputStreamReader is = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);

        try (BufferedReader reader = new BufferedReader(is)) {
            String data = reader.lines().collect(Collectors.joining("\n"));

            return data;
        }
    }

    @GetMapping("/News/Update/GetThreads")
    public String mdnfUpdateMockApi() throws IOException {
        ClassPathResource resource = new ClassPathResource("mock-data/mdnf-update-data.json");
        InputStreamReader is = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);

        try (BufferedReader reader = new BufferedReader(is)) {
            String data = reader.lines().collect(Collectors.joining("\n"));

            return data;
        }
    }
}
