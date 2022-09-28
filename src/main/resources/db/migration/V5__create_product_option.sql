
create table product_option
(
    id bigint unsigned auto_increment,
    name varchar(100) not null,
    price int not null,
    minimum_order smallint unsigned not null,
    maximum_order smallint unsigned,
    option_group_id  bigint unsigned not null,
    created_at datetime DEFAULT CURRENT_TIMESTAMP,
    updated_at datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (id),
    FOREIGN key (option_group_id) references option_group(id) on DELETE RESTRICT on UPDATE RESTRICT
) engine = InnoDB default character set = UTF8MB4;
