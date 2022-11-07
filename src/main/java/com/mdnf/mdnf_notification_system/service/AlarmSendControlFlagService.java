package com.mdnf.mdnf_notification_system.service;

import com.mdnf.mdnf_notification_system.domain.AlarmSendOption;
import com.mdnf.mdnf_notification_system.repository.AlarmSendControlFlagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlarmSendControlFlagService {

    private final AlarmSendControlFlagRepository alarmSendControlFlagRepository;

    @Transactional
    public void changeAlarmSendOption(boolean flag, String type) {
        AlarmSendOption alarmSendFlagInfo = alarmSendControlFlagRepository.findByAlarmType(type);

        if (!ObjectUtils.isEmpty(alarmSendFlagInfo)) {
            if (flag) {
                alarmSendFlagInfo.alarmSendOn();
            } else {
                alarmSendFlagInfo.alarmSendOff();
            }
        }
    }

    @Transactional(readOnly = true)
    public AlarmSendOption getAlarmSendOption(String type) {
        return alarmSendControlFlagRepository.findByAlarmType(type);
    }
}
