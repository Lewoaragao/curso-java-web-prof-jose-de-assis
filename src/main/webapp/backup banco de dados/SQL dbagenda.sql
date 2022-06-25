create database dbagenda;
show databases;
use dbagenda;

create table contatos(
	idcon int primary key auto_increment,
	nome varchar(50) not null,
    fone varchar(15) not null,
    email varchar(50)
);

show tables;
describe contatos;

-- CRUD CREATE
insert into contatos(nome, fone, email) values('Bill Gates', '99999-1234', 'bill@outlook.com');

-- CRUD READ
select * from contatos;
select * from contatos order by nome;
select * from contatos where idcon = 5;

-- CRUD UPDATE
update contatos set nome = 'Bruce Wayne' where idcon = 5;
update contatos set nome = 'Bruce Wayne Junior', fone='91234-1234', email='bruce@email.com' where idcon = 5;


-- CRUD DELETE
delete from contatos where idcon = 5;






