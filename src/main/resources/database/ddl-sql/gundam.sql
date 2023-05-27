create table gundam (
                        id bigint not null auto_increment,
                        created_at datetime not null,
                        deleted_at datetime,
                        updated_at datetime,
                        is_send_alarm tinyint(1) default 1,
                        is_sold_out tinyint(1) default 1,
                        nickname varchar(500),
                        sold_out_datetime datetime,
                        title varchar(500),
                        url varchar(3000),
                        primary key (id)
) engine=InnoDB  DEFAULT CHARSET=utf8mb4;