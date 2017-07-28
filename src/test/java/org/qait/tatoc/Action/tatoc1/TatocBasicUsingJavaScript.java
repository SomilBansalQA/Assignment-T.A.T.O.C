package org.qait.tatoc.Action.tatoc1;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class TatocBasicUsingJavaScript {



	By launchWindow = By.linkText("Launch Popup Window");
	By name = By.id("name");
	By submit = By.id("submit");
	By generateToken = By.linkText("Generate Token");
	By token = By.id("token");
	By endOfBasicCourse = By.cssSelector("Span[class='finish']");

	WebDriver driver;

	public TatocBasicUsingJavaScript(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOnBasicCourse(){

		((JavascriptExecutor) driver)
		.executeScript("document.getElementsByTagName('a')[0].click();");
	}

	public void clickOnGreenBox() {

		((JavascriptExecutor) driver)
		.executeScript("document.querySelector('.greenbox').click();");
	}

	public void clickOnRepaint() {
		((JavascriptExecutor) driver)
		.executeScript("document.getElementsByTagName('a')[0].click();");
	}

	public void clickOnProceed() {
		((JavascriptExecutor) driver)
		.executeScript("document.getElementsByTagName('a')[1].click();");
	}

	public void clickOnProceedWhenColorOfBothBoxAreSame() {

		driver.switchTo().frame("main");
		String colorBox1 =	 (String) ((JavascriptExecutor) driver)
				.executeScript("return document.getElementById('answer').getAttribute('class')");
				
		System.out.println("Main Box Color " + colorBox1);
		do {
			driver.switchTo().frame("child");
			String colorBox2 =  (String) ((JavascriptExecutor) driver)
					.executeScript("return document.getElementById('answer').getAttribute('class')");
			
			System.out.println("Child Box Color " + colorBox2);

			if (colorBox2.equals(colorBox1)) {
				driver.switchTo().parentFrame();

				break;
			} else {

				driver.switchTo().parentFrame();
				clickOnRepaint();
			}
		} while (true);

		clickOnProceed();
	
		}

	public void clickOnProceedWhenDragMeBoxIsInCellLabeledDropBox() {
		WebElement From = (WebElement) ((JavascriptExecutor) driver).executeScript("return document.getElementById('dragbox')");
		
		WebElement To =  (WebElement) ((JavascriptExecutor) driver).executeScript("return document.getElementById('dragbox')");
		Actions builder = new Actions(driver);
		Action dragAndDrop = builder.clickAndHold(From).moveToElement(To).release(To).build();
		dragAndDrop.perform();
		clickOnProceed();
	}

	public void clickOnLaunchWindow() {
		driver.findElement(launchWindow).click();
	}

	public void clickOnProceedWhenPopUpWindowFormWasSubmittedProperly()

	{
		String parentHandle = driver.getWindowHandle();
		clickOnLaunchWindow();
		for (String childHandle : driver.getWindowHandles()) {
			driver.switchTo().window(childHandle);
		}
		driver.findElement(name).sendKeys("Somil Bansal");
		driver.findElement(submit).click();
		driver.switchTo().window(parentHandle);
		clickOnProceed();
	}

	public void clickOnGenerateToken() {
		driver.findElement(generateToken).click();
	}

	public String getToken() {
		String tokenValue = driver.findElement(token).getText();
		return tokenValue;
	}

	public void clickOnProceedWhenTokenCookieIsSetProperlyAndTokenIsValid() {
		
		clickOnGenerateToken();
		String tokenValue = getToken().substring(7);
		System.out.println("Token valus is:" + tokenValue);
		Cookie name = new Cookie("Token", tokenValue);
		driver.manage().addCookie(name);
		Set<Cookie> cookiesList = driver.manage().getCookies();
		for (Cookie getcookies : cookiesList) {
			System.out.println(getcookies);
		}
		clickOnProceed();
	}

	public String getEndOfBasicCourseText() {
		return driver.findElement(endOfBasicCourse).getText();
	}
}
