package com.mdnf.mdnf_notification_system.repository;

import com.mdnf.mdnf_notification_system.domain.Alarm;
import com.mdnf.mdnf_notification_system.domain.MdnfNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface MdnfNoticeRepository extends JpaRepository<MdnfNotice, Long> {

    @Query("SELECT a FROM MdnfNotice a WHERE a.isLatestContent = true")
    MdnfNotice findLatestContent();
}
