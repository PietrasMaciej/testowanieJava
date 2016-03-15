package wej01;

import java.util.ArrayList;
import java.util.List;

public class BikeManager {
	private String mark;
	private String model;
	private int size;
	
	
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	public void add(BikeManager bm)
	{
		 bm.setMark(mark);
	}
	
	public List<BikeManager> getAllBikes()
	{
		List bikes = new ArrayList();
		BikeManager b = new BikeManager();
		b.setMark("Romet");
		 for(int i = 0; i < bikes.size(); i++) {
			 
	            bikes.add(b);
	        }
		return bikes;
		 
	}

}
