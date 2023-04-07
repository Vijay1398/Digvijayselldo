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

public class RoiConfigurationTest extends SetUp {

	final static Logger logger = Logger.getLogger(RoiConfigurationTest.class);

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

	@AfterTest

	public void endingTest() throws Exception {

		Thread.sleep(3000);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		logger.info("Logging out of Selldo......");
		adminDashboardPage.loggingOut();

		logger.info("Closing Browser......");
		driver.close();
	}

	@Test(priority = 1)

	public void addingNewRoiConfigurationTest() throws Exception {

		test = extent.createTest("addingNewRoiConfigurationTest");
		setExtentTest(test);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		AutomationDashboardPage automationDashboardPage = new AutomationDashboardPage(driver);

		AllCampaignPage allCampaignPage = new AllCampaignPage(driver);

		SoftAssert assertion = new SoftAssert();

		test.log(Status.INFO, "Clicking on wand Icon.......");
		adminDashboardPage.clickOnWandIcon();

		test.log(Status.INFO, "Clicking on Campaign SetUp tab.......");
		automationDashboardPage.cickOnCampaignSetUp();

		test.log(Status.INFO, "Capturing Campaign name for which ROI configuration is to be added......");
		String campaignName = driver.findElement(By.xpath("//th[text()='name']/following::span[1]"))
				.getAttribute("innerHTML");
		System.out.println("Campaign name-" + campaignName);

		test.log(Status.INFO, "Verifying that selected campaign is active......");
		Assert.assertEquals(
				driver.findElement(By.xpath("//th[text()='name']/following::span[3]")).getAttribute("innerHTML"),
				"Active", "Not matched");

		test.log(Status.INFO, "Selecting ROI Config from Action bar.......");
		allCampaignPage.selectRoiConfigution();

		Thread.sleep(2000);
		
		test.log(Status.INFO, "Clicking on Add New Config Button.......");
		allCampaignPage.clickOnAddNewConfigButton();

		Thread.sleep(2000);

		test.log(Status.INFO, "Selecting Source.......");
		allCampaignPage.selectSource();

		test.log(Status.INFO, "Selecting Sub source.......");
		allCampaignPage.selectSubSource();

		allCampaignPage.enterExpectedCpl("500000");

		allCampaignPage.enterAmountSpent("400000");

		test.log(Status.INFO, "Selecting Start date.......");
		allCampaignPage.selectStartDate();

		test.log(Status.INFO, "Selecting End date.......");
		allCampaignPage.selectEndDate();

		test.log(Status.INFO, "Clicking on Save button.......");
		allCampaignPage.clickOnSaveButton();

		assertion.assertAll();
	}

	@Test(priority = 2)

	public void editingRoiConfigurationTest() throws Exception {

		test = extent.createTest("editingRoiConfigurationTest");

		AllCampaignPage allCampaignPage = new AllCampaignPage(driver);

		SoftAssert assertion = new SoftAssert();

		Thread.sleep(2000);

		test.log(Status.INFO, "Selecting Edit from Action bar.......");
		allCampaignPage.selectEdit_NewRoiConfig();

		test.log(Status.INFO, "Changing Expected CPL.......");
		allCampaignPage.changeExpectedCpl("700000");

		test.log(Status.INFO, "Changing Amount Spent.......");
		allCampaignPage.changeAmountSpent("600000");

		test.log(Status.INFO, "Changing Start date.......");
		allCampaignPage.changeStartDate();

		test.log(Status.INFO, "Changing End date.......");
		allCampaignPage.changeEndDate();

		test.log(Status.INFO, "Clicking on Save button.......");
		allCampaignPage.saveEditConfig();

		assertion.assertAll();
	}

	@Test(priority = 3)

	public void deletingRoiConfigurationTest() throws Exception {

		test = extent.createTest("deletingRoiConfigurationTest");

		AllCampaignPage allCampaignPage = new AllCampaignPage(driver);

		SoftAssert assertion = new SoftAssert();

		Thread.sleep(2000);

		test.log(Status.INFO, "Selecting Delete from Action bar.......");
		allCampaignPage.selectDelete_NewRoiConfig();

		test.log(Status.INFO, "Clicking on Ok from popup window.......");
		driver.switchTo().alert().accept();

		assertion.assertAll();
	}

}
