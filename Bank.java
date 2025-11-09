import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Bank {

    private String accountNo; // Store the account number entered by the user

    // Entry method to capture account number
    public void entry() {
        accountNo = JOptionPane.showInputDialog(null,
                "Enter your Account Number to simulate card entry:",
                "Account Entry",
                JOptionPane.PLAIN_MESSAGE);

        if (accountNo == null || accountNo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No account number entered. Exiting.");
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(null, "Account Number captured: " + accountNo);
        }
    }

    // Method to show the main banking GUI
    public void showGUI() {
        JFrame frame = new JFrame("School Banking - Account: " + accountNo);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new FlowLayout());

        JButton button1 = new JButton("Deposit");
        JButton button2 = new JButton("Withdrawal");
        JButton button3 = new JButton("Check Balance");

        frame.add(button1);
        frame.add(button2);
        frame.add(button3);

	button1.addActionListener(e -> {
		String Deposit = JOptionPane.showInputDialog(
				null,
				"Enter the amount you want to deposit:",
				"Amount Entry",
				JOptionPane.PLAIN_MESSAGE
				);
		
		JOptionPane.showMessageDialog(
				frame,
				"Deposit for Account " + accountNo + ": " + Deposit );
	});



        button2.addActionListener(e -> {
		String Amount=JOptionPane.showInputDialog(
				null,
				"Enter the amount you wanna Withdrawal:",
				"Amount Withdrawal",
				JOptionPane.PLAIN_MESSAGE
				);
		JOptionPane.showMessageDialog(frame, "Withdrawal for Account " + accountNo + ":" +Amount );
        });

        button3.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Check Balance for Account " + accountNo);
        });

        frame.setVisible(true);
    }

    // Main method
    public static void main(String[] args) {
        Bank bankApp = new Bank();
        bankApp.entry();     // Capture account number first
        bankApp.showGUI();   // Then show the main GUI
    }
}

