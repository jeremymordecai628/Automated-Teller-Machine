package AutomatedTellerMachine;

public class Withdrawal{
	
	// Create an instance of Data class
	Data data = new Data();

	public double withdraw(String accountNo, double Amount) {
		if (Amount > 0) {
			double balance = data.getBalance(accountNo);
			if (Amount > balance) {
				System.out.println(" You cannot withdraw  you have insuffienciet funds .");
				return -1; 
			}
			double newBalance = balance - Amount;

			boolean success = data.updateBalance(accountNo, newBalance);
			if (success) {
				return newBalance;
			} else {
				System.out.println(" Failed to update balance in database.");
				return -1;
			}
		} else {
			System.out.println(" You cannot deposit less than or equal to 0.");
			return -1;
		}
	}
}
