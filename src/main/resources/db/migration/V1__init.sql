create table user (
    user_phone varchar(20) primary key,
    user_password varchar(255) not null,
    user_name varchar(255) not null,
    user_gender varchar(8) not null,
    user_birth date not null,
    ins_dtm datetime not null,
    upd_dtm datetime not null,
    role varchar(20) not null
);

create table trainer (
    trainer_id varchar(30) primary key,
    trainer_password varchar(255) not null,
    trainer_name varchar(10) not null,
    trainer_phone varchar(20) not null,
    gym_id bigint not null,
    ins_dtm datetime not null,
    upd_dtm datetime not null,
    role varchar(20) not null
);

create table registration (
    registration_id bigint auto_increment primary key,
    gym_id bigint not null,
    trainer_id varchar(30) not null,
    user_phone varchar(20) not null,
    total int not null,
    remaining int not null,
    date_of_use date not null,
    ins_dtm datetime not null,
    upd_dtm datetime not null
);

