/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package websocket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author buith
 */
@ApplicationScoped
@ServerEndpoint(value="actions")
public class PlayerWebsocketEndpoint {
    @OnOpen
    public void open(Session session) {
        
    }
    
    @OnClose
    public void close(Session session) {
        
    }
    @OnError
    public void onError(Throwable error) {
    }

    @OnMessage
    public void handleMessage(String message, Session session) {
    }
}
