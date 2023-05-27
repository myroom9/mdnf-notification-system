package com.mdnf.mdnf_notification_system.domain;

import com.mdnf.mdnf_notification_system.dto.gundam.request.RegisterGundamSite;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString
@Entity
@DynamicUpdate
@Table(name = "gundam")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Gundam extends BaseEntity {

    @Builder
    public Gundam(String title, String nickname, String url, boolean isSoldOut, boolean isSendAlarm, LocalDateTime soldOutDatetime) {
        this.title = title;
        this.nickname = nickname;
        this.url = url;
        this.isSoldOut = isSoldOut;
        this.isSendAlarm = isSendAlarm;
        this.soldOutDatetime = soldOutDatetime;
    }

    /**
     * 구매 가능 상태 세팅
     */
    public void setNotSoldOutGoods() {
        this.isSoldOut = false;
        this.isSendAlarm = false;
    }

    /**
     * 품절 상태 세팅
     */
    public void setSoldOutGoods() {
        this.isSoldOut = true;
        this.isSendAlarm = false;
    }

    /**
     * 알람 발송 완료 상태 변경
     */
    public void setSendAlarmComplete() {
        this.isSendAlarm = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 500)
    private String title;

    @Column(name = "nickname", length = 500)
    private String nickname;

    @Column(name = "url", length = 3000)
    private String url;

    @Column(name = "is_sold_out", columnDefinition = "tinyint(1) default 1")
    private boolean isSoldOut;

    @Column(name = "is_send_alarm", columnDefinition = "tinyint(1) default 1")
    private boolean isSendAlarm;

    @Column(name = "sold_out_datetime")
    private LocalDateTime soldOutDatetime;
}
