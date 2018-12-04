package kata5p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Kata5P1 {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:sqlite:Kata5.db";
        String sql = "SELECT * FROM PEOPLE";
        try ( Connection conn = DriverManager.getConnection(url);
              Statement stmt = conn.createStatement();
              ResultSet rs = stmt.executeQuery(sql)){
            while(rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" +
                        rs.getString("Name") + "\t" +
                        rs.getString("Apellidos") + "\t" +
                        rs.getString("Departamento") + "\t");
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
