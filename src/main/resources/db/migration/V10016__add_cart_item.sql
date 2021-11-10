INSERT INTO t_items (
                         id,
                         product_id,
                         quantity,
                         cart_id
)
VALUES
    (NEXT VALUE FOR grocery_shop_sequence,
    (SELECT id FROM t_products WHERE name='Avocado'),
    2,
     (SELECT id FROM t_carts WHERE user_id = (SELECT user_id
                                    FROM t_users
                                    WHERE email = 'user@endava.com')));