package AutomatedTellerMachine;

public class Deposit {
    // Create an instance of Data class
    Data data = new Data();

    public double deposit(String accountNo, double depositAmount) {
        if (depositAmount > 0) {
            double balance = data.getBalance(accountNo);
            double newBalance = balance + depositAmount;

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

