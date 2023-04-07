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

import adminPages.EditPartnerFormPage;
import adminPages.PartnersPage;
import adminPages.SettingsPage;
import crm.selldo.ClientLoginPage;
import crm.selldo.LoginPage;
import utility.SetUp;

public class SearchingChannelPartnerTest extends SetUp {

	final static Logger logger = Logger.getLogger(SearchingChannelPartnerTest.class);

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

	public void searchingChannelPartnerTest() throws Exception {

		test = extent.createTest("searchingChannelPartnerTest");

		SettingsPage settingsPage = new SettingsPage(driver);

		PartnersPage PartnersPage = new PartnersPage(driver);

		EditPartnerFormPage editPartnerFormPage = new EditPartnerFormPage(driver);

		SoftAssert assertion = new SoftAssert();

		test.log(Status.INFO, "Clicking on Partners tab.......");
		settingsPage.clickOnPartnersTab();

		test.log(Status.INFO, "Clicking on Manage Partners tab.......");
		settingsPage.clickOnManagePartnersTab();

		test.log(Status.INFO, "Capturing Channel Partners name to be searched......");
		String partnerNameToBeSearched = driver
				.findElement(By.cssSelector("table > tbody > tr > td:nth-child(1)")).getAttribute("innerHTML");
		System.out.println("Channel Partner Before Search-" + partnerNameToBeSearched);

		test.log(Status.INFO, "Searching Channel Partner by name......");
		PartnersPage.searchPartner(partnerNameToBeSearched);

		test.log(Status.INFO, "Capturing Channel Partners name appeared at the top of the list......");
		String partnerNameAfterSearch = driver.findElement(By.xpath("//th[text()='Channel Partner']/following::td[1]"))
				.getAttribute("innerHTML");
		System.out.println("Channel Partner After Search-" + partnerNameAfterSearch);

		test.log(Status.INFO, "Verifying Partners name before and after search......");
		System.out.println("Started verification");
		Assert.assertEquals(partnerNameToBeSearched, partnerNameAfterSearch, "Not matched");
		System.out.println("Completed verification");
		Thread.sleep(2000);

		test.log(Status.INFO, "Capturing Channel Partners email to be searched......");
		PartnersPage.selectEdit();
		Thread.sleep(2000);
		editPartnerFormPage.clickOnContactDetailsTab();
		String partnerEmailToBeSearched = driver
				.findElement(By.xpath("//label[@for='channel_partner_contact_email']/following::input[1]"))
				.getAttribute("value");
		System.out.println("Channel Partner Email Before Search-" + partnerEmailToBeSearched);

		test.log(Status.INFO, "Capturing Channel Partners phone number to be searched......");
		String partnerPhoneNoToBeSearched = driver
				.findElement(By.xpath("//label[@for='channel_partner_contact_phone']/following::input[1]"))
				.getAttribute("value").replaceAll("\\s+", "").substring(3);
		System.out.println("Channel Partner Phone Before Search-" + partnerPhoneNoToBeSearched);

		test.log(Status.INFO, "Closing the edit form of Channel Partner.....");
		editPartnerFormPage.closeForm();

		test.log(Status.INFO, "Searching Channel Partner by email......");
		PartnersPage.searchPartner(partnerEmailToBeSearched);

		test.log(Status.INFO, "Verifying Partners name before and after search......");
		System.out.println("Started verification");
		Assert.assertEquals(partnerNameToBeSearched, partnerNameAfterSearch, "Not matched");
		System.out.println("Completed verification");

		test.log(Status.INFO, "Searching Channel Partner by Phone number......");
		PartnersPage.searchPartner(partnerPhoneNoToBeSearched);

		test.log(Status.INFO, "Verifying Partners name before and after search......");
		System.out.println("Started verification");
		Assert.assertEquals(partnerNameToBeSearched, partnerNameAfterSearch, "Not matched");
		System.out.println("Completed verification");

		assertion.assertAll();

	}
}
