create table product_inventory
(
    id              int unsigned auto_increment,
    product_id      int unsigned not null,
    stock           smallint unsigned not null,
    seller_id       int unsigned not null,
    primary key (id),
    FOREIGN key (seller_id) references user (id) on DELETE RESTRICT on UPDATE RESTRICT,
    FOREIGN key (product_id) references product (id) on DELETE RESTRICT on UPDATE RESTRICT
) engine = InnoDB
  default character set = UTF8MB4;

alter table `option_inventory`
    ADD FOREIGN KEY (seller_id) references user (id) on DELETE RESTRICT on UPDATE RESTRICT