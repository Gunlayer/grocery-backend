ALTER TABLE t_products
    ADD CONSTRAINT PK_product_id PRIMARY KEY (id);

ALTER TABLE t_views
    ADD CONSTRAINT PK_views_id PRIMARY KEY (id);

ALTER TABLE product_sizes
    ADD CONSTRAINT FK_sizes_of_products FOREIGN KEY (product_id) REFERENCES t_products (id);

ALTER TABLE t_views
    ADD CONSTRAINT FK_views FOREIGN KEY (id) REFERENCES t_products (id);