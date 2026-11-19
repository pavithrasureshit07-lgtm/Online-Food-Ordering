# Scenario 12 – Online Food Ordering and Restaurant Sales Analysis System

A console-first academic project using Java 17, Maven, MySQL, and Python.

## Project structure

- `Java-Console/` – menu-driven Java application (JDBC + Maven)
- `Database/` – schema and sample data
- `DBMS-Database/` – database setup and verification guide
- `FDS-Python/` – retrieves MySQL data and preprocesses order/sales data
- `FDS-Visualization/` – creates sales and popular-item charts
- `SE-SRS/` – software requirements specification
- `SE-Diagrams/` – editable Graphviz source and SVG diagrams
- `SE-Testing/` – test cases and test report template
- `Documentation/` – setup and execution guide
- `Executable/` – executable Java application JAR

## Requirements

- Java JDK 17 or later
- Maven 3.8+
- MySQL Server 8+
- MySQL Workbench
- Python 3.10 or later
- Visual Studio Code

## 1. Create the database

Open MySQL Workbench, connect to your local MySQL Server, and execute:

`Database/schema.sql`

Optionally execute `Database/sample_data.sql` for sample menu and customer records.

The database name used in this project is `restaurant_db`.

Verify the database using:

```sql
USE restaurant_db;
SHOW TABLES;
SELECT * FROM menu_items;
SELECT * FROM customers;
```

## 2. Configure Java

Open `Java-Console` as the Maven project in VS Code.

Set the database environment variables in PowerShell. Replace the password placeholder with your own MySQL password locally.

```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/restaurant_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
$env:DB_USER="root"
$env:DB_PASSWORD="YOUR_NEW_MYSQL_PASSWORD"
```

Run the following commands to build and execute the Java application:

```powershell
mvn clean package
java -jar target/online-food-ordering-1.0.0.jar
```

The application provides a menu-driven interface to manage customers, menu items, orders, order status, and daily sales reports.

## 3. Run Python FDS

From the project root, navigate to the Python folder:

```powershell
cd FDS-Python
```

Install the required Python libraries:

```powershell
py -m pip install -r requirements.txt
```

Configure the MySQL connection variables:

```powershell
$env:DB_HOST="localhost"
$env:DB_PORT="3306"
$env:DB_NAME="restaurant_db"
$env:DB_USER="root"
$env:DB_PASSWORD="YOUR_NEW_MYSQL_PASSWORD"
```

Run the Python data retrieval and preprocessing program:

```powershell
py retrieve_preprocess.py
```

Output:

`FDS-Python/output/processed_orders.csv`

The program retrieves order, customer, and menu information from MySQL, preprocesses the records, and saves the processed data as a CSV file.

## 4. Create visualizations

After successfully running the Python retrieval program, navigate to the visualization folder:

```powershell
cd ..\FDS-Visualization
```

Install the required libraries:

```powershell
py -m pip install -r requirements.txt
```

Run the visualization program:

```powershell
py create_visualizations.py
```

Charts are saved in:

`FDS-Visualization/output/`

The generated visualizations include:

- `daily_sales.png` – Daily restaurant sales.
- `popular_items.png` – Top 10 popular menu items.
- `order_status.png` – Order status distribution.

## 5. Run the Executable JAR

The executable Java application is available in:

`Executable/online-food-ordering-1.0.0.jar`

From the project root, configure the database environment variables and run:

```powershell
java -jar .\Executable\online-food-ordering-1.0.0.jar
```

Make sure the MySQL Server is running before starting the application.

## 6. Project Testing

Test cases are available in:

`SE-Testing/Test_Cases.md`

The testing covers:

- Customer registration
- Duplicate phone validation
- Menu item creation
- Single-item and multiple-item orders
- Quantity validation
- Unavailable menu item handling
- Order status updates
- Customer order retrieval
- Python data retrieval
- Data visualization generation

Record the actual result, pass/fail status, test date, and tester for each test case before submission.

## Important

- MySQL Server must be running before executing the Java application.
- The database name used throughout the project is `restaurant_db`.
- Configure database credentials using environment variables.
- Never store your actual MySQL password in README files or upload it to GitHub.
- The Java application writes directly to MySQL.
- Python retrieves the latest saved database records when you run the FDS script.
- Python preprocessing and visualization are separate steps; they are not automatically launched by the Java menu.
- Run `retrieve_preprocess.py` before running `create_visualizations.py`.
- Sample data is optional and can be used for demonstration purposes.

## Conclusion

The Online Food Ordering and Restaurant Sales Analysis System integrates Java application development, MySQL database management, and Python data analysis.

It provides a simple solution for managing restaurant customers, menu items, and orders while generating useful sales reports and visualizations.