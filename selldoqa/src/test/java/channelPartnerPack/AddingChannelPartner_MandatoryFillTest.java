package channelPartnerPack;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import adminPages.NewPartnerFormPage;
import adminPages.PartnersPage;
import adminPages.SettingsPage;
import crm.selldo.ClientLoginPage;
import crm.selldo.LoginPage;
import utility.SetUp;

public class AddingChannelPartner_MandatoryFillTest extends SetUp{

	final static Logger logger = Logger.getLogger(AddingChannelPartner_MandatoryFillTest.class);

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

	@Test

	public void addingChannelPartner_MandatoryFillTest() throws Exception {

		test = extent.createTest("addingChannelPartner_MandatoryFillTest");

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
		newPartnerFormPage.enterPartnerName(property.getProperty("ChannelPartnerName_addingChannelPartner_MandatoryFillTest"));

		test.log(Status.INFO, "Clicking on Contact Details tab.......");
		newPartnerFormPage.clickOnContactDetailsTab();

		test.log(Status.INFO, "Selecting Salutation from dropdown.......");
		newPartnerFormPage.selectSalutation();

		test.log(Status.INFO, "Entering First name of partner.......");
		newPartnerFormPage.enterFirstName(property.getProperty("FirstName_addingChannelPartner_MandatoryFillTest"));

		test.log(Status.INFO, "Entering Last name of partner.......");
		newPartnerFormPage.enterLastName(property.getProperty("LastName_addingChannelPartner_MandatoryFillTest"));

		test.log(Status.INFO, "Entering Phone number.......");
		newPartnerFormPage.enterPhone(" " + property.getProperty("Phone_addingChannelPartner_MandatoryFillTest"));

		test.log(Status.INFO, "Entering Email.......");
		newPartnerFormPage.enterEmail(property.getProperty("Email_addingChannelPartner_MandatoryFillTest"));

		test.log(Status.INFO, "Clicking on Save button.....");
		newPartnerFormPage.clickOnSaveButton();
		
		String channelPartnerName =property.getProperty("ChannelPartnerName_addingChannelPartner_MandatoryFillTest");
		PartnersPage.searchPartner(channelPartnerName);
		
		test.log(Status.INFO, "Capturing Channel Partner name appeared at the top of the list......");
		String channelPartnerNameAfterSearch = driver
				.findElement(By.xpath("//th[text()='Channel Partner']/following::td[1]")).getAttribute("innerHTML");
		System.out.println("Channel Partner After Search-" + channelPartnerNameAfterSearch);

		test.log(Status.INFO, "Validating Channel Partners name after adding new Partner.....");
		SoftAssert assertion = new SoftAssert();
		System.out.println("Started verification");
		Assert.assertEquals(channelPartnerName,
				channelPartnerNameAfterSearch, "Not matched");
		System.out.println("Completed verification");
		assertion.assertAll();

	}

}
