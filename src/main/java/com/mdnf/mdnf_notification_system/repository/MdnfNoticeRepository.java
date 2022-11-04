package com.mdnf.mdnf_notification_system.repository;

import com.mdnf.mdnf_notification_system.domain.MdnfNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MdnfNoticeRepository extends JpaRepository<MdnfNotice, Long> {

    @Query("SELECT a FROM MdnfNotice a WHERE a.isLatestContent = true AND a.boardType = 'notice'")
    MdnfNotice findLatestNoticeContent();

    @Query("SELECT a FROM MdnfNotice a WHERE a.isLatestContent = true AND a.boardType = 'dev-note'")
    MdnfNotice findLatestDevNoteContent();
}
