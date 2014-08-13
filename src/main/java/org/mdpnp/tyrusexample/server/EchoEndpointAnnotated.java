package org.mdpnp.tyrusexample.server;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;






//import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple "HelloWorld-ish"server-side websocket endpoint to deploy in the container
 * which will echo the received messages back to the sender (OnMessage) for demo purposes. <p>
 * 
 * This class is a simple POJO, converted into a websocket by the annotation @ServerEndpoint <p>
 * 
 * Implements events onOpen, onMessage and onClose. 
 * Socket status: this is not necessary
 * CONNECTING = 0
 * OPEN = 1 
 * CLOSING = 2
 * CLOSED = 3
 * 
 * 
 * @author diego@mdpnp.org
 *
 */
@ServerEndpoint(value = "/echo")
public class EchoEndpointAnnotated {
	
	private static Logger annotatedEndpointLogger = LoggerFactory.getLogger(EchoEndpointAnnotated.class);
	
	/**
	 * This method is invoked each time that the client receives a WebSocket message.
	 * @param message
	 * @param session
	 * @return
	 */
    @OnMessage 
    public String onMessage(String message, Session session) {    
    	annotatedEndpointLogger.info(session.getId() + " sending message "+message);
    	return message; //echo back the message received
    }
    
    
    /**
     * OnOpen (when a socket has been opened) allows us to intercept the creation of a new session.
     * The session class allows us to send data to the user.
     * In the method onOpen, we'll let the user know that the handshake was 
     * successful.
     */
    @OnOpen
    public void onOpen(Session session){
        annotatedEndpointLogger.info(session.getId() + " connection open");
        try {
            session.getBasicRemote().sendText("Connection Established");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @OnClose
    public void onClose(Session session){
    	annotatedEndpointLogger.info(session.getId() + " connection closed");
    }
    
	@OnError
	public void onError(Session session, Throwable error) {
		annotatedEndpointLogger.error(session.getId() + error.getMessage());
	}
}
