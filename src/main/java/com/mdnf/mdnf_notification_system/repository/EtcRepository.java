package com.mdnf.mdnf_notification_system.repository;

import com.mdnf.mdnf_notification_system.domain.Etc;
import com.mdnf.mdnf_notification_system.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EtcRepository extends JpaRepository<Etc, Long> {
    Etc findByType(String type);
}
