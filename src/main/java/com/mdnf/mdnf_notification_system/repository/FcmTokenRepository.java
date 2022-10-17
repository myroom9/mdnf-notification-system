package com.mdnf.mdnf_notification_system.repository;

import com.mdnf.mdnf_notification_system.domain.FcmToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FcmTokenRepository extends JpaRepository<FcmToken, Long> {

}
