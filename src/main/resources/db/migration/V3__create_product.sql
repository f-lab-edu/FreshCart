create table product
(
    id          int unsigned auto_increment,
    name        varchar(80)  not null,
    price       int          not null,
    status      varchar(20)  not null,
    description varchar(255) not null,
    single_type boolean      not null,
    created_at  datetime DEFAULT CURRENT_TIMESTAMP,
    updated_at  datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    category_id int unsigned,
    user_id     int unsigned,
    primary key (id),
    FOREIGN KEY (category_id) references category (id) on DELETE RESTRICT on UPDATE RESTRICT,
    FOREIGN KEY (user_id) references user (id) on DELETE RESTRICT on UPDATE RESTRICT
) engine = InnoDB
  default character set = UTF8MB4;
