
--insert into account_tbl(name,email,password,role,disabled)values('cashier','cash@gmail.com','c123','Member',1);

insert into address_tbl(state,township,street)values('Yangon','InnSein','HtanTaPin');
insert into address_tbl(state,township,street)values('Yangon','InnSein','YadanarMyaing');
insert into address_tbl(state,township,street)values('Yangon','InnSein','SiiSat');
insert into address_tbl(state,township,street)values('Yangon','Hlaing','ThanLann');
insert into address_tbl(state,township,street)values('Yangon','Hlaing','Zizawa');
insert into address_tbl(state,township,street)values('Yangon','Hlaing','GinnGar');
insert into address_tbl(state,township,street)values('Yangon','Hlaing','ThuKha');
insert into address_tbl(state,township,street)values('Yangon','PazunTaung','Zizawa');
insert into address_tbl(state,township,street)values('Yangon','PazunTaung','Thazin');
insert into address_tbl(state,township,street)values('Yangon','PazunTaung','HninSi');

insert into contact_tbl(primary_phone,secondary_phone,email)values('09-123456','09-232321','andrew@gmail.com');
insert into contact_tbl(primary_phone,secondary_phone,email)values('09-123457','09-232322','john@gmail.com');
insert into contact_tbl(primary_phone,secondary_phone,email)values('09-123458','09-232323','derrek@gmail.com');
insert into contact_tbl(primary_phone,secondary_phone,email)values('09-123459','09-232324','mercy@gmail.com');
insert into contact_tbl(primary_phone,secondary_phone,email)values('09-213456','09-232325','annie@gmail.com');
insert into contact_tbl(primary_phone,secondary_phone,email)values('09-213457','09-232326','marrie@gmail.com');
insert into contact_tbl(primary_phone,secondary_phone,email)values('09-213458','09-232327','herry@gmail.com');
insert into contact_tbl(primary_phone,secondary_phone,email)values('09-213459','09-232328','elle@gmail.com');
insert into contact_tbl(primary_phone,secondary_phone,email)values('09-321456','09-232329','hawsey@gmail.com');
insert into contact_tbl(primary_phone,secondary_phone,email)values('09-321457','09-232320','martin@gmail.com');

insert into customer_tbl(name,member_type,address_id,contact_id)values('Andrew','Silver',1,1);
insert into customer_tbl(name,member_type,address_id,contact_id)values('John','Gold',2,2);
insert into customer_tbl(name,member_type,address_id,contact_id)values('Derrek','Diamond',3,3);
insert into customer_tbl(name,member_type,address_id,contact_id)values('Mercy','Silver',4,4);
insert into customer_tbl(name,member_type,address_id,contact_id)values('Annie','Gold',5,5);
insert into customer_tbl(name,member_type,address_id,contact_id)values('Marrie','Diamond',6,6);
insert into customer_tbl(name,member_type,address_id,contact_id)values('Herry','Silver',7,7);          
insert into customer_tbl(name,member_type,address_id,contact_id)values('Elle','Gold',8,8);           
insert into customer_tbl(name,member_type,address_id,contact_id)values('Halcy','Diamond',9,9);          
insert into customer_tbl(name,member_type,address_id,contact_id)values('Martin','Silver',10,10);         

insert into category_tbl(name,category_id) values('Drinks',null);
insert into category_tbl(name,category_id) values('Snacks',null);
insert into category_tbl(name,category_id) values('Fruits',null);
insert into category_tbl(name,category_id) values('Furniture',null);
insert into category_tbl(name,category_id) values('Vegetables',null);
insert into category_tbl(name,category_id) values('CoolDrinks',1);
insert into category_tbl(name,category_id) values('InstantFoods',2);
insert into category_tbl(name,category_id) values('HotDrinks',1);
insert into category_tbl(name,category_id) values('FruitDrinks',4);
insert into category_tbl(name,category_id) values('EnergyDrinks',4);

insert into size_tbl(name) values ('Small');
insert into size_tbl(name) values ('Medium');
insert into size_tbl(name) values ('Large');
insert into size_tbl(name) values ('Small Package');
insert into size_tbl(name) values ('Large Package');

insert into product_tbl(name,category_id) values ('Gar Gar',7);
insert into product_tbl(name,category_id) values ('Apple',3);
insert into product_tbl(name,category_id) values ('Yogurt',6);
insert into product_tbl(name,category_id) values ('YumYum',7);
insert into product_tbl(name,category_id) values ('Coffee',8);
insert into product_tbl(name,category_id) values ('Broccoli',5);
insert into product_tbl(name,category_id) values ('Shark',10);
insert into product_tbl(name,category_id) values ('Victor',6);
insert into product_tbl(name,category_id) values ('Blanket',4);
insert into product_tbl(name,category_id) values ('Shwe Man May',7);

insert into supplier_tbl(name) values ('John Doe');
insert into supplier_tbl(name) values ('Andrew Saw');
insert into supplier_tbl(name) values ('Derrek J');
insert into supplier_tbl(name) values ('Mercy Linn');
insert into supplier_tbl(name) values ('Annie M');
insert into supplier_tbl(name) values ('Marrie A');
insert into supplier_tbl(name) values ('Herry Jane');
insert into supplier_tbl(name) values ('Elle Venus');
insert into supplier_tbl(name) values ('Halcy Grace');
insert into supplier_tbl(name) values ('Martin Drew');

insert into product_price_tbl(price_type,price,product_id,size_id,create_at)values('Purchase',500,1,1,'2022-10-12');
insert into product_price_tbl(price_type,price,product_id,size_id,create_at)values('Purchase',500,1,2,'2022-10-12');
insert into product_price_tbl(price_type,price,product_id,size_id,create_at)values('Purchase',2500,6,3,'2022-10-12');
insert into product_price_tbl(price_type,price,product_id,size_id,create_at)values('Purchase',1000,6,1,'2022-10-12');
insert into product_price_tbl(price_type,price,product_id,size_id,create_at)values('Purchase',1500,2,1,'2022-10-12');
insert into product_price_tbl(price_type,price,product_id,size_id,create_at)values('Purchase',5000,2,3,'2022-10-12');
insert into product_price_tbl(price_type,price,product_id,size_id,create_at)values('Purchase',1500,3,4,'2022-10-12');
insert into product_price_tbl(price_type,price,product_id,size_id,create_at)values('Purchase',13500,3,5,'2022-10-12');
insert into product_price_tbl(price_type,price,product_id,size_id,create_at)values('Purchase',25000,9,3,'2022-10-12');

insert into product_price_tbl(price_type,price,product_id,size_id,create_at)values('Sale',700,1,1,'2022-10-12');
insert into product_price_tbl(price_type,price,product_id,size_id,create_at)values('Sale',700,1,2,'2022-10-12');
insert into product_price_tbl(price_type,price,product_id,size_id,create_at)values('Sale',3000,6,3,'2022-10-12');
insert into product_price_tbl(price_type,price,product_id,size_id,create_at)values('Sale',1300,6,1,'2022-10-12');
insert into product_price_tbl(price_type,price,product_id,size_id,create_at)values('Sale',2000,2,1,'2022-10-12');
insert into product_price_tbl(price_type,price,product_id,size_id,create_at)values('Sale',6000,2,3,'2022-10-12');
insert into product_price_tbl(price_type,price,product_id,size_id,create_at)values('Sale',1700,3,4,'2022-10-12');
insert into product_price_tbl(price_type,price,product_id,size_id,create_at)values('Sale',15000,3,5,'2022-10-12');
insert into product_price_tbl(price_type,price,product_id,size_id,create_at)values('Sale',30000,9,3,'2022-10-12');


