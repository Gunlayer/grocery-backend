CREATE TABLE t_reviews (
	review_id bigint NOT NULL,
	comment_body varchar(4000),
	rating integer,
	user_id bigint,
	product_id bigint
);
