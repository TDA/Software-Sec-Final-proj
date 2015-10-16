create database if not exists `unibank`;

USE `unibank`;

DROP TABLE IF EXISTS `user_roles`;
DROP TABLE IF EXISTS `users`;

CREATE  TABLE users (
  username VARCHAR(45) NOT NULL,
  password VARCHAR(60) NOT NULL,
  firstname VARCHAR(45) NOT NULL,
  lastname VARCHAR(45) NOT NULL,
  AccountType VARCHAR(45) NOT NULL,
  email VARCHAR(45) NOT NULL,
  SSN VARCHAR(45) NOT NULL,
  enabled boolean not null,
  PRIMARY KEY (username));
  

CREATE TABLE user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (role,username),
  KEY fk_username_idx (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username));
  

INSERT INTO users 
  VALUES ('kenilabc', '$2a$10$23sLqI0HtA8xkxudo7ntxu0WAmxEcjgaTjrmvc1MOt.yNkEk7XrZm', 'Kenil', 'Bhatt', 'Individual', 'fake@fake.com', '123456789', false);

INSERT INTO user_roles (username, role)
VALUES ('kenilabc', 'ROLE_USER');
  
