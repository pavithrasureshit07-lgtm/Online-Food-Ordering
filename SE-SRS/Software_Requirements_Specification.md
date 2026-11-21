# Software Requirements Specification (SRS)

## Scenario 12 – Online Food Ordering and Restaurant Sales Analysis System

PURPOSE

The purpose of this project is to develop an Online Food Ordering and Restaurant Sales Analysis System using Java, MySQL, and Python.

The system manages restaurant customers, menu items, food orders, order status, and sales information through a Java console application connected to a MySQL database.

SCOPE

The application provides the following features:

- Customer registration and management.
- Restaurant menu item management.
- Adding and viewing menu items.
- Updating menu item availability.
- Placing orders with multiple food items.
- Automatic calculation of order totals.
- Updating and viewing order status.
- Generating daily sales reports.
- Retrieving and preprocessing database records using Python.
- Generating sales and menu item visualizations.

The database used by the application is `restaurant_db`.

Python retrieves stored records from MySQL and generates visual reports using Pandas and Matplotlib.

FUNCTIONAL AND NON FUNCTIONAL REQUIREMENTS

1. Customer Management Module
Functional Requirements
ID	Requirement
FR01	The system shall allow users to add new customer details.
FR02	The system shall store customer name, phone number and email address.
FR03	The system shall display the list of registered customers.
FR04	The system shall generate a unique customer ID for each customer.
FR05	The system shall allow the system to retrieve customer details using the customer ID.

Non-Functional Requirements
ID	Requirement
NFR01	Customer details shall be stored securely in the database.
NFR02	The system shall respond to customer-related operations quickly.
NFR03	The system shall maintain accurate customer information.
NFR04	The system shall be easy to use and understand.

2. Menu Management Module
Functional Requirements
ID	Requirement
FR06	The system shall allow users to add new food items.
FR07	The system shall store food name, category and price.
FR08	The system shall display all available food items.
FR09	The system shall allow users to change food item availability.
FR10	The system shall maintain the availability status of each food item.

Non-Functional Requirements
ID	Requirement
NFR05	Menu details shall be stored accurately in the database.
NFR06	Menu information shall be displayed within a reasonable response time.
NFR07	The system shall provide a simple menu interface.
NFR08	The system shall maintain consistency when menu details are updated.

3. Order Management Module
Functional Requirements
ID	Requirement
FR11	The system shall allow customers to place food orders using their customer ID.
FR12	The system shall allow users to select food items and enter quantities.
FR13	The system shall automatically calculate the total order amount.
FR14	The system shall generate a unique order ID for every order.
FR15	The system shall allow users to update the order status.
FR16	The system shall display orders placed by a particular customer.
FR17	The system shall prevent ordering of unavailable food items.

Non-Functional Requirements
ID	Requirement
NFR09	Order details shall be stored reliably in the database.
NFR10	The total amount shall be calculated accurately.
NFR11	Order operations shall be completed within a reasonable time.
NFR12	The system shall maintain data consistency between customers, orders and menu items.
NFR13	The system shall handle invalid order inputs appropriately.

4. Sales Analysis Module
Functional Requirements
ID	Requirement
FR18	The system shall retrieve order data from the MySQL database.
FR19	The system shall preprocess the retrieved sales data using Python.
FR20	The system shall calculate daily sales amounts.
FR21	The system shall calculate the number of orders received.
FR22	The system shall identify popular food items based on order data.
FR23	The system shall save the processed data as a CSV file.

Non-Functional Requirements
ID	Requirement
NFR14	Sales calculations shall produce accurate results.
NFR15	The system shall process sales data efficiently.
NFR16	The system shall handle missing or invalid data appropriately.
NFR17	The processed data shall be stored in an organized format.
NFR18	The Python program shall be maintainable and reusable.

5. Data Visualization Module
Functional Requirements
ID	Requirement
FR24	The system shall generate a daily sales chart.
FR25	The system shall generate a popular food items chart.
FR26	The system shall generate an order status chart.
FR27	The system shall display sales information graphically.
FR28	The system shall save generated charts as image files.

Non-Functional Requirements
ID	Requirement
NFR19	Charts shall be clear and easy to understand.
NFR20	Visualizations shall represent the processed data accurately.
NFR21	Charts shall be generated within a reasonable time.
NFR22	The charts shall use readable titles, labels and legends wherever required.
NFR23	The visualization program shall be easy to maintain and modify.
suitable for your Software Engineering SRS documentat

DATA ENTITIES
The main entities used in the system are:

**1. Customer**
- Customer ID
- Customer Name
- Phone
- Email

**2. MenuItem**
- Item ID
- Item Name
- Category
- Price
- Availability

**3. Order**
- Order ID
- Customer ID
- Order Date
- Total Amount
- Order Status

**4. OrderItem**
- Order Item ID
- Order ID
- Item ID
- Quantity
- Unit Price
- Line Total

DATABASE REQUIREMENTS

The system uses the MySQL database named `restaurant_db`.

The database contains the following tables:

- `customers`
- `menu_items`
- `orders`
- `order_items`
- `payments`

Primary keys and foreign keys are used to maintain relationships between the tables. Constraints help maintain data accuracy and consistency.

