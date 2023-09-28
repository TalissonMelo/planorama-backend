CREATE TABLE user (
    id VARCHAR(255) UNIQUE NOT NULL PRIMARY KEY,
    email VARCHAR(120) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(255) UNIQUE NOT NULL,
    active tinyint(1) NOT NULL,
    photo VARCHAR(255)
)engine=InnoDB default charset=utf8;