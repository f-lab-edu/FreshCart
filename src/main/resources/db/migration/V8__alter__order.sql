alter table order_item
    add FOREIGN KEY (order_id) references `order` (id) on DELETE RESTRICT on UPDATE RESTRICT;
alter table order_item
    drop column name,
    drop column price;

alter table order_item_option
    add order_id int unsigned;
alter table order_item_option
    add FOREIGN KEY (order_id) references `order` (id) on DELETE RESTRICT on UPDATE RESTRICT;

SET FOREIGN_KEY_CHECKS = 0;
alter table order_item_option
    drop column name,
    DROP FOREIGN KEY `order_item_option_ibfk_1`,
#     DROP FOREIGN KEY `order_item_option_group_id`,
    drop column order_item_option_group_id,
    drop column price;

drop table order_item_option_group;

SET FOREIGN_KEY_CHECKS = 1;