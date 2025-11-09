import javax.swing.*;
import java.awt.event.*;

public class Bank {
	public static void main(String[] args) {
		JFrame frame = new JFrame("School Banking");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setLayout(new java.awt.FlowLayout());

		// Components
		JLabel label = new JLabel("Enter Amount:");
		JTextField textField = new JTextField(10);
		JButton button1 = new JButton("Deposit");
		JButton button2 = new JButton("Withdrawal");
		JButton button3 = new JButton("Check Balance");

		// Add components to frame
		frame.add(button1);
		frame.add(button2);
		frame.add(button3);

		// Onclick listeners
		button1.addActionListener(e -> {
			JOptionPane.showMessageDialog(frame, "Deposit button clicked!");
		});

		button2.addActionListener(e -> {
			frame.add(label);
			frame.add(textField);
			String input = textField.getText();
			JOptionPane.showMessageDialog(frame, "Withdrawal button clicked! Amount: " + input);
		});

		button3.addActionListener(e -> {
			JOptionPane.showMessageDialog(frame, "Check Balance button clicked!");
		});

		frame.setVisible(true);
	}
}

