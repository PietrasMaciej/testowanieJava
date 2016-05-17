package com.example.jbehavedemo.messenger;

public interface MessageService {
		
	ConnectionStatus checkConnection(String server);
	
	SendingStatus send(String server, String message) throws MalformedRecipientException;

}
