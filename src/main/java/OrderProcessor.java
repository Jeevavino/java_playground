public class OrderProcessor {

    // This LOOKS like it needs nested ifs (but it doesn't!)
    public static String processOrderNested(String item, int quantity, double price, String customerType) {
        if (item != null && !item.isEmpty()) {
            if (quantity > 0) {
                if (price > 0) {
                    if (customerType != null) {
                        if (customerType.equals("VIP") || customerType.equals("Regular") || customerType.equals("Guest")) {
                            if (quantity <= 100) {
                                if (price <= 1000) {
                                    double total = quantity * price;
                                    if (customerType.equals("VIP")) {
                                        total = total * 0.8; // 20% discount
                                    }
                                    return "Order processed! Total: $" + String.format("%.2f", total);
                                } else {
                                    return "Price too high - maximum $1000 per item";
                                }
                            } else {
                                return "Quantity too high - maximum 100 items";
                            }
                        } else {
                            return "Invalid customer type - use VIP, Regular, or Guest";
                        }
                    } else {
                        return "Customer type is required";
                    }
                } else {
                    return "Price must be positive";
                }
            } else {
                return "Quantity must be positive";
            }
        } else {
            return "Item name is required";
        }
    }

    // Same logic with immediate return - crystal clear!
    public static String processOrder(String item, int quantity, double price, String customerType) {
        if (item == null || item.isEmpty()) {
            return "Item name is required";
        }

        if (quantity <= 0) {
            return "Quantity must be positive";
        }

        if (price <= 0) {
            return "Price must be positive";
        }

        if (customerType == null) {
            return "Customer type is required";
        }

        if (!customerType.equals("VIP") && !customerType.equals("Regular") && !customerType.equals("Guest")) {
            return "Invalid customer type - use VIP, Regular, or Guest";
        }

        if (quantity > 100) {
            return "Quantity too high - maximum 100 items";
        }

        if (price > 1000) {
            return "Price too high - maximum $1000 per item";
        }

        // Happy path - calculate and return total
        double total = quantity * price;
        if (customerType.equals("VIP")) {
            total = total * 0.8; // 20% discount
        }

        return "Order processed! Total: $" + String.format("%.2f", total);
    }

    public static void main(String[] args) {
        // Test cases: item, quantity, price, customerType
        Object[][] orders = {
                {null, 5, 10.0, "Regular"},
                {"Laptop", 0, 500.0, "VIP"},
                {"Phone", 2, -100.0, "Guest"},
                {"Tablet", 3, 200.0, null},
                {"Monitor", 1, 300.0, "Premium"},
                {"Keyboard", 150, 50.0, "Regular"},
                {"Mouse", 10, 1500.0, "VIP"},
                {"Headphones", 2, 100.0, "VIP"},
                {"Speaker", 5, 80.0, "Regular"}
        };

        System.out.println("=== Testing Orders ===");
        for (int i = 0; i < orders.length; i++) {
            Object[] order = orders[i];
            String result = processOrder(
                    (String) order[0],
                    (Integer) order[1],
                    (Double) order[2],
                    (String) order[3]
            );
            System.out.println("Order " + (i + 1) + ": " + result);
        }
    }
}
