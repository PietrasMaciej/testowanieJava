package com.example.jbehavedemo;

import static org.hamcrest.CoreMatchers.either;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.example.jbehavedemo.app.Messenger;
import com.example.jbehavedemo.messenger.MessageServiceSimpleImpl;

public class MessengerSteps {
	
	private Messenger messenger;
	
	private static final String VALID_SERVER = "inf.ug.edu.pl";
	private static final String INVALID_SERVER = "inf.ug.edu.eu";
	private static final String VALID_MESSAGE = "some message";
	private static final String INVALID_MESSAGE = "ab";

	
	private static  String validSrv;
	private static  String validMsg;
	private static  String invalidSrv;
	private static  String invalidMsg;

	@Given("a messenger")
	public void messengerSetup(){
		messenger = new Messenger(new MessageServiceSimpleImpl());
	}
	
	@When("set server to $INVALID_SERVER and message to $VALID_MESSAGE")
	public void setArgumentsSendingError(String INVALID_SERVER, String VALID_MESSAGE){
		invalidSrv = INVALID_SERVER;
		validMsg = VALID_MESSAGE;
	}
	
	@Then("sending message should return $SENDING_ERROR")
	public void shouldSentSendingError(int SENDING_ERROR){
		assertEquals(SENDING_ERROR, messenger.sendMessage(invalidSrv, validMsg));
	}
	
	
	@When("server is set to $VALID_SERVER and message to $INVALID_MESSAGE")
	public void setArgumentsSentException(String VALID_SERVER, String INVALID_MESSAGE){
		validSrv = VALID_SERVER;
		invalidMsg = INVALID_MESSAGE;
	}
	
	@Then("sendingMessage method should return exception with code status $EXCEPTION")
	public void shouldSentException(int EXCEPTION){
		assertEquals(2, messenger.sendMessage(VALID_SERVER, INVALID_MESSAGE));
	}

	
	@When("server is set to $VALID_SERVER and message is set to $VALID_MESSAGE")
	public void setArguments(String VALID_SERVER2, String VALID_MESSAGE2){
		validSrv = VALID_SERVER;
		validMsg = VALID_MESSAGE;
	}
	
	@Then("sending message method should return $SENT or $SENDING_ERROR")
	public void shouldSent(int SENT, int SENDING_ERROR){
	  assertThat(messenger.sendMessage(validSrv, validMsg),
				either(equalTo(SENT)).or(equalTo(SENDING_ERROR)));
	}
	
	
	@When("server's set to $VALID_SERVER")
	public void setServer(String VALID_SERVER){
		validSrv = VALID_SERVER;
	}
	
	@Then("test connection should return $SUCCESS")
	public void shouldTest(int SUCCESS){
		assertEquals(SUCCESS, messenger.testConnection(VALID_SERVER));
	}
	
	
//	@When("server is $INVALID_SERVER2")
//	public void setInvalidServer(String INVALID_SERVER2){
//		invalidSrv2 = INVALID_SERVER2;
//	}
//	
//	@Then("test connection method should return $FAILURE")
//	public void shouldTestInvalidConnection(int FAILURE){
//		assertEquals(FAILURE, messenger.testConnection(INVALID_SERVER));
//	}
	
	
//	@When("set arguments to $a and $b")
//	public void setArguments(int a, int b){
//		calc.setLeftOp(a);
//		calc.setRightOp(b);
//	}
//	
//    @Then("adding should return $result")
//	public void shouldAdd(int result){
//		assertEquals(result, calc.add());
//	}
//    
//    @Then("subtracting should return $result")
//  	public void shouldSubstract(int result){
//  		assertEquals(result, calc.subtract());
//  	}
}
