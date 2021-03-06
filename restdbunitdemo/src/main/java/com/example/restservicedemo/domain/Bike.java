package com.example.restservicedemo.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Bike {
	
	private long id;
	private String model;	
	private int yop;
	
	private Person owner;
	
	public Bike(long id, String model, int yop) {
		super();
		this.id = id;
		this.model = model;
		this.yop = yop;
	}
	
	
	public Bike() {
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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


	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}
	
}
