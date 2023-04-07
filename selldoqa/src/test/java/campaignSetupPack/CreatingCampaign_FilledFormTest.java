package campaignSetupPack;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import adminPages.AdminDashboardPage;
import adminPages.AllCampaignPage;
import adminPages.AutomationDashboardPage;
import adminPages.NewCampaignFormPage;
import crm.selldo.ClientLoginPage;
import crm.selldo.LoginPage;
import utility.SetUp;

public class CreatingCampaign_FilledFormTest extends SetUp {

	final static Logger logger = Logger.getLogger(CreatingCampaign_FilledFormTest.class);
	String campaignName;

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
		clientLogin.clientLogin(property.getProperty("client_name"));

	}

	@Test

	public void creatingCampaign_FilledFormTest() throws Exception {

		test = extent.createTest("creatingCampaign_FilledFormTest");
		setExtentTest(test);

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		AutomationDashboardPage automationDashboardPage = new AutomationDashboardPage(driver);

		AllCampaignPage allCampaignPage = new AllCampaignPage(driver);

		NewCampaignFormPage newCampaignFormPage = new NewCampaignFormPage(driver);

		
		
		test.log(Status.INFO, "Clicking on wand Icon.......");
		adminDashboardPage.clickOnWandIcon();

		// Issue refactored
		test.log(Status.INFO, "Clicking on Campaign SetUp tab.......");
		automationDashboardPage.cickOnCampaignSetUp();

		Thread.sleep(5000);
		
		test.log(Status.INFO, "Clicking on New Campaign button......");
		allCampaignPage.clickOnNewCampaignButton();

		campaignName = property.getProperty("campaignName_creatingCampaign_FilledFormTest");
		String trackingEmail = property.getProperty("trackingEmail_creatingCampaign");

		// For generating random name
		test.log(Status.INFO, "Entering Campaign Name");
		newCampaignFormPage.enterRandomCampaignName(campaignName);
		
		

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");

		
		test.log(Status.INFO, "Selecting Project under Overview Tab.....");
		newCampaignFormPage.selectProject_TrackingEmails();
		
		test.log(Status.INFO, "Accepting an alert popup");
		newCampaignFormPage.clickAlertPopup();
		
		
		// test.log(Status.INFO, "Selecting Team from dropdown......");
		// newCampaignFormPage.selectTeam();
		
		Thread.sleep(5000);

		test.log(Status.INFO, "Clicking Save and Next button to go to the next tab........");
		newCampaignFormPage.clickOnSaveAndNextButton();

		// Tracking Emails Tab...................................


		test.log(Status.INFO, "Clicking on Add button under Tracking Emails tab.....");
		newCampaignFormPage.clickOnAddButton_TrackingEmail();

		// Refactored
		test.log(Status.INFO, "Entering Tracking email name under Tracking Emails Tab.....");
		newCampaignFormPage.enterTrackingEmailName(trackingEmail);

		//test.log(Status.INFO, "Selecting Project under Tracking Emails Tab.....");
		//newCampaignFormPage.selectProject_TrackingEmails();

		test.log(Status.INFO, "Selecting Source under Tracking Emails Tab.....");
		newCampaignFormPage.selectSource_TrackingEmails();

		test.log(Status.INFO, "Selecting Sub source under Tracking Emails Tab.....");
		newCampaignFormPage.enterSubSource_TrackingEmails("Testing");

		test.log(Status.INFO, "Clicking on Save button under Tracking Emails Tab.....");
		newCampaignFormPage.clickOnSaveButton_TrackingEmails();

		// Tracking Numbers Tab.....................................

		test.log(Status.INFO, "Clicking on Tracking Numbers Tab.....");
		newCampaignFormPage.clickOnTrackingNumbersTab();

		test.log(Status.INFO, "Clicking on Action bar.....");
		newCampaignFormPage.clickOnShowOtherVirtualNumber_TrackingNumbers();

		test.log(Status.INFO, "Clicking on Action bar.....");
		newCampaignFormPage.clickOnActionBar_TrackingNumbers();

		if (driver.findElement(By.xpath("//th[text()='Actions']/following::a[@class='action_button dropdown-item'][1]"))
				.isDisplayed()) {

			test.log(Status.INFO, "Clicking on Add link under action bar.......");
			newCampaignFormPage.clickOnAddLink_TrackingNumbers();

			Thread.sleep(2000);

			test.log(Status.INFO, "Clicking on Add link under action bar.......");
			newCampaignFormPage.clickOnSaveButton_TrackingNumbers();
		}

		// Input Channels.....................................

		test.log(Status.INFO, "Clicking on Input Channels Tab.....");
		newCampaignFormPage.clickOnInputChannelTab();

		test.log(Status.INFO, "Clicking on Add button.....");
		newCampaignFormPage.clickOnAddButton_InputChannel();

		Thread.sleep(2000);
		
		test.log(Status.INFO, "Selecting Api Channels from dropdown.....");
		newCampaignFormPage.selectApiChannel();

		test.log(Status.INFO, "Clicking on Save button.....");
		newCampaignFormPage.clickOnSaveButton_InputChannel();

		// SMS Shortcodes.....................................

		String message = property.getProperty("message_SmsShortCode");
		String source = property.getProperty("source_SmsShortCode");

		test.log(Status.INFO, "Clicking on SMS Shortcodes Tab.....");
		newCampaignFormPage.clickOnSmsShortcodesTab();

		test.log(Status.INFO, "Accepting an alert popup");
		newCampaignFormPage.clickAlertPopup();

		test.log(Status.INFO, "Selecting SMS Shortcodes from dropdown.....");
		newCampaignFormPage.selectSmsShortcode_SmsShortcode();

		// Entering message for SMS
		test.log(Status.INFO, "Entering message to send the SMS............");
		newCampaignFormPage.enterMessage(message);

		// Selecting Project
		/*test.log(Status.INFO, "Selecting Project for Sms ShortCode...........");
		newCampaignFormPage.selectProject_SmsShortCode(); */
		Thread.sleep(3000);
		// Clicking Save button
		test.log(Status.INFO, "Clicking on Save button.....");
		newCampaignFormPage.clickOnSaveButton_SmsShortcode();
		Thread.sleep(3000);
		jse.executeScript("window.scrollBy(0,300)", "");

		test.log(Status.INFO, "Clicking on Next button.....");
		newCampaignFormPage.clickOnNextButton_SmsShortcode();

		Thread.sleep(3000);
		
		campaignName = driver
				.findElement(By.xpath("//li[@class=\"breadcrumb-item active\"]")).getText();
		
		test.log(Status.INFO, "Clicking on Finish button.....");
		newCampaignFormPage.clickOnFinishButton();		
		
		Thread.sleep(3000);
		
		test.log(Status.INFO, "Capturing Campaign name appeared at the top of the list......");
		//String campaignName_List = driver
			//	.findElement(By.xpath("//*[@id=\"campaign_form_container\"]/div[2]/div/ul[1]/li/span")).getText();
		
		String campaignName_List = driver
				.findElement(By.cssSelector("div.page-content-wrapper:nth-child(8) div.container-fluid div.row:nth-child(3) div.col-lg-12 div.page-container.index-page-container div.table-filter-container.pt-2 div.row div.col-lg-12.index-table.pl-0.pr-0 table.table.table-responsive tbody:nth-child(2) tr:nth-child(1) td:nth-child(1) > span.td-bold")).getText();
			
		System.out.println(campaignName);
		System.out.println(campaignName_List);
		
		SoftAssert assertion = new SoftAssert();

		test.log(Status.INFO, "Validating Campaign name appears at the top of the list after being added.....");
		Assert.assertEquals(campaignName_List, campaignName, "Not matched");
		System.out.println("Completed verification");

		assertion.assertAll();
		
		/*  jse.executeScript("window.scrollBy(0,600)", "");

		test.log(Status.INFO, "Clicking on Finish button.....");
		newCampaignFormPage.clickOnFinishButton(); */
		//assertion.assertAll();
		
	}
}
