DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;

DROP TABLE IF EXISTS offer_images;
DROP TABLE IF EXISTS offer;
DROP TABLE IF EXISTS bd_main_page;

CREATE table users (
    id int AUTO_INCREMENT PRIMARY key,
    name varchar(250) not null,
    last_name varchar(250) not null,
    age int not null,
    password varchar(250) not null,
    email varchar(250) not null
);
INSERT INTO users (name, last_name, age, password, email) values
('admin','adminlast','20','admin','admin@ru'),
('user','userlast','22','user','user@ru');

create table roles (
    id int AUTO_INCREMENT PRIMARY KEY,
    name varchar(250) not null
);

INSERT INTO roles (name) values
('ROLE_ADMIN'),
('ROLE_USER');

CREATE TABLE offer_images (
    id text,
    name text,
    type text,
    data BLOB
);
CREATE TABLE user_role (
    id int auto_increment primary key,
    user_id int not null,
    role_id int not null
);
CREATE TABLE offer (
    id int auto_increment primary key,
    name_offer varchar(250),
    desc_offer text,
    price_offer varchar(250),
    offer_category varchar(250),
    image_id text
);
INSERT INTO user_role (user_id, role_id) VALUES
    ('1','1'),
    ('2','2');
CREATE TABLE bd_main_page (
    id int auto_increment primary key,
    title varchar(250),
    text text
);
INSERT INTO bd_main_page (title, text) VALUES
    ('title 1','text 1'),
    ('title 2',' text 2');
