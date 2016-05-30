package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.junit.Assert.assertNotNull;

import javax.ws.rs.core.MediaType;

import org.junit.BeforeClass;
import org.junit.Test;

import com.example.restservicedemo.domain.Bike;
import com.example.restservicedemo.domain.Person;
import com.jayway.restassured.RestAssured;

public class BikeServiceTest {

	public static final String BIKE_MAKE = "Romet";
	public static final String BIKE_MODEL = "Zefir";
	
	private static Person person;
	private static final String FIRST_NAME = "Jan";

	@BeforeClass
	public static void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo/api";
		
		person = new Person(2L, FIRST_NAME, 1895);
		
		given().
	       contentType("application/json").
	       body(person).
		when().	     
			post("/person/").
		then().
			assertThat().statusCode(201);
	}

	@Test
	public void addBikes() {
		delete("/bike/").then().assertThat().statusCode(200);

		Bike bike = new Bike(1L, BIKE_MAKE, BIKE_MODEL, 1994, person);

		given().contentType(MediaType.APPLICATION_JSON).body(bike).when().post("/bike/").then().assertThat()
				.statusCode(201);

		Bike rBike = get("/bike/1").as(Bike.class);

		assertThat(BIKE_MAKE, equalToIgnoringCase(rBike.getMake()));
		assertThat(BIKE_MODEL, equalToIgnoringCase(rBike.getModel()));

	}

	@Test
	public void getAllBikes() {
		String bikes = get("/bike/all/").asString();

		assertNotNull(bikes);
	}

}
