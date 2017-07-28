package org.qait.tatoc.test.tatoc1;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.qait.tatoc.Action.tatoc1.Driver;
import org.qait.tatoc.Action.tatoc1.TatocBasicUsingJavaScript;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TatocBasicUSingJavaScriptTest {

	Driver driverObj;
	public TatocBasicUsingJavaScript tatocBasicviaJavaScriptObj;
	WebDriver driver;
	String endOfCourse = "Obstacle Course is Complete!";

	@BeforeTest
	void setup() {
		driverObj = new Driver();
		
	}
	@Test(priority = 0)
	void testLaunchApplication() {
		driver = driverObj.LaunchApplication();
		tatocBasicviaJavaScriptObj = new TatocBasicUsingJavaScript(driver);
		Reporter.log("Successfully Launched Tatoc Page");
	}
  
	@Test(priority = 1)
		void testClickOnBasicCourse() {
			tatocBasicviaJavaScriptObj.clickOnBasicCourse();
			Reporter.log("User click on basic link");
		}

	@Test(priority = 2)
		void testClickOnGreenBox() {
			tatocBasicviaJavaScriptObj.clickOnGreenBox();
			Reporter.log("User click on greenBox");
		}

		@Test(priority = 3)
		void testclickOnProceedWhenColorOfBothBoxAreSame() {
			tatocBasicviaJavaScriptObj.clickOnProceedWhenColorOfBothBoxAreSame();
			 Reporter.log("Successfully done the test, DRAG ME box is in the cell labeled DROPBOX");
		}
		
		@Test(priority = 4)
		void testclickOnProceedWhenDragMeBoxIsInCellLabeledDropBox() {
			tatocBasicviaJavaScriptObj.clickOnProceedWhenDragMeBoxIsInCellLabeledDropBox();
			Reporter.log("The popup window form was submitted Suceessfully");
		}

		@Test(priority = 5)
		void testclickOnProceedWhenPopUpWindowFormWasSubmittedProperly() {
			tatocBasicviaJavaScriptObj.clickOnProceedWhenPopUpWindowFormWasSubmittedProperly();
		}

		@Test(priority = 6)
		void testclickOnProceedWhenTokenCookieIsSetProperlyAndTokenIsValid() {
			tatocBasicviaJavaScriptObj.clickOnProceedWhenTokenCookieIsSetProperlyAndTokenIsValid();
			assertEquals(endOfCourse, tatocBasicviaJavaScriptObj.getEndOfBasicCourseText());
			Reporter.log("Successfully finish T.A.T.O.C Basic Course");
		}
	
}
