-- Queries for cassandra
-- keyspace used: example
USE example;
CREATE TABLE user (user_id uuid PRIMARY KEY, name text); --User.java
CREATE TABLE sales(countryCode text, areaCode text, sales int, PRIMARY KEY((countryCode, areaCode))); --Sales.java

-- User Defined Types (UDT)
CREATE TYPE address (street text, zip_code int);
CREATE TABLE company (company_id uuid PRIMARY KEY, name text, address address);