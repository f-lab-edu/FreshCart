create table product
(
    id          int unsigned auto_increment,
    name        varchar(80)  not null,
    price       int          not null,
    status      varchar(20)  not null,
    description varchar(255) not null,
    single_type boolean      not null,
    category_id int unsigned,
    seller_id   int unsigned,
    created_at  datetime DEFAULT CURRENT_TIMESTAMP,
    updated_at  datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (id),
    FOREIGN KEY (category_id) references category (id) on DELETE RESTRICT on UPDATE RESTRICT,
    FOREIGN KEY (seller_id) references user (id) on DELETE RESTRICT on UPDATE RESTRICT
) engine = InnoDB
  default character set = UTF8MB4;

create table option_group
(
    id            bigint unsigned auto_increment,
    name          varchar(100)      not null,
    is_required   boolean           not null,
    exclusive     boolean           not null,
    minimum_order smallint unsigned not null,
    maximum_order smallint unsigned,
    product_id    int unsigned      not null,
    created_at    datetime DEFAULT CURRENT_TIMESTAMP,
    updated_at    datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (id),
    FOREIGN key (product_id) references product (id) on DELETE RESTRICT on UPDATE RESTRICT
) engine = InnoDB
  default character set = UTF8MB4;


create table product_option
(
    id              bigint unsigned auto_increment,
    name            varchar(100)    not null,
    price           int             not null,
    option_group_id bigint unsigned not null,
    created_at      datetime DEFAULT CURRENT_TIMESTAMP,
    updated_at      datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (id),
    FOREIGN key (option_group_id) references option_group (id) on DELETE RESTRICT on UPDATE RESTRICT
) engine = InnoDB
  default character set = UTF8MB4;
