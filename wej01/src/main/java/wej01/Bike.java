package wej01;

import java.util.ArrayList;
import java.util.List;

public class Bike {
	private String mark;
	private String model;
	private int size;
	

	public Bike(String mark, String model, int size) {
		this.mark = mark;
		this.model = model;
		this.size = size;
	}
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
	


}
