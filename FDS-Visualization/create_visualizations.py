
from pathlib import Path
import pandas as pd
import matplotlib.pyplot as plt

# Project folder paths
ROOT = Path(__file__).resolve().parent
SOURCE = ROOT.parent / "FDS-Python" / "output" / "processed_orders.csv"
OUT = ROOT / "output"

# Create output folder if it does not exist
OUT.mkdir(exist_ok=True)

# Check whether processed data exists
if not SOURCE.exists():
    raise SystemExit(
        "Processed data not found. "
        "Run FDS-Python/retrieve_preprocess.py first."
    )

# Read processed CSV data
df = pd.read_csv(SOURCE, parse_dates=["order_date"])

if df.empty:
    raise SystemExit(
        "No order data available yet. "
        "Place sample orders and run preprocessing again."
    )

# --------------------------------
# 1. Daily Restaurant Sales
# --------------------------------

# Remove cancelled orders and duplicate order records
valid_orders = (
    df[df["status"] != "Cancelled"]
    .drop_duplicates(subset=["order_id"])
)

# Calculate daily sales without counting an order more than once
daily = (
    valid_orders
    .groupby(valid_orders["order_date"].dt.date)["total_amount"]
    .sum()
)

daily.plot(
    kind="bar",
    title="Daily Restaurant Sales",
    xlabel="Date",
    ylabel="Sales"
)

plt.tight_layout()
plt.savefig(OUT / "daily_sales.png", dpi=160)
plt.close()

# --------------------------------
# 2. Top 10 Popular Menu Items
# --------------------------------

popular = (
    df.groupby("item_name")["quantity"]
    .sum()
    .sort_values(ascending=False)
    .head(10)
)

popular.plot(
    kind="bar",
    title="Top 10 Popular Menu Items",
    xlabel="Menu Item",
    ylabel="Quantity Ordered"
)

plt.tight_layout()
plt.savefig(OUT / "popular_items.png", dpi=160)
plt.close()

# --------------------------------
# 3. Order Status Distribution
# --------------------------------

status = (
    df.drop_duplicates(subset=["order_id"])["status"]
    .value_counts()
)

status.plot(
    kind="pie",
    autopct="%1.1f%%",
    title="Order Status Distribution",
    ylabel=""
)

plt.tight_layout()
plt.savefig(OUT / "order_status.png", dpi=160)
plt.close()

# --------------------------------
# Completion Message
# --------------------------------

print("All visualizations generated successfully!")
print("Output folder:", OUT)
print("1. daily_sales.png")
print("2. popular_items.png")
print("3. order_status.png")
