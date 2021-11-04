INSERT INTO t_users (
    user_id,
    email,
    password,
    user_role,
    user_status
)
VALUES
	(NEXT VALUE FOR grocery_shop_sequence,
	'admin@endava.com',
	'$2a$12$WQmh3G/A6nsrnO2pTFd71eAlhWlI9uKNsSLkY5mTgONXxdd4GLbf.',
	'ADMIN',
	'ACTIVE'
	),
    (NEXT VALUE FOR grocery_shop_sequence,
	'user@endava.com',
	'$2a$12$234bgDgtLpoQDUnGAv/OfuPmi35jqc4.AhhI4VD7wlb.pGUlz6CcC',
	'USER',
	'ACTIVE'
	);