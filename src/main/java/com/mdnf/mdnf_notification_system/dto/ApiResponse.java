package com.mdnf.mdnf_notification_system.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApiResponse<T> {
    private int status;
    private String code;
    private String message;
    private T data;
    private LocalDateTime responseAt;

    public static <T> ApiResponse<T> success() {
        return ApiResponse.<T>defaultBuilder().build();
    }

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>defaultBuilder()
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> fail() {
        return ApiResponse.<T>builder()
                .status(500)
                .code("50000000")
                .message("fail")
                .data(null)
                .responseAt(LocalDateTime.now()).build();
    }

    private static <T> ApiResponseBuilder<T> defaultBuilder() {
        return ApiResponse.<T>builder()
                .status(200)
                .code("20000000")
                .message("success")
                .data(null)
                .responseAt(LocalDateTime.now());
    }
}

