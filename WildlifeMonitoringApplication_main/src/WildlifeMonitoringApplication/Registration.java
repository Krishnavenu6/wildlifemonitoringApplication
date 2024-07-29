package WildlifeMonitoringApplication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Registration {
    private String name;
    private String emailId;
    private String password;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/WildlifeMonitoringdb";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    private Scanner sc = new Scanner(System.in);

    public void start() {
        System.out.println("Welcome to Wildlife Monitoring Application");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");

        int choice = sc.nextInt();

        try {
            switch (choice) {
                case 1:
                    registers();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("Thank you for using the application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    start();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registers() throws Exception {
        System.out.println("Enter Name:");
        name = sc.next();

        System.out.println("Enter Email ID:");
        emailId = sc.next();

        System.out.println("Enter Password:");
        password = sc.next();

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String query = "INSERT INTO user1 (Name, EmailId, Password) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, emailId);
                preparedStatement.setString(3, password);

                preparedStatement.executeUpdate();
                System.out.println("Registration Completed Successfully!");
                postRegistrationOptions();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void postRegistrationOptions() throws Exception {
        System.out.println("Enter 1 to Login");
        System.out.println("Enter 2 to Exit");
        int select = sc.nextInt();

        switch (select) {
            case 1:
                login();
                break;
            case 2:
                System.out.println("Thank you.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                postRegistrationOptions();
                break;
        }
    }

    public void login() throws Exception {
        System.out.println("Enter your Email ID:");
        emailId = sc.next();

        System.out.println("Enter your Password:");
        password = sc.next();

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String query = "SELECT * FROM user1 WHERE EmailId = ? AND Password = ?";
            try (PreparedStatement preparedStatement = con.prepareStatement(query)) {
                preparedStatement.setString(1, emailId);
                preparedStatement.setString(2, password);

                ResultSet rs = preparedStatement.executeQuery();

                if (rs.next()) {
                    System.out.println("You are Logged into the Website");
                    home();
                } else {
                    System.out.println("Wrong Email or Password");
                    login();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void home() {
        System.out.println("Welcome to the Wildlife Monitoring Home Page");
        // Implement home functionality here
    }
}