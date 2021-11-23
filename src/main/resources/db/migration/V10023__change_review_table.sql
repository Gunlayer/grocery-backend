ALTER TABLE t_reviews
DROP
CONSTRAINT FK_user_id;

ALTER TABLE t_reviews
DROP
COLUMN user_id;

ALTER TABLE t_reviews
    ADD email varchar(255) NOT NULL;

-- ALTER TABLE t_reviews
--     ADD CONSTRAINT FK_reviews_users_email FOREIGN KEY (email) REFERENCES t_users (email);