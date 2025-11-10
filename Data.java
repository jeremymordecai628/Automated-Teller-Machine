import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Data {

    private static final String DB_URL = "jdbc:sqlite:bank.db";

    /**
     * Get a database connection
     */
    private static Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");  // ✅ load SQLite driver
            return DriverManager.getConnection(DB_URL);
        } catch (ClassNotFoundException e) {
            System.out.println("❌ SQLite JDBC driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("❌ Database connection error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Authenticate user by account number and password
     */
    public boolean authenticate(String accountNo, String password) {
        String sql = "SELECT * FROM user WHERE account_no = ? AND password = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (conn == null) {
                System.out.println("⚠️ Connection failed.");
                return false;
            }

            pstmt.setString(1, accountNo);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.out.println("❌ Database error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Get account balance
     */
    public double getBalance(String accountNo) {
        String sql = "SELECT balance FROM account WHERE account_no = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (conn == null) return -1;

            pstmt.setString(1, accountNo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble("balance");
            }

        } catch (SQLException e) {
            System.out.println("❌ Database error: " + e.getMessage());
        }

        return -1; // account not found
    }

    /**
     * Update account balance
     */
    public boolean updateBalance(String accountNo, double newBalance) {
        String sql = "UPDATE account SET balance = ? WHERE account_no = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            if (conn == null) return false;

            pstmt.setDouble(1, newBalance);
            pstmt.setString(2, accountNo);

            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("❌ Database error: " + e.getMessage());
            return false;
        }
    }

    // ✅ Test method
    public static void main(String[] args) {
        Data data = new Data();

        String accountNo = "ACC1001";
        String password = "pass123";

        if (data.authenticate(accountNo, password)) {
            System.out.println("✅ Login successful!");
            double balance = data.getBalance(accountNo);
            System.out.println("Current balance: " + balance);

            double newBalance = balance + 500; // deposit example
            if (data.updateBalance(accountNo, newBalance)) {
                System.out.println("💰 Balance updated!");
            }
            System.out.println("New balance: " + data.getBalance(accountNo));
        } else {
            System.out.println("❌ Invalid account or password");
        }
    }
}

