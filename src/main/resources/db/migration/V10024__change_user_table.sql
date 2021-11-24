ALTER TABLE t_users
ALTER
COLUMN email varchar (255) NOT NULL;

ALTER TABLE t_users
DROP
CONSTRAINT PK_user_id;

ALTER TABLE t_users
DROP
COLUMN user_id;

ALTER TABLE t_users
    ADD CONSTRAINT PK_users_email PRIMARY KEY (email);

ALTER TABLE t_items
    ADD CONSTRAINT FK_items_users_email FOREIGN KEY (email) REFERENCES t_users (email);

ALTER TABLE t_reviews
    ADD CONSTRAINT FK_reviews_users_email FOREIGN KEY (email) REFERENCES t_users (email);