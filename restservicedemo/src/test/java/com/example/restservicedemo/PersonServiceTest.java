package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNotNull;

import javax.ws.rs.core.MediaType;

import org.junit.BeforeClass;
import org.junit.Test;

import com.example.restservicedemo.domain.Person;
import com.jayway.restassured.RestAssured;

public class PersonServiceTest {
	
	private static final String PERSON_FIRST_NAME = "Jasiu";
	
	@BeforeClass
    public static void setUp(){
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
		RestAssured.basePath = "/restservicedemo/api";   	
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
	
	@Test
	public void clearPersons() {
		delete("/person/").then().assertThat().statusCode(200);
		given().
	       contentType(MediaType.APPLICATION_JSON).
	       body(Person.class).
	    when().get("/person/0").
	    then().assertThat().body("firstName", equalTo(null));
	}
	
	@Test
	public void getAllPersons() {	
		String persons = get("/person/all").asString();
		assertNotNull(persons);
	}
	

}
