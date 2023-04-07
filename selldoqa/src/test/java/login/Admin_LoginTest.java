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

public class Admin_LoginTest extends SetUp {

	WebDriverWait wait;

	final static Logger logger = Logger.getLogger(Admin_LoginTest.class);

	@BeforeTest

	public void admin_LoginTest() throws Exception {

		mysetUp();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	@Test
	public void adminLoginTest() throws Exception {

		wait = new WebDriverWait(driver, 8);
		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		test = extent.createTest("adminLoginTest");
		setExtentTest(test);

		LoginPage login = new LoginPage(driver);

		getExtTest().log(Status.INFO, "Logging in to client page.......");
		login.login(property.getProperty("name") + "+" + property.getProperty("admin_email"),
				property.getProperty("password"));

		Thread.sleep(3000);

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
