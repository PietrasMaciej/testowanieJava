package com.example.webguidemo;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;

import static org.junit.Assert.*;

public class TelemanSteps {
	
	private final Pages pages;

	public TelemanSteps(Pages pages) {
		this.pages = pages;
	}
	
	@Given("user is on Home page")
    public void userIsOnHomePage(){        
        pages.home().open();        
    }
 
    @When("user opens a link")
    public void userClicksOnLink(){        
        pages.home().clickLink();
    }
 
    @Then("another page is shown")
    public void pageIsShown(){
       assertEquals("Selenium Framework | Setup Visual Studio", pages.visual().getTitle());
    }	
    
    @When("user type a text on input")
    public void userTypeText(){   
    	pages.home().open();     
        pages.home().textBoxInteraction();
    }
    
    @Then("input has a value")
    public void textShown(){
       assertEquals("This is a text box test", pages.home().findElement(By.id("vfb-9")).getAttribute("value"));
       
    }	
    
    @When("user check a checkBox")
    public void userClickCheckBox(){      
        pages.home().checkBoxInteraction();
    }
    
    @Then("checkBox is clicked")
    public void checkBoxClicked(){
    	assertTrue(pages.home().findElement(By.id("vfb-6-0")).isSelected());
       
    }	

}
