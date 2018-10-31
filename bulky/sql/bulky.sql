create database bulky;

use bulky;
create table account(
	aid int not null primary key auto_increment,
	acredit int null,
	aname varchar(255),
	alogo varchar(255),
	astatus int null,
	adtmod datetime null
);


create table users(
	uid int not null primary key auto_increment,
	uname varchar(255) null,
	upasswd varchar(200) null,
	uemail varchar(255) null,
	ucode01 varchar(50) null,
	ucode02 varchar(50) null,
	uaccount int null,
	utype int null,
	udtmod datetime null,
	urole varchar(50) null,
	umod int null
);
