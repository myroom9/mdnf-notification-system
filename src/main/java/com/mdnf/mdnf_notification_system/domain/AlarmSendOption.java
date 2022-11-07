package com.mdnf.mdnf_notification_system.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter @ToString @Setter
@Entity
@DynamicUpdate
@Table(name = "alarm_send_options")
public class AlarmSendOption {

    protected AlarmSendOption() {}

    public void alarmSendOn() {
        this.sendFlag = true;
    }

    public void alarmSendOff() {
        this.sendFlag = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "alarm_type", unique = true)
    private String alarmType;

    @Column(name = "send_flag")
    private boolean sendFlag;
}
