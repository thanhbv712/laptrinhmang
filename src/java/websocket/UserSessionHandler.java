/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;
import model.User;
import org.json.simple.JSONObject;

/**
 *
 * @author buith
 */
@ApplicationScoped
public class UserSessionHandler {
    private final Set<Session> sessions = new HashSet<>();
    private final Set<User> users = new HashSet<>();
    
    public void addSession(Session session) {
     sessions.add(session);
    }
    
    public void removeSession(Session session) {
        sessions.remove(session);
    }
    
    
    public List<User> getDevices() {
        return new ArrayList<>(users);
    }

    public void addUser(User user) {
    }

    public void removeUser(int id) {
    }

    public void toggleUser(int id) {
    }

    private User getDeviceById(int id) {
        return null;
    }

    private JSONObject createAddMessage(User user) {
        return null;
    }

    private void sendToAllConnectedSessions(JSONObject message) {
    }

    private void sendToSession(Session session, JSONObject message) {
    }
}
