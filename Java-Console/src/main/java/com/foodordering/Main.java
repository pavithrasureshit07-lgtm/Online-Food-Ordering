
package com.foodordering;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        try (Connection ignored = DB.get()) {
            System.out.println("Connected to MySQL database: restaurant_db");
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
            System.out.println("Check MySQL Server and DB_URL/DB_USER/DB_PASSWORD.");
            return;
        }

        while (true) {

            System.out.println("\n===== ONLINE FOOD ORDERING SYSTEM =====");
            System.out.println("1. Add customer");
            System.out.println("2. List customers");
            System.out.println("3. Add menu item");
            System.out.println("4. View menu");
            System.out.println("5. Change item availability");
            System.out.println("6. Place order");
            System.out.println("7. Update order status");
            System.out.println("8. View customer orders");
            System.out.println("9. Daily sales report");
            System.out.println("0. Exit");

            int choice = readInt("Enter choice: ");

            try {
                switch (choice) {

                    case 1 -> addCustomer();
                    case 2 -> listCustomers();
                    case 3 -> addMenuItem();
                    case 4 -> viewMenu();
                    case 5 -> changeAvailability();
                    case 6 -> placeOrder();
                    case 7 -> updateStatus();
                    case 8 -> customerOrders();
                    case 9 -> dailySales();

                    case 0 -> {
                        System.out.println("Goodbye!");
                        return;
                    }

                    default -> System.out.println("Invalid choice.");
                }

            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number entered.");
            }
        }
    }

    private static int readInt(String prompt) {

        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid whole number.");
            }
        }
    }

    private static String read(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    // 1. Add Customer

    private static void addCustomer() throws SQLException {

        String name = read("Customer name: ");
        String phone = read("Phone: ");
        String email = read("Email (optional): ");

        String sql = "INSERT INTO customers(customer_name, phone, email) VALUES(?,?,?)";

        try (Connection c = DB.get();
             PreparedStatement p = c.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            p.setString(1, name);
            p.setString(2, phone);
            p.setString(3, email.isEmpty() ? null : email);

            p.executeUpdate();

            try (ResultSet r = p.getGeneratedKeys()) {
                if (r.next()) {
                    System.out.println("Customer added. ID: " + r.getInt(1));
                }
            }
        }
    }

    // 2. List Customers

    private static void listCustomers() throws SQLException {

        String sql = "SELECT * FROM customers ORDER BY customer_id";

        try (Connection c = DB.get();
             Statement s = c.createStatement();
             ResultSet r = s.executeQuery(sql)) {

            System.out.println("\nID | Name | Phone | Email");

            while (r.next()) {
                System.out.printf("%d | %s | %s | %s%n",
                        r.getInt("customer_id"),
                        r.getString("customer_name"),
                        r.getString("phone"),
                        r.getString("email"));
            }
        }
    }

    // 3. Add Menu Item

    private static void addMenuItem() throws SQLException {

        String name = read("Item name: ");
        String category = read("Category: ");
        BigDecimal price = new BigDecimal(read("Price: "));

        String sql = "INSERT INTO menu_items(item_name, category, price) VALUES(?,?,?)";

        try (Connection c = DB.get();
             PreparedStatement p = c.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {

            p.setString(1, name);
            p.setString(2, category);
            p.setBigDecimal(3, price);

            p.executeUpdate();

            try (ResultSet r = p.getGeneratedKeys()) {
                if (r.next()) {
                    System.out.println("Menu item added. ID: " + r.getInt(1));
                }
            }
        }
    }

    // 4. View Menu

    private static void viewMenu() throws SQLException {

        String sql = "SELECT * FROM menu_items ORDER BY category, item_name";

        try (Connection c = DB.get();
             Statement s = c.createStatement();
             ResultSet r = s.executeQuery(sql)) {

            System.out.println("\nID | Item | Category | Price | Available");

            while (r.next()) {
                System.out.printf("%d | %s | %s | %.2f | %s%n",
                        r.getInt("item_id"),
                        r.getString("item_name"),
                        r.getString("category"),
                        r.getBigDecimal("price"),
                        r.getBoolean("available") ? "Yes" : "No");
            }
        }
    }

    // 5. Change Availability

    private static void changeAvailability() throws SQLException {

        int id = readInt("Menu item ID: ");
        boolean available = read("Available? (y/n): ").equalsIgnoreCase("y");

        String sql = "UPDATE menu_items SET available=? WHERE item_id=?";

        try (Connection c = DB.get();
             PreparedStatement p = c.prepareStatement(sql)) {

            p.setBoolean(1, available);
            p.setInt(2, id);

            System.out.println(
                    p.executeUpdate() > 0
                            ? "Availability updated."
                            : "Item not found.");
        }
    }

    // 6. Place Order

    private static void placeOrder() throws SQLException {

        listCustomers();

        int customerId = readInt("Customer ID: ");

        viewMenu();

        Map<Integer, Integer> cart = new LinkedHashMap<>();

        while (true) {

            int itemId = readInt("Item ID (0 to finish): ");

            if (itemId == 0) {
                break;
            }

            int qty = readInt("Quantity: ");

            if (qty <= 0) {
                System.out.println("Quantity must be positive.");
                continue;
            }

            cart.merge(itemId, qty, Integer::sum);
        }

        if (cart.isEmpty()) {
            System.out.println("No items selected.");
            return;
        }

        try (Connection c = DB.get()) {

            c.setAutoCommit(false);

            try {

                String customerCheck =
                        "SELECT customer_id FROM customers WHERE customer_id=?";

                try (PreparedStatement p = c.prepareStatement(customerCheck)) {

                    p.setInt(1, customerId);

                    try (ResultSet r = p.executeQuery()) {
                        if (!r.next()) {
                            throw new SQLException("Customer ID does not exist.");
                        }
                    }
                }

                int orderId;

                String orderSql =
                        "INSERT INTO orders(customer_id, total_amount) VALUES(?,0)";

                try (PreparedStatement p = c.prepareStatement(
                        orderSql, Statement.RETURN_GENERATED_KEYS)) {

                    p.setInt(1, customerId);
                    p.executeUpdate();

                    try (ResultSet r = p.getGeneratedKeys()) {
                        if (!r.next()) {
                            throw new SQLException("Order ID not generated.");
                        }
                        orderId = r.getInt(1);
                    }
                }

                BigDecimal total = BigDecimal.ZERO;

                for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {

                    String itemSql =
                            "SELECT price, available FROM menu_items WHERE item_id=?";

                    try (PreparedStatement p = c.prepareStatement(itemSql)) {

                        p.setInt(1, entry.getKey());

                        try (ResultSet r = p.executeQuery()) {

                            if (!r.next() || !r.getBoolean("available")) {
                                throw new SQLException(
                                        "Item " + entry.getKey()
                                                + " not found or unavailable.");
                            }

                            BigDecimal price = r.getBigDecimal("price");

                            BigDecimal line = price.multiply(
                                    BigDecimal.valueOf(entry.getValue()));

                            total = total.add(line);

                            String itemInsert =
                                    "INSERT INTO order_items(order_id,item_id,quantity,unit_price,line_total) VALUES(?,?,?,?,?)";

                            try (PreparedStatement ins = c.prepareStatement(itemInsert)) {

                                ins.setInt(1, orderId);
                                ins.setInt(2, entry.getKey());
                                ins.setInt(3, entry.getValue());
                                ins.setBigDecimal(4, price);
                                ins.setBigDecimal(5, line);

                                ins.executeUpdate();
                            }
                        }
                    }
                }

                String updateSql =
                        "UPDATE orders SET total_amount=? WHERE order_id=?";

                try (PreparedStatement p = c.prepareStatement(updateSql)) {

                    p.setBigDecimal(1, total);
                    p.setInt(2, orderId);

                    p.executeUpdate();
                }

                c.commit();

                System.out.printf(
                        "Order placed successfully. Order ID: %d | Total: %.2f%n",
                        orderId, total);

            } catch (Exception e) {

                c.rollback();

                if (e instanceof SQLException se) {
                    throw se;
                }

                throw new SQLException(e);

            } finally {
                c.setAutoCommit(true);
            }
        }
    }

    // 7. Update Order Status

    private static void updateStatus() throws SQLException {

        int id = readInt("Order ID: ");

        System.out.println("1. Placed");
        System.out.println("2. Preparing");
        System.out.println("3. Completed");
        System.out.println("4. Cancelled");

        int x = readInt("New status: ");

        String status = switch (x) {
            case 1 -> "Placed";
            case 2 -> "Preparing";
            case 3 -> "Completed";
            case 4 -> "Cancelled";
            default -> null;
        };

        if (status == null) {
            System.out.println("Invalid status.");
            return;
        }

        String sql = "UPDATE orders SET status=? WHERE order_id=?";

        try (Connection c = DB.get();
             PreparedStatement p = c.prepareStatement(sql)) {

            p.setString(1, status);
            p.setInt(2, id);

            System.out.println(
                    p.executeUpdate() > 0
                            ? "Order status updated."
                            : "Order not found.");
        }
    }

    // 8. View Customer Orders

    private static void customerOrders() throws SQLException {

        int id = readInt("Customer ID: ");

        String sql =
                "SELECT o.order_id,o.order_date,o.total_amount,o.status " +
                "FROM orders o WHERE o.customer_id=? " +
                "ORDER BY o.order_date DESC";

        try (Connection c = DB.get();
             PreparedStatement p = c.prepareStatement(sql)) {

            p.setInt(1, id);

            try (ResultSet r = p.executeQuery()) {

                System.out.println("Order ID | Date | Total | Status");

                while (r.next()) {
                    System.out.printf("%d | %s | %.2f | %s%n",
                            r.getInt(1),
                            r.getTimestamp(2),
                            r.getBigDecimal(3),
                            r.getString(4));
                }
            }
        }
    }

    // 9. Daily Sales Report

    private static void dailySales() throws SQLException {

        String sql =
                "SELECT DATE(order_date) day, " +
                "COUNT(*) orders_count, " +
                "SUM(total_amount) sales " +
                "FROM orders WHERE status <> 'Cancelled' " +
                "GROUP BY DATE(order_date) ORDER BY day DESC";

        try (Connection c = DB.get();
             Statement s = c.createStatement();
             ResultSet r = s.executeQuery(sql)) {

            System.out.println("Date | Orders | Sales");

            while (r.next()) {
                System.out.printf("%s | %d | %.2f%n",
                        r.getDate(1),
                        r.getInt(2),
                        r.getBigDecimal(3));
            }
        }
    }
}
