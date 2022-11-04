-- develop.mdnf_notice definition

CREATE TABLE `mdnf_notice` (
                               `id` bigint(20) NOT NULL AUTO_INCREMENT,
                               `board_type` varchar(20) DEFAULT NULL,
                               `create_date` decimal(19,2) DEFAULT NULL,
                               `is_latest_content` bit(1) DEFAULT NULL,
                               `modify_date` decimal(19,2) DEFAULT NULL,
                               `thread_id` int(11) DEFAULT NULL,
                               `thread_modify_date` decimal(19,2) DEFAULT NULL,
                               `title` varchar(100) DEFAULT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;