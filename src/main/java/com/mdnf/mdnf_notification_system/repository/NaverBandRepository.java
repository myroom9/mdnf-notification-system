package com.mdnf.mdnf_notification_system.repository;

import com.mdnf.mdnf_notification_system.domain.MdnfNotice;
import com.mdnf.mdnf_notification_system.domain.NaverBand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NaverBandRepository extends JpaRepository<NaverBand, Long> {

}
