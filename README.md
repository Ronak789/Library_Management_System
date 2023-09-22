# Library_Management_System
library management system using Java and MySql

run following queries in MySql, before running project:

create database library_management_system;
use library_management_system;
create table adminDetails (id int primary key auto_increment, name varchar(255),userName varchar(255), password varchar(255), salt varchar(255));
create table studentdetails (stdid int primary key auto_increment, name varchar(255), FatherName varchar(255), course varchar(255), year int, semester int);
create table totalstudents (total int);
create table bookDetails (id int primary key auto_increment, name varchar(255), edition int, publisher varchar(255), stock int);
create table totalbooks (total int);
insert into totalbooks values(0);
insert into totalstudents values(0);
create table bookIssueDetails (studentId int, studentName varchar(255), bookId int, bookName varchar(255), dateOfIssue timestamp default current_timestamp);
create table bookReturnDetails (studentid int, studentname varchar(255), bookid int, bookname varchar(255), bookIssueDate timestamp, bookReturnDate timestamp default current_timestamp);
