# Supplier-Portal
Coding Challenge：A Supplier Portal

[Presentation Google Slides](https://docs.google.com/presentation/d/1Zt73gnnYM78iOryg1uR94Ggba1NEbh9VyYybA_xsEtM/edit?usp=sharing)

## Requirements

JAVA openjdk version 17
Apache Maven 3.8
PostgreSQL 12

## Setup

### Connect to PostgreSQL
Create and setup DB as defined in `src\main\resources\application.properties`

### Initialize or Reset Database
This script will drop all tables related with this project, and create them again.

It will also insert test data and create views.

```sql
\i script/db_init/run-all.sql
```