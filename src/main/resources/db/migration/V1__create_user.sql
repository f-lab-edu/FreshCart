create table user
(
    id           int unsigned auto_increment,
    email        varchar(30)  not null,
    password     varchar(256) not null,
    phone_number varchar(11)  not null,
    name         varchar(30)  not null,
    role         varchar(10)  not null,
    primary key (id)
) engine = InnoDB
  default character set = UTF8MB4;
