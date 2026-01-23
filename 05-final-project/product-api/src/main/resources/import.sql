
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
insert into contact_tbl(primary_phone,secondary_phone,email)values('09-321457','09-232320','martin@gmail.com');

insert into customer_tbl(name,member_type,address_id,contact_id,is_active)values('Andrew','Silver',1,1,true);
insert into customer_tbl(name,member_type,address_id,contact_id,is_active)values('John','Gold',2,2,true);
insert into customer_tbl(name,member_type,address_id,contact_id,is_active)values('Derrek','Diamond',3,3,true);
insert into customer_tbl(name,member_type,address_id,contact_id,is_active)values('Mercy','Silver',4,4,true);
insert into customer_tbl(name,member_type,address_id,contact_id,is_active)values('Annie','Gold',5,5,true);
insert into customer_tbl(name,member_type,address_id,contact_id,is_active)values('Marrie','Diamond',6,6,true);
insert into customer_tbl(name,member_type,address_id,contact_id,is_active)values('Herry','Silver',7,7,true);          
insert into customer_tbl(name,member_type,address_id,contact_id,is_active)values('Elle','Gold',8,8,true);           
insert into customer_tbl(name,member_type,address_id,contact_id,is_active)values('Halcy','Diamond',9,9,true);          
insert into customer_tbl(name,member_type,address_id,contact_id,is_active)values('Martin','Silver',10,10,true);   
insert into customer_tbl(name,member_type,address_id,contact_id,is_active)values('Martin new','Gold',11,11,false); 

insert into category_tbl(name,category_id,is_active) values('Drinks',null,true);
insert into category_tbl(name,category_id,is_active) values('Snacks',null,true);
insert into category_tbl(name,category_id,is_active) values('Fruits',null,true);
insert into category_tbl(name,category_id,is_active) values('Furniture',null,true);
insert into category_tbl(name,category_id,is_active) values('Vegetables',null,true);
insert into category_tbl(name,category_id,is_active) values('CoolDrinks',1,true);
insert into category_tbl(name,category_id,is_active) values('InstantFoods',2,true);
insert into category_tbl(name,category_id,is_active) values('HotDrinks',1,true);
insert into category_tbl(name,category_id,is_active) values('FruitDrinks',4,true);
insert into category_tbl(name,category_id,is_active) values('EnergyDrinks',4,true);

insert into size_tbl(name,is_active) values ('Small',true);
insert into size_tbl(name,is_active) values ('Medium',true);
insert into size_tbl(name,is_active) values ('Large',true);
insert into size_tbl(name,is_active) values ('Small Package',true);
insert into size_tbl(name,is_active) values ('Large Package',true);

insert into product_tbl(name,category_id,is_active) values ('Gar Gar',7,true);
insert into product_tbl(name,category_id,is_active) values ('Apple',3,true);
insert into product_tbl(name,category_id,is_active) values ('Yogurt',6,true);
insert into product_tbl(name,category_id,is_active) values ('YumYum',7,true);
insert into product_tbl(name,category_id,is_active) values ('Coffee',8,true);
insert into product_tbl(name,category_id,is_active) values ('Broccoli',5,true);
insert into product_tbl(name,category_id,is_active) values ('Shark',10,true);
insert into product_tbl(name,category_id,is_active) values ('Victor',6,true);
insert into product_tbl(name,category_id,is_active) values ('Blanket',4,true);
insert into product_tbl(name,category_id,is_active) values ('Shwe Man May',7,true);
insert into product_tbl(name,category_id,is_active) values ('Shwe Man May new',7,false);

insert into supplier_tbl(name,is_active) values ('John Doe',true);
insert into supplier_tbl(name,is_active) values ('Andrew Saw',true);
insert into supplier_tbl(name,is_active) values ('Derrek J',true);
insert into supplier_tbl(name,is_active) values ('Mercy Linn',true);
insert into supplier_tbl(name,is_active) values ('Annie M',true);
insert into supplier_tbl(name,is_active) values ('Marrie A',true);
insert into supplier_tbl(name,is_active) values ('Herry Jane',true);
insert into supplier_tbl(name,is_active) values ('Elle Venus',true);
insert into supplier_tbl(name,is_active) values ('Halcy Grace',true);
insert into supplier_tbl(name,is_active) values ('Martin Drew',true);

insert into product_price_tbl(price_type,price,product_id,size_id,create_at,is_active)values('Purchase',500,1,1,'2022-10-12',true);
insert into product_price_tbl(price_type,price,product_id,size_id,create_at,is_active)values('Purchase',500,1,2,'2022-10-12',true);
insert into product_price_tbl(price_type,price,product_id,size_id,create_at,is_active)values('Purchase',2500,6,3,'2022-10-12',true);
insert into product_price_tbl(price_type,price,product_id,size_id,create_at,is_active)values('Purchase',1000,6,1,'2022-10-12',true);
insert into product_price_tbl(price_type,price,product_id,size_id,create_at,is_active)values('Purchase',1500,2,1,'2022-10-12',true);
insert into product_price_tbl(price_type,price,product_id,size_id,create_at,is_active)values('Purchase',5000,2,3,'2022-10-12',true);
insert into product_price_tbl(price_type,price,product_id,size_id,create_at,is_active)values('Purchase',1500,3,4,'2022-10-12',true);
insert into product_price_tbl(price_type,price,product_id,size_id,create_at,is_active)values('Purchase',13500,3,5,'2022-10-12',true);
insert into product_price_tbl(price_type,price,product_id,size_id,create_at,is_active)values('Purchase',25000,9,3,'2022-10-12',true);

insert into product_price_tbl(price_type,price,product_id,size_id,create_at,is_active)values('Sale',700,1,1,'2022-10-12',true);
insert into product_price_tbl(price_type,price,product_id,size_id,create_at,is_active)values('Sale',700,1,2,'2022-10-12',true);
insert into product_price_tbl(price_type,price,product_id,size_id,create_at,is_active)values('Sale',3000,6,3,'2022-10-12',true);
insert into product_price_tbl(price_type,price,product_id,size_id,create_at,is_active)values('Sale',1300,6,1,'2022-10-12',true);
insert into product_price_tbl(price_type,price,product_id,size_id,create_at,is_active)values('Sale',2000,2,1,'2022-10-12',true);
insert into product_price_tbl(price_type,price,product_id,size_id,create_at,is_active)values('Sale',6000,2,3,'2022-10-12',true);
insert into product_price_tbl(price_type,price,product_id,size_id,create_at,is_active)values('Sale',1700,3,4,'2022-10-12',true);
insert into product_price_tbl(price_type,price,product_id,size_id,create_at,is_active)values('Sale',15000,3,5,'2022-10-12',true);
insert into product_price_tbl(price_type,price,product_id,size_id,create_at,is_active)values('Sale',30000,9,3,'2022-10-12',true);


