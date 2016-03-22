package wej01;

import java.util.ArrayList;
import java.util.List;

public class BikeManager implements IBikeManager {
	List<Bike> bikes = new ArrayList<Bike>();

	@Override
	public void add(Bike b)
	{
		 bikes.add(b);
	}
	
	@Override
	public List<Bike> getAllBikes()
	{
		return bikes;
		 
	}
}
