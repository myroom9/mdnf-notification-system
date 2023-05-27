package com.mdnf.mdnf_notification_system.repository;

import com.mdnf.mdnf_notification_system.domain.Gundam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GundamRepository extends JpaRepository<Gundam, Long> {
    @Query("SELECT a FROM Gundam a WHERE a.isSendAlarm = false AND a.deletedAt IS NULL")
    List<Gundam> findAllByDeletedAtIsNullAndSendAlarmIsFalse();
}
