create table user
(
    user_phone    varchar(20) primary key,
    user_password varchar(255) not null,
    user_name     varchar(255) not null,
    user_gender   varchar(8)   not null,
    user_birth    date         not null,
    ins_dtm       datetime     not null,
    upd_dtm       datetime     not null,
    role          varchar(20)  not null
) CHARACTER SET UTF8;

create table trainer
(
    trainer_id       varchar(30) primary key,
    trainer_password varchar(255) not null,
    trainer_name     varchar(10)  not null,
    trainer_phone    varchar(20)  not null,
    gym_id           bigint       not null,
    ins_dtm          datetime     not null,
    upd_dtm          datetime     not null,
    role             varchar(20)  not null
) CHARACTER SET UTF8;

create table registration
(
    id              bigint auto_increment primary key,
    registration_id varchar(1000) unique,
    gym_id          bigint      not null,
    trainer_id      varchar(30) not null,
    user_phone      varchar(20) not null,
    total           int         not null,
    remaining       int         not null,
    start_date      date        not null,
    end_date        date        not null,
    status          int         not null,
    ins_dtm         datetime    not null,
    upd_dtm         datetime    not null
) CHARACTER SET UTF8;

create table reservation
(
    id              bigint auto_increment primary key,
    reservation_id  varchar(1000) unique,
    registration_id varchar(1000) not null,
    trainer_id      varchar(30)   not null,
    user_phone      varchar(20)   not null,
    start_time      datetime      not null,
    end_time        datetime      not null,
    usage_state     int default -1,
    ins_dtm         datetime      not null,
    upd_dtm         datetime      not null
) CHARACTER SET UTF8;

create table gym
(
    gym_id      varchar(255) primary key,
    gym_name    varchar(255) not null,
    gym_tel     varchar(255) not null,
    gym_address varchar(255) not null,
    ins_dtm     datetime     not null,
    upd_dtm     datetime     not null
) CHARACTER SET UTF8;

create table workout_history
(
    history_id             bigint auto_increment primary key,
    reservation_id         varchar(1000) not null,
    workout_association_id bigint        not null,
    weight                 float         not null,
    weight_count           float         not null,
    weight_set             float         not null,
    ins_dtm                datetime      not null,
    upd_dtm                datetime      not null
) CHARACTER SET UTF8;

create table workout_descriptions
(
    description_id bigint auto_increment primary key,
    reservation_id varchar(1000) not null,
    description    varchar(10000),
    ins_dtm        datetime      not null,
    upd_dtm        datetime      not null
) CHARACTER SET UTF8;

create table workout_code
(
    code    int primary key,
    workout varchar(255) not null,
    ins_dtm datetime     not null,
    upd_dtm datetime     not null
) CHARACTER SET UTF8;

create table workout_target
(
    code    int primary key,
    target  varchar(255) not null,
    ins_dtm datetime     not null,
    upd_dtm datetime     not null
) CHARACTER SET UTF8;

create table workout_association
(
    workout_association_id bigint auto_increment primary key,
    workout_code           int      not null,
    target_code            int      not null,
    ins_dtm                datetime not null,
    upd_dtm                datetime not null
) CHARACTER SET UTF8;

insert into workout_code
values (100, "바벨 플랫 벤치 프레스", now(), now()),
       (101, "바벨 디클라인 벤치 프레스", now(), now()),
       (102, "바벨 인클라인 벤치 프레스", now(), now()),
       (103, "와이드 그립 벤치 프레스", now(), now()),
       (104, "정지 벤치 프레스", now(), now()),

       (200, "바벨 로우", now(), now()),
       (201, "팬들레이 로우", now(), now()),
       (202, "티바 로우", now(), now()),

       (300, "클린 앤 저크", now(), now()),
       (301, "바벨 쓰러스터", now(), now()),
       (302, "오버헤드 프레스", now(), now()),
       (303, "푸시 프레스", now(), now()),
       (304, "시티드 바벨 오버헤드 프레스", now(), now()),
       (305, "바벨 클린 앤 프레스", now(), now()),

       (400, "클로즈 그립 벤치 프레스", now(), now()),
       (401, "바벨 스탠딩 오버헤드 트라이셉 익스텐션", now(), now()),

       (500, "바벨 컬", now(), now()),
       (501, "바벨 클로즈 그립 컬", now(), now()),
       (502, "바벨 와이드 그립 컬", now(), now()),
       (503, "바벨 리버스 컬", now(), now()),
       (504, "스파이더 바벨 컬", now(), now()),

       (800, "바벨 힙 쓰러스트", now(), now()),

       (900, "클린", now(), now()),
       (901, "바벨 루마니안 데드리프트", now(), now()),
       (902, "바벨 스티프 데드리프트", now(), now()),
       (903, "바벨 데드리프트", now(), now()),
       (904, "랙 풀", now(), now()),
       (905, "바벨 스모 데드리프트", now(), now()),
       (906, "바벨 굿 모닝", now(), now()),
       (907, "바벨 스모 루마니안 데드리프트", now(), now()),

       (1000, "스내치", now(), now()),
       (1001, "바벨 런지", now(), now()),
       (1002, "바벨 스쿼트", now(), now()),
       (1003, "바벨 불가리안 스플릿 스쿼트", now(), now()),
       (1004, "바벨 프론트 스쿼트", now(), now()),
       (1005, "바벨 오버헤드 스쿼트", now(), now()),
       (1006, "정지 데드리프트", now(), now()),
       (1007, "정지 스쿼트", now(), now()),
       (1008, "바벨 점프 스쿼트", now(), now()),

       (1100, "바벨 슈러그", now(), now()),
       (1101, "바벨 오버헤드 슈러그", now(), now()),

       (1200, "스탠딩 바벨 카프 레이즈", now(), now())
;

insert into workout_target
values (100, "가슴", now(), now()),
       (200, "등", now(), now()),
       (300, "어깨", now(), now()),
       (400, "삼두", now(), now()),
       (500, "이두", now(), now()),
       (600, "전완", now(), now()),
       (700, "복근", now(), now()),
       (800, "둔근", now(), now()),
       (900, "햄스트링", now(), now()),
       (1000, "대퇴사두", now(), now()),
       (1100, "승모", now(), now()),
       (1200, "종아리", now(), now());

insert into workout_association
values (1, 100, 100, now(), now()),
       (2, 100, 400, now(), now()),
       (3, 101, 100, now(), now()),
       (4, 101, 400, now(), now()),
       (5, 102, 100, now(), now()),
       (6, 102, 400, now(), now()),
       (7, 103, 100, now(), now()),
       (8, 103, 300, now(), now()),
       (9, 104, 100, now(), now()),
       (10, 104, 300, now(), now()),
       (11, 200, 200, now(), now()),
       (12, 200, 500, now(), now()),
       (13, 201, 200, now(), now()),
       (14, 202, 200, now(), now()),
       (15, 202, 500, now(), now()),
       (16, 300, 300, now(), now()),
       (17, 300, 1100, now(), now()),
       (18, 301, 300, now(), now()),
       (19, 301, 1000, now(), now()),
       (20, 302, 300, now(), now()),
       (21, 302, 400, now(), now()),
       (22, 303, 300, now(), now()),
       (23, 303, 1000, now(), now()),
       (24, 304, 300, now(), now()),
       (25, 305, 300, now(), now()),
       (26, 305, 1000, now(), now()),
       (27, 400, 400, now(), now()),
       (28, 400, 100, now(), now()),
       (29, 401, 400, now(), now()),
       (30, 401, 300, now(), now()),
       (31, 500, 500, now(), now()),
       (32, 500, 600, now(), now()),
       (33, 501, 500, now(), now()),
       (34, 501, 600, now(), now()),
       (35, 502, 500, now(), now()),
       (36, 503, 500, now(), now()),
       (37, 503, 600, now(), now()),
       (38, 504, 500, now(), now()),
       (39, 504, 300, now(), now()),
       (40, 800, 800, now(), now()),
       (41, 800, 900, now(), now()),
       (42, 900, 900, now(), now()),
       (43, 900, 1100, now(), now()),
       (44, 901, 900, now(), now()),
       (45, 901, 200, now(), now()),
       (46, 902, 900, now(), now()),
       (47, 902, 200, now(), now()),
       (48, 903, 900, now(), now()),
       (49, 903, 200, now(), now()),
       (50, 904, 900, now(), now()),
       (51, 904, 200, now(), now()),
       (52, 905, 900, now(), now()),
       (53, 905, 200, now(), now()),
       (54, 906, 900, now(), now()),
       (55, 906, 700, now(), now()),
       (56, 906, 800, now(), now()),
       (57, 907, 900, now(), now()),
       (58, 907, 200, now(), now()),
       (59, 1000, 1000, now(), now()),
       (60, 1000, 500, now(), now()),
       (61, 1001, 1000, now(), now()),
       (62, 1001, 1200, now(), now()),
       (63, 1002, 1000, now(), now()),
       (64, 1002, 800, now(), now()),
       (65, 1003, 1000, now(), now()),
       (66, 1003, 800, now(), now()),
       (67, 1004, 1000, now(), now()),
       (68, 1004, 800, now(), now()),
       (69, 1005, 1000, now(), now()),
       (70, 1005, 800, now(), now()),
       (71, 1006, 1000, now(), now()),
       (72, 1006, 900, now(), now()),
       (73, 1007, 1000, now(), now()),
       (74, 1007, 900, now(), now()),
       (75, 1008, 1000, now(), now()),
       (76, 1008, 1200, now(), now()),
       (77, 1100, 1100, now(), now()),
       (78, 1101, 1100, now(), now()),
       (79, 1200, 1200, now(), now())
;