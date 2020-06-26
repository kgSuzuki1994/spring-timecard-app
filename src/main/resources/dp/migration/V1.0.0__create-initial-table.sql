drop table if exists users;

CREATE TABLE 'users' (
    'id' bigint unsigned not null auto_increment,
    'name' varchar(64) not null,
    'password' varchar(128) not null,
    primary key ('id')
) charset=utf8;
