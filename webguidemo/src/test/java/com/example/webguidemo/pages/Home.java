package com.example.webguidemo.pages;

import java.util.concurrent.TimeUnit;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Home extends WebDriverPage {

	WebDriver driver;
	private boolean acceptNextAlert = true;
	
	public Home(WebDriverProvider driverProvider) {
		super(driverProvider);
		driver = driverProvider.get();
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
	
	public void textBoxClear() {
		findElement(By.id("vfb-9")).clear();
	}
	
	public void textBoxInteraction(String text) {
		findElement(By.id("vfb-9")).sendKeys(text);
	}
	
	public String textBoxValue() {
		return findElement(By.id("vfb-9")).getAttribute("value");
	}
	
	public void checkBoxInteraction() {
		findElement(By.id("vfb-6-0")).click();
	}
	
	public boolean checkBoxSelected() {
		return findElement(By.id("vfb-6-0")).isSelected();
	}
	
	public void checkRadioButton() {
		findElement(By.id("vfb-7-3")).click();
	}
	
	public boolean radioButtonSelected() {
		return findElement(By.id("vfb-7-3")).isSelected();
	}
	
	public void clickAlert() {
		findElement(By.id("alert")).click();
	}
	
	public String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	}
	
	public void clickSubmit() {
		findElement(By.cssSelector("#vfb-4")).click();
	}
	
	public String validatorMsg() {
		return findElement(By.cssSelector("#item-vfb-2 > ul > li.vfb-item.vfb-item-secret > span > label.vfb-error")).getText();
	}
	
	public void typeValue(String value) {
		findElement(By.xpath("//*[@id='vfb-3']")).sendKeys(value);
	}
	
	public void clearValue() {
		findElement(By.xpath("//*[@id='vfb-3']")).clear();
	}
	
	public void dragTheElement() {
		findElement(By.xpath("//*[@id='draga']"));
	}
	
	public void dragTheElementTo() {
		findElement(By.xpath("//*[@id='dragb']"));
	}
	
	public String dragged() {
		return findElement(By.cssSelector("#dragb")).getText();
	}
	
}
