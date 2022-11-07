package com.mdnf.mdnf_notification_system.repository;

import com.mdnf.mdnf_notification_system.domain.AlarmSendOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AlarmSendControlFlagRepository extends JpaRepository<AlarmSendOption, Long> {
    @Query("SELECT a FROM AlarmSendOption a WHERE a.alarmType =:alarmType")
    AlarmSendOption findByAlarmType(String alarmType);
}
