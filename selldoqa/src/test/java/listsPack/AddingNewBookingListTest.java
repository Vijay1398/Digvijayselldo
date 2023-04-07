package listsPack;

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
import adminPages.BookingListPage;
import adminPages.SettingsPage;
import crm.selldo.ClientLoginPage;
import crm.selldo.LoginPage;
import utility.SetUp;

public class AddingNewBookingListTest extends SetUp {

	final static Logger logger = Logger.getLogger(AddingNewBookingListTest.class);

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

		Thread.sleep(6000);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		logger.info("Logging out of Selldo......");
		adminDashboardPage.loggingOut();

		logger.info("Closing Browser......");
		driver.close();
	}

	@Test

	public void addingNewBookingListTest() throws Exception {

		test = extent.createTest("addingNewBookingListTest");

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		SettingsPage settingsPage = new SettingsPage(driver);

		BookingListPage bookingListPage = new BookingListPage(driver);

		SoftAssert assertion = new SoftAssert();

		test.log(Status.INFO, "Clicking on Search List tab...........");
		settingsPage.clickOnBookingListTab();

		test.log(Status.INFO, "Clicking on New Booking Search Criteria button.........");
		bookingListPage.clickOnNewBookingSearchCriteriaButton();

		String bookingListName = property.getProperty("listName_AddingNewBookingListTest");

		test.log(Status.INFO, "Entering List name....................");
		bookingListPage.enterBookingListName(bookingListName);

		test.log(Status.INFO, "Selecting Project..........");
		bookingListPage.selectProject();

		test.log(Status.INFO, "Selecting Campaign....");
		bookingListPage.selectCampaign();

		test.log(Status.INFO, "Selecting Publishers.....................");
		bookingListPage.selectPublishers();

		test.log(Status.INFO, "Selecting Team.....................");
		bookingListPage.selectTeams();
		
		test.log(Status.INFO, "Selecting Sales.....................");
		bookingListPage.selectSales();

		test.log(Status.INFO, "Clicking on Save button...............");
		bookingListPage.clickOnSaveButton();

		Thread.sleep(5000);

		test.log(Status.INFO, "Validating Booking list name after adding new Booking list.....");
		System.out.println("Started verification");
		Assert.assertEquals(
				driver.findElement(By.cssSelector("table tbody tr:nth-child(1) td:nth-child(1)")).getAttribute("innerHTML"),
				bookingListName, "Not matched");
		System.out.println("Completed verification");
		assertion.assertAll(); 
		
		Thread.sleep(7500);

	}

}
