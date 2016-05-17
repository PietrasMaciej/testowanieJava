package com.example.webguidemo;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Action;

import static org.junit.Assert.*;

public class PracticeformSteps {
	
	private final Pages pages;
	private static final String ALERT = "Please share this website with your friends and in your organization.";
	private static final String VALIDATOR = "This field is required.";
	
	public PracticeformSteps(Pages pages) {
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
    
    @When("user types $text on input")
    public void userTypeText(String text){   
    	pages.home().open();
    	pages.home().textBoxClear();
        pages.home().textBoxInteraction(text);
    }
    
    @Then("input has a value $inputText")
    public void textShown(String inputText){
       assertEquals(inputText, pages.home().textBoxValue());
       
    }	
    
    @When("user checks a checkBox")
    public void userClickCheckBox(){      
        pages.home().checkBoxInteraction();
    }
    
    @Then("checkBox is clicked")
    public void checkBoxClicked(){
    	assertTrue(pages.home().checkBoxSelected());
       
    }	
    
    @When("user clicks a radio button")
    public void clickRadioBox(){      
        pages.home().checkRadioButton();
    }
    
    @Then("radio button is clicked")
    public void radioBoxClicked(){
    	assertTrue(pages.home().radioButtonSelected());
    }	
    
    @When("user clicks a button")
    public void userClicksAlert(){      
        pages.home().clickAlert();
    }
    
    @Then("alert is shown")
    public void alertShown(){
    	assertEquals(ALERT, pages.home().closeAlertAndGetItsText());
    }	
    
    @When("user clicks verification submit")
    public void userClicksSubmit(){      
        pages.home().clickSubmit();
    }
    
    @Then("validator is shown")
    public void validatorShown(){
    	assertEquals(VALIDATOR, pages.home().validatorMsg());
    }	
    
    @When("user types $letter in verification field")
    public void userClicksSubmitLetter(String letter){      
        pages.home().typeValue(letter);
    }
    
    @Then("$invalid message is shown")
    public void validatorLetterIsShown(String invalid){
    	assertEquals(invalid, pages.home().validatorMsg());
    }	
    
    @When("user types $threeDigits in verification")
    public void userClicksSubmitWithDigits(String threeDigits){  
    	pages.home().clearValue();
        pages.home().typeValue(threeDigits);
    }
    
    @Then("following message is shown $msg")
    public void validatorShownTooMany(String msg){
    	assertEquals(msg, pages.home().validatorMsg());
    }	
    
//    @When("user drags an element to another")
//    public void dragAnElement() throws InterruptedException{      
//    	WebElement From = pages.home().findElement(By.xpath("//*[@id='draga']"));
//    	WebElement To = pages.home().findElement(By.xpath("//*[@id='dragb']"));
//        Actions builder = new Actions(pages.home());
//        builder.clickAndHold(From).moveToElement(To).release(To).build().perform();
//        Thread.sleep(10000);
//    }
//    
//    @Then("the element is draggable")
//    public void dragTo(){
//    	//assertEquals("Drag To! Drag Me!", pages.home().dragged());
//    }	
    

}
