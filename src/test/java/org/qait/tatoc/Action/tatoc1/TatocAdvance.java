package org.qait.tatoc.Action.tatoc1;

import static com.jayway.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.jayway.restassured.http.ContentType;

public class TatocAdvance {

	WebDriver driver;
	Tatocdatabase tatocdatabaseobj;

	By advanceCourse = By.linkText("Advanced Course");
	By menu2 = By.className("m2");
	By goNext = By.cssSelector(".menuitem:nth-child(5)");
	By symbolDisplay = By.id("symboldisplay");
	By name = By.id("name");
	By passkey = By.id("passkey");
	By proceed = By.id("submit");

	public TatocAdvance(WebDriver driver) {
		this.driver = driver;
		tatocdatabaseobj = new Tatocdatabase();
	}

	public void clickOnAdvanceCourse() {
		driver.findElement(advanceCourse).click();
	}

	public void clickOnNextFromMenu2() {
		WebElement element = driver.findElement(menu2);

		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		driver.findElement(goNext).click();
	}

	public void clickOnProceed() {
		driver.findElement(proceed).click();
	}

	public void submitValidcredentials() {
		String Symbol = driver.findElement(symbolDisplay).getText();
		String userNameAndPassword = tatocdatabaseobj.getUserNameAndPasswordFromCredential(Symbol);
		System.out.println(userNameAndPassword);
		String[] words = userNameAndPassword.split("\\s");
		driver.findElement(name).sendKeys(words[0]);
		driver.findElement(passkey).sendKeys(words[1]);
		clickOnProceed();
	}

}
