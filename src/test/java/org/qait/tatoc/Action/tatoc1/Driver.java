package org.qait.tatoc.Action.tatoc1;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Driver {

	WebDriver driver = null;
	String browser;
	PropertiesFileReader configObj;
	String exePath;

	public WebDriver LaunchApplication() {

		configObj = new PropertiesFileReader("config.properties");
		browser = (String) configObj.getOptionValue("browser");
		System.out.println(browser);
		if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}

		else if (browser.equalsIgnoreCase("chrome")) {

			exePath = "C:" + File.separator + "Users" + File.separator + "somilbansal" + File.separator + "Desktop"
					+ File.separator + "Driver" + File.separator + "chromedriver.exe";

			System.setProperty("webdriver.chrome.driver", exePath);
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("IE")) {

			exePath = "C:" + File.separator + "Users" + File.separator + "somilbansal" + File.separator + "Desktop"
					+ File.separator + "Driver" + File.separator + "IEDriverServer.exe";

			System.setProperty("webdriver.ie.driver", exePath);
			driver = new InternetExplorerDriver();
		}

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://10.0.1.86/tatoc");
		return driver;
	}
}
