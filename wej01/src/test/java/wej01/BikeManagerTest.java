package wej01;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

public class BikeManagerTest {
	BikeManager bike = new BikeManager("Romet");
	List<BikeManager> bike = bike.getAllBikes();
	
	bike.add(bike);
	
	@Test
	public void checkAdding() {
		assertEquals(bike.getMark(), bike.setMark("Romet"));

	}
}
