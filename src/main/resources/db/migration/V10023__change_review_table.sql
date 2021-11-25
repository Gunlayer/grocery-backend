ALTER TABLE t_reviews
DROP
CONSTRAINT FK_user_id;

ALTER TABLE t_reviews
DROP
COLUMN user_id;

ALTER TABLE t_reviews
    ADD email varchar(255) NOT NULL;