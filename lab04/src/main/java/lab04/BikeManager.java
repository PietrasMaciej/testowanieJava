package lab04;

import java.util.List;


public class BikeManager {
	
	private IMyList container;
	
	public BikeManager(IMyList container) {
		this.container = container;
	}
	
	public void add(Bike b)
	{
		 container.add(b);
	}
	
	
	public IMyList getAllBikes()
	{
		return container.getAllBikes();
		 
	}

}
