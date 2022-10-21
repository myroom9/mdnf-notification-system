package com.mdnf.mdnf_notification_system.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "users")
public class User {

    protected User() {}

    @Builder
    public User(String mdnfUserId, String fcmToken) {
        this.mdnfUserId = mdnfUserId;
        this.fcmToken = fcmToken;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mdnf_user_id", length = 255)
    private String mdnfUserId;

    @Column(name = "fcm_token", length = 1000)
    private String fcmToken;
}
