package com.mdnf.mdnf_notification_system.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSignUpRequestDto {
    private String mdnfUserId;
    private String fcmToken;
}
