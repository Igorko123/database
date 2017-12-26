
CREATE SCHEMA IF NOT EXISTS db_spring DEFAULT CHARACTER SET utf8 ;
USE db_spring ;

CREATE TABLE IF NOT EXISTS Company (
  company_id BIGINT NOT NULL AUTO_INCREMENT,
  company_name VARCHAR(45) NOT NULL,
  author VARCHAR(45) NOT NULL,
  publisher VARCHAR(50) NULL,
  imprint_year INT NULL,
  amount INT NOT NULL,
  PRIMARY KEY (company_id)
  ) ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS city (
  city_id BIGINT NOT NULL AUTO_INCREMENT,
  city VARCHAR(25) NOT NULL,
  PRIMARY KEY (city_id)
  ) ENGINE = InnoDB
AUTO_INCREMENT = 1 
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS director (
  director_id BIGINT NOT NULL AUTO_INCREMENT,
  surname VARCHAR(25) NOT NULL,
  name VARCHAR(25) NOT NULL,
  email VARCHAR(45) NULL,
  city_id BIGINT NULL,
  street VARCHAR(30) NULL,
  apartment VARCHAR(10) NULL,
  PRIMARY KEY (director_id),
  CONSTRAINT fk_director_city1
    FOREIGN KEY (city_id)
    REFERENCES db_spring.city (city_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS director_company (
  director_id BIGINT NOT NULL,
  company_id BIGINT NOT NULL,
  PRIMARY KEY (director_id, company_id),
  CONSTRAINT directorcompany_ibfk_1
    FOREIGN KEY (director_id)
    REFERENCES db_spring.director (director_id),
  CONSTRAINT directorcompany_ibfk_2
    FOREIGN KEY (company_id)
    REFERENCES db_spring.company (company_id)
) ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS logger (
  logger_id BIGINT NOT NULL AUTO_INCREMENT,
  director VARCHAR(50) NOT NULL,
  company VARCHAR(90) NOT NULL,
  action VARCHAR(10) NOT NULL,
  time_stamp DATETIME NOT NULL,
  user VARCHAR(50) NULL,
  PRIMARY KEY (logger_id)
) ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;









