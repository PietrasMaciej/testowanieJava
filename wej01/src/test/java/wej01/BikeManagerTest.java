package wej01;

import static org.junit.Assert.*;

import org.junit.Test;

public class BikeManagerTest {
	
	Bike b = new Bike("Romet", "Fajny", 12);
	Bike b1 = new Bike("Romet", "Fajny", 14);
	
	BikeManager bm = new BikeManager();
	
	
	@Test
	public void checkAdding() {
		
		bm.add(b);
		
		assertEquals(b.getModel(), bm.getAllBikes().get(0).getModel());
		assertEquals("Fajny", bm.getAllBikes().get(0).getModel());
		assertEquals(b, bm.getAllBikes().get(0));
		assertEquals(1, bm.getAllBikes().size());
		
		assertFalse(bm.getAllBikes().isEmpty());

	}
	
	@Test
	public void checkGetAllBikes() {
		bm.add(b1);
		assertEquals(1, bm.getAllBikes().size());
		
	}
}
