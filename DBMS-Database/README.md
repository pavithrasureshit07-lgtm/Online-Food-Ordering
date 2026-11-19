# DBMS – MySQL setup

1. Start the MySQL Server service.
2. Open MySQL Workbench and connect to the local server.
3. Open and execute `Database/schema.sql`.
4. Execute `Database/sample_data.sql` if you want demonstration records.
5. Verify:
```sql
USE restaurant_db;
SHOW TABLES;
SELECT * FROM menu_items;
SELECT * FROM customers;
```
MySQL Server is the database engine. MySQL Workbench is the graphical client used to administer it.
