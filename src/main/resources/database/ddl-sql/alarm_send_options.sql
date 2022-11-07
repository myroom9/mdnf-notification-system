-- develop.alarm_send_options definition

CREATE TABLE `alarm_send_options` (
                                      `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                      `alarm_type` varchar(255) DEFAULT NULL,
                                      `send_flag` bit(1) DEFAULT NULL,
                                      PRIMARY KEY (`id`),
                                      UNIQUE KEY `UK_dhgoqr9x6d33amf1rynr4y3g7` (`alarm_type`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;