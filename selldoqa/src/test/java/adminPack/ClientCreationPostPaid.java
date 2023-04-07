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
import utility.GetTestData;
import utility.SetUp;

public class ClientCreationPostPaid extends SetUp {

	final static Logger logger = Logger.getLogger(ClientCreationPostPaid.class);

	// Description: Creating a new Post paid client from Post Sales

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

		login.login(property.getProperty("support_manager_email"), property.getProperty("password"));
	}

	@AfterTest

	public void endingTest() throws Exception {

		logger.info("Closing Browser......");
		driver.close();
	}

	@Test

	public void creatingClientfromSupport() throws Exception {

		Thread.sleep(2000);

		test = extent.createTest("PostPaid_Client_creation_by_Support");
		setExtentTest(test);

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		ClientLoginPage clientLogin = new ClientLoginPage(driver);

		getExtTest().log(Status.INFO, "Clicking on Create client button.......");
		clientLogin.clickOnCreateClientButton();

		CreateClientFormPage createClientForm = new CreateClientFormPage(driver);

		GetTestData getTestData = new GetTestData();
		
		getExtTest().log(Status.INFO, "Entering Client first name...");
		createClientForm.enterClientFirstName();
		
		getExtTest().log(Status.INFO, "Entering client last name....");
		createClientForm.enterClientLastName();
		
		getExtTest().log(Status.INFO, "Entering Client Phone Number.......");
		createClientForm.addClientPhoneNumber();

		String businessName = getTestData.location.replaceAll("\\s+", "");
		getExtTest().log(Status.INFO, "Entering Business Name as " + businessName + ".......");
		createClientForm.enterBusinessName(businessName);

		getExtTest().log(Status.INFO, "Entering Short Name.......");
		createClientForm.enterShortName("sky");

		getExtTest().log(Status.INFO, "Entering Client website.......");
		createClientForm.enterClientWebsite(businessName);

		getExtTest().log(Status.INFO, "Entering Client Email.......");
		createClientForm.enterClientEmail();
		
		getExtTest().log(Status.INFO, "Entering TRANSACTIONAL SMS MASK.......");
		createClientForm.enterSMSmask("SDMASK");

		/*
		 * getExtTest().log(Status.INFO, "Clicking on upload button.......");
		 * createClientForm.clickOnUploadButton();
		 */

		getExtTest().log(Status.INFO, "Uploading image for Client Logo.......");
		createClientForm.uploadFile();

		Thread.sleep(3000);

		getExtTest().log(Status.INFO, "Entering Client Address.......");
		createClientForm.enterAddress_NM(property.getProperty("Address_creatingAClient_FullyFilledFormTest"),
				property.getProperty("Street_creatingAClient_FullyFilledFormTest"),
				property.getProperty("City_creatingAClient_FullyFilledFormTest"),
				property.getProperty("Country_creatingAClient_FullyFilledFormTest"),
				property.getProperty("Zip_creatingAClient_FullyFilledFormTest"));

		getExtTest().log(Status.INFO, "Entering Users First Name.......");
		createClientForm.enterFirstName();

		getExtTest().log(Status.INFO, "Entering Users Last Name.......");
		createClientForm.enterLastName();

		getExtTest().log(Status.INFO, "Entering Users Phone Number.......");
		createClientForm.enterUserPhoneNumber();

		getExtTest().log(Status.INFO, "Entering Users Email..........");
		createClientForm.enterUserEmail();

		getExtTest().log(Status.INFO, "Entering Team Name.......");
		createClientForm.enterUsersTeam();

		getExtTest().log(Status.INFO, "Clicking on Save Button......");
		createClientForm.clickOnSaveButton();

		Thread.sleep(10000);

		getExtTest().log(Status.INFO, "Logging into newly created client " + businessName + "......");

		clientLogin.clientLogin(businessName);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		getExtTest().log(Status.INFO, "Verifying Post paid client name from support Dashboard.......");
		adminDashboardPage.verifyingClientLogin();

		getExtTest().log(Status.INFO, "Logging out from support Dashboard.......");
		clientLogin.logout();

	}
}
