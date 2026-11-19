
import os
import mysql.connector
import pandas as pd

connection = None

try:
    # Read database credentials securely from environment variables
    db_password = os.getenv("DB_PASSWORD")

    if not db_password:
        raise ValueError("DB_PASSWORD environment variable is not set.")

    # Connect to MySQL database
    connection = mysql.connector.connect(
        host=os.getenv("DB_HOST", "localhost"),
        port=int(os.getenv("DB_PORT", "3306")),
        user=os.getenv("DB_USER", "root"),
        password=db_password,
        database=os.getenv("DB_NAME", "restaurant_db")
    )

    if connection.is_connected():
        print("Connected to MySQL Database Successfully!")

    # Read order data
    query = """
    SELECT order_id, customer_id, order_date, status, total_amount
    FROM orders
    """

    df = pd.read_sql(query, connection)

    print("\nORDER DETAILS")
    print(df)

except (mysql.connector.Error, ValueError) as error:
    print("Database connection or configuration error:", error)

finally:
    if connection is not None and connection.is_connected():
        connection.close()
        print("\nDatabase connection closed.")
