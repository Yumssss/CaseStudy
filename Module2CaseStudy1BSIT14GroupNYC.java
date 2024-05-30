import java.util.*;
import javax.swing.JOptionPane;
public class Module2CaseStudy1BSIT14GroupNYC {
    static double totalBill = 0.0;
    static int totalItemsBought = 0;
    static StringBuilder receipt = new StringBuilder("Items Bought:\n");
    
    public static void main (String[] args){
String[] itemCodes = {"A", "B", "C", "D", "E"};
        String[] itemDescriptions = {
                "3-in-1 coffee",
                "Cup noodles",
                "Laundry soap",
                "Bottled water - 1 liter",
                "Bottled water - 500 ml"};
        double[] itemPrices = {10.00, 20.00, 25.00, 20.00, 12.00};
        int[] itemStocks = {100, 36, 15, 8, 24};

        while (true) {
            String[] options = {"Buy", "Payment", "Inventory", "Exit"};
            int choice = JOptionPane.showOptionDialog(null, getInventoryString(itemCodes, itemDescriptions, itemPrices, itemStocks), "Store Menu",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

            if (choice == 0) {
                buyItem(itemCodes, itemDescriptions, itemPrices, itemStocks);
            } else if (choice == 1) {
                makePayment();
            } else if (choice == 2) {
                JOptionPane.showMessageDialog(null, getInventoryString(itemCodes, itemDescriptions, itemPrices, itemStocks), "Inventory", JOptionPane.INFORMATION_MESSAGE);
            } else if (choice == 3) {
                System.exit(0);
            }
        }
    }

    static String getInventoryString(String[] itemCodes, String[] itemDescriptions, double[] itemPrices, int[] itemStocks) {
        StringBuilder sb = new StringBuilder();
        sb.append("Item Code\tItem Description\tPrice\tStock\n");
        for (int i = 0; i < itemCodes.length; i++) {
            sb.append(itemCodes[i]).append("\t\t").append(itemDescriptions[i]).append("\t").append(itemPrices[i]).append("\t").append(itemStocks[i]).append("\n");
        }
        return sb.toString();
    }

    static void buyItem(String[] itemCodes, String[] itemDescriptions, double[] itemPrices, int[] itemStocks) {
        String itemCode = JOptionPane.showInputDialog("Enter item code:");
        int itemIndex = getItemIndexByCode(itemCodes, itemCode);

        if (itemIndex == -1) {
            JOptionPane.showMessageDialog(null, "Invalid item code.");
            return;
        }

        int quantity;
        try {
            quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity:"));
            if (quantity <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid quantity.");
            return;
        }

        if (itemStocks[itemIndex] < quantity) {
            JOptionPane.showMessageDialog(null, itemCodes[itemIndex] + " out of stock.");
            return;
        }

        itemStocks[itemIndex] -= quantity;
        double totalItemPrice = itemPrices[itemIndex] * quantity;
        
        // Update total bill and total items bought
        totalBill += totalItemPrice;
        totalItemsBought += quantity;
        
        // Update receipt
        receipt.append(itemDescriptions[itemIndex]).append("\t        ").append(quantity).append("\t        ").append(totalItemPrice).append("\n");
		

        JOptionPane.showMessageDialog(null, "Item added to cart. Total price for this item: " + totalItemPrice);
    }

    static int getItemIndexByCode(String[] itemCodes, String itemCode) {
        for (int i = 0; i < itemCodes.length; i++) {
            if (itemCodes[i].equalsIgnoreCase(itemCode)) {
                return i;
            }
        }
        return -1;
    }

    static void makePayment() {
        if (totalItemsBought == 0) {
            JOptionPane.showMessageDialog(null, "No items bought.", "Payment", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        receipt.append("\nNo. of items purchased: ").append(totalItemsBought).append("\n");
        receipt.append("Total Bill: ").append(totalBill).append("\n");
        receipt.append("Thank you for shopping!");

        JOptionPane.showMessageDialog(null, receipt.toString(), "Payment", JOptionPane.INFORMATION_MESSAGE);

        // Reset the bill and items for the next transaction
        totalBill = 0.0;
        totalItemsBought = 0;
        receipt = new StringBuilder("Items Bought:\n");
        
  }
}