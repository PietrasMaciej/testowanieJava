package com.example.mockdemo.app;

import org.junit.Before;
import org.junit.Test;

import com.example.mockdemo.messenger.ConnectionStatus;
import com.example.mockdemo.messenger.MalformedRecipientException;
import com.example.mockdemo.messenger.MessageService;
import com.example.mockdemo.messenger.SendingStatus;

import static org.mockito.Mockito.*;

public class MockitoAppTest {
	
	private static final String VALID_SERVER = "inf.ug.edu.pl";
	private static final String INVALID_SERVER = "inf.ug.edu.eu";

	private static final String VALID_MESSAGE = "some message";
	private static final String INVALID_MESSAGE = "ab";

	private Messenger messenger;
	private MessageService msMock;

	@Before
	public void setUP() throws MalformedRecipientException {
		msMock = mock(MessageService.class);
		messenger = new Messenger(msMock);
	}

	@Test
	public void sendingValidRecipientAndServer()
			throws MalformedRecipientException {

		when(msMock.send(VALID_SERVER, VALID_MESSAGE)).add(
				SendingStatus.SENT).atLeastOnce();
		when(msMock.checkConnection(VALID_SERVER)).add(
				ConnectionStatus.SUCCESS);

		assertThat(messenger.testConnection(VALID_SERVER), equalTo(0));
		assertThat(messenger.sendMessage(VALID_SERVER, VALID_MESSAGE),
				either(equalTo(0)).or(equalTo(1)));

		verify(msMock);
	}

	@Test
	public void sendingInvalidServer() throws MalformedRecipientException {

		expect(msMock.checkConnection(INVALID_SERVER)).andReturn(
				ConnectionStatus.FAILURE);
		expect(msMock.send(INVALID_SERVER, VALID_MESSAGE)).andReturn(
				SendingStatus.SENDING_ERROR);
		replay(msMock);

		assertThat(messenger.testConnection(INVALID_SERVER), equalTo(1));
		assertEquals(1, messenger.sendMessage(INVALID_SERVER, VALID_MESSAGE));

		verify(msMock);
	}

	@Test
	public void sendingInvalidReceipient() throws MalformedRecipientException {

		expect(msMock.send(VALID_SERVER, INVALID_MESSAGE)).andThrow(
				new MalformedRecipientException()).atLeastOnce();

		replay(msMock);

		assertEquals(2, messenger.sendMessage(VALID_SERVER, INVALID_MESSAGE));
		verify(msMock);
	}

	// Przechwytywanie parametrow
	@Test
	public void sendingConnectionStatus() {

		Capture<String> capturedServer = EasyMock.newCapture();

		expect(msMock.checkConnection(capture(capturedServer))).andReturn(
				ConnectionStatus.FAILURE);
		replay(msMock);

		assertEquals(1, messenger.testConnection(INVALID_SERVER));
		assertEquals(INVALID_SERVER, capturedServer.getValue());

		verify(msMock);
	}
	
}
