ALTER TABLE t_order_content
    ADD CONSTRAINT PK_order_content_id PRIMARY KEY (id);

ALTER TABLE t_order_content
    ADD CONSTRAINT FK_order_id FOREIGN KEY (order_id) REFERENCES t_orders (id);

ALTER TABLE t_order_content
    ADD CONSTRAINT FK_new_product_id FOREIGN KEY (product_id) REFERENCES t_products (id);