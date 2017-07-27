package org.qait.tatoc.test.tatoc1;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.qait.tatoc.Action.tatoc1.Driver;
import org.qait.tatoc.Action.tatoc1.TatocAdvance;
import org.qait.tatoc.Action.tatoc1.TatocAdvanceRestAPI;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TatocAdvanceTest {

	Driver driverObj;
	TatocAdvance advanceObj;
	TatocAdvanceRestAPI tatocRestObj;
	WebDriver driver;
	String endOfCourse = "Obstacle Course is Complete!";

	@BeforeTest
	void setup() {
		driverObj = new Driver();

	}

	@Test(priority = 0)
	void testLaunchApplication() {
		driver = driverObj.LaunchApplication();
		advanceObj = new TatocAdvance(driver);
		tatocRestObj = new TatocAdvanceRestAPI(driver);
		Reporter.log("Successfully Launched Tatoc Page");
	}

	@Test(priority = 1)
	void testClickOnAdvanceCourse() {
		advanceObj.clickOnAdvanceCourse();
		Reporter.log("User click on advance link");
	}

	@Test(priority = 2)
	void testClickOnNexrFromMenu2() {
		advanceObj.clickOnNexrFromMenu2();
		Reporter.log("User click on Next from menu2");
	}

	@Test(priority = 3)
	void testSubmitValidcredentials() {
		advanceObj.submitValidcredentials();
		Reporter.log("User click on Next from menu2");
	}

	@Test(priority = 4)
	public void testRegisterforAccess() {
		tatocRestObj.RegisterforAccess();
	}

	@Test(priority = 5)
	public void testClickOnDownload() {
		tatocRestObj.clickOnDownload();
	}

	@Test(priority = 6)
	public void testreadingFile() {
		tatocRestObj.readingFile();
		tatocRestObj.setSignatureValue();
		tatocRestObj.clickonSubmitButton();
		assertEquals(endOfCourse, tatocRestObj.getEndOfBasicCourseText());
	}
}
