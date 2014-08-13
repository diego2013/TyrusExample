package org.mdpnp.tyrusexample.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.glassfish.tyrus.server.Server;

/**
 * Run Tyrus in standalone mode, for demo purposes. Run this class to open a server-side
 * endpoint and then run the client class that will open the client endpoint.
 * 
 * @author diego@mdpnp.org
 *
 */
public class RunStandalone {
	
	
	public static void main(String[] args){
		runServer();		
	}
	
	/**
	 * Use Tyrus (org.galssfish.tyrus.server) in standalone mode, to deploy our (server-side) Endpoint
	 */
	public static void runServer() {
		 /** 
		  * Breakdown of the pieces of our URL where the endpoint will be accesible:
		  * (scheme ws:// because it's a websocket)
		  * ws://localhost:port/path/endpoint
		  * so we will be able to access this example on the uri ws://localhost:8080/TyrusExample/echo
		  */
		org.glassfish.tyrus.server.Server server = new Server("localhost", 8080, "/TyrusExample", null, EchoEndpointAnnotated.class);

	    try {
	        server.start();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	        System.out.print("Press ENTER key to stop the server.");
	        reader.readLine();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        server.stop();
	    }
	}

}
