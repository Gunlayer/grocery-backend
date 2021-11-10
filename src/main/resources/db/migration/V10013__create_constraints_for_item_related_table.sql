ALTER TABLE t_items
ADD CONSTRAINT PK_item_id PRIMARY KEY (id);

ALTER TABLE t_items
ADD CONSTRAINT FK_item_of_cart FOREIGN KEY (user_id) REFERENCES t_users(user_id);

ALTER TABLE t_items
ADD CONSTRAINT FK_product FOREIGN KEY (product_id) REFERENCES t_products(id);
