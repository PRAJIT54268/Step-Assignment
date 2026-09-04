import java.util.*;

public class FoodOrder {
    private final String studentName, dishName;
    private boolean delivered;

    public FoodOrder(String studentName, String dishName) {
        if (studentName == null || studentName.trim().isEmpty() ||
            dishName == null || dishName.trim().isEmpty())
            throw new IllegalArgumentException();

        this.studentName = studentName;
        this.dishName = dishName;
    }

    public void markDelivered() {
        if (delivered)
            System.out.println("Already delivered");
        else {
            delivered = true;
            System.out.println("Order delivered");
        }
    }

    public static void processBatch(String[][] rawOrders) {
        int valid = 0, rejected = 0;

        for (String[] o : rawOrders) {
            try {
                new FoodOrder(o[0], o[1]);
                valid++;
            } catch (Exception e) {
                rejected++;
            }
        }

        System.out.println("Valid: " + valid + " | Rejected: " + rejected);
    }

    public static void main(String[] args) {
        String[][] orders = {
            {"Ravi", "Paneer Butter Masala"},
            {"", "Chole Bhature"},
            {"Meera", " "},
            {"Divya", "Veg Biryani"}
        };

        processBatch(orders);
    }
}