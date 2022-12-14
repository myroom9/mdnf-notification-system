package com.mdnf.mdnf_notification_system.repository;

import com.mdnf.mdnf_notification_system.domain.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    @Query("SELECT a FROM Alarm a WHERE a.alarmSchedule BETWEEN :startTime AND :endTime")
    List<Alarm> findAllAlarmsByTime(LocalDateTime startTime, LocalDateTime endTime);

    @Query("SELECT a FROM Alarm a WHERE a.alarmSchedule BETWEEN :startTime AND :endTime AND a.sendFlag = false")
    List<Alarm> findAllNotSentAlarmsByTime(LocalDateTime startTime, LocalDateTime endTime);
}
