CREATE TABLE `etcs` (
                        `id` bigint(20) NOT NULL AUTO_INCREMENT,
                        `extra1` varchar(1000) DEFAULT NULL,
                        `extra2` varchar(1000) DEFAULT NULL,
                        `extra3` varchar(1000) DEFAULT NULL,
                        `type` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;