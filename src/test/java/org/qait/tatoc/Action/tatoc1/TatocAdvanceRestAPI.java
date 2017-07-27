package org.qait.tatoc.Action.tatoc1;

import static com.jayway.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class TatocAdvanceRestAPI {

	Response response;

	WebDriver driver;
	By sessionId = By.id("session_id");
	By proceed = By.linkText("Proceed");
	By downloadFile = By.linkText("Download File");
	By signatureValue = By.cssSelector("input#signature");
	By Submit = By.className("submit");
	By endOfBasicCourse = By.cssSelector("Span[class='finish']");

	String Signature = "";

	public TatocAdvanceRestAPI(WebDriver driver) {
		this.driver = driver;
	}

	public String getSessionID() {
		String sessionID;
		sessionID = driver.findElement(sessionId).getText().substring(12);
		return sessionID;
	}

	public String getGeneratedToken() {
		String token;
		response = given().get("http://10.0.1.86/tatoc/advanced/rest/service/token/" + getSessionID()).then()
				.contentType(ContentType.JSON).extract().response();
		assertEquals(response.statusCode(), 200);
		System.out.println(response.asString());
		token = response.jsonPath().getString("token");
		return token;
	}

	public void clickOnProceed() {
		driver.findElement(proceed).click();
	}

	public void RegisterforAccess() {

		driver.navigate().to("http://10.0.1.86/tatoc/advanced/rest/#");
		response = given().contentType(ContentType.JSON).when()
				.post("http://10.0.1.86/tatoc/advanced/rest/service/register?signature=" + getGeneratedToken() + "&id="
						+ getSessionID() + "&allow_access=1");
		System.out.println(response.statusCode());
		clickOnProceed();
	}

	public void clickOnDownload() {
		driver.findElement(downloadFile).click();
	}

	public void readingFile() {

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		File file = new File("C:" + File.separator + "Users" + File.separator + "somilbansal" + File.separator
				+ "Downloads" + File.separator + "file_handle_test.dat");

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			String line = null;
			int count = 0;
			while ((line = br.readLine()) != null) {
				count++;
				if (count == 3) {
					Signature = line.substring(11);
				}
			}
      		br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void setSignatureValue() {
		driver.findElement(signatureValue).sendKeys(Signature);
	}

	public void clickonSubmitButton() {
		driver.findElement(Submit).click();
	}

	public String getEndOfBasicCourseText() {
		return driver.findElement(endOfBasicCourse).getText();
	}

}
