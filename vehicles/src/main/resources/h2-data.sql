TRUNCATE TABLE manufacturer;
-- TRUNCATE TABLE city;

INSERT INTO CITY (CITY_CODE, CITY_NAME) VALUES ('GGM', 'Gurugram');
INSERT INTO CITY (CITY_CODE, CITY_NAME) VALUES ('BOM', 'Mumbai');


INSERT INTO MANUFACTURER(MNFR_NAME, MNFR_ADDRESS_1, MNFR_ADDRESS_2, MNFR_CITY_CODE, MNFR_STATE_CODE, MNFR_COUNTRY_CODE) VALUES ('Maruthi Suzuki India Ltd.', 'Old Palam Gurugram Road', null, 'GGM', 'HR', 'IN');
INSERT INTO MANUFACTURER(MNFR_NAME, MNFR_ADDRESS_1, MNFR_ADDRESS_2, MNFR_CITY_CODE, MNFR_STATE_CODE, MNFR_COUNTRY_CODE) VALUES ('Tata Motors Ltd.', '24, Bombay House', 'Homy Mody Street', 'BOM', 'MH', 'IN');
