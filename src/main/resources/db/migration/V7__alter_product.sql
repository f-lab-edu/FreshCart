alter table `option`
ADD product_id int unsigned;

alter table `option`
ADD FOREIGN KEY (product_id) references product (id) on DELETE RESTRICT on UPDATE RESTRICT