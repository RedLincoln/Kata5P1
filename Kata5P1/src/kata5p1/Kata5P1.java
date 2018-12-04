package kata5p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Kata5P1 {

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
    
    
    public static void main(String[] args) throws SQLException {
        Kata5P1 kata5p1 = new Kata5P1();
        kata5p1.selectAll("PEOPLE");
        
        //
        
        createNewTable();
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
