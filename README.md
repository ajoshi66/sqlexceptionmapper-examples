# Example projects that use SQL Exceptin Mapper

## Vehicles
A project created just to demonstrate SQL Exception Mapper working.

It has a Manufacturer entity that can be managed through REST Service.

The application has MANUFACTURER and CITY as tables. In total, the DB has following constraints:
* Primary Key -- MNFR_ID of MANUFACTURER is generated, where as CITY_CODE of CITY table is assigned.
* Not Null -- Multiple columns of MANUFACTURER and both columns of CITY have NOT NULL constraint
* Unique Key -- MNFR_NAME does not accept duplicate values
* Foreign Key -- MNFR_CITY_CODE references CITY_CODE of CITY table

<<TO BE CONTINUED>>
