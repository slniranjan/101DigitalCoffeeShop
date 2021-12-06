-- Create sample Shops
INSERT INTO public.shop(
	name, close_time, contact_details, latitude, longitude, number_of_queue, open_time, avg_order_process_time)
	VALUES ('abc', '23:00:00', '0711234567', 26.2754584, -80.1055376
, 1, '6:00:00', 5);

INSERT INTO public.shop(
	name, close_time, contact_details, latitude, longitude, number_of_queue, open_time, avg_order_process_time)
	VALUES ('abc', '23:00:00', '0711234534', 45.2754584, -70.1055376
, 2, '6:00:00', 5);

INSERT INTO public.shop(
	name, close_time, contact_details, latitude, longitude, number_of_queue, open_time, avg_order_process_time)
	VALUES ('abc', '23:00:00', '0711234561', 194.2754584, -70.1055376
, 3, '6:00:00', 6);

-- Create sample shop users (admin,operator)
INSERT INTO public.shop_user(
	name, password, user_role, shop_id)
	VALUES ('shop1-admin', 'password', 'admin', 1);

INSERT INTO public.shop_user(
	name, password, user_role, shop_id)
	VALUES ('shop2-admin', 'password', 'admin', 2);

INSERT INTO public.shop_user(
	name, password, user_role, shop_id)
	VALUES ('shop2-operator', 'password', 'operator', 2);

-- Create sample Queues
INSERT INTO public.shop_queue(name, size, shop_id)
	VALUES ('shop1-queue1', 20, 1);

INSERT INTO public.shop_queue(
	name, size, shop_id)
	VALUES ('shop2-queue1', 10, 2);

INSERT INTO public.shop_queue(
	name, size, shop_id)
	VALUES ('shop2-queue2', 20, 2);

INSERT INTO public.shop_queue(
	name, size, shop_id)
	VALUES ('shop3-queue1', 20, 3);

INSERT INTO public.shop_queue(
	name, size, shop_id)
	VALUES ('shop3-queue2', 20, 3);

INSERT INTO public.shop_queue(
	name, size, shop_id)
	VALUES ('shop3-queue3', 20, 3);

-- Create Sample menu items
INSERT INTO public.menu_item(
	description, item_name, price)
	VALUES ('Adding espresso to your menu helps you cater to the widest range of coffee enthusiasts.', 'Espresso', 20.5);

INSERT INTO public.menu_item(
	description, item_name, price)
	VALUES ('Authentic latte drinks are all made out of an espresso base', 'Latte', 10);

INSERT INTO public.menu_item(
	description, item_name, price)
	VALUES ('Cold brew is all the rage these days.', 'Iced Coffee', 8);

INSERT INTO public.menu_item(
	description, item_name, price)
	VALUES ('he ubiquitous bagel – what else.', 'Bagels', 8);

INSERT INTO public.menu_item(
	description, item_name, price)
	VALUES ('For those who like sugary snacks, nothing can go wrong with a donut.', 'Donuts', 38);

INSERT INTO public.menu_item(
	description, item_name, price)
	VALUES ('Think about some of the most popular snacks that people like having with their coffee', 'Croissant', 6);