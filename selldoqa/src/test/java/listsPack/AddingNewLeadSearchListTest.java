package listsPack;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import adminPages.AdminDashboardPage;
import adminPages.SearchListPage;
import adminPages.SettingsPage;
import crm.selldo.ClientLoginPage;
import crm.selldo.LoginPage;
import utility.SetUp;

public class AddingNewLeadSearchListTest extends SetUp {

	final static Logger logger = Logger.getLogger(AddingNewLeadSearchListTest.class);

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

		logger.info("Closing Browser......");
		driver.close();
	}

	@Test

	public void addingNewLeadSearchListTest() throws Exception {

		test = extent.createTest("addingNewLeadSearchListTest");

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		SettingsPage settingsPage = new SettingsPage(driver);

		SearchListPage searchListPage = new SearchListPage(driver);

		SoftAssert assertion = new SoftAssert();

		test.log(Status.INFO, "Clicking on Search List tab...........");
		settingsPage.clickOnSearchListTab();

		test.log(Status.INFO, "Clicking on New List button.........");
		searchListPage.clickOnNewListButton();

		String listName = property.getProperty("listName_AddingNewLeadSearchListTest").toLowerCase();

		test.log(Status.INFO, "Entering List name....................");
		searchListPage.enterListName(listName);

		test.log(Status.INFO, "Selecting Scheduled Activity..........");
		searchListPage.selectScheduledActivity();

		test.log(Status.INFO, "Selecting Order......");
		searchListPage.selectOrder();

		test.log(Status.INFO, "Selecting Scheduled Activity range....");
		searchListPage.selectScheduledActivityRange();

		test.log(Status.INFO, "Selecting Purpose.....................");
		searchListPage.selectPurpose();

		test.log(Status.INFO, "Clicking on Save button...............");
		searchListPage.clickOnSaveButton();

		Thread.sleep(2000);

		test.log(Status.INFO, "Validating lead search list name after adding new lead search list.....");
		System.out.println("Started verification");
		Assert.assertEquals(
				driver.findElement(By.xpath("//th[text()='name']/following::span[1]")).getText().toLowerCase(),
				listName, "Not matched");
		System.out.println("Completed verification");
		assertion.assertAll();
		
		Thread.sleep(3000);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		logger.info("Logging out of Selldo......");
		adminDashboardPage.loggingOut();
	}
}