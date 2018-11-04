package utils;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

/**
 *
 * @author buith
 */
public class DBConnection {
    public Connection conn = null;
    public DBConnection(){
        this.createConnection();
    }
    public void createConnection() {
        
        String url = "jdbc:mysql://localhost:3306/laptrinhmang"; //MySQL URL and followed by the database name
        String username = "root"; //MySQL username
        String password = ""; //MySQL password

        try {
            try {
                Class.forName("com.mysql.jdbc.Driver"); //loading mysql driver 
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            this.conn = (Connection) DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
            System.out.println("Printing connection object " + conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public boolean checkLogin(User user) {
        try {
            Statement state = this.conn.createStatement();
            String sql = "SELECT username, password FROM players WHERE username = '" + user.getUsername() +"'";
            System.out.println(sql);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                if (user.getUsername().equals(rs.getString("username")) && user.getPassword().equals(rs.getString("password"))) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}