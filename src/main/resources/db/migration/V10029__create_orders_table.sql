CREATE TABLE t_orders
(
    id         bigint       NOT NULL,
    email      varchar(255) NOT NULL,
    address    varchar(255),
    price      float,
    order_date date
);