-- DROP SEQUENCE IF EXISTS manufacturer_seq;

DROP TABLE IF EXISTS manufacturer;
DROP TABLE IF EXISTS city;


-- Schema version 0.1

CREATE TABLE IF NOT EXISTS schema_version (
	MAJOR_VERSION INTEGER NOT NULL,
	MINOR_VERSION INTEGER NOT NULL
);


CREATE TABLE IF NOT EXISTS city (
	CITY_CODE VARCHAR(5) PRIMARY KEY,
	CITY_NAME VARCHAR(50) NOT NULL
);


CREATE TABLE IF NOT EXISTS manufacturer (
	MNFR_ID INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	MNFR_NAME VARCHAR(50) NOT NULL,
	MNFR_ADDRESS_1 VARCHAR(100) NOT NULL,
	MNFR_ADDRESS_2 VARCHAR(100),
	MNFR_CITY_CODE VARCHAR(5) NOT NULL,
	MNFR_STATE VARCHAR(50) NOT NULL,
	MNFR_COUNTRY VARCHAR (50) NOT NULL,
	CONSTRAINT UK_MNFR_NAME UNIQUE NULLS NOT DISTINCT (MNFR_NAME),
	CONSTRAINT FK_MNFR_CITY FOREIGN KEY (MNFR_CITY_CODE) REFERENCES CITY
);



-- CREATE SEQUENCE IF NOT EXISTS manufacturer_seq AS INTEGER;