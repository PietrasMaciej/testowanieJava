package com.example.shdemo.service;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import junit.framework.TestCase;

import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.shdemo.domain.Person;

@Ignore
public class PureDBUnitTest {
	
	@Autowired
	SellingManager sellingManager;
	
	private SellingMangerHibernateImpl smi = new SellingMangerHibernateImpl();
	SellingManager sm;

	static IDatabaseConnection connection ;
	private static IDatabaseTester databaseTester;
	
	@BeforeClass
	public static void setUp() throws Exception {
		Connection jdbcConnection;
		jdbcConnection = DriverManager.getConnection(
				"jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
		connection = new DatabaseConnection(jdbcConnection);
		
		databaseTester = new JdbcDatabaseTester(
				"org.hsqldb.jdbcDriver", "jdbc:hsqldb:hsql://localhost/workdb", "sa", "");
		IDataSet dataSet = new FlatXmlDataSetBuilder().build(
				new FileInputStream(new File("src/test/resources/fullData.xml")));
		databaseTester.setDataSet(dataSet);
		databaseTester.onSetup();
		
		//DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
	}
	
	@Test
	public void addPersonTest() throws Exception {
		Person person = new Person();
		person.setId(5L);
		person.setFirstName("Zbyszek");
		person.setPin("0000");
		person.setRegistrationDate(new Date());
		
		sellingManager.addClient(person);
		
		System.out.println(sm.findClientByPin("0000").getFirstName() + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		IDataSet dbDataSet = connection.createDataSet();
		ITable actualTable = dbDataSet.getTable("PERSON");
//		ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
//				(actualTable, new String[]{"REGISTRATIONDATE"});
		
		IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(
				new File("src/test/resources/personData.xml"));
		ITable expectedTable = expectedDataSet.getTable("PERSON");
		
		Assertion.assertEquals(expectedTable, actualTable);
		
	}
	
	@AfterClass
	public static void tearDown() throws Exception{
		databaseTester.onTearDown();
	}
	
	
}
