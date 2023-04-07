package login;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import crm.selldo.AdminSupport_HomePage;
import crm.selldo.ClientLoginPage;
import crm.selldo.LoginPage;
import utility.SetUp;

public class SupportManager_LoginTest extends SetUp {

	final static Logger logger = Logger.getLogger(SupportManager_LoginTest.class);

	@BeforeTest

	public void admin_LoginTest() throws Exception {

		mysetUp();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

	}

	@Test

	public void supportManager_LoginTest() throws Exception {

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		test = extent.createTest("SupportManager_LoginTest");
		setExtentTest(test);
		LoginPage login = new LoginPage(driver);

		getExtTest().log(Status.INFO, "Logging in as Support Manager.......");
		login.login(property.getProperty("support_manager_email"), property.getProperty("password"));

		ClientLoginPage clientLoginPage = new ClientLoginPage(driver);

		AdminSupport_HomePage ashp = new AdminSupport_HomePage(driver);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		if (!driver.findElements(By.cssSelector("#main-menu-support-dashboard > a > i")).isEmpty()) {

			getExtTest().log(Status.INFO, "Logout and again login.......");
			ashp.loggingOut();

			getExtTest().log(Status.INFO, "Logging in to Support Home Page......");
			login.login(property.getProperty("support_manager_email"), property.getProperty("password"));

		} else {

			getExtTest().log(Status.INFO, "Logging in to Support Home Page......");

		}
		clientLoginPage.clientLogin(property.getProperty("v2_client_name"));
		Thread.sleep(3000);
		getExtTest().log(Status.INFO, "Verifying that client login is successfull.......");
		ashp.verifyingClientLogin();
		getExtTest().log(Status.INFO, "Logging out of Selldo......");
		ashp.loggingOut();
	}

	@AfterTest

	public void endingTest() throws Exception {

		Thread.sleep(3000);

		getExtTest().log(Status.INFO, "browser closed.......");
		driver.close();
	}

}
