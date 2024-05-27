import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class AddBlogType {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";
    private static final String PASSWORD = "Walden0042$$";

    public static void main(String[] args) {
        Connection conn = null;
        Statement statement = null;

        try {
            // Connect to MySQL server
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // Create database if not exists
            statement = conn.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS files");
            statement.close();

            // Connect to the newly created database
            conn.setCatalog("files");

            // Create table with BLOB column
            statement = conn.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS images (image BLOB)");
            statement.close();

            // Prepare insert statement with BLOB parameter
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO images (image) VALUES (?)");

            // Read image file into input stream
            File imageFile = new File("src/resources/gui_java.png");
            FileInputStream inputStream = new FileInputStream(imageFile);

            // Set BLOB parameter
            preparedStatement.setBinaryStream(1, inputStream);

            // Execute insert statement
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Image inserted into database.");

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (statement != null)
                    statement.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
