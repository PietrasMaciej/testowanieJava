package com.example.restservicedemo;

import com.example.restservicedemo.domain.Person;
import com.example.restservicedemo.service.BikeManager;
import com.example.restservicedemo.service.PersonManager;
import com.jayway.restassured.RestAssured;
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

import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.delete;

public class PersonServiceRESTDBTest {
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
			//config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqldbDataTypeFactory());
	        bm.dropBikeTable();
  
	    }

	    @Before
		public void set() throws Exception {	    		
			pm.dropPersonTable();
			pm.createPersonTable();
		 	databaseTester = new JdbcDatabaseTester(
	                "org.hsqldb.jdbcDriver", "jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
		 	
	        IDataSet dataSet = new FlatXmlDataSetBuilder().build(
	                new FileInputStream(new File("src/test/resources/fullData.xml")));
	        databaseTester.setDataSet(dataSet);
	        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
	        databaseTester.onSetup();
	       
			
		}
	    
	    @Test
	    public void addPerson() throws Exception {
	        Person addingNewPerson = new Person(4, "Boromir", 1678);
	        given().contentType(MediaType.APPLICATION_JSON).body(addingNewPerson)
	                .when().post("/person/").then().assertThat().statusCode(201);

	        IDataSet dbDataSet = connection.createDataSet();
	        ITable actualTable = dbDataSet.getTable("PERSON");
	        ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
	                (actualTable, new String[]{"P_ID"});

	        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
	                new File("src/test/resources/personData.xml"));
	        ITable expectedTable = expectedDataSet.getTable("PERSON");

	        Assertion.assertEquals(expectedTable, filteredTable);
	    }
	    
	    @Test
		public void deletePerson() throws Exception {
			delete("/person/clear/0").then().assertThat().statusCode(200);
			
			IDataSet dbDataSet = connection.createDataSet();
			ITable actualTable = dbDataSet.getTable("PERSON");
			ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
		                (actualTable, new String[]{"P_ID"});
			
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
					new File("src/test/resources/deletedPersonData.xml"));
			ITable expectedTable = expectedDataSet.getTable("PERSON");
			
			Assertion.assertEquals(expectedTable, filteredTable);
		}

	    @AfterClass
	    public static void tearDown() throws Exception {

			//pm.dropPersonTable();
			
	        databaseTester.onTearDown();
	    }
}
