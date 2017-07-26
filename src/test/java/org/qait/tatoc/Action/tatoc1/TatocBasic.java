package org.qait.tatoc.Action.tatoc1;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class TatocBasic {

	By basicCourse = By.linkText("Basic Course");
	By greenBox = By.className("greenbox");
	By repaintBox2 = By.linkText("Repaint Box 2");
	By proceed = By.linkText("Proceed");
	By mainFrame = By.id("main");
	By childFrame = By.id("child");
	By box = By.id("answer");
	By drag = By.id("dragbox");
	By drop = By.id("dropbox");
	By launchWindow = By.linkText("Launch Popup Window");
	By name = By.id("name");
	By submit = By.id("submit");
	By generateToken = By.linkText("Generate Token");
	By token = By.id("token");
	By endOfBasicCourse = By.cssSelector("Span[class='finish']");

	WebDriver driver;

	public TatocBasic(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOnBasicCourse(){
		driver.findElement(basicCourse).click();
	}

	public void clickOnGreenBox() {
		driver.findElement(greenBox).click();
	}

	public void clickOnRepaint() {
		driver.findElement(repaintBox2).click();
	}

	public void clickOnProceed() {
		driver.findElement(proceed).click();
	}

	public void clickOnProceedWhenColorOfBothBoxAreSame() {

		driver.switchTo().frame("main");
		String colorBox1 = driver.findElement(box).getAttribute("class");
		System.out.println("Main Box Color " + colorBox1);
		do {
			driver.switchTo().frame("child");
			String colorBox2 = driver.findElement(box).getAttribute("class");
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
		WebElement From = driver.findElement(drag);
		WebElement To = driver.findElement(drop);
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
