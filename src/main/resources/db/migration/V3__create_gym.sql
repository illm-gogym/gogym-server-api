create table gym (
    gym_id varchar(255) primary key,
    gym_name varchar(255) not null,
    gym_tel varchar(255) not null,
    gym_address varchar(255) not null,
    ins_dtm datetime not null,
    upd_dtm datetime not null
);