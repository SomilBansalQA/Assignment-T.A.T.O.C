package org.qait.tatoc.Action.tatoc1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class TatocAdvance {

	WebDriver driver;
	Tatocdatabase tatocdatabaseobj;
	PropertiesFileReader dataObj;
	By advanceCourse = By.linkText("Advanced Course");
	By menu2 = By.className("m2");
	By goNext = By.cssSelector(".menuitem:nth-child(5)");

	
	By symbolDisplay= By.id("symboldisplay");
	By name=By.id("name");
	By passkey=By.id("passkey");
	By proceed=By.id("submit");
	
	
	public TatocAdvance(WebDriver driver) {
		this.driver = driver;
		tatocdatabaseobj =new Tatocdatabase();
		dataObj = new PropertiesFileReader("database.properties");
	}

	public void clickOnAdvanceCourse() {
		driver.findElement(advanceCourse).click();
	}

	public void clickOnNexrFromMenu2() {
		WebElement element = driver.findElement(menu2);

		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		driver.findElement(goNext).click();

	}

	
	public void clickOnProceed(){
		driver.findElement(proceed).click();
	}
	
	public void submitValidcredentials(){
	
		String Symbol=driver.findElement(symbolDisplay).getText();
		tatocdatabaseobj.FetchUserNameAndPassWordAndThenSetinDatabasePropertyFile(Symbol);
		driver.findElement(name).sendKeys((String)dataObj.getOptionValue("username"));
		driver.findElement(passkey).sendKeys((String)dataObj.getOptionValue("password"));
		clickOnProceed();
	}
	
}
