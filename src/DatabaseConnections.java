import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnections {

    private static final String URL = "jdbc:mysql://localhost:3306/evening_batch";
    private static final String USER = "root";
    private static final String PASSWORD = "Walden0042$$";

    public static void main(String[] args) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            // Test each CRUD operation
            insertStudent(3, "John Doe");
            readStudents();
            updateStudent(3, "Jane Doe");
            readStudents();
            deleteStudent(3);
            readStudents();
            
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found: " + e.getMessage());
        }
    }

    // Create
    public static void insertStudent(int id, String name) {
        String insertSql = "INSERT INTO student (id, name) VALUES (?, ?)";
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement ps = c.prepareStatement(insertSql)) {

            ps.setInt(1, id);
            ps.setString(2, name);

            int rowAffected = ps.executeUpdate();
            if (rowAffected > 0) {
                System.out.println("Data added successfully");
            } else {
                System.out.println("Data insert failure");
            }
        } catch (SQLException e) {
            System.out.println("Insert Error: " + e.getMessage());
        }
    }

    // Read
    public static void readStudents() {
        String selectSql = "SELECT * FROM student";
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement ps = c.prepareStatement(selectSql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                System.out.println(rs.getInt("id") + " : " + rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("Read Error: " + e.getMessage());
        }
    }

    // Update
    public static void updateStudent(int id, String newName) {
        String updateSql = "UPDATE student SET name = ? WHERE id = ?";
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement ps = c.prepareStatement(updateSql)) {

            ps.setString(1, newName);
            ps.setInt(2, id);

            int rowAffected = ps.executeUpdate();
            if (rowAffected > 0) {
                System.out.println("Data updated successfully");
            } else {
                System.out.println("Data update failure");
            }
        } catch (SQLException e) {
            System.out.println("Update Error: " + e.getMessage());
        }
    }

    // Delete
    public static void deleteStudent(int id) {
        String deleteSql = "DELETE FROM student WHERE id = ?";
        try (Connection c = DriverManager.getConnection(URL, USER, PASSWORD);
                PreparedStatement ps = c.prepareStatement(deleteSql)) {

            ps.setInt(1, id);

            int rowAffected = ps.executeUpdate();
            if (rowAffected > 0) {
                System.out.println("Data deleted successfully");
            } else {
                System.out.println("Data delete failure");
            }
        } catch (SQLException e) {
            System.out.println("Delete Error: " + e.getMessage());
        }
    }
}
