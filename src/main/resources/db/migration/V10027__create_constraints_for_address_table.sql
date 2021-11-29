ALTER TABLE t_addresses
    ADD CONSTRAINT PK_address_email PRIMARY KEY (user_email);

ALTER TABLE t_addresses
    ADD CONSTRAINT FK_address_users_email FOREIGN KEY (user_email) REFERENCES t_users (email);