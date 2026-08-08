<<<<<<< Updated upstream
package week1;
=======
>>>>>>> Stashed changes
public class WarehouseInventory {
    public static void main(String[] args) {
        int[] sectionA = {20, 15, 30};
        int[] sectionB = {25, 10, 30};
        int totalA = 0;
        int totalB = 0;
        int highest = sectionA[0];
        String section = "Section A";
        int index = 0;
        for (int i = 0; i < sectionA.length; i++) {
            totalA += sectionA[i];
            if (sectionA[i] > highest) {
                highest = sectionA[i];
                section = "Section A";
                index = i;
            }
        }
        for (int i = 0; i < sectionB.length; i++) {
            totalB += sectionB[i];
            if (sectionB[i] > highest) {
                highest = sectionB[i];
                section = "Section B";
                index = i;
            }
        }
        System.out.println("Section A Total: " + totalA);
        System.out.println("Section B Total: " + totalB);
        if (totalA == totalB) {
            System.out.println("Status: Balanced");
        } else {
            System.out.println("Status: Not Balanced");
        }
        System.out.println("Highest Quantity: " + highest +
                " (" + section + ", Item " + (index + 1) + ")");
    }
}