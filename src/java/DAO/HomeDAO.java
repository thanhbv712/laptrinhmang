/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import utils.DBConnection;


/**
 *
 * @author BMA GALAXY
 */
public class HomeDAO extends DBConnection {

    public ArrayList<User> getListUser() {
        Statement state;
        ArrayList<User> listUser = new ArrayList<>();
        try {
            state = this.conn.createStatement();
            String sql = "SELECT * FROM players WHERE username <> 'admin'";
            ResultSet resultSet = state.executeQuery(sql);
            while(resultSet.next()){
                listUser.add(new User(resultSet.getInt("ID"),resultSet.getString("name"),resultSet.getString("email") , resultSet.getString("username"), resultSet.getString("password"),
                             resultSet.getString("status")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listUser;
    }
    
    public User getUserById(int id) {
        PreparedStatement preState;
        try {
            String sql = " SELECT * "
                        + " FROM players "
                        + " WHERE id = ? ";
            preState = this.conn.prepareStatement(sql);
            preState.setInt(1, id);
            ResultSet rs = preState.executeQuery();
            if(rs.next()) {
                User user = new User();
                user.setID(rs.getInt("ID"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setStatus(rs.getString("status"));
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
