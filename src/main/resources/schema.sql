DROP TABLE IF EXISTS SAT_DICT;
  
CREATE TABLE SAT_DICT (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  word VARCHAR(250) NOT NULL,
  meaning VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS SAT_HEROES;
  
CREATE TABLE SAT_HEROES (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);