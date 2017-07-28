package org.qait.tatoc.test.tatoc1;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.qait.tatoc.Action.tatoc1.Driver;
import org.qait.tatoc.Action.tatoc1.TatocBasic;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TatocBasicTest {
	Driver driverObj;
	TatocBasic basicObj;
	WebDriver driver;
	String endOfCourse = "Obstacle Course is Complete!";

	@BeforeTest
	void setup() {
		driverObj = new Driver();

	}

	@Test(priority = 0)
	void testLaunchApplication() {
		driver = driverObj.LaunchApplication();
		basicObj = new TatocBasic(driver);
		Reporter.log("Successfully Launched Tatoc Page");
	}

	@Test(priority = 1)
	void testClickOnBasicCourse() {
		basicObj.clickOnBasicCourse();
		Reporter.log("User click on basic link");
	}

	@Test(priority = 2)
	void testClickOnGreenBox() {
		basicObj.clickOnGreenBox();
		Reporter.log("User click on greenBox");
	}

	@Test(priority = 3)
	void testclickOnProceedWhenColorOfBothBoxAreSame() {
		basicObj.clickOnProceedWhenColorOfBothBoxAreSame();
		Reporter.log("Successfully done, color of both boxes are same ");
	}

	@Test(priority = 4)
	void testclickOnProceedWhenDragMeBoxIsInCellLabeledDropBox() {
		basicObj.clickOnProceedWhenDragMeBoxIsInCellLabeledDropBox();
        Reporter.log("Successfully done the test, DRAG ME box is in the cell labeled DROPBOX");
	}

	@Test(priority = 5)
	void testclickOnProceedWhenPopUpWindowFormWasSubmittedProperly() {
		basicObj.clickOnProceedWhenPopUpWindowFormWasSubmittedProperly();
		Reporter.log("The popup window form was submitted Suceessfully");
	}

	@Test(priority = 6)
	void testclickOnProceedWhenTokenCookieIsSetProperlyAndTokenIsValid() {
		basicObj.clickOnProceedWhenTokenCookieIsSetProperlyAndTokenIsValid();
		assertEquals(endOfCourse, basicObj.getEndOfBasicCourseText());
		Reporter.log("cookie is set properly");
		Reporter.log("Successfully finish T.A.T.O.C Basic Course");
	}
}
