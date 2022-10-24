package com.mdnf.mdnf_notification_system.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @ToString @Setter
@Entity
@DynamicUpdate
@Table(name = "alarms")
public class Alarm {

    protected Alarm() {}

    @Builder
    public Alarm(String title, String content, LocalDateTime alarmSchedule) {
        this.title = title;
        this.content = content;
        this.alarmSchedule = alarmSchedule;
    }

    public void updateAlarmSendFlagToCompleted() {
        this.sendFlag = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "content", length = 100)
    private String content;

    @Column(name = "sendFlag")
    private boolean sendFlag;

    @Column(name = "schedule")
    private LocalDateTime alarmSchedule;
}
