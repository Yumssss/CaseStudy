import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Module2CaseStudy1BSIT14GroupNYC extends JFrame implements ActionListener {
   JButton button1,button2,button3,button4;
 
 //  private List<String> totalItemBought = new ArrayList<>();
   	private String descriptionItemBought[];
    private int quantityItemBought[];
    private double totalPriceItemBought[];
	private double totalBill;
	private int totalQuantity;
   private int itemBought = 0;

   public Module2CaseStudy1BSIT14GroupNYC() {
        setTitle("MINI SHOP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create buttons
        button1 = new JButton("Buy");
		button1.addActionListener(this);
        button2 = new JButton("Payment");
		button2.addActionListener(this);
        button3 = new JButton("Inventory");
		button3.addActionListener(this);
        button4 = new JButton("Exit");
		button4.addActionListener(this);
        
        // Create labels
        JLabel label1 = new JLabel("Item code");
        JLabel label2 = new JLabel("Item Description");
        JLabel label3 = new JLabel("Price");
        JLabel label4 = new JLabel("A");
		JLabel label5 = new JLabel("3-in-1 coffee");
		JLabel label6 = new JLabel("10.00");
		JLabel label7 = new JLabel("B");
		JLabel label8 = new JLabel("Cup Noodles");
		JLabel label9 = new JLabel("20.00");
		JLabel label10 = new JLabel("C");
		JLabel label11 = new JLabel("Laundry soap");
		JLabel label12 = new JLabel("25.00");
		JLabel label13 = new JLabel("D");
		JLabel label14 = new JLabel("Bottled water - 1 liter");
		JLabel label15 = new JLabel("20.00");
		JLabel label16 = new JLabel("E");
		JLabel label17 = new JLabel("Bottled water - 500 ml");
        JLabel label18 = new JLabel("12.00");
        // Set layout to BorderLayout
        setLayout(new BorderLayout());
        
        // Create a panel to hold buttons and labels
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6,3,75,0)); // 2 rows, 4 columns
        
        // Add labels and buttons to the panel
        mainPanel.add(label1);
        mainPanel.add(label2);
        mainPanel.add(label3);
        mainPanel.add(label4);
		mainPanel.add(label5);
		mainPanel.add(label6);
		mainPanel.add(label7);
		mainPanel.add(label8);
		mainPanel.add(label9);
		mainPanel.add(label10);
		mainPanel.add(label11);
		mainPanel.add(label12);
		mainPanel.add(label13);
		mainPanel.add(label14);
		mainPanel.add(label15);
		mainPanel.add(label16);
		mainPanel.add(label17);
		mainPanel.add(label18);
		add(mainPanel, BorderLayout.NORTH);
		
		
		JPanel mainPanel2 = new JPanel();
		mainPanel2.setLayout(new FlowLayout());
        mainPanel2.add(button1);
        mainPanel2.add(button2);
        mainPanel2.add(button3);
        mainPanel2.add(button4);
		
        
		add(mainPanel2, BorderLayout.CENTER);
        // Add the panel to the frame
        
        
        // Set frame size and make it visible
        setSize(400, 200);
        setVisible(true);
    }//end of GUI JFRAME
	
	@Override
	public void actionPerformed(ActionEvent b){
		

		if (b.getSource()==button1){
			

		
		itemBought++;
		
		JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String[] items = {"3-in-1 coffee", "Cup noodles", "Laundry soap", "Bottled water - 1 liter", "Bottled water - 500 ml"};
        double[] prices = {30.00, 20.00, 25.00, 15.00, 12.00};
        String[] codes = {"A", "B", "C", "D", "E"};

        double totalBill = 0.0;
        boolean continueShopping = true;
		

        while (continueShopping) {
            String userInputItem = JOptionPane.showInputDialog(frame,"Please enter the code of the desired item (A, B, C, D, E):");

            
            int itemIndex = -1;
			String userItemcodes = "";
            for (int i = 0; i < codes.length; i++) {
                if (codes[i].equalsIgnoreCase(userInputItem)) {
                    itemIndex = i;
					userItemcodes = codes[i];
                    break;
                }
            }

            if (itemIndex == -1) {
                JOptionPane.showMessageDialog(frame, "Invalid item code. Please try again.");
                continue;
            }
            
            boolean validQuantity = false;
            int quantity = 0;
            while (!validQuantity) {
                String quantityInput = JOptionPane.showInputDialog(frame,"Enter quantity for " + items[itemIndex] + ":");
                try {
                    quantity = Integer.parseInt(quantityInput);
                    if (quantity > 0) {
                        validQuantity = true;
                    } else {
                        JOptionPane.showMessageDialog(frame, "Quantity must be greater than 0. Please try again.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frame, "Invalid quantity. Please enter a number.");
                }
            }
			String message = "Would you like to continue this purchase.\n" +
                         "Item code: "+userItemcodes+"\n" +
                         "Quantity; "+quantity;
			
			 int choice = JOptionPane.showConfirmDialog( frame, message, "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		
		if (choice == JOptionPane.YES_OPTION){
			double itemTotal = prices[itemIndex] * quantity;
            totalBill += itemTotal;
            totalQuantity += quantity;
			
            JOptionPane.showMessageDialog(frame,"Your transaction has been recorded.");

            
            /*int response = JOptionPane.showConfirmDialog(null, "Do you want to buy another item?", "Continue Shopping?", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.NO_OPTION) {*/
			
			
			//}
			continueShopping = false;
				 
        } else if (choice == JOptionPane.NO_OPTION) {
           continueShopping = false;
        } else {
           continueShopping = false;
        }
            }frame.dispose();
        
		}//button 1
		if (b.getSource() ==button2){
			
			String message = "Items Bought:\n\n" +
                         descriptionItemBought[itemBought]+"\t"+quantityItemBought[itemBought]+"\t"+totalPriceItemBought+"\n" +
                         "No. of Item Purchased; "+totalQuantity+"\n"+
						 "Total Bill: "+totalBill+"\n\n"+
						 "\"Thank you for shopping\"";
						 
			JOptionPane.showMessageDialog(null,message,"PAYMENT",JOptionPane.INFORMATION_MESSAGE);			 
		}//button 2
		if (b.getSource() ==button3){}
		if (b.getSource() ==button4){
			System.exit(0);
		}
	}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Module2CaseStudy1BSIT14GroupNYC());
		  	
    }
}
