package channelPartnerPack;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import adminPages.AdminDashboardPage;
import adminPages.NewPartnerFormPage;
import adminPages.PartnersPage;
import adminPages.SettingsPage;
import crm.selldo.ClientLoginPage;
import crm.selldo.LoginPage;
import utility.SetUp;

public class AddingChannelPartner_FilledFormTest extends SetUp {

	final static Logger logger = Logger.getLogger(AddingChannelPartner_FilledFormTest.class);

	// Description:

	@BeforeTest

	public void sales_presalesLogin() throws Exception {

		mysetUp();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		LoginPage login = new LoginPage(driver);

		logger.info("Logging in to client page.......");
		login.login(property.getProperty("superadmin_name") + property.getProperty("superadmin_email"),
				property.getProperty("password"));

		ClientLoginPage clientLogin = new ClientLoginPage(driver);

		logger.info("Logging in to Admin/Support Home Page......");
		clientLogin.clientLogin(property.getProperty("Other_client_name"));
	}

	@AfterTest

	public void endingTest() throws Exception {

		Thread.sleep(3000);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		logger.info("Logging out of Selldo......");
		adminDashboardPage.loggingOut();

		logger.info("Closing Browser......");
		driver.close();
	}
	
	

	@Test

	public void addingChannelPartner_FilledFormTest() throws Exception {

		test = extent.createTest("addingChannelPartner_FilledFormTest");

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		SettingsPage settingsPage = new SettingsPage(driver);

		PartnersPage PartnersPage = new PartnersPage(driver);

		NewPartnerFormPage newPartnerFormPage = new NewPartnerFormPage(driver);

		test.log(Status.INFO, "Clicking on Partners tab.......");
		settingsPage.clickOnPartnersTab();

		test.log(Status.INFO, "Clicking on Manage Partners tab.......");
		settingsPage.clickOnManagePartnersTab();

		test.log(Status.INFO, "Clicking on New Partner Tab.......");
		PartnersPage.clickOnNewPartnerButton();

		Thread.sleep(2000);

		test.log(Status.INFO, "Entering Channel Partner name.......");
		newPartnerFormPage
				.enterPartnerName(property.getProperty("ChannelPartnerName_addingChannelPartner_FilledFormTest"));

		test.log(Status.INFO, "Selecting date of joining from calender......");
		newPartnerFormPage.selectDateOfJoining();

		test.log(Status.INFO, "Entering Source of recruitment.......");
		newPartnerFormPage.enterSourceOfRecruitment(
				property.getProperty("SourceOfRecruitment_addingChannelPartner_FilledFormTest"));

		test.log(Status.INFO, "Clicking on Contact Details tab.......");
		newPartnerFormPage.clickOnContactDetailsTab();
		Thread.sleep(2000);

		test.log(Status.INFO, "Selecting Salutation from dropdown.......");
		newPartnerFormPage.selectSalutation();

		test.log(Status.INFO, "Entering First name of partner.......");
		newPartnerFormPage.enterFirstName(property.getProperty("FirstName_addingChannelPartner_FilledFormTest"));

		test.log(Status.INFO, "Entering Last name of partner.......");
		newPartnerFormPage.enterLastName(property.getProperty("LastName_addingChannelPartner_FilledFormTest"));

		test.log(Status.INFO, "Entering Phone number.......");
		newPartnerFormPage.enterPhone(" " + property.getProperty("Phone_addingChannelPartner_FilledFormTest"));

		test.log(Status.INFO, "Entering Email.......");
		newPartnerFormPage.enterEmail(property.getProperty("Email_addingChannelPartner_FilledFormTest"));

		test.log(Status.INFO, "Entering Alternate phone number.......");
		newPartnerFormPage
				.enterAlternatePhone(" " + property.getProperty("AlternatePhone_addingChannelPartner_FilledFormTest"));

		test.log(Status.INFO, "Entering Alternate email.......");
		newPartnerFormPage
				.enterAlternateEmail(property.getProperty("AlternateEmail_addingChannelPartner_FilledFormTest"));

		test.log(Status.INFO, "Entering Designation......");
		newPartnerFormPage.enterDesignation(property.getProperty("Designation_addingChannelPartner_FilledFormTest"));

		test.log(Status.INFO, "Entering PAN number.......");
		newPartnerFormPage.enterPanNumber(property.getProperty("PanNumber_addingChannelPartner_FilledFormTest"));

		test.log(Status.INFO, "Clicking on Specialization tab.......");
		newPartnerFormPage.clickOnSpecialization();

		Thread.sleep(2000);

		test.log(Status.INFO, "Selecting Channel Partner type......");
		newPartnerFormPage.selectChannelPartnerType();

		test.log(Status.INFO, "Entering Rera number.......");
		newPartnerFormPage.enterReraNumber();

		test.log(Status.INFO, "Selecting Property type.......");
		newPartnerFormPage.selectPropertyType();

		test.log(Status.INFO, "Entering Minimum Budget.......");
		newPartnerFormPage.enterMinBudget(property.getProperty("MinBudget_addingChannelPartner_FilledFormTest"));

		test.log(Status.INFO, "Entering Maximum Budget.......");
		newPartnerFormPage.enterMaxBudget(property.getProperty("MaxBudget_addingChannelPartner_FilledFormTest"));

		test.log(Status.INFO, "Clicking on Save button.....");
		newPartnerFormPage.clickOnSaveButton();

		String channelPartnerName = property.getProperty("ChannelPartnerName_addingChannelPartner_FilledFormTest");
		PartnersPage.searchPartner(channelPartnerName);

		test.log(Status.INFO, "Capturing Channel Partner name appeared at the top of the list......");
		String channelPartnerNameAfterSearch = driver
				.findElement(By.cssSelector("table tbody tr:nth-child(1) td:nth-child(1)")).getAttribute("innerHTML");
		System.out.println(channelPartnerNameAfterSearch);

		test.log(Status.INFO, "Validating Channel Partners name after adding new Partner.....");
		SoftAssert assertion = new SoftAssert();
		System.out.println("Started verification");
		Assert.assertEquals(channelPartnerName, channelPartnerNameAfterSearch, "Not matched");
		System.out.println("Completed verification");
		assertion.assertAll();

	}
}
