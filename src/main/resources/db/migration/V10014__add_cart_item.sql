INSERT INTO t_items (id,
                     product_id,
                     quantity,
                     size,
                     user_id)
VALUES (NEXT VALUE FOR grocery_shop_sequence,
             (SELECT id FROM t_products WHERE name = 'Avocado'),
             2,
             2,
             (SELECT user_id FROM t_users WHERE email = 'user@endava.com')),
       (NEXT VALUE FOR grocery_shop_sequence,
             (SELECT id FROM t_products WHERE name = 'Garlic'),
             2,
             3,
             (SELECT user_id FROM t_users WHERE email = 'user@endava.com'));