package wej01;

import java.util.ArrayList;
import java.util.List;

public class BikeManager {
	List<Bike> bikes = new ArrayList<Bike>();

	public void add(Bike b)
	{
		 bikes.add(b);
	}
	
	public List<Bike> getAllBikes()
	{
		return bikes;
		 
	}
}
