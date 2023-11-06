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

