ALTER TABLE t_reviews
    ADD CONSTRAINT PK_review_id PRIMARY KEY (review_id);

ALTER TABLE t_reviews
    ADD CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES t_users (user_id);

ALTER TABLE t_reviews
    ADD CONSTRAINT FK_product_id FOREIGN KEY (product_id) REFERENCES t_products (id);