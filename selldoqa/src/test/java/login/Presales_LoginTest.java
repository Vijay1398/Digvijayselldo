package login;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import adminPages.AdminDashboardPage;
import crm.selldo.LoginPage;
import utility.SetUp;

public class Presales_LoginTest extends SetUp {

	WebDriverWait wait;

	final static Logger logger = Logger.getLogger(Presales_LoginTest.class);

	@BeforeTest

	public void presales_LoginTest() throws Exception {

		mysetUp();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	@Test
	public void presalesLoginTest() throws Exception, EmailException {

		wait = new WebDriverWait(driver, 8);
		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		test = extent.createTest("presalesLoginTest");
		setExtentTest(test);
		LoginPage login = new LoginPage(driver);
		
		getExtTest().log(Status.INFO, "Logging in to presales user......");
		login.login(property.getProperty("name") + "+" + property.getProperty("presales_email"),
				property.getProperty("password"));

		Thread.sleep(3000);

		logger.info("Verifying that login is successfull.......");

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		getExtTest().log(Status.INFO, "Logging out of Selldo......");
		adminDashboardPage.loggingOut();

	}

	@AfterTest

	public void endingTest() throws Exception {

		Thread.sleep(3000);

		getExtTest().log(Status.INFO, "browser closed.......");
		driver.close();
	}

}
