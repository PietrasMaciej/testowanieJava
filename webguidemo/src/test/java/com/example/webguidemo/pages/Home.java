package com.example.webguidemo.pages;

import java.util.concurrent.TimeUnit;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

public class Home extends WebDriverPage {

	public Home(WebDriverProvider driverProvider) {
		super(driverProvider);
	}

	private final static String SPORT_LINK = "//*[@id='main-menu']/a[4]";
	private final static String LINK_TEXT = "Setup Visual Studio";

	
	public void open() {
		get("http://www.seleniumframework.com/Practiceform/");
		manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	public void clickLink(){
		//findElement(By.xpath(SPORT_LINK)).click();
		findElement(By.linkText(LINK_TEXT)).click();
	}
	
	public void textBoxInteraction() {
		findElement(By.id("vfb-9")).sendKeys(" test");
	}
	
	public void checkBoxInteraction() {
		findElement(By.id("vfb-6-0")).click();
	}
	
	public void checkRadioButton() {
		findElement(By.id("vfb-7-3")).click();
	}
	
}
