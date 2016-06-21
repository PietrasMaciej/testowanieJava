package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertNotNull;

import javax.ws.rs.core.MediaType;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.example.restservicedemo.domain.Person;
import com.example.restservicedemo.service.BikeManager;
import com.example.restservicedemo.service.PersonManager;
import com.jayway.restassured.RestAssured;

public class APersonServiceTest {
	
	static PersonManager pm = new PersonManager();
	static BikeManager bm = new BikeManager();
	
	private static final String PERSON_FIRST_NAME = "Jasiu";
	
	@BeforeClass
    public static void setUp(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo/api";   	
		bm.clearBikes();
		pm.clearPersons();
    }
	
	@Test
	public void getPerson() {
		Person person = new Person(1L, PERSON_FIRST_NAME, 1976);
		given().
	       contentType(MediaType.APPLICATION_JSON).
	       body(person).
	    when().	     
	    post("/person/").then().assertThat().statusCode(201);
		get("/person/1").then().assertThat().body("firstName", equalTo(PERSON_FIRST_NAME));

		Person example = get("/person/1").as(Person.class);
		assertThat(example.getFirstName(), equalToIgnoringCase(PERSON_FIRST_NAME));
	}
	
	@Test
	public void addPersons(){		
		
		delete("/person/").then().assertThat().statusCode(200);
		
		Person person = new Person(1L, PERSON_FIRST_NAME, 1976);
		
		given().
	       contentType(MediaType.APPLICATION_JSON).
	       body(person).
	    when().	     
	    post("/person/").then().assertThat().statusCode(201);
				
		Person rPerson = get("/person/1").as(Person.class);
		
		assertThat(PERSON_FIRST_NAME, equalToIgnoringCase(rPerson.getFirstName()));
		
	}
	
//	@Test
//	public void clearPersons() {
//		delete("/person/").then().assertThat().statusCode(200);
//		given().
//	       contentType(MediaType.APPLICATION_JSON).
//	       body(Person.class).
//	    when().get("/person/0").
//	    then().assertThat().body("firstName", equalTo(null));
//	}
	
	@Test
	public void getPersons(){
		delete("/bike/").then().assertThat().statusCode(200);
		delete("/person/").then().assertThat().statusCode(200);
		Person p1 = new Person(4L,"Mariusz", 1973);
		Person p2 = new Person(5L,"Ryszard", 2004);
		given()
			.contentType(MediaType.APPLICATION_JSON)
			.body(p1)
		.when()
			.post("/person/").then().assertThat().statusCode(201);
		
		given()
			.contentType(MediaType.APPLICATION_JSON)
			.body(p2)
		.when()
			.post("/person/").then().assertThat().statusCode(201);

		given()
		.when()
			.get("/person/all")
		.then()
			.body("person[0].id", equalTo("4"))
			.body("person[0].firstName", equalTo("Mariusz"))
			.body("person[0].yob", equalTo("1973"))
			.body("person[1].id", equalTo("5"))
			.body("person[1].firstName",equalTo("Ryszard"))
			.body("person[1].yob", equalTo("2004"))
			.body("person.id", hasItems("4","5"));
	}
	
	@Test
	public void getAllPersons() {	
		String persons = get("/person/all").asString();
		assertNotNull(persons);
	}
	
	@AfterClass
    public static void tearDown(){
		bm.clearBikes();
		pm.clearPersons();
    }
	

}
