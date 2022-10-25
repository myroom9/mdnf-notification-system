package com.mdnf.mdnf_notification_system.repository;

import com.mdnf.mdnf_notification_system.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByFcmToken(String fcmToken);


    void deleteByFcmToken(String fcmToken);
}
