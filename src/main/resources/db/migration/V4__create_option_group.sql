create table option_group
(
    id                bigint unsigned auto_increment,
    name              varchar(100) not null,
    is_required       boolean      not null,
    is_count_required boolean      not null,
    product_id        int unsigned not null,
    created_at        datetime DEFAULT CURRENT_TIMESTAMP,
    updated_at        datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (id),
    FOREIGN key (product_id) references product (id) on DELETE RESTRICT on UPDATE RESTRICT
) engine = InnoDB
  default character set = UTF8MB4;
