drop table if exists product;

create table product
(
    id          int unsigned auto_increment,
    name       varchar(256)  not null,
    price    varchar(256) not null,
    status   varchar(20) not null,
    description mediumtext not null,
    single_type boolean not null,
    created_at datetime DEFAULT CURRENT_TIMESTAMP,
    updated_at datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (id)
) engine = InnoDB default character set = UTF8MB4;
