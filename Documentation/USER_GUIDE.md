# User Guide

## 1. Start MySQL Server

Start the MySQL Server service and open MySQL Workbench. Connect to your local MySQL Server.

## 2. Create the Database

1. Open `Database/schema.sql` in MySQL Workbench.
2. Execute the complete SQL script.
3. The database name is `restaurant_db`.
4. Optionally execute `Database/sample_data.sql` to insert demonstration records.

Verify the database:

```sql
USE restaurant_db;
SHOW TABLES;
SELECT * FROM customers;
SELECT * FROM menu_items;
```

## 3. Configure Java Database Connection

Open the project in VS Code and navigate to the Java folder:

```powershell
cd Java-Console
```

Set the database environment variables:

```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/restaurant_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
$env:DB_USER="root"
$env:DB_PASSWORD="YOUR_NEW_MYSQL_PASSWORD"
```

Replace the password placeholder with your own MySQL password locally.

## 4. Build and Run the Java Application

Build the application:

```powershell
mvn clean package
```

Run the application:

```powershell
java -jar target/online-food-ordering-1.0.0.jar
```

## 5. Use the Numbered Menu

1. Add customers before placing orders.
2. Add or review menu items.
3. Change menu item availability.
4. Place an order by entering the customer ID.
5. Enter item IDs and quantities.
6. Enter `0` when finished selecting items.
7. Update order status as the order progresses.
8. View customer orders.
9. Generate the daily sales report.

Follow the instructions displayed in the terminal.

## 6. Verify MySQL Integration

Open MySQL Workbench and execute:

```sql
USE restaurant_db;

SELECT * FROM customers;
SELECT * FROM menu_items;
SELECT * FROM orders;
SELECT * FROM order_items;
```

The records entered through the Java application should appear in MySQL.

## 7. Run Python FDS Retrieval

Navigate to the Python folder from the project root:

```powershell
cd FDS-Python
```

Install the required libraries:

```powershell
py -m pip install -r requirements.txt
```

Set the database environment variables:

```powershell
$env:DB_HOST="localhost"
$env:DB_PORT="3306"
$env:DB_NAME="restaurant_db"
$env:DB_USER="root"
$env:DB_PASSWORD="YOUR_NEW_MYSQL_PASSWORD"
```

Run the retrieval and preprocessing program:

```powershell
py retrieve_preprocess.py
```

The processed data is saved in:

`FDS-Python/output/processed_orders.csv`

## 8. Generate Visualizations

Navigate to the visualization folder:

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

The generated charts are saved in:

`FDS-Visualization/output/`

Charts include:

- Daily Restaurant Sales
- Top 10 Popular Menu Items
- Order Status Distribution

## 9. Important Notes

- MySQL Server must be running before starting the Java application.
- The database name throughout the project is `restaurant_db`.
- Java writes records directly into MySQL.
- Python retrieves the latest saved database records when its script is executed.
- Run the Python retrieval script before generating visualizations.
- Python processing is a separate step and is not automatically launched by the Java menu.
- Never store your actual database password in project documentation or upload it to GitHub.