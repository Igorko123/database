USE db_spring;

DELIMITER //
CREATE TRIGGER AfterInsertDirectorCompany
AFTER INSERT
ON director_company FOR EACH ROW
BEGIN
	DECLARE name_director VARCHAR(50);
    DECLARE name_company VARCHAR(90);
    SELECT CONCAT(surname, ' ', name) INTO name_director
    FROM director WHERE director_id=new.director_id;
    SELECT CONCAT(company_name, ' / ', Author) INTO name_company
    FROM company WHERE company_id=new.company_id;
	INSERT INTO logger (director, company, action,
								time_stamp, user)
	VALUES(name_director,  name_company, 'GOT', NOW(), USER() );
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER AfterDeletePersonBook
AFTER DELETE
ON director_company FOR EACH ROW
BEGIN
	DECLARE name_director VARCHAR(50);
    DECLARE name_company VARCHAR(90);
    SELECT CONCAT(surname, ' ', name) INTO name_director
    FROM director WHERE director_id=old.director_id;
    SELECT CONCAT(company_name, ' / ', author) INTO name_company
    FROM company WHERE company_id=old.company_id;
	INSERT INTO Logger (director, company, action,
                      time_stamp, user)
	VALUES(name_director,  name_company, 'GAVEBACK', NOW(), USER() );
END //
DELIMITER ;






