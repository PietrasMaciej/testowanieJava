package com.example.restservicedemo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.example.restservicedemo.domain.Bike;
import com.example.restservicedemo.domain.Person;
import com.example.restservicedemo.service.BikeManager;
import com.example.restservicedemo.service.PersonManager;

public class ABLTest {
	//static PersonManager pm = new PersonManager();
	PersonManager pm = new PersonManager();
	BikeManager bm = new BikeManager();
	Person p1, p2;
	
	@Before
	public void setUp() {
		bm.clearBikes();
		pm.clearPersons();
		p1 = new Person(5, "Korneliusz", 1980);
		p2 = new Person(6, "Alojzy", 1990);
	}

	@Test
	public void checkPersonAdding() {

		assertEquals(1, pm.addPerson(p1));
		
	}
	
	@Test
	public void checkGetPerson(){

		pm.addPerson(p2);
		
		Person first = pm.getPerson(p2.getId());
		assertEquals(6, first.getId());
		assertEquals("Alojzy", first.getFirstName());
	}

}
