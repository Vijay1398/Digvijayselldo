package login;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import adminPages.AdminDashboardPage;
import crm.selldo.AdminSupport_HomePage;
import crm.selldo.ClientLoginPage;
import crm.selldo.LoginPage;
import utility.SetUp;

public class SuperAdmin_LoginTest extends SetUp {

	final static Logger logger = Logger.getLogger(SuperAdmin_LoginTest.class);

	@Test

	public void superAdmin_LoginTest() throws Exception {

		mysetUp();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);
		
		test = extent.createTest("Super Admin Login Test");
		setExtentTest(test);
		LoginPage login = new LoginPage(driver);

		
		getExtTest().log(Status.INFO, "Loggin as Super admin page......");
		login.login(property.getProperty("superadmin_name") + property.getProperty("superadmin_email"),
				property.getProperty("password"));
		ClientLoginPage clientLoginPage = new ClientLoginPage(driver);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		getExtTest().log(Status.INFO, "Logging in to Admin Home Page......");
		clientLoginPage.clientLogin(property.getProperty("v2_client_name"));

		AdminSupport_HomePage ashp = new AdminSupport_HomePage(driver);

		Thread.sleep(3000);

		getExtTest().log(Status.INFO, "Verifying that client login is successfull.......");
		ashp.verifyingClientLogin();

		getExtTest().log(Status.INFO, "Logging out of Selldo......");
		ashp.loggingOut();
		
		getExtTest().log(Status.INFO,"Closing Browser......");
		driver.close();
	}
}
