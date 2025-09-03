drop table if exists customer_tbl;

create table customer_tbl(
	id int primary key auto_increment,
	name varchar(45) not null,
	memberType enum('Silver','Gold','Diamond','Platinum') default 'Silver',
	active tinyint(1) default 1
);

insert into customer_tbl(name) values ('Andrew');
insert into customer_tbl(name) values ('John');
insert into customer_tbl(name) values ('William');

drop table if exists account_tbl; 

create table account_tbl(
	id int primary key auto_increment,
	customer_id int,
	balance double(10,2),
	foreign key(customer_id) references customer_tbl(id)
	ON DELETE CASCADE 
	ON UPDATE CASCADE
);

insert into account_tbl(customer_id,balance)values(1,50000.00);
insert into account_tbl(customer_id,balance)values(2,50000.00);
insert into account_tbl(customer_id,balance)values(3,50000.00);

--show procedure status where db = 'testDb';
drop procedure if exists selectCustomerByMember ;

delimiter //
	create procedure selectCustomerByMember(IN mType varchar(20),OUT cuCount int)
	begin
		
	 select count(*) into cuCount from customer_tbl where memberType = mType;
	end//
	
delimiter ;

----call selectCustomerByMember('Silver',@cuCount);


