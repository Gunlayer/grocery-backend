INSERT INTO t_carts (id,
                     user_id)
VALUES (NEXT VALUE FOR grocery_shop_sequence,
             (SELECT user_id
              FROM t_users
              WHERE email = 'user@endava.com'));