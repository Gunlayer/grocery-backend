INSERT INTO t_views (id, number)
SELECT id, 0
FROM t_products
WHERE id BETWEEN 26 and 45;
