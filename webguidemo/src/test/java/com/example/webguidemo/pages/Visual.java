package com.example.webguidemo.pages;

import java.util.concurrent.TimeUnit;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

public class Visual extends WebDriverPage{
	
	public Visual(WebDriverProvider driverProvider) {
		super(driverProvider);		
	}

	public void open() {
		get("http://www.seleniumframework.com/setup-visual-studio/");
		manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

}
