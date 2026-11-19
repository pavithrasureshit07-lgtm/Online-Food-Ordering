
USE restaurant_db;

INSERT IGNORE INTO customers
(customer_id, customer_name, phone, email)
VALUES
(1, 'Ananya Kumar', '9000000001', 'ananya@example.com'),
(2, 'Arun Raj', '9000000002', 'arun@example.com');

INSERT IGNORE INTO menu_items
(item_id, item_name, category, price, available)
VALUES
(1, 'Veg Burger', 'Fast Food', 90.00, TRUE),
(2, 'Paneer Pizza', 'Pizza', 220.00, TRUE),
(3, 'Masala Dosa', 'South Indian', 80.00, TRUE),
(4, 'Chicken Biryani', 'Main Course', 180.00, TRUE),
(5, 'Lime Juice', 'Beverage', 40.00, TRUE);
