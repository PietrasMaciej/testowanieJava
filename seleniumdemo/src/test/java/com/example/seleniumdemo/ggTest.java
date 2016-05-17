package com.example.seleniumdemo;

import static org.junit.Assert.*;

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


public class ggTest {

	private static WebDriver driver;
	WebElement element;

	@BeforeClass
	public static void driverSetup() {
		// ChromeDrirver, FireforxDriver, ...
		//System.setProperty("webdriver.chrome.driver", "/home/PJWSTK/s11890/Pobrane/chromedriver");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Make_\\Desktop\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void homePage(){
		  driver.get("https://www.google.pl/?gws_rd=ssl");
		    driver.findElement(By.id("lst-ib")).clear();
		    driver.findElement(By.id("lst-ib")).sendKeys("gmail");
		    driver.findElement(By.name("btnG")).click();
		    driver.findElement(By.linkText("Gmail - Google")).click();
		    driver.findElement(By.id("gmail-sign-in")).click();
		    driver.findElement(By.id("Email")).clear();
		    driver.findElement(By.id("Email")).sendKeys("lol");
		    driver.findElement(By.id("next")).click();
		    File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		    assertNotNull(screenshot);

			try {
				FileUtils.copyFile(screenshot, new File("C:\\Users\\Make_\\Desktop\\chromedriver_win32\\polsat.png"));
				//FileUtils.copyFile(screenshot, new File("/home/PJWSTK/s11890/Pobrane/polsat.png"));
			} catch (IOException e) {
				e.printStackTrace();
				assertTrue(false);
			}
	}
	

	@AfterClass
	public static void cleanp() {
		driver.quit();
	}
}
