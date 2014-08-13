package org.mdpnp.tyrusexample.client;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.websocket.ClientEndpoint;
import javax.websocket.ClientEndpointConfig;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.glassfish.tyrus.client.ClientManager;
import org.json.JSONObject;

/**
 * Runs a client enpoint connection. Make sure there's a server-side
 * endpoint opne too, either with the standalone (RunStandalone) example, 
 * by deploying a war with the EchoEndpointAnnotated class or by sending your request
 * to ENDPOINT = "ws://openice.info/DDS/DDS";
 * 
 * @author diego@mdpnp.org
 *
 */
public class RunClient {
	 private static CountDownLatch messageLatch;
	 
	 //TODO Play with the different messages and endpoint URLs
	 //Demo: echo back the classic "Hello World" message
//	    private static final String SENT_MESSAGE = "Hello World";
//	    private static final String ENDPOINT ="ws://localhost:8080/TyrusExample/echo";
	 
	    //send a JSON encoded request to subscribe to a topic
	 	private static final String SENT_MESSAGE = "{\"messageType\": \"Subscribe\", \"domain\": 15, \"topic\": \"Numeric\"}";
// 	    private static final String ENDPOINT = "ws://openice.info/DDS/DDS"; //old URL
	 	private static final String ENDPOINT = "ws://openice.info/websocket/";
	 	

	    public static void main(String [] args){
	    	 	
	        try {
	        	
		    	//prepare JSON subscription request object
			 	final JSONObject jsonReq = new JSONObject();//request
			 	jsonReq.put("messageType", "Subscribe");
			 	jsonReq.put("domain", new Integer(15));
			 	jsonReq.put("topic", "Numeric");
			 	
			 	
	            messageLatch = new CountDownLatch(1); //this is just to make it stop

	            final ClientEndpointConfig cec = ClientEndpointConfig.Builder.create().build();

	            ClientManager client = ClientManager.createClient();
	            
	            //We could use an annotated or programmatic Endpoint classes
//	            client.connectToServer(new ClientAnnotatedEndpoint(), new URI(ENDPOINT)); //client annotated Endpoint
//	            client.connectToServer(new EchoEndpointProgrammatic(), new URI(ENDPOINT));//client programmatic class extending javax.websocket.Endpoint 
	            
	            //or just define here a javax.websocket.Endpoint  
	                        
	            client.connectToServer(new Endpoint() {

	                @Override
	                public void onOpen(Session session, EndpointConfig config) {
	                    try {
	                        session.addMessageHandler(new MessageHandler.Whole<String>() {

	                            @Override
	                            public void onMessage(String message) {
	                                System.out.println("Received message: "+message);
	                                messageLatch.countDown();
	                            }
	                        });
//	                        session.getBasicRemote().sendText(SENT_MESSAGE);
	                        session.getBasicRemote().sendText(jsonReq.toString());
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }, cec, new URI(ENDPOINT));
	
	            messageLatch.await(100, TimeUnit.SECONDS);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    /**
	     * A client endpoint. Annotation is @ClientEndpoint as opposed
	     * to @ServerEndpoint, of course. Implements the 4 endpoint event methods: onOpen, onMessage, onClose, onError
	     *
	     */
	 	@ClientEndpoint
	 	public static class ClientAnnotatedEndpoint {
		     // This is an example of an annotated client side endpoint. Is an inner class, but could be its
		     // own class and would work just the same. 
	 		
        	@OnClose
        	public void onClose(Session session) {
        		
        	}
        	@OnError
        	public void onError(Session session, Throwable error) {
        		
        	}
        	
        	@OnMessage
        	public void onMessage(Session session, String msg) {
        		System.out.println("msg="+msg);
        	}
        	
            @OnOpen
            public void onOpen (Session session) {
                try {
                    session.getBasicRemote().sendText(SENT_MESSAGE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }     	 		
	 	}
}
