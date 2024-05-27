import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnections {
	

    public static void main(String[] args) {
        
    	try {
    		
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/evening_batch", "root", "Walden0042$$");
            
            String sql = "insert into student (id,name) values (2,'maulik')";
            
            Statement s = c.createStatement();
            
            s.executeUpdate(sql);
            
            String sql1 = "select * from student";
            
            ResultSet result =  s.executeQuery(sql1);
            
            while(result.next()) {
            	System.out.println(result.getInt("id") + " : "+result.getString("name"));
            }
            
            System.out.println("Data inserted");
            
            if(!c.isClosed() && c!=null) {
            	System.out.println("Connection Established");
            }else {
            	System.out.println("Connection not Established");
            }
          
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    	}  
    }

}
