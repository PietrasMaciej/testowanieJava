package com.example.restservicedemo;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.get;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.ws.rs.core.MediaType;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.example.restservicedemo.domain.Bike;
import com.example.restservicedemo.domain.Person;
import com.example.restservicedemo.service.BikeManager;
import com.example.restservicedemo.service.PersonManager;
import com.jayway.restassured.RestAssured;

public class BikeServiceRESTDBTest {
	private static IDatabaseConnection connection ;
    private static IDatabaseTester databaseTester;

    private static PersonManager pm = new PersonManager();
    private static BikeManager bm = new BikeManager();

    @BeforeClass
    public static void setUp() throws Exception{
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/restservicedemo/api";

        Connection jdbcConnection;
        jdbcConnection = DriverManager.getConnection(
                "jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
        connection = new DatabaseConnection(jdbcConnection);
        //DatabaseConfig config = connection.getConfig();
        //config.setProperty(DatabaseConfig.FEATURE_DATATYPE_WARNING, false);
		//config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqldbDataTypeFactory());
        

    }

    @Before
	public void set() throws Exception {
    	bm.dropBikeTable();
		pm.dropPersonTable();
		pm.createPersonTable();
		bm.createBikeTable();
	 	databaseTester = new JdbcDatabaseTester(
                "org.hsqldb.jdbcDriver", "jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(
                new FileInputStream(new File("src/test/resources/fullDataWithBike.xml")));
        databaseTester.setDataSet(dataSet);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.onSetup();
       
		
	}
    
    @Test
    public void addBike() throws Exception {
    	
        Person person = new Person(1, "Geralt", 1208);
        Bike bike = new Bike(3, "Kross", "A8", 2008, person);
        
        
        given().contentType(MediaType.APPLICATION_JSON).body(bike).
        when().post("/bike/").
        then().assertThat().statusCode(201);

        IDataSet dbDataSet = connection.createDataSet();
        ITable actualTable = dbDataSet.getTable("BIKE");

        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
                new File("src/test/resources/bikeData.xml"));
        ITable expectedTable = expectedDataSet.getTable("BIKE");

        Assertion.assertEquals(expectedTable, actualTable);
    }
    
    @Test
	public void deleteBike() throws Exception {
    	
		delete("/bike/clear/2").then().assertThat().statusCode(200);
		
		IDataSet dbDataSet = connection.createDataSet();
		ITable actualTable = dbDataSet.getTable("BIKE");
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
				new File("src/test/resources/deletedBikeData.xml"));
		ITable expectedTable = expectedDataSet.getTable("BIKE");
		
		Assertion.assertEquals(expectedTable, actualTable);
	}
    
    @Ignore
    @Test
	public void sellBike() throws Exception {
		Person p = new Person(6L, "Marcel", 2004); 
		Bike b = new Bike(8L, "Giant", "Super", 2007, p);
		given().contentType(MediaType.APPLICATION_JSON)
			.body(p).when().post("/person/").then().assertThat().statusCode(201);
		
		given().contentType(MediaType.APPLICATION_JSON)
		.body(b).when().post("/bike/").then().assertThat().statusCode(201);
		
		Person person = get("/person/6").as(Person.class);
		Bike bike = get("/bike/8").as(Bike.class);
		
		given().contentType(MediaType.APPLICATION_JSON)
			.when().post("/bike/sell/" + bike.getId() + "/" + person.getId()).then().assertThat().statusCode(201);
		
		IDataSet dbDataSet = connection.createDataSet();
		ITable actualTable = dbDataSet.getTable("BIKE");
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
				new File("src/test/resources/sellBikeData.xml"));
		ITable expectedTable = expectedDataSet.getTable("BIKE");
		Assertion.assertEquals(expectedTable, actualTable);
	}
    
    @AfterClass
    public static void tearDown() throws Exception {
    	bm.dropBikeTable();
		pm.dropPersonTable();
		pm.createPersonTable();
		bm.createBikeTable();
        databaseTester.onTearDown();
    }
}
