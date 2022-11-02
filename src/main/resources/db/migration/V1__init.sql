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

