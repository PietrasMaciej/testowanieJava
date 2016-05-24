package com.example.restservicedemo.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Bike {
	
	private long id;
	private String make;
	private String model;	
	private int yop;
	private long personId;
	
	public Bike(long id, String make, String model, int yop) {
		super();
		this.id = id;
		this.make = make;
		this.model = model;
		this.yop = yop;
	}
	
	public Bike(long id, String make, String model, int yop, long personId) { 
		super();
		this.id = id;
		this.make = make;
		this.model = model;
		this.yop = yop;
		this.personId = personId;
	}
	
	public Bike() {
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getYop() {
		return yop;
	}
	public void setYop(int yop) {
		this.yop = yop;
	}
	public long getPersonId() {
		return personId;
	}
	public void setPersonId(long personId) {
		this.personId = personId;
	}
	
	
}
