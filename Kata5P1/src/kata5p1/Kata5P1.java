package kata5p1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import kata5p1.view.MailListReader;

public class Kata5P1 {
    
     public static void main(String[] args) throws SQLException, IOException {
        Kata5P1 kata5p1 = new Kata5P1();
        kata5p1.selectAll("PEOPLE");
        
        //
        
        createNewTable();
    
        
        // 
        
        String emailPath = "email.txt";
        List<String> emailList = MailListReader.read(emailPath);
        for (String mail : emailList) {
           kata5p1.insert(mail, "Kata5.db");
        }
        
     }
     
     
     public void insert(String mail, String database) throws SQLException{
        String sql = "INSERT INTO EMAIL(mail) VALUES (?)";
        try (Connection conn = this.connect(database);
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setString(1, mail);
            pstmt.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }   
     }
    
     
    public Connection connect(String datebase){
        String url = "jdbc:sqlite:" + datebase;
        Connection conn = null;
        try { 
             conn = DriverManager.getConnection(url);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    
    
    public void selectAll(String table){
        String sql = "SELECT * FROM PEOPLE";
        try ( Connection conn = this.connect("Kata5.db");
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
    
    
   
    public static void createNewTable() {
        String url = "jdbc:sqlite:Kata5.db";
        String sql = "CREATE TABLE IF NOT EXISTS EMAIL (\n"
                + " id integer PRIMARY KEY AUTOINCREMENT, \n"
                + " mail text NOT NULL);";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement ()) {
            
            stmt.execute(sql);
            System.out.println("Tabla creada");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
         }       
    }
    
}
