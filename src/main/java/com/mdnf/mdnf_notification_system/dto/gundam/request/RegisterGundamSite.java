package com.mdnf.mdnf_notification_system.dto.gundam.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterGundamSite {
    private String title;
    private String nickname;
    private String url;
}
