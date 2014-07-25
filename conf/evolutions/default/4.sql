# --- !Ups
ALTER TABLE products ADD COLUMN in_stock INT(1) NOT NULL;
UPDATE products SET in_stock = 1 WHERE id < 3;

# --- !Downs
