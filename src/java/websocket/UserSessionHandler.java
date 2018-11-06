/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;
import model.User;
import org.json.simple.JSONObject;

/**
 *
 * @author buith
 */
//@ApplicationScoped
public class UserSessionHandler {
    private final Set<Session> sessions = new HashSet<>();
    private final Set<User> users = new HashSet<>();
    
    public void addSession(Session session) {
     sessions.add(session);
     for(User u : users) {
         JSONObject addMessage = createAddMessage(u);
         sendToSession(session, addMessage);
     }
    }
    
    public void removeSession(Session session) {
        sessions.remove(session);
    }
    
    
    public List<User> getDevices() {
        return new ArrayList<>(users);
    }

    public void addUser(User user) {
        users.add(user);
        JSONObject json = createAddMessage(user);
        sendToAllConnectedSessions(json);
    }

    public void removeUser(int id) {
        User user = getUserById(id);
        if(user != null) {
            users.remove(user);
            JSONObject json = new JSONObject();
            json.put("action", "remove");
            json.put("id", id);
            sendToAllConnectedSessions(json);
        }
    }

    public void toggleUser(int id) {
        User u = getUserById(id);
        JSONObject json = new JSONObject();
        if(u != null) {
            if("1".equals(u.getStatus())) {
                u.setStatus("0");
            } else {
                u.setStatus("1");
            }
            json.put("action", "toggle");
            json.put("id", u.getID());
            json.put("status", u.getStatus());
            sendToAllConnectedSessions(json);
        }
    }

    private User getUserById(int id) {
        for(User u: users) {
            if(u.getID() == id) {
                return u;
            }
        }
        return null;
    }

    public JSONObject createAddMessage(User user) {
        JSONObject json = new JSONObject();
        json.put("action", "add");
        json.put("id", user.getID());
        json.put("username", user.getUsername());
        json.put("status", user.getStatus());
        return json;
    }

    public void sendToAllConnectedSessions(JSONObject message){
        for(Session sess : sessions) {
            sendToSession(sess, message);
        }
    }

    public void sendToSession(Session session, JSONObject message){
        try {
            session.getBasicRemote().sendText(message.toString());
        } catch (IOException ex) {
            Logger.getLogger(UserSessionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
}
