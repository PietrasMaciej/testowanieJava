package com.example.restservicedemo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.example.restservicedemo.service.BikeManager;
import com.example.restservicedemo.service.PersonManager;

public class SomeSiteTest {

	private static PersonManager pm = new PersonManager();
    private static BikeManager bm = new BikeManager();
	
	private static WebDriver driver;
	WebElement element;

	@BeforeClass
	public static void driverSetup() {
		//System.setProperty("webdriver.chrome.driver", "./scripts/chromedriver.exe");
		//System.setProperty("webdriver.chrome.driver", "/home/PJWSTK/s11890/Pobrane/chromedriver");
		System.setProperty("webdriver.chrome.driver", "/Users/kuba/Dev/chromedriver/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		bm.dropBikeTable();
		pm.dropPersonTable();
		pm.createPersonTable();
		bm.createBikeTable();
	}

	@Test
	public void homePage(){
		driver.get("http://localhost:8080/restservicedemo/");
		
		assertEquals("Simple Service", driver.getTitle());
		
		assertEquals("Person form:", driver.findElement(By.cssSelector("div.page-header > h1")).getText());
	}
	
	@Test
	public void addPerson(){
		driver.findElement(By.id("idPerson")).clear();
	    driver.findElement(By.id("idPerson")).sendKeys("5");
	    driver.findElement(By.id("name")).clear();
	    driver.findElement(By.id("name")).sendKeys("Marian");
	    driver.findElement(By.id("yob")).clear();
	    driver.findElement(By.id("yob")).sendKeys("1997");
	    driver.findElement(By.id("btnSave")).click();
	    assertEquals("5", driver.findElement(By.cssSelector("td")).getText());

		
	}

	@AfterClass
	public static void cleanp() {
		driver.quit();
		bm.dropBikeTable();
		pm.dropPersonTable();
		pm.createPersonTable();
		bm.createBikeTable();
	}
	
}
