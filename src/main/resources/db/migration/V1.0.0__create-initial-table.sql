DROP TABLE IF EXISTS accounts;

CREATE TABLE IF NOT EXISTS accounts (
    id       bigint unsigned NOT NULL auto_increment,
    username varchar(64)     NOT NULL,
    password varchar(128)    NOT NULL,
    primary key (id)
);

DROP TABLE IF EXISTS records;

CREATE TABLE IF NOT EXISTS records (
    id          bigint unsigned NOT NULL auto_increment,
    account_id  bigint unsigned NOT NULL,
    begin_time  DATETIME DEFAULT NULL,
    finish_time DATETIME DEFAULT NULL,
    primary key (id)
);
