# --- !Ups
CREATE TABLE IF NOT EXISTS users (
  id INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  login VARCHAR(10) NOT NULL,
  passa VARCHAR(255) NOT NULL,
  birthday DATE NOT NULL,
  created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY(id)
) ENGINE=InnoDB;

# --- !Downs
DROP TABLE users;
