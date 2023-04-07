package adminPack;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

import adminPages.AdminDashboardPage;
import crm.selldo.ClientLoginPage;
import crm.selldo.CreateClientFormPage;
import crm.selldo.LoginPage;
import utility.SetUp;

public class CreatingAClient_RequiredFilledFormTest extends SetUp {

	final static Logger logger = Logger.getLogger(CreatingAClient_RequiredFilledFormTest.class);

	// Description: Creating a new Client by filling only required fields only

	@BeforeTest
	
	
	public void adminLogin() throws Exception {

		mysetUp();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		LoginPage login = new LoginPage(driver);

		logger.info("Logging in to client page.......");

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		login.login(property.getProperty("superadmin_name") + property.getProperty("superadmin_email"),
				property.getProperty("password"));
	}

	@AfterTest

	public void endingTest() throws Exception {

		ClientLoginPage cl = new ClientLoginPage(driver);

		logger.info("Clicking on Logout button.......");
		cl.logout();

		logger.info("browser closed.......");
		driver.close();

	}

	@Test

	public void creatingAClient_RequiredFilledFormTest() throws InterruptedException, AWTException, IOException {

		Thread.sleep(2000);

		test = extent.createTest("Creating Client with Required Filled Form Test");

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		ClientLoginPage clientLogin = new ClientLoginPage(driver);

		test.log(Status.INFO, "Clicking on Create client button.......");
		clientLogin.clickOnCreateClientButton();

		CreateClientFormPage createClientForm = new CreateClientFormPage(driver);
		
		test.log(Status.INFO, "Entering Client first name...");
		createClientForm.enterClientFirstName();
		
		test.log(Status.INFO, "Entering client last name....");
		createClientForm.enterClientLastName();

		test.log(Status.INFO, "Entering Business Name.......");
		String clientObj = property.getProperty("ClientName_creatingAClient_RequiredFilledFormTest");
		createClientForm.enterBusinessName(clientObj);

		test.log(Status.INFO, "Entering Short Name.......");
		createClientForm.enterShortName("sky");

		test.log(Status.INFO, "Entering Client website.......");
		createClientForm.enterClientWebsite(clientObj);

		test.log(Status.INFO, "Entering Client Email.......");
		createClientForm.enterClientEmail();

		test.log(Status.INFO, "Entering Email domain.......");
		createClientForm.enterEmailDomain(property.getProperty("EmailDomain_creatingAClient_RequiredFilledFormTest"));

		test.log(Status.INFO, "Clicking on upload button.......");
		createClientForm.clickOnUploadButton();

		test.log(Status.INFO, "Uploading image for Client Logo.......");
		createClientForm.uploadFile();

		Thread.sleep(3000);

		test.log(Status.INFO, "Entering Client Phone Number.......");
		createClientForm.enterClientPhoneNumber(" " +
				property.getProperty("ClientPhoneNumber_creatingAClient_RequiredFilledFormTest"));

		test.log(Status.INFO, "Selecting city for Indian PRI.......");
		createClientForm.selectCityForIndianPRI(
				property.getProperty("CityForIndianPRI_creatingAClient_RequiredFilledFormTest"));

		test.log(Status.INFO, "Selecting Indian PRI.......");
		createClientForm.selectIndianPRInumber();

		test.log(Status.INFO, "Entering SMS mask.......");
		createClientForm.enterSMSmask(property.getProperty("CityForIndianPRI_creatingAClient_RequiredFilledFormTest"));

		test.log(Status.INFO, "Entering SMS mask.......");
		createClientForm.enterAddress_M(property.getProperty("Address_creatingAClient_RequiredFilledFormTest"),
				property.getProperty("City_creatingAClient_RequiredFilledFormTest"),
				property.getProperty("Zip_creatingAClient_RequiredFilledFormTest"), property.getProperty("Country_creatingAClient_RequiredFilledFormTest"));

		test.log(Status.INFO, "Entering Users First Name.......");
		createClientForm.enterFirstName();

		test.log(Status.INFO, "Entering Users Second Name.......");
		createClientForm.enterLastName();

		test.log(Status.INFO, "Entering Users Phone Number.......");
		createClientForm.enterUserPhoneNumber();

		test.log(Status.INFO, "Entering Users Email..........");
		createClientForm.enterUserEmail();

		test.log(Status.INFO, "Entering Team Name.......");
		createClientForm.enterUsersTeam();

		test.log(Status.INFO, "Selecting Promotional Email.......");
		createClientForm.selectPromotionalEmail();

		test.log(Status.INFO, "Selecting Marketing Domain......");
		createClientForm.selectMarketingDomain();

		test.log(Status.INFO, "Clicking on Save Button......");
		createClientForm.clickOnSaveButton();

		Thread.sleep(6000);

		test.log(Status.INFO, "Clicking on Create Clients Link......");
		clientLogin.clickOnClientsLink();

		test.log(Status.INFO, "Logging into newly created client......");
		clientLogin.clientLogin(clientObj);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		test.log(Status.INFO, "Verifying page title of Admin dashboard.......");
		adminDashboardPage.verifyingClientLogin();
	}
}
