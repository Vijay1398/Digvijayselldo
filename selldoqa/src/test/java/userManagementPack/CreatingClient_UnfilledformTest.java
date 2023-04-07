package userManagementPack;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import crm.selldo.ClientLoginPage;
import crm.selldo.CreateClientFormPage;
import crm.selldo.LoginPage;
import utility.SetUp;

public class CreatingClient_UnfilledformTest extends SetUp {

	final static Logger logger = Logger.getLogger(CreatingClient_UnfilledformTest.class);

	// Description: Creating a new Client by filling only required fields only

	@BeforeTest

	public void adminLogin() throws Exception {

		mysetUp();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

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

		ClientLoginPage clientLogin = new ClientLoginPage(driver);

		logger.info("Clicking on Logout button.......");
		clientLogin.superAdminlogout();

		logger.info("browser closed.......");
		driver.close();

	}

	@Test

	public void creatingClient_UnfilledformTest() throws Exception {

		Thread.sleep(2000);

		test = extent.createTest("Creating Client Unfilled Form Test");
		setExtentTest(test);

		ClientLoginPage clientLogin = new ClientLoginPage(driver);

		getExtTest().log(Status.INFO, "Entering Business Name.......");
		clientLogin.clickOnCreateClientButton();

		CreateClientFormPage createClientForm = new CreateClientFormPage(driver);

		getExtTest().log(Status.INFO, "Clicking on Save Button......");
		createClientForm.clickOnSaveButton();

		Thread.sleep(2000);

		SoftAssert assertion = new SoftAssert();

		getExtTest().log(Status.INFO, "Verifying error message below Business Name Field.......");
		Assert.assertEquals(createClientForm.getBusinessNameRequiredMessage(), "This field is required.",
				"Not matched");

		getExtTest().log(Status.INFO, "Verifying error message below Short name Field.......");
		Assert.assertEquals(createClientForm.getShortNameRequiredMessage(), "This field is required.", "Not matched");

		getExtTest().log(Status.INFO, "Verifying error message below Website Field.......");
		Assert.assertEquals(createClientForm.getWebsiteFieldRequiredMessage(), "This field is required.",
				"Not matched");

		getExtTest().log(Status.INFO, "Verifying error message below Email Field.......");
		Assert.assertEquals(createClientForm.getEmailFieldRequiredMessage(), "This field is required.", "Not matched");

		getExtTest().log(Status.INFO, "Verifying error message below Email Domain Field.......");
		Assert.assertEquals(createClientForm.getEmailDomainRequiredMessage(), "This field is required.", "Not matched");

		getExtTest().log(Status.INFO, "Verifying error message below Upload Logo......");
		Assert.assertEquals(createClientForm.getUploadLogoRequiredMessage(), "This field is required.", "Not matched");

		getExtTest().log(Status.INFO, "Verifying error message below Client Phone Field.......");
		Assert.assertEquals(createClientForm.getClientPhoneRequiredMessage(), "This field is required.", "Not matched");

		getExtTest().log(Status.INFO, "Verifying error message below Address Field.......");
		Assert.assertEquals(createClientForm.getAddressFieldRequiredMessage(), "This field is required.",
				"Not matched");

		getExtTest().log(Status.INFO, "Verifying error message below City Field.......");
		Assert.assertEquals(createClientForm.getCityFieldRequiredMessage(), "This field is required.", "Not matched");

		getExtTest().log(Status.INFO, "Verifying error message below State dropdown.......");
		Assert.assertEquals(createClientForm.getStateDropdownRequiredMessage(), "This field is required.",
				"Not matched");

		getExtTest().log(Status.INFO, "Verifying error message below Country dropdown.......");
		Assert.assertEquals(createClientForm.getCountryDropdownRequiredMessage(), "This field is required.",
				"Not matched");

		getExtTest().log(Status.INFO, "Verifying error message below Zip Field.......");
		Assert.assertEquals(createClientForm.getZipFieldRequiredMessage(), "This field is required.", "Not matched");

		getExtTest().log(Status.INFO, "Verifying error message below First name Field.......");
		Assert.assertEquals(createClientForm.getFirstNameRequiredMessage(), "This field is required.", "Not matched");

		getExtTest().log(Status.INFO, "Verifying error message below Last name Field.......");
		Assert.assertEquals(createClientForm.getLastNameRequiredMessage(), "This field is required.", "Not matched");

		getExtTest().log(Status.INFO, "Verifying error message below User Email Field.......");
		Assert.assertEquals(createClientForm.getUserEmailRequiredMessage(), "This field is required.", "Not matched");

		getExtTest().log(Status.INFO, "Verifying error message below Team Field.......");
		Assert.assertEquals(createClientForm.getTeamFieldRequiredMessage(), "This field is required.", "Not matched");

		getExtTest().log(Status.INFO, "Verifying error message below Marketing Domain Field.......");
		Assert.assertEquals(createClientForm.getMarketingDomainRequiredMessage(), "This field is required.",
				"Not matched");

		Assert.assertEquals(driver.findElement(createClientForm.DomainMessage).getText(),"Domains will be selected from promotional email vendor.", "Not matched");

		assertion.assertAll();

	}

}
