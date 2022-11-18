package com.mdnf.mdnf_notification_system.service;

import com.mdnf.mdnf_notification_system.domain.Etc;
import com.mdnf.mdnf_notification_system.domain.User;
import com.mdnf.mdnf_notification_system.repository.EtcRepository;
import com.mdnf.mdnf_notification_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EtcService {
    private final EtcRepository etcRepository;

    public Etc getEtc(String type) {
        return etcRepository.findByType(type);
    }
}
