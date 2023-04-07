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

public class DeactivatingAndReactivatingCampaignTest extends SetUp {

	final static Logger logger = Logger.getLogger(DeactivatingAndReactivatingCampaignTest.class);

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

	@Test(priority = 1)

	public void deactivatingCampaignTest() throws Exception {

		test = extent.createTest("Deactivating Campaign Test");
		setExtentTest(test);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		AutomationDashboardPage automationDashboardPage = new AutomationDashboardPage(driver);

		AllCampaignPage allCampaignPage = new AllCampaignPage(driver);

		SoftAssert assertion = new SoftAssert();

		test.log(Status.INFO, "Clicking on wand Icon.......");
		adminDashboardPage.clickOnWandIcon();

		test.log(Status.INFO, "Clicking on Campaign SetUp tab.......");
		automationDashboardPage.cickOnCampaignSetUp();

		test.log(Status.INFO, "Capturing Campaign name to be deactivated......");
		String campaignName = driver.findElement(By.xpath("//th[text()='name']/following::span[1]"))
				.getAttribute("innerHTML");
		System.out.println("Campaign name-" + campaignName);

		test.log(Status.INFO, "Verifying that selected campaign is active......");
		String status = driver.findElement(By.xpath("//th[text()='name']/following::span[3]")).getAttribute("innerHTML");
		System.out.println("Status is-" + status);
		Assert.assertEquals(status,"Active");

		// To Deactivate campaign

		test.log(Status.INFO, "Selecting Deactivate Campaign from Action bar.......");
		allCampaignPage.deactivateCampaign();

		test.log(Status.INFO, "Clicking on Funnel Icon......");
		allCampaignPage.clickOnFunnelIcon();

		test.log(Status.INFO, "Entering Campaign name to be searched......");
		allCampaignPage.enterCampaignName(campaignName);

		test.log(Status.INFO, "Clicking on Apply Button......");
		allCampaignPage.clickOnApplyButton();

		test.log(Status.INFO, "Capturing Campaign name after search......");
		String campaignNameAfterSearch = driver.findElement(By.xpath("//th[text()='name']/following::span[1]"))
				.getAttribute("innerHTML");
		System.out.println("Campaign name after search-" + campaignNameAfterSearch);

		test.log(Status.INFO, "Validating Campaign name after search......");
		Assert.assertEquals(campaignName,campaignNameAfterSearch,"Not matched");

		test.log(Status.INFO, "Verifying that selected campaign is Inactive.....");
		Assert.assertEquals(
				driver.findElement(By.xpath("//th[text()='name']/following::span[3]")).getAttribute("innerHTML"),
				"Inactive", "Not matched");

		// To Reactivate campaign

		test.log(Status.INFO, "Selecting Reactivate Campaign from Action bar.......");
		allCampaignPage.reactivateCampaign();

		test.log(Status.INFO, "Clicking on Funnel Icon......");
		allCampaignPage.clickOnFunnelIcon();

		test.log(Status.INFO, "Entering Campaign name to be searched......");
		allCampaignPage.enterCampaignName(campaignName);

		test.log(Status.INFO, "Clicking on Apply Button......");
		allCampaignPage.clickOnApplyButton();

		test.log(Status.INFO, "Capturing Campaign name after search......");
		String activeCampaignNameAfterSearch = driver.findElement(By.xpath("//th[text()='name']/following::span[1]"))
				.getAttribute("innerHTML");
		System.out.println("Active campaign name after search-" + activeCampaignNameAfterSearch);

		test.log(Status.INFO, "Validating Campaign name after search......");
		Assert.assertEquals(activeCampaignNameAfterSearch, campaignName, "Not matched");

		test.log(Status.INFO, "Verifying that selected campaign is Active.....");
		Assert.assertEquals(
				driver.findElement(By.xpath("//th[text()='name']/following::span[3]")).getAttribute("innerHTML"),
				"Active", "Not matched");

		assertion.assertAll();

	}

}
