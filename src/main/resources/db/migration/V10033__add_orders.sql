INSERT INTO t_orders (id,
                      email,
                      address,
                      price,
                      order_date)
VALUES (NEXT VALUE FOR grocery_shop_sequence,
             'jorik.pirojok@gmail.com',
             'For Agrepina Agrepinovna, Strada Ciocirliei 666, satul Jora de Mijloc',
             50.4,
             '1992-01-17');