create table `order`
(
    id               int unsigned auto_increment,
    user_id          int unsigned not null,
    receiver_name    varchar(30)  not null,
    receiver_phone   varchar(11)  not null,
    receiver_address varchar(100) not null,
    order_status     varchar(20)  not null,
    created_at       datetime DEFAULT CURRENT_TIMESTAMP,
    updated_at       datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (id),
    FOREIGN KEY (user_id) references user (id) on DELETE RESTRICT on UPDATE RESTRICT
) engine = InnoDB
  default character set = UTF8MB4;

create table order_item
(
    id         bigint unsigned auto_increment,
    product_id int unsigned not null,
    order_id   int unsigned,
    name       varchar(80)  not null,
    price      int          not null,
    count      int          not null,
    primary key (id),
    FOREIGN key (order_id) references `order` (id) on DELETE RESTRICT on UPDATE RESTRICT,
    FOREIGN key (product_id) references product (id) on DELETE RESTRICT on UPDATE RESTRICT
) engine = InnoDB
  default character set = UTF8MB4;

create table order_item_option_group
(
    id                      bigint unsigned auto_increment,
    product_option_group_id bigint unsigned not null,
    name                    varchar(100)    not null,
    order_item_id           bigint unsigned not null,
    primary key (id),
    FOREIGN key (order_item_id) references order_item (id) on DELETE RESTRICT on UPDATE RESTRICT,
    FOREIGN key (product_option_group_id) references option_group (id) on DELETE RESTRICT on UPDATE RESTRICT

) engine = InnoDB
  default character set = UTF8MB4;

create table order_item_option
(
    id                         bigint unsigned auto_increment,
    product_option_id          bigint unsigned not null,
    order_item_option_group_id bigint unsigned not null,
    name                       varchar(100)    not null,
    price                      int             not null,
    primary key (id),
    FOREIGN key (order_item_option_group_id) references order_item_option_group (id) on DELETE RESTRICT on UPDATE RESTRICT,
    FOREIGN key (product_option_id) references product_option (id) on DELETE RESTRICT on UPDATE RESTRICT
) engine = InnoDB
  default character set = UTF8MB4;
