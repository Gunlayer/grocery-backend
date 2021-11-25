ALTER TABLE t_addresses
    ADD CONSTRAINT PK_address_email PRIMARY KEY (email);

ALTER TABLE t_addresses
    ADD CONSTRAINT FK_address_users_email FOREIGN KEY (email) REFERENCES t_users (email);