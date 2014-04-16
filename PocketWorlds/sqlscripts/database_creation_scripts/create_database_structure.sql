/*CREATE DATABASE POCKET_WORLDS_TEST;
USE POCKET_WORLDS_TEST; */

CREATE TABLE PW_MAP_TYPE_LEVELS (
     PW_MAP_TYPE_LEVEL_ID INT NOT NULL UNIQUE AUTO_INCREMENT,
     PW_MAP_TYPE_LEVEL_KEY INT NOT NULL UNIQUE,
     PW_MAP_TYPE_LEVEL_NAME CHAR(30) NOT NULL,
     PRIMARY KEY (PW_MAP_TYPE_LEVEL_ID)
);

CREATE TABLE PW_MAP_TYPES (
     PW_MAP_TYPE_ID INT NOT NULL UNIQUE AUTO_INCREMENT,
     PW_MAP_TYPE_KEY INT NOT NULL UNIQUE,
	 PW_MAP_TYPE_LEVEL_KEY INT NOT NULL,
     PW_MAP_TYPE_NAME CHAR(30) NOT NULL,
     PRIMARY KEY (PW_MAP_TYPE_ID),
	 FOREIGN KEY (PW_MAP_TYPE_LEVEL_KEY) REFERENCES PW_MAP_TYPE_LEVELS(PW_MAP_TYPE_LEVEL_KEY)
		ON DELETE RESTRICT
);

CREATE TABLE PW_MAP_ELEM_TYPES (
     PW_MAP_ELEM_TYPE_ID INT NOT NULL UNIQUE AUTO_INCREMENT,
     PW_MAP_ELEM_TYPE_KEY INT NOT NULL UNIQUE,
     PW_MAP_ELEM_TYPE_NAME CHAR(30) NOT NULL,
     PRIMARY KEY (PW_MAP_ELEM_TYPE_ID)
);

CREATE TABLE PW_MAPS (
     PW_MAP_ID INT NOT NULL AUTO_INCREMENT,
     PW_MAP_TYPE_KEY INT NOT NULL,
     PW_MAP_NAME CHAR(30) NOT NULL,
     PW_MAP_SIZE_X INT NOT NULL,
     PW_MAP_SIZE_Y INT NOT NULL,
	 PW_MAP_PARENT_ID INT,
     PRIMARY KEY (PW_MAP_ID),
	 FOREIGN KEY (PW_MAP_TYPE_KEY) REFERENCES PW_MAP_TYPES(PW_MAP_TYPE_KEY)
		ON DELETE RESTRICT,
	 FOREIGN KEY (PW_MAP_PARENT_ID) REFERENCES PW_MAPS(PW_MAP_ID)
		ON DELETE RESTRICT
);

CREATE TABLE PW_MAPS_ELEMENTS (
	 PW_MAP_ID INT NOT NULL,
     X_POSITION INT NOT NULL,
     Y_POSITION INT NOT NULL,
     PW_MAP_ELEM_TYPE_KEY INT NOT NULL,
     PRIMARY KEY (PW_MAP_ID,X_POSITION,Y_POSITION),
	 FOREIGN KEY (PW_MAP_ID) REFERENCES PW_MAPS(PW_MAP_ID)
		ON DELETE RESTRICT,
	 FOREIGN KEY (PW_MAP_ELEM_TYPE_KEY) REFERENCES PW_MAP_ELEM_TYPES(PW_MAP_ELEM_TYPE_KEY)
		ON DELETE RESTRICT
);
DELIMITER $$ 
CREATE PROCEDURE CREATE_MAP(
	NEW_MAP_NAME CHAR(30),
	NEW_MAP_SIZE_X INT,
	NEW_MAP_SIZE_Y INT,
	OUT NEW_MAP_ID INT)
BEGIN
	DECLARE CURRENT_X INT DEFAULT 0;
	DECLARE CURRENT_Y INT DEFAULT 0;
	INSERT INTO PW_MAPS (PW_MAP_TYPE_KEY, PW_MAP_NAME, PW_MAP_SIZE_X, PW_MAP_SIZE_Y)
		VALUES	(0, NEW_MAP_NAME, NEW_MAP_SIZE_X, NEW_MAP_SIZE_Y);
	SET NEW_MAP_ID = LAST_INSERT_ID();
	WHILE CURRENT_X < NEW_MAP_SIZE_X DO
		WHILE CURRENT_Y < NEW_MAP_SIZE_Y DO
			INSERT INTO PW_MAPS_ELEMENTS (PW_MAP_ID, X_POSITION, Y_POSITION, PW_MAP_ELEM_TYPE_KEY)
				VALUES	(NEW_MAP_ID, CURRENT_X, CURRENT_Y, 101);	
		SET CURRENT_Y = CURRENT_Y + 1;
		END WHILE;
		SET CURRENT_Y = 0;
	SET CURRENT_X = CURRENT_X + 1;
	END WHILE;
END$$ 
DELIMITER ; 

/*
DELIMITER $$ 
CREATE PROCEDURE CREATE_POCKET_MAP(
	IN MASTER_MAP_ID INT,
	IN MASTER_MAP_X INT,
	IN MASTER_MAP_Y INT,
	IN NEW_MAP_NAME CHAR(30),
	IN NEW_MAP_SIZE_X INT,
	IN NEW_MAP_SIZE_Y INT) 
BEGIN 
	SELECT 1;
END$$ 
DELIMITER ; /**/
