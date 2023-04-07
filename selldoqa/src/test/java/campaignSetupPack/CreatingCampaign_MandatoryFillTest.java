package campaignSetupPack;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
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

public class CreatingCampaign_MandatoryFillTest extends SetUp {

	final static Logger logger = Logger.getLogger(CreatingCampaign_MandatoryFillTest.class);

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
		clientLogin.clientLogin(property.getProperty("client_name_for_campaign"));

	}

	@Test

	public void creatingCampaign_MandatoryFillTest() throws Exception {

		test = extent.createTest("creatingCampaign_MandatoryFillTest");
		setExtentTest(test);

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		AutomationDashboardPage automationDashboardPage = new AutomationDashboardPage(driver);

		AllCampaignPage allCampaignPage = new AllCampaignPage(driver);

		NewCampaignFormPage newCampaignFormPage = new NewCampaignFormPage(driver);

		SoftAssert assertion = new SoftAssert();

		test.log(Status.INFO, "Clicking on wand Icon.......");
		adminDashboardPage.clickOnWandIcon();

		test.log(Status.INFO, "Clicking on Campaign SetUp tab.......");
		automationDashboardPage.cickOnCampaignSetUp();
		
		Thread.sleep(2000);

		test.log(Status.INFO, "Clicking on New Campaign button......");
		allCampaignPage.clickOnNewCampaignButton();

		String campaignName = property.getProperty("campaignName_creatingCampaign_MandatoryFillTest");

		test.log(Status.INFO, "Entering Campaign name......");
		newCampaignFormPage.enterCamapignName(campaignName);

		Thread.sleep(2000);

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");

		test.log(Status.INFO, "Selecting sales from dropdown.....");
		newCampaignFormPage.selectSales();

		test.log(Status.INFO, "Clicking on Save and Next button....");
		newCampaignFormPage.clickOnSaveAndNextButton();

		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
      
      test.log(Status.INFO, "Clicking on SMS Shortcodes Tab.....");
		newCampaignFormPage.clickOnSmsShortcodesTab();
		
				
		Thread.sleep(3000);
		jse.executeScript("window.scrollBy(0,300)", "");

		test.log(Status.INFO, "Clicking on Next button.....");
		newCampaignFormPage.clickOnNextButton_SmsShortcode();

		campaignName = driver
				.findElement(By.xpath("//li[@class=\"breadcrumb-item active\"]")).getText();
				
		
		test.log(Status.INFO, "Clicking on Finish button.....");
		newCampaignFormPage.clickOnFinishButton();	
		
		Thread.sleep(3000);
		

		test.log(Status.INFO, "Capturing Campaign name appeared at the top of the list......");
		String campaignName_List = driver
				.findElement(By.cssSelector("div.page-content-wrapper:nth-child(8) div.container-fluid div.row:nth-child(3) div.col-lg-12 div.page-container.index-page-container div.table-filter-container.pt-2 div.row div.col-lg-12.index-table.pl-0.pr-0 table.table.table-responsive tbody:nth-child(2) tr:nth-child(1) td:nth-child(1) > span.td-bold")).getText();
			
		
		System.out.println(campaignName);
		System.out.println(campaignName_List);

		test.log(Status.INFO, "Validating Campaign name appears at the top of the list after being added.....");
		Assert.assertEquals(campaignName_List, campaignName, "Not matched");

		assertion.assertAll();
	}
	
	

	
}
