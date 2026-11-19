import os
from pathlib import Path
import pandas as pd
import mysql.connector

ROOT = Path(__file__).resolve().parent
OUT = ROOT / "output"
OUT.mkdir(exist_ok=True)

conn = mysql.connector.connect(
    host=os.getenv("DB_HOST", "localhost"),
    port=int(os.getenv("DB_PORT", "3306")),
   database=os.getenv("DB_NAME", "restaurant_db"),
    user=os.getenv("DB_USER", "root"),
    password=os.getenv("DB_PASSWORD", ""),
)
query = '''
SELECT o.order_id, o.order_date, o.status, o.total_amount,
       c.customer_id, c.customer_name, oi.item_id, m.item_name,
       m.category, oi.quantity, oi.unit_price, oi.line_total
FROM orders o
JOIN customers c ON c.customer_id=o.customer_id
JOIN order_items oi ON oi.order_id=o.order_id
JOIN menu_items m ON m.item_id=oi.item_id
ORDER BY o.order_date
'''
try:
    df = pd.read_sql(query, conn)
finally:
    conn.close()

df["order_date"] = pd.to_datetime(df["order_date"], errors="coerce")
df["total_amount"] = pd.to_numeric(df["total_amount"], errors="coerce").fillna(0)
df["quantity"] = pd.to_numeric(df["quantity"], errors="coerce").fillna(0).astype(int)
df["line_total"] = pd.to_numeric(df["line_total"], errors="coerce").fillna(0)
df = df.dropna(subset=["order_id", "order_date"])
df.to_csv(OUT / "processed_orders.csv", index=False)
print(f"Processed {len(df)} order-item rows.")
print(f"Saved: {OUT / 'processed_orders.csv'}")
