package campaignSetupPack;

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

import adminPages.AdminDashboardPage;
import adminPages.AllCampaignPage;
import adminPages.AutomationDashboardPage;
import crm.selldo.ClientLoginPage;
import crm.selldo.LoginPage;
import utility.SetUp;

public class SearchingCampaignByNameTest extends SetUp {

	final static Logger logger = Logger.getLogger(SearchingCampaignByNameTest.class);

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

	public void searchingCampaignByNameTest() throws Exception {

		test = extent.createTest("searchingCampaignByNameTest");
		setExtentTest(test);

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		AutomationDashboardPage automationDashboardPage = new AutomationDashboardPage(driver);

		AllCampaignPage allCampaignPage = new AllCampaignPage(driver);
		
		test.log(Status.INFO, "Clicking on wand Icon.......");
		adminDashboardPage.clickOnWandIcon();

		test.log(Status.INFO, "Clicking on Campaign SetUp tab.......");
		automationDashboardPage.cickOnCampaignSetUp();

		test.log(Status.INFO, "Capturing Campaign name to be searched......");
		String campaignNameToBeSearched = driver.findElement(By.cssSelector("table > tbody > tr:nth-child(1) > td:nth-child(1) > span"))
				.getAttribute("innerHTML");
		System.out.println("Campaign name to be searched-" + campaignNameToBeSearched);

		test.log(Status.INFO, "Clicking on Funnel Icon......");
		allCampaignPage.clickOnFunnelIcon();

		test.log(Status.INFO, "Entering Campaign name to be searched......");
		allCampaignPage.enterCampaignName(campaignNameToBeSearched);

		test.log(Status.INFO, "Clicking on Apply Button......");
		allCampaignPage.clickOnApplyButton();

		test.log(Status.INFO, "Capturing Campaign name appeared at the top of the list......");
		String campaignNameAfterSearch = driver.findElement(By.cssSelector("table > tbody > tr:nth-child(1) > td:nth-child(1) > span"))
				.getAttribute("innerHTML");
		System.out.println("Campaign name after search-" + campaignNameAfterSearch);

		SoftAssert assertion = new SoftAssert();
		System.out.println("Started verification");
		Assert.assertEquals(campaignNameToBeSearched, campaignNameAfterSearch, "Not matched");
		System.out.println("Completed verification");
		assertion.assertAll();

	}

}
