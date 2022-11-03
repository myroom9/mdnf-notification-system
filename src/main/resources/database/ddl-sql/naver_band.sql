-- develop.naver_band definition

CREATE TABLE `naver_band` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT,
                              `access_token` varchar(1000) DEFAULT NULL,
                              `band_key` varchar(255) DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;