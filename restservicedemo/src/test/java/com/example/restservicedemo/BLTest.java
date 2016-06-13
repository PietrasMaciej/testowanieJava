package com.example.restservicedemo;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.example.restservicedemo.domain.Bike;
import com.example.restservicedemo.domain.Person;
import com.example.restservicedemo.service.BikeManager;
import com.example.restservicedemo.service.PersonManager;

public class BLTest {
	
	static PersonManager pm = new PersonManager();
	static BikeManager bm = new BikeManager();
	
	Person p1, p2, p3, p4, p5;
	Bike b1, b2;
	
	@Before
	public void setUp() {
		bm.clearBikes();
		pm.clearPersons();	
	}

	@Test
	public void checkPersonAdding() {
		p1 = new Person(5, "Korneliusz", 1980);
		assertEquals(1, pm.addPerson(p1));	
	}
	
	@Test
	public void checkGetPerson(){
		p2 = new Person(6, "Alojzy", 1990);
		pm.addPerson(p2);
		
		Person first = pm.getPerson(p2.getId());
		assertEquals(6, first.getId());
		assertEquals("Alojzy", first.getFirstName());
	}
	
	@Test
	public void checkDeletePersonWithId(){
		p3 = new Person(0, "Marcel", 2008);
		assertEquals(1, pm.addPerson(p3));	
		
		pm.clearPerson(0L);
		assertTrue(pm.getAllPersons().size() == 0);	
	}
	
	@Test
	public void checkIfOwned() {
		
		Person p4 = new Person();
		p4.setId(7L);
		p4.setFirstName("Grażyna");
		p4.setYob(1978);

		Person p5 = new Person();
		p5.setId(8L);
		p5.setFirstName("Błażej");
		p5.setYob(1978);

		assertEquals(1, pm.addPerson(p4));
		assertEquals(1, pm.addPerson(p5));

		List<Person> persons = pm.getAllPersons();

		assertTrue(persons.size() > 1);

		b1 = new Bike();
		b1.setId(1L);
		b1.setMake("BMX");
		b1.setModel("Junior");
		b1.setYop(1989);
		b1.setOwner(p4);

		b2 = new Bike();
		b2.setId(2L);
		b2.setMake("BMX");
		b2.setModel("Senior");
		b2.setYop(1999);
		b2.setOwner(p5);

		assertEquals(1, bm.addBike(b1));
		assertEquals(1, bm.addBike(b2));

		List<Bike> bikes = bm.getAllBikes();

		assertTrue(bikes.size() > 0);

		Person owner = persons.get(1);
		assertEquals(8, owner.getId());
		
		Bike ownedBike = bikes.get(1);
		assertEquals(2, ownedBike.getId());
	
		assertEquals(p4.getFirstName(), b1.getOwner().getFirstName());
		assertEquals(p5.getFirstName(), b2.getOwner().getFirstName());
	}
	
	@AfterClass
	public static void tearDown() {
		bm.clearBikes();
		pm.clearPersons();	
	}

}
