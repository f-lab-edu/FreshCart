create table option_inventory
(
    id              bigint unsigned auto_increment,
    option_id       bigint unsigned not null,
    stock           smallint unsigned not null,
    seller_id       int unsigned not null,
    created_at      datetime DEFAULT CURRENT_TIMESTAMP,
    updated_at      datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (id),
    FOREIGN key (option_id) references product_option (id) on DELETE RESTRICT on UPDATE RESTRICT
) engine = InnoDB
  default character set = UTF8MB4;
