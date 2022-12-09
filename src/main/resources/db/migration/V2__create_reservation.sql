create table reservation (
    reservation_id bigint auto_increment primary key,
    trainer_id varchar(30) not null,
    user_phone varchar(20) not null,
    start_time datetime not null,
    end_time datetime not null,
    description varchar(1000) not null,
    usage_state int default -1,
    ins_dtm datetime not null,
    upd_dtm datetime not null
);
