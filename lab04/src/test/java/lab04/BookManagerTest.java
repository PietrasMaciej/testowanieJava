package lab04;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.easymock.EasyMockRule;
import  static org.easymock.EasyMock.*;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


public class BookManagerTest {
	
	@Rule
	public EasyMockRule mocks = new EasyMockRule(this);
	
	@Mock
	private IMyList mock;

	@TestSubject
	private BikeManager myApp = new BikeManager(mock);
	
	@Test
	public void checkAdd() {
		Bike b = new Bike("Romet", "Szybki", 26);
		mock.add(b);
		expectLastCall();
		expect(mock.size()).andReturn(1);
		expect(mock.getAllBikes()).andReturn(mock);
		
		replay(mock);
		myApp.add(b);
		
		assertEquals(1, myApp.getAllBikes().size());
		
		verify(mock);
	}
	
	//SUT
//	private BikeManager bm;
//	private IBikeManager mock;
	
	
}
