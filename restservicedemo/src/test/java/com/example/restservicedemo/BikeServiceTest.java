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

import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
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
	public void clearBikes() {
		delete("/bike").then().assertThat().statusCode(200);
		given()
	       	.contentType(MediaType.APPLICATION_JSON)
	       	.body(Bike.class)
	    .when()
	    .then()
	    	.body("", Matchers.hasSize(0));
		
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
	public void getBikeWithOwner(){
		//delete("/bike/").then().assertThat().statusCode(200);
		//delete("/person/").then().assertThat().statusCode(200);
		Person person1 = new Person(3L, "Janusz", 1971);
		Bike bike1 = new Bike(2L,"Romet", "Blyskawica", 1983, person1);
		given()
			.contentType(MediaType.APPLICATION_JSON)
			.body(person1)
		.when()
			.post("/person/").then().assertThat().statusCode(201);
		given()
			.contentType(MediaType.APPLICATION_JSON)
			.body(bike1)
		.when()
			.post("/bike").then().assertThat().statusCode(201);
		
		given()
		.when()
			.get("/bike/2")
		.then()
			.body("id", equalTo("2"))
			.body("make", equalTo("Romet"))
			.body("model", equalTo("Blyskawica"))
			.body("owner.id", equalTo("3"))
			.body("owner.firstName", equalTo("Janusz"))
			.body("owner.yob", equalTo("1971"))
			.body("yop", equalTo("1983"));
		
	}

	@Test
	public void getAllBikes() {
		String bikes = get("/bike/all/").asString();

		assertNotNull(bikes);
	}
	
	@AfterClass
	public static void setDown() {
		//delete("/person/clear/2").then().assertThat().statusCode(200);
		//delete("/bike/").then().assertThat().statusCode(200);
		//delete("/person/").then().assertThat().statusCode(200);
	}

}
