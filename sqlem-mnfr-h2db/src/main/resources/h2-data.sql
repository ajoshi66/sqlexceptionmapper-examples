TRUNCATE TABLE MANUFACTURER;

-- INSERT INTO City Table
INSERT INTO City (CITY_CODE, CITY_NAME) VALUES ('BLR', 'Bangalore');
INSERT INTO City (CITY_CODE, CITY_NAME) VALUES ('PNQ', 'Pune');
INSERT INTO City (CITY_CODE, CITY_NAME) VALUES ('BOM', 'Mumbai');
INSERT INTO City (CITY_CODE, CITY_NAME) VALUES ('DEL', 'Delhi');
INSERT INTO City (CITY_CODE, CITY_NAME) VALUES ('MAS', 'Chennai');


-- INSERT INTO MANUFACTURER TABLE
INSERT INTO MANUFACTURER (MNFR_NAME, MNFR_ADDRESS_1, MNFR_ADDRESS_2, MNFR_CITY_CODE, MNFR_STATE, MNFR_COUNTRY) VALUES ('Maruthi Suzuki', 'Delhi', NULL, 'DEL', 'Delhi', 'India');
INSERT INTO MANUFACTURER (MNFR_NAME, MNFR_ADDRESS_1, MNFR_ADDRESS_2, MNFR_CITY_CODE, MNFR_STATE, MNFR_COUNTRY) VALUES ('Honda Motors', 'Pune', NULL, 'PNQ', 'Maharashtra', 'India');

