package listsPack;

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
import adminPages.SearchListPage;
import adminPages.SettingsPage;
import crm.selldo.ClientLoginPage;
import crm.selldo.LoginPage;
import utility.SetUp;

public class EditingLeadSearchListTest extends SetUp {

	final static Logger logger = Logger.getLogger(EditingLeadSearchListTest.class);

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

	public void editingLeadSearchListTest() throws Exception {

		test = extent.createTest("editingLeadSearchListTest");

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		SettingsPage settingsPage = new SettingsPage(driver);

		SearchListPage SearchListPage = new SearchListPage(driver);

		SoftAssert assertion = new SoftAssert();

		test.log(Status.INFO, "Clicking on Search List tab...........");
		settingsPage.clickOnSearchListTab();

		test.log(Status.INFO, "Selecting Edit link from action bar...........");
		SearchListPage.selectEditLink();

		Thread.sleep(2000);

		String listName = property.getProperty("listName_EditingLeadSearchListTest");

		test.log(Status.INFO, "Changing List name....................");
		SearchListPage.changeListName(listName);

		test.log(Status.INFO, "Selecting Scheduled Activity..........");
		SearchListPage.changeScheduledActivity();

		//test.log(Status.INFO, "Selecting Purpose.....................");
		//SearchListPage.changePurpose();

		test.log(Status.INFO, "Clicking on Save button...............");
		SearchListPage.clickOnSaveButton();

		Thread.sleep(2000);

		test.log(Status.INFO, "Validating lead search list name after editing new lead search list.....");
		System.out.println("Started verification");

		driver.findElement(By.xpath("//th[text()='name']/following::span[1]")).getAttribute("innerHTML").contentEquals(listName);
		System.out.println("Completed verification");
		assertion.assertAll();

	}

}
