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
import crm.selldo.EditUserFormPage;
import crm.selldo.LoginPage;
import utility.GetTestData;
import utility.SetUp;

public class EditingUserTest extends SetUp {

	final static Logger logger = Logger.getLogger(EditingUserTest.class);

	// Description: Editing a user

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

	public void editingUserTest() throws Exception {

		test = extent.createTest("Editing User Test");
		setExtentTest(test);

		GetTestData getTestData = new GetTestData();

		Thread.sleep(3000);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		getExtTest().log(Status.INFO, "Clicking on Setting Icon.......");
		adminDashboardPage.clickOnSettingIcon();

		SettingsPage settingsPage = new SettingsPage(driver);

		getExtTest().log(Status.INFO, "Clicking on User Management tab.......");
		settingsPage.clickOnUserManagementTab();

		UserManagementPage userManagementPage = new UserManagementPage(driver);

		getExtTest().log(Status.INFO, "Clicking on Manage Users tab.......");
		userManagementPage.clickOnManageUsers();

		ManageUsersPage manageUsersPage = new ManageUsersPage(driver);

		String phoneObj = "+91" + " " + getTestData.phoneNumber;
		String phone_SearchObj = phoneObj;

		getExtTest().log(Status.INFO, "Selecting Edit from dropdown......");
		manageUsersPage.selectEdit();
		Thread.sleep(3000);

		EditUserFormPage editUserForm = new EditUserFormPage(driver);

		getExtTest().log(Status.INFO, "Changing First name........");
		String firstNameObj = getTestData.firstName;
		editUserForm.changeFirstName(firstNameObj);

		getExtTest().log(Status.INFO, "Changing Last Name........");
		String lastNameObj = getTestData.lastName;
		editUserForm.changeLastName(lastNameObj);

		getExtTest().log(Status.INFO, "Changing Phone number........");
		editUserForm.changePhone(phoneObj);

		Thread.sleep(3000);

		String emailId = getTestData.email;
		getExtTest().log(Status.INFO, "Changing Email Id.	.......");
		editUserForm.changeEmail(emailId);

		getExtTest().log(Status.INFO, "Clicking on Save Button.......");
		editUserForm.clickOnSaveButton();

		Thread.sleep(4000);

		getExtTest().log(Status.INFO, "Searching user by Email Id.......");
		manageUsersPage.searchUser(emailId);

		String adminNameObj = firstNameObj + " " + lastNameObj;

		SoftAssert assertion = new SoftAssert();

		getExtTest().log(Status.INFO, "Validating user name.......");
		Assert.assertEquals(
				driver.findElement(By.xpath("//th[text()='name']/following::span[1]")).getAttribute("innerHTML"),
				adminNameObj, "Not matched");

		assertion.assertAll();
	}
}
