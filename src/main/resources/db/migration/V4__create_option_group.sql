create table option_group
(
    id                   bigint unsigned not null,
    name                 varchar(100)    not null,
    required_option      boolean         not null,
    minimum_order_option boolean         not null,
    product_id           int unsigned    not null,
    created_at           datetime DEFAULT CURRENT_TIMESTAMP,
    updated_at           datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (id),
    FOREIGN key (product_id) references product (id) on DELETE RESTRICT on UPDATE RESTRICT
) engine = InnoDB
  default character set = UTF8MB4;
