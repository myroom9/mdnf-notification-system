-- develop.alarms definition

CREATE TABLE `alarms` (
                          `id` bigint(20) NOT NULL AUTO_INCREMENT,
                          `content` varchar(100) DEFAULT NULL,
                          `schedule` datetime DEFAULT NULL,
                          `title` varchar(255) DEFAULT NULL,
                          `send_flag` bit(1) DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;