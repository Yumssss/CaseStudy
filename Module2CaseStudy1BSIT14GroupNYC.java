import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Module2CaseStudy1BSIT14GroupNYC extends JFrame {

    static double totalBill = 0.0;
    static int totalItemsBought = 0;
    static StringBuilder receipt = new StringBuilder("Items Bought:\n");

    String[] itemCodes = {"A", "B", "C", "D", "E"};
    String[] itemDescriptions = {
            "3-in-1 coffee",
            "Cup noodles",
            "Laundry soap",
            "Bottled water - 1 liter",
            "Bottled water - 500 ml"};
    double[] itemPrices = {10.00, 20.00, 25.00, 20.00, 12.00};
    int[] itemStocks = {100, 36, 15, 8, 24};

    JTable itemTable;
    DefaultTableModel tableModel;

    public Module2CaseStudy1BSIT14GroupNYC() {
        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4, 10, 10)); // 1 row, 4 columns, with spacing

        // Create buttons
        JButton buyButton = new JButton("Buy");
        JButton paymentButton = new JButton("Payment");
        JButton inventoryButton = new JButton("Inventory");
        JButton exitButton = new JButton("Exit");

        // Add action listeners to buttons
        buyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                buyItem();
            }
        });
        paymentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                makePayment();
            }
        });
        inventoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showInventory();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Add buttons to the panel
        buttonPanel.add(buyButton);
        buttonPanel.add(paymentButton);
        buttonPanel.add(inventoryButton);
        buttonPanel.add(exitButton);

        // Create a table to show the item information
        String[] columnNames = {"Item Code", "Item Description", "Price"};
        tableModel = new DefaultTableModel(columnNames, 0);
        updateTableModel();
        itemTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(itemTable);

        // Add the button panel and the table to the frame
        setLayout(new BorderLayout());
        add(buttonPanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.CENTER);

        // Set frame properties
        setTitle("Store Menu");
        setSize(400, 170);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void buyItem() {
        // Your existing buyItem code here
        String itemCode = JOptionPane.showInputDialog("Enter item code to buy:");
        int index = -1;
		String userItemcodes = "";
        for (int i = 0; i < itemCodes.length; i++) {
            if (itemCodes[i].equalsIgnoreCase(itemCode)) {
                index = i;
				userItemcodes = itemCodes[i];
                break;
            }
        }
        if (index != -1) {
            int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity:"));
			
			String message = "Would you like to continue this purchase.\n" +
                         "Item code: "+userItemcodes+"\n" +
                         "Quantity; "+quantity;
			
			 int confirmation = JOptionPane.showConfirmDialog( null, message, "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			 
			 if(confirmation == JOptionPane.YES_OPTION){
				if (itemStocks[index] >= quantity) {
                itemStocks[index] -= quantity;
                totalBill += itemPrices[index] * quantity;
				double totalItem = itemPrices[index] * quantity;
                totalItemsBought += quantity;
                receipt.append(itemDescriptions[index]).append("   ").append(quantity).append("   ").append(totalItem).append("\n");
                JOptionPane.showMessageDialog(null, "Item added to cart!");
                updateTableModel();
            } else {
                JOptionPane.showMessageDialog(null, "Not enough stock!");
            
        } 
				 
				 
		}else {System.exit(0);}
			 
	
            
    }else {
            JOptionPane.showMessageDialog(null, "Invalid item code!");
        }
	}
    private void makePayment() {
        // Your existing makePayment code here
        //JOptionPane.showMessageDialog(null, "Total Bill: " + totalBill + "\nItems Bought: " + totalItemsBought + "\n" + receipt.toString());
		JOptionPane.showMessageDialog(null,receipt.toString() + "Total Bill: " + totalBill + "\nNo. of item Purchased: " + totalItemsBought + "\n\n\"Thank you for shopping\"" );
        totalBill = 0.0;
        totalItemsBought = 0;
        receipt.setLength(0);
        receipt.append("Items Bought:\n");
    }

    private void showInventory() {
        // Create a dialog to show the inventory table
        JDialog inventoryDialog = new JDialog(this, "Inventory", true);
        String[] columnNames = {"Item Code", "Stock"};
        Object[][] data = new Object[itemCodes.length][2];
        for (int i = 0; i < itemCodes.length; i++) {
            data[i][0] = itemCodes[i];
            data[i][1] = itemStocks[i];
        }

        JTable inventoryTable = new JTable(new DefaultTableModel(data, columnNames));
        JScrollPane scrollPane = new JScrollPane(inventoryTable);
        inventoryDialog.add(scrollPane, BorderLayout.CENTER);

        inventoryDialog.setSize(400, 300);
        inventoryDialog.setLocationRelativeTo(this);
        inventoryDialog.setVisible(true);
    }

    private void updateTableModel() {
        tableModel.setRowCount(0); // Clear the table
        for (int i = 0; i < itemCodes.length; i++) {
            tableModel.addRow(new Object[]{itemCodes[i], itemDescriptions[i], itemPrices[i]});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Module2CaseStudy1BSIT14GroupNYC().setVisible(true);
            }
        });
    }
}
