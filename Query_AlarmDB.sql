drop database if exists alarmesDB;
create database if not exists alarmesDB;

use alarmesDB;

create table alarm(
	id integer(3) primary key auto_increment,
    alarm_name varchar(100),
    criticality varchar(10),
    responsible varchar(30),
    description varchar(200),
    actuation varchar(30),
    escalation varchar(100),
    squad varchar(30)
);

create table client(
	id integer(3) primary key auto_increment,
    email varchar(150),
    pwd varchar(100),
    role varchar(45)
);


 INSERT INTO `client` (`email`, `pwd`, `role`)
  VALUES ("tiago",'$2y$12$oRRbkNfwuR8ug4MlzH5FOeui.//1mkd.RsOAJMbykTSupVy.x/vb2', 'ROLE_USER');

#mudar tudo ainda
CREATE TABLE `authorities` (
  `id` int NOT NULL AUTO_INCREMENT,
  `client_id` int NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `client_id` (`id`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`)
);

 INSERT INTO `authorities` (`customer_id`, `name`)
  VALUES (1, 'ROLE_USER');

 INSERT INTO `authorities` (`customer_id`, `name`)
  VALUES (1, 'ROLE_ADMIN');
  
  select * from client;

