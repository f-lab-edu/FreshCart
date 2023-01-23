alter table option_inventory
    rename column stock to quantity;

alter table product_inventory
    rename column stock to quantity;