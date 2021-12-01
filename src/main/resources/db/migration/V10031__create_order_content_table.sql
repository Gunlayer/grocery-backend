CREATE TABLE t_order_content
(
    id               bigint NOT NULL,
    order_id         bigint NOT NULL,
    product_id       bigint NOT NULL,
    product_quantity int,
    product_size     int
);