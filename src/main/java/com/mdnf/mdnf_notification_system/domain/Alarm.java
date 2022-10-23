package com.mdnf.mdnf_notification_system.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "alarms")
public class Alarm {

    protected Alarm() {}

    @Builder
    public Alarm(String alarmTitle, LocalDateTime alarmSchedule) {
        this.alarmTitle = alarmTitle;
        this.alarmSchedule = alarmSchedule;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 255)
    private String alarmTitle;

    @Column(name = "schedule")
    private LocalDateTime alarmSchedule;
}
