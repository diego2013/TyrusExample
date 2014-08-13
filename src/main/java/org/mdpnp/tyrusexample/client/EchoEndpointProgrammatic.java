package org.mdpnp.tyrusexample.client;

import java.io.IOException;

import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.Session;


//import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * EchoEndpointProgrammatic is not ServerApplicationConfig descendant
 * so can't be used on server side
 * @author diego@mdpnp.org
 *
 */
public class EchoEndpointProgrammatic extends Endpoint{
	
	private static Logger programaticEndPointLogger = LoggerFactory.getLogger(EchoEndpointProgrammatic.class);
	
    @Override
    public void onOpen(final Session session, EndpointConfig config) {
    	programaticEndPointLogger.info(session.getId() + " has opened a connection");
        session.addMessageHandler(new MessageHandler.Whole<String>() {
            @Override
            public void onMessage(String message) {
                try {
                	programaticEndPointLogger.info(session.getId() + " sending message "+message);
                    session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
