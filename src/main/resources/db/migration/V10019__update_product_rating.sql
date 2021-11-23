UPDATE t_products
SET rating = null;

ALTER TABLE t_products
ALTER
COLUMN rating decimal(3,2);