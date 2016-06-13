package com.example.restservicedemo;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.example.restservicedemo.domain.Bike;
import com.example.restservicedemo.domain.Person;
import com.example.restservicedemo.service.PersonManager;

public class BLTest {

	PersonManager pm = new PersonManager();

	@Test
	@Ignore
	public void checkCarAdding() {

		Bike c = new Bike();
		c.setModel("Syrena");
		c.setYop(1973);

		assertEquals(1, pm.addCar(c));
	}

	@Test
	@Ignore
	public void checkSell() {

		Bike c1 = new Bike();
		c1.setModel("Syrena");
		c1.setYop(1973);

		Bike c2 = new Bike();
		c2.setModel("Fiat Punto");
		c2.setYop(1999);

		assertEquals(1, pm.addCar(c1));
		assertEquals(1, pm.addCar(c2));

		List<Bike> cars = pm.getAllCars();

		assertTrue(cars.size() > 0);

		Bike carToSell = cars.get(1);

		Person p1 = new Person();
		p1.setFirstName("Zieliński");
		p1.setYob(1978);

		Person p2 = new Person();
		p2.setFirstName("Kowalski");
		p2.setYob(1978);

		assertEquals(1, pm.addPerson(p1));
		assertEquals(1, pm.addPerson(p2));

		List<Person> persons = pm.getAllPersons();

		assertTrue(persons.size() > 1);

		Person owner = persons.get(1);
		
		
		pm.sellCar(carToSell, owner);
		
		Bike rCar = pm.getCarWithOwner(carToSell);
		
		assertEquals(owner.getFirstName(), rCar.getOwner().getFirstName());

	}

	@Test
	@Ignore
	public void checkGetAll() {

	}

}
