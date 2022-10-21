package com.mdnf.mdnf_notification_system.repository;

import com.mdnf.mdnf_notification_system.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
