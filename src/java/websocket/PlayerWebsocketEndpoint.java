/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import DAO.HomeDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import model.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author buith
 */
@ApplicationScoped
@ServerEndpoint(value="/actions")
public class PlayerWebsocketEndpoint {
    private static String ADD_ACTION = "add";
    private static String REMOVE_ACTION = "remove";
    private static String TOGGLE_ACTION = "toggle";
    @Inject
    private UserSessionHandler sessionHandler;
    
    @OnOpen
    public void open(Session session) {
        sessionHandler.addSession(session);
    }
    
    @OnClose
    public void close(Session session) {
        sessionHandler.removeSession(session);
    }
    @OnError
    public void onError(Throwable error) {
        Logger.getLogger(PlayerWebsocketEndpoint.class.getName()).log(Level.SEVERE, null, error);
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
        JSONParser parser = new JSONParser();
        HomeDAO dao = new HomeDAO();
        try {
            JSONObject jsObj = (JSONObject) parser.parse(message);
            String action =(String) jsObj.get("action");
            if(ADD_ACTION.equals(action)) {
                User u = dao.getUserById((int) jsObj.get("ID"));
                sessionHandler.addUser(u);
            }
            if(REMOVE_ACTION.equals(action)) {
                int id = (int) jsObj.get("id");
                sessionHandler.removeUser(id);
            }
            
            if(TOGGLE_ACTION.equals(action)) {
                int id = (int) jsObj.get("id");
                sessionHandler.toggleUser(id);
            }
        } catch (ParseException ex) {
            Logger.getLogger(PlayerWebsocketEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
