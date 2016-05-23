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
import com.jayway.restassured.RestAssured;

public class BikeServiceTest {

	public static final String BIKE_MAKE = "Romet";
	public static final String BIKE_MODEL = "Zefir";

	@BeforeClass
	public static void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo/api";
	}

	@Test
	public void addBikes() {
		delete("/bike/").then().assertThat().statusCode(200);

		Bike bike = new Bike(1L, BIKE_MAKE, BIKE_MODEL, 1994);

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
