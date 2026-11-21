Test ID	Actual Result	Status	Test Date	Tested By
TC01	Customer added successfully.	Pass	21-11-2026	Student
TC02	Customer list displayed successfully.	Pass	21-11-2026	Student
TC03	New menu item added successfully.	Pass	21-11-2026	Student
TC04	Menu items displayed with price and availability.	Pass	21-11-2026	Student
TC05	Menu item availability updated successfully.	Pass	21-11-2026	Student
TC06	Order placed successfully with total amount ₹540.00.	Pass	21-11-2026	Student
TC07	Order status updated successfully.	Pass	21-11-2026	Student
TC08	Customer order details displayed successfully.	Pass	21-11-2026	Student
TC09	Daily sales report displayed successfully.	Pass	21-11-2026	Student
TC10	Application exited successfully.	Pass	21-11-2026	Student


1. Customer Management Module

Test ID	Test Scenario	Test Input	Expected Result
TC01	Add a customer with valid details.	Name: Raja, Phone: 6398735643, Email: raja@gmail.com	Customer should be added successfully.
TC02	Add a customer without an email address.	Name: Kumar, Phone: 9876543210, Email: Blank	Customer should be added with an optional email left empty.
TC03	View all registered customers.	Select option 2.	All customer records should be displayed with ID, name, phone and email.
TC04	Enter an invalid phone number while adding a customer.	Phone: abc123	System should reject invalid phone input if validation is implemented.
TC05	Enter an empty customer name.	Name: Blank	System should reject the empty name if validation is implemented.

2. Menu Management Module

Test ID	Test Scenario	Test Input	Expected Result
TC06	Add a new food item.	Name: French Fries, Category: Snacks, Price: 70	Food item should be added successfully.
TC07	Add a food item with a valid price.	Name: Burger, Category: Fast Food, Price: 150	Item should be stored with the correct price.
TC08	View all menu items.	Select option 4.	All menu items should be displayed with ID, name, category, price and availability.
TC09	Change an available item to unavailable.	Item ID: 3, Availability: No	Item availability should change to No.
TC10	Enter an invalid menu item ID.	Item ID: 999	System should display an appropriate error message.

3. Order Management Module

Test ID	Test Scenario	Test Input	Expected Result
TC11	Place an order using a valid customer ID.	Customer ID: 9	System should accept the customer details and allow ordering.
TC12	Place an order with a valid food item.	Item ID: 5, Quantity: 3	Order should be placed successfully.
TC13	Calculate the total order amount.	Chicken Biryani: ₹180 × 3	Total amount should be ₹540.
TC14	Place an order using an unavailable food item.	Item ID: 3	System should prevent ordering the unavailable item.
TC15	Update an existing order status.	Order ID: 7, Status: Placed	Order status should be updated successfully.
TC16	View orders using a valid customer ID.	Customer ID: 9	All orders belonging to that customer should be displayed.
TC17	Enter an invalid customer ID while placing an order.	Customer ID: 999	System should display an appropriate error message.

4. Sales Analysis Module

Test ID	Test Scenario	Test Input	Expected Result
TC18	Retrieve order data from MySQL.	Execute sales analysis Python program.	Order details should be retrieved successfully.
TC19	Generate daily sales report.	Existing order records	Daily order count and sales amount should be displayed.
TC20	Calculate total sales.	Order amounts from the database	Total sales should be calculated correctly according to the report's rules.
TC21	Process order data using Pandas.	Retrieved order dataset	Data should be processed and stored in the output CSV file.

5. Data Visualization Module

Test ID	Test Scenario	Test Input	Expected Result
TC22	Generate daily sales chart.	Processed order data	Daily sales graph should be generated successfully.
TC23	Generate popular food items chart.	Food item and order quantity data	Popular food items should be represented graphically.
TC24	Generate order status chart.	Order status data	Order statuses should be displayed in a chart.
TC25	Save generated visualizations.	Execute visualization Python program.	Graph images should be saved in the output folder.


