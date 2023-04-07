package userManagementPack;

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
import adminPages.ManageUsersPage;
import adminPages.SettingsPage;
import adminPages.UserManagementPage;
import crm.selldo.ClientLoginPage;
import crm.selldo.LeadProfilePage;
import crm.selldo.LoginPage;
import crm.selldo.SalesPresalesDashboardPage;
import utility.SetUp;

public class SearchingUserTest extends SetUp {

	final static Logger logger = Logger.getLogger(SearchingUserTest.class);

	// Description: Searching existing users by name, email, primary and
	// secondary phone number

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

	@Test

	public void searchingUserTest() throws Exception {

		test = extent.createTest("searchingUserTest");
		setExtentTest(test);

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		Thread.sleep(3000);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		UserManagementPage userManagementPage = new UserManagementPage(driver);

		SalesPresalesDashboardPage salesPresalesDashboardPage = new SalesPresalesDashboardPage(driver);

		getExtTest().log(Status.INFO, "Signing in as a sales user to which lead is to be pulled.......");
		adminDashboardPage.loginAsUser(property.getProperty("signInAs_user_searchingUserTest"));

		getExtTest().log(Status.INFO, "Opening edit profile form of user....");
		salesPresalesDashboardPage.editUserProfile();

		getExtTest().log(Status.INFO, "Capturing first name of user from its profile....");
		String firstName = driver.findElement(By.xpath("//input[@id='user_first_name']")).getAttribute("value");
		System.out.println(firstName);

		getExtTest().log(Status.INFO, "Capturing last name of user from its profile....");
		String lastName = driver.findElement(By.xpath("//input[@id='user_last_name']")).getAttribute("value");
		System.out.println(lastName);

		String userNameToBeSearched = firstName + " " + lastName;

		getExtTest().log(Status.INFO, "Capturing phone number of user from its profile....");
		String phoneNumber = driver
				.findElement(By.xpath("//input[@class='phone_number form-control user_primary_phone']"))
				.getAttribute("value");
		System.out.println(phoneNumber);
		System.out.println("Length Of leadId -> " + phoneNumber.length());
		String phoneNumberToBeSearched = phoneNumber.substring(4, +phoneNumber.length()).replaceAll("\\s+", "");
		System.out.println("Retrieving sub string from string -> " + phoneNumberToBeSearched);

		getExtTest().log(Status.INFO, "Capturing email of user from its profile....");
		String email = driver.findElement(By.xpath("//input[@id='user_email']")).getAttribute("value");
		System.out.println(email);

		getExtTest().log(Status.INFO, "Capturing secondary phone number of user from its profile....");
		String secondaryPhoneNumber = driver.findElement(By.xpath("//input[@name='secondary_phone_number']"))
				.getAttribute("value");
		System.out.println(secondaryPhoneNumber);
		System.out.println("Length Of leadId -> " + secondaryPhoneNumber.length());
		String secondaryPhoneNumberToBeSearched = secondaryPhoneNumber.substring(4, +secondaryPhoneNumber.length())
				.replaceAll("\\s+", "");
		System.out.println("Retrieving sub string from string -> " + secondaryPhoneNumberToBeSearched);

		getExtTest().log(Status.INFO, "Closing edit user profile form......");
		salesPresalesDashboardPage.closeEditProfile();

		LeadProfilePage leadProfilePage = new LeadProfilePage(driver);

		getExtTest().log(Status.INFO, "Going back to Admin page.......");
		leadProfilePage.backToAdmin();

		getExtTest().log(Status.INFO, "Clicking on Setting Icon.......");
		adminDashboardPage.clickOnSettingIcon();

		SettingsPage settingsPage = new SettingsPage(driver);

		getExtTest().log(Status.INFO, "Clicking on User Management tab.......");
		settingsPage.clickOnUserManagementTab();

		getExtTest().log(Status.INFO, "Clicking on Manage Users tab.......");
		userManagementPage.clickOnManageUsers();

		ManageUsersPage manageUsersPage = new ManageUsersPage(driver);

		getExtTest().log(Status.INFO, "Searching user by name.......");
		manageUsersPage.searchUser(userNameToBeSearched);

		SoftAssert assertion = new SoftAssert();

		getExtTest().log(Status.INFO, "Validating user name.......");
		Assert.assertEquals(
				driver.findElement(By.xpath("//th[text()='name']/following::span[1]")).getAttribute("innerHTML"),
				userNameToBeSearched, "Not matched");

		getExtTest().log(Status.INFO, "Resetting the filter.......");
		manageUsersPage.resettingFilter();

		getExtTest().log(Status.INFO, "Searching user by Phone number.......");
		manageUsersPage.searchUser(phoneNumberToBeSearched);

		getExtTest().log(Status.INFO, "Validating user name.......");
		Assert.assertEquals(
				driver.findElement(By.xpath("//th[text()='name']/following::span[1]")).getAttribute("innerHTML"),
				userNameToBeSearched, "Not matched");

		getExtTest().log(Status.INFO, "Resetting the filter.......");
		manageUsersPage.resettingFilter();

		getExtTest().log(Status.INFO, "Searching user by email.......");
		manageUsersPage.searchUser(email);

		getExtTest().log(Status.INFO, "Validating user name.......");
		Assert.assertEquals(
				driver.findElement(By.xpath("//th[text()='name']/following::span[1]")).getAttribute("innerHTML"),
				userNameToBeSearched, "Not matched");

		getExtTest().log(Status.INFO, "Resetting the filter.......");
		manageUsersPage.resettingFilter();

		getExtTest().log(Status.INFO, "Searching user by secondary phone number.......");
		manageUsersPage.searchUser(secondaryPhoneNumberToBeSearched);

		getExtTest().log(Status.INFO, "Validating user name.......");
		Assert.assertEquals(
				driver.findElement(By.xpath("//th[text()='name']/following::span[1]")).getAttribute("innerHTML"),
				userNameToBeSearched, "Not matched");

		assertion.assertAll();
	}
}
