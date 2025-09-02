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


delimiter //
	create procedure selectCustomerByMember(IN mType varchar(20),OUT cuCount int)
	begin
		select count(*) from customer_tbl where memberType = mType;
	end//
	
delimiter ;

call selectCustomerByMember('Silver',@cuCount);
