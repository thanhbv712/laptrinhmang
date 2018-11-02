/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

/**
 *
 * @author buith
 */
public class DBConnection {

    public static Connection createConnection() {
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/laptrinhmang"; //MySQL URL and followed by the database name
        String username = "root"; //MySQL username
        String password = ""; //MySQL password

        try {
            try {
                Class.forName("com.mysql.jdbc.Driver"); //loading mysql driver 
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            con = (Connection) DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
            System.out.println("Printing connection object " + con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
