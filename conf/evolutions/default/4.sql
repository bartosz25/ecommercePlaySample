# --- !Ups
ALTER TABLE products ADD COLUMN in_stock INT(1) NOT NULL;

# --- !Downs
