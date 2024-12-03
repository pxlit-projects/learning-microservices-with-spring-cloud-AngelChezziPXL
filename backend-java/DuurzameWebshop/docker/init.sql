-- Create the databases
CREATE DATABASE IF NOT EXISTS products_db;
CREATE DATABASE IF NOT EXISTS shoppingcart_db;
CREATE DATABASE IF NOT EXISTS logbook_db;

-- Grant all privileges on the databases to the specified user
GRANT ALL PRIVILEGES ON products_db.* TO 'user'@'%';
GRANT ALL PRIVILEGES ON shoppingcart_db.* TO 'user'@'%';
GRANT ALL PRIVILEGES ON logbook_db.* TO 'user'@'%';

-- Apply the changes
FLUSH PRIVILEGES;
