CREATE TABLE t_items (
	id bigint NOT NULL,
	product_id bigint NOT NULL,
	quantity int DEFAULT 1,
    user_id bigint NOT NULL,
    size int
);