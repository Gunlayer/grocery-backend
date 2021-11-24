ALTER TABLE t_items
DROP
CONSTRAINT FK_user;

ALTER TABLE t_items
DROP
COLUMN user_id;

DELETE
FROM t_items;

ALTER TABLE t_items
    ADD email varchar(255) NOT NULL;