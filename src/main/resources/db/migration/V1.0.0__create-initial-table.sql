DROP TABLE IF EXISTS accounts;

CREATE TABLE IF NOT EXISTS accounts (
    id       bigint unsigned NOT NULL auto_increment,
    username varchar(64)     NOT NULL,
    password varchar(128)    NOT NULL,
    primary key (id)
);
