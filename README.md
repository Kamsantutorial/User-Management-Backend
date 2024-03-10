# Bakong Rest API

## Description 
This is a project that provides APIs for a web application. It is built using spring boot framework and uses MySQL database.

## Installation
1. clone the repository: 
- git clone 'git@192.168.2.9:CHUOBB01/bakong-rest.git' or 'http://192.168.2.9/CHUOBB01/bakong-rest.git'
2. install MySQL
3. build and run the project

## Configuration
1. Primary Database
- database name: 'bakong_aquiring_uat'
- username: 'root'
- password:
2. AFIS Database
- Database name: 'afis_final'
- username: 'postgres'
- password: 'postgres123'
3. Bakong PG Database
- database name: 'tps'
- username: 'tps'
- password: 'L4JzX60KIgVk5hZg'
4. Data Distribution Management - DDM (Tertiary)
- database name: central_data
- username: 'root'
- password: 

## Run Script
- initial_database.sql
- after_script.sql
- insert_menu_transaction_script.sql
- script.sql

## Test APIs
- using Swagger UI (http://localhost:8080/swagger-ui/index.html#/) or postman
