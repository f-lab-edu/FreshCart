alter table product_option
rename to `option`;

alter table order_item_option_group
rename column product_option_group_id to option_group_id;

alter table order_item_option
rename column product_option_id to option_id;