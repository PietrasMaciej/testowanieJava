package com.example.shdemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
		@NamedQuery(name = "car.unsold", query = "Select c from Car c where c.sold = false")
})
public class Car {

	private Long id;
	private String make;
	private String model;
	private Boolean sold = false;
	
	
	private Person person;

	
	public Car(Long id, String make, String model, Boolean sold, Person person) {
		super();
		this.id = id;
		this.make = make;
		this.model = model;
		this.sold = sold;
		this.person = person;
	}

	public Car() {
		super();
	}

	@ManyToOne
	@JoinColumn(name="o_id")
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Boolean getSold() {
		return sold;
	}

	public void setSold(Boolean sold) {
		this.sold = sold;
	}
}
