INSERT INTO category_tbl (name, active) VALUES ('Electronics', true);
INSERT INTO category_tbl (name, active) VALUES ('Clothing', true);
INSERT INTO category_tbl (name, active) VALUES ('Books', true);
INSERT INTO category_tbl (name, active) VALUES ('Sports', true);
INSERT INTO category_tbl (name,subCategory_id, active) VALUES ('Handset',1, true);
INSERT INTO category_tbl (name,subCategory_id, active) VALUES ('Technical',3, true);
INSERT INTO category_tbl (name,subCategory_id, active) VALUES ('Novel',3, true);

-- Electronics
INSERT INTO product_tbl (name, dt_price, ws_price, active, category_id)VALUES ('iPhone 15 Pro', 1299.00, 1199.00, true, 5);
INSERT INTO product_tbl (name, dt_price, ws_price, active, category_id)VALUES ('Samsung Galaxy S24', 1099.00, 999.00, true, 5);

-- Clothing
INSERT INTO product_tbl (name, dt_price, ws_price, active, category_id)VALUES ('Men T-Shirt', 25.00, 15.00, true, 2);
INSERT INTO product_tbl (name, dt_price, ws_price, active, category_id)VALUES ('Women Jeans', 55.00, 40.00, true, 2);

-- Books
INSERT INTO product_tbl (name, dt_price, ws_price, active, category_id)VALUES ('Spring Boot in Action', 45.00, 35.00, true, 6);
INSERT INTO product_tbl (name, dt_price, ws_price, active, category_id)VALUES ('Learning Python', 50.00, 42.00, true, 6);

-- Sports
INSERT INTO product_tbl (name, dt_price, ws_price, active, category_id)VALUES ('Football', 30.00, 20.00, true, 4);
INSERT INTO product_tbl (name, dt_price, ws_price, active, category_id)VALUES ('Tennis Racket', 120.00, 100.00, true, 4);

INSERT INTO product_tbl (name, dt_price, ws_price, active, category_id)VALUES ('Za', 10.00, 10.00, true, 7);
INSERT INTO product_tbl (name, dt_price, ws_price, active, category_id)VALUES ('Belu', 10.00, 10.00, true, 7);

INSERT INTO customer_tbl (name, member_type) VALUES ('Alice Johnson', 'Gold');
INSERT INTO customer_tbl (name, member_type) VALUES ('Bob Smith', 'Silver');
INSERT INTO customer_tbl (name, member_type) VALUES ('Charlie Brown', 'NoMember');
INSERT INTO customer_tbl (name, member_type) VALUES ('Diana Prince', 'Platinum');
INSERT INTO customer_tbl (name, member_type) VALUES ('Ethan Hunt', 'Diamond');


INSERT INTO contact_tbl (email, primary_phone, secondary_phone, customer_id)VALUES ('alice.johnson@example.com', '0911111111', '0922222222', 1);
INSERT INTO contact_tbl (email, primary_phone, secondary_phone, customer_id)VALUES ('bob.smith@example.com', '0933333333', '0944444444', 2);
INSERT INTO contact_tbl (email, primary_phone, secondary_phone, customer_id)VALUES ('charlie.brown@example.com', '0955555555', '0966666666', 3);
INSERT INTO contact_tbl (email, primary_phone, secondary_phone, customer_id)VALUES ('diana.prince@example.com', '0977777777', '0988888888', 4);
INSERT INTO contact_tbl (email, primary_phone, secondary_phone, customer_id)VALUES ('ethan.hunt@example.com', '0999999999', '0901234567', 5);

INSERT INTO voucher_tbl (customer_id) VALUES (1);
INSERT INTO voucher_tbl (customer_id) VALUES (2);
INSERT INTO voucher_tbl (customer_id) VALUES (3);
INSERT INTO voucher_tbl (customer_id) VALUES (1);
INSERT INTO voucher_tbl (customer_id) VALUES (5);
INSERT INTO voucher_tbl (customer_id) VALUES (1);
INSERT INTO voucher_tbl (customer_id) VALUES (2);
INSERT INTO voucher_tbl (customer_id) VALUES (3);
INSERT INTO voucher_tbl (customer_id) VALUES (4);
INSERT INTO voucher_tbl (customer_id) VALUES (5);
INSERT INTO voucher_tbl (customer_id) VALUES (1);
INSERT INTO voucher_tbl (customer_id) VALUES (2);
INSERT INTO voucher_tbl (customer_id) VALUES (4); -- 1,4,6,11 -- 2,7,12


INSERT INTO voucher_detail_tbl (voucher_id, product_id, qty, sub_total)VALUES (1, 1, 1, 1299.00); -- 1x iPhone 15 Pro
INSERT INTO voucher_detail_tbl (voucher_id, product_id, qty, sub_total)VALUES (1, 2, 2, 2198.00); -- 2x Samsung Galaxy S24

INSERT INTO voucher_detail_tbl (voucher_id, product_id, qty, sub_total)VALUES (2, 3, 3, 75.00);  -- 3x Men T-Shirt
INSERT INTO voucher_detail_tbl (voucher_id, product_id, qty, sub_total)VALUES (2, 4, 1, 55.00);  -- 1x Women Jeans


INSERT INTO voucher_detail_tbl (voucher_id, product_id, qty, sub_total) VALUES (2, 3, 2, 50.00);  -- 2x T-Shirt
INSERT INTO voucher_detail_tbl (voucher_id, product_id, qty, sub_total) VALUES (2, 4, 1, 55.00);  -- 1x Jeans

INSERT INTO voucher_detail_tbl (voucher_id, product_id, qty, sub_total) VALUES (3, 5, 1, 45.00);  -- Spring Boot in Action
INSERT INTO voucher_detail_tbl (voucher_id, product_id, qty, sub_total) VALUES (3, 6, 2, 100.00); -- 2x Learning Python

INSERT INTO voucher_detail_tbl (voucher_id, product_id, qty, sub_total) VALUES (4, 2, 1, 1099.00); -- Samsung Galaxy S24
INSERT INTO voucher_detail_tbl (voucher_id, product_id, qty, sub_total) VALUES (4, 3, 3, 75.00);   -- 3x T-Shirts
INSERT INTO voucher_detail_tbl (voucher_id, product_id, qty, sub_total) VALUES (4, 5, 1, 45.00);

INSERT INTO voucher_detail_tbl (voucher_id, product_id, qty, sub_total) VALUES (5, 7, 2, 60.00);   -- 2x Football
INSERT INTO voucher_detail_tbl (voucher_id, product_id, qty, sub_total) VALUES (5, 8, 1, 120.00);  -- Tennis Racket

INSERT INTO voucher_detail_tbl (voucher_id, product_id, qty, sub_total) VALUES (6, 6, 1, 50.00);   -- Learning Python

INSERT INTO voucher_detail_tbl (voucher_id, product_id, qty, sub_total) VALUES (7, 1, 1, 1299.00); -- iPhone





