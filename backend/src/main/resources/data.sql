-- Insert roles first
INSERT INTO roles (name) VALUES ('USER'), ('ADMIN');

-- Insert users with encoded passwords
INSERT INTO users (username, email, password, role_id) VALUES 
('john_doe', 'john@example.com', '$2a$12$jg01TpuoewUnbAVZ9PvxceqUwcesL8EjWruX/qazfznxKQidljVfW', 1),
('admin_user', 'admin@example.com', '$2a$10$7QJ9z8Qh9z8Qh9z8Qh9z8Qh9z8Qh9z8Qh9z8Qh9z8Qh9z8Qh9z8Qh', 2),
('anderson', 'andsinusmomega@gmail.com', '$2a$10$7QJ9z8Qh9z8Qh9z8Qh9z8Qh9z8Qh9z8Qh9z8Qh9z8Qh9z8Qh9z8Qh', 2);

-- Insert products
INSERT INTO products (name, description, price, stock) VALUES 
('Product 1', 'Description for product 1', 10.99, 100),
('Product 2', 'Description for product 2', 20.99, 50),
('Product 3', 'Description for product 3', 15.49, 75);

-- Insert an order for the first user
INSERT INTO orders (user_id, total_price) VALUES 
(1, 31.97);

-- Insert order items with valid user_id and product_id references
INSERT INTO order_items (order_id, product_id, quantity, price) VALUES 
(1, 1, 2, 21.98),
(1, 3, 1, 15.49);

-- Insert a cart for the first user with valid user_id reference
INSERT INTO carts (user_id) VALUES 
(1);

-- Add items to the cart with valid product_id references
INSERT INTO cart_items (cart_id, product_id, quantity) VALUES 
(1, 2, 3);
