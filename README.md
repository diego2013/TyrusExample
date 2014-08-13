TyrusExample
============

Demo project to play around Tyrus, the open source reference implementation for the Java API for websockets

Medical Device 'Plug and Play' Program.
www.mdpnp.org

August 13, 2014

The purpose of this project is to provide a simple example on the usage of websockets.
This program is distributed for illustrative purposes only, and with no warranties of any kind.


DEPENDENCY MANAGEMENT
---------------------
Use the "eclipse" gradle task to build eclipse project files

HOW TO USE THIS TUTORIAL
------------------------

This project provides several entities to help you familiarize with websockets and their
four associated events.

A pom.xml and build.gradle files are provided to manage dependencies. SLF4J has been added
as default logging mechanism. Lack of logging would not affect the purpose or behavior
of this demo project.

Tyrus (https://tyrus.java.net/) is the open source reference implementation for the Java API
for websockets.

The class org.mdpnp.tyrusexample.EchoEndpointAnnotated provides an example of a server-side 
endpoint created using annotations. This endpoit echoes back all messages received. To run
this endpoint you just need to deploy it within a War on GlassFish, or alternatively use 
the RunStandalone example class provided to run this endpoint (or any other of your creation) as a 
Tyrus standalone server (org.glassfish.tyrus.server.Server).

The package org.mdpnp.tyrusexample.client provides examples of client-side endpoint (both using
annotations or by programmatically coding your endpoint).

The RunClient class codes a simple example of coding a JSON request to be sent to a websocket endpoint.
Two different URLs are provided, to be able to run a simple "print Hello World" request against
the "echo" demo endpoint and a JSON request to subscribe to a DDS topic that an endpoint running
at the MD PnP's lab at Cambridge is providing to demonstrate medical device interoperability.

A mock webpage tyrusdemo.html is provided under src/main/resources to illustrate the usage of 
endpoint on Javascript. With minor modifications this page can be used with the two provided 
different endpoint (echo and the real MD PnP's DDS subscription via websocket example).

To see an example of what you could built using the data obtained by subscribing to the topics
being published via DDS by the medical devices running at the MD pNP's lab at Cambridge visit
http://openice.info


TO LEARN MORE
-------------

To learn more about medical device interoperability and the work being done at the MD PnP program
contact the program at info@mdpnp.org   or visit http://www.mdpnp.org/

To learn more about this demo project mail diego@mdpnp.org
