# --- !Ups
CREATE TABLE IF NOT EXISTS shopping_carts (
  id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  access_key VARCHAR(255) NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS shopping_carts_products (
  shopping_carts_id INT(11) UNSIGNED NOT NULL,
  products_id INT(5) UNSIGNED NOT NULL,
  quantity INT(2) NOT NULL,
  PRIMARY KEY(shopping_carts_id, products_id)
) ENGINE=InnoDB;


# --- !Downs
DROP TABLE shopping_carts;
DROP TABLE shopping_carts_products;
