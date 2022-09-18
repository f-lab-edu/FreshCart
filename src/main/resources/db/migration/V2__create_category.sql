drop table if exists category;

create table category
(
    id          int unsigned auto_increment,
    name        varchar(30)  not null,
    parent_id   int unsigned references category(id),
    primary key (id)
) engine = InnoDB default character set = UTF8MB4;