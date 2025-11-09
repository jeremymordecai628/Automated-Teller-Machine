import java.sql.*;
import java.util.Scanner;  // ✅ Correct import

public class Authenticate {
    private static final String DB_URL = "jdbc:sqlite:bank.db";

    public static boolean login(String accountNo, String password) {
        String sql = "SELECT * FROM user WHERE account_no = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, accountNo);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);  // ✅ Scanner now works

        System.out.print("Enter Account Number: ");
        String accountNo = input.nextLine();

        System.out.print("Enter Password: ");
        String password = input.nextLine();

        if (login(accountNo, password)) {
            System.out.println("✅ Login successful!");
        } else {
            System.out.println("❌ Invalid account number or password.");
        }

        input.close();
    }
}

