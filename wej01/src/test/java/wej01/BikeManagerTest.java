package wej01;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

public class BikeManagerTest {
	
	Bike b = new Bike("Romet", "lghjgh", 12);
	
	BikeManager bm = new BikeManager();
	
	
	@Test
	public void checkAdding() {
		bm.add(b);
		assertEquals(b.getModel(), bm.getAllBikes().get(0).getModel());

	}
}
