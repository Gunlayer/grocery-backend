INSERT INTO t_order_content (id,
                             order_id,
                             product_id,
                             product_quantity,
                             product_size)
VALUES (NEXT VALUE FOR grocery_shop_sequence,
             (SELECT id FROM t_orders WHERE email = 'jorik.pirojok@gmail.com'),
             (SELECT id FROM t_products WHERE name = 'Avocado'),
             2,
             1);