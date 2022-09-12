drop table if exists "user" CASCADE;

create table "user"
(
    id          int unsigned auto_increment primary key,
    email       varchar(30)  not null,
    password    varchar(256) not null,
    phoneNumber varchar(11)  not null,
    name        varchar(30)  not null,
    role        varchar(10)  not null,

    primary key (id)
) engine = InnoDB
  default character set = utf8;