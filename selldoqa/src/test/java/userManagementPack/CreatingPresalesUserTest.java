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
import adminPages.NewUserFormPage;
import adminPages.SettingsPage;
import adminPages.UserManagementPage;
import crm.selldo.ClientLoginPage;
import crm.selldo.LoginPage;
import utility.GetTestData;
import utility.SetUp;

public class CreatingPresalesUserTest extends SetUp {

	final static Logger logger = Logger.getLogger(CreatingPresalesUserTest.class);

	// Description: Creating a new Presales User

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

	public void creatingPresalesUserTest() throws Exception {

		test = extent.createTest("creatingPresalesUserTest");
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

		getExtTest().log(Status.INFO, "Clicking on New User Button......");
		manageUsersPage.clickOnNewUserButton();

		NewUserFormPage newUserFormPage = new NewUserFormPage(driver);

		getExtTest().log(Status.INFO, "Entering First Name........");
		String adminFirstNameObj = getTestData.firstName;
		newUserFormPage.enterFirstName(adminFirstNameObj);

		getExtTest().log(Status.INFO, "Entering Last Name........");
		String adminLastNameObj = getTestData.lastName;
		newUserFormPage.enterLastName(adminLastNameObj);

		getExtTest().log(Status.INFO, "Entering Phone number........");
		String phoneObj = " " + getTestData.phoneNumber;
		newUserFormPage.enterPhone(phoneObj);

		Thread.sleep(3000);

		String email = getTestData.email;
		getExtTest().log(Status.INFO, "Entering Email Id.	.......");
		newUserFormPage.enterEmail(email);

		getExtTest().log(Status.INFO, "Clicking on Professional Details Tab......");
		newUserFormPage.clickOnProfessionalDetailsTab();

		Thread.sleep(2000);

		getExtTest().log(Status.INFO, "Selecting Presales department.......");
		newUserFormPage.selectDepartment_Presales();

		getExtTest().log(Status.INFO, "Selecting Presales role.......");
		newUserFormPage.selectRole_Presales();

		getExtTest().log(Status.INFO, "Selecting Team from dropdown.......");
		newUserFormPage.selectTeam();

		getExtTest().log(Status.INFO, "Clicking on Save Button.......");
		newUserFormPage.clickOnSaveButton();

		Thread.sleep(6000);

		getExtTest().log(Status.INFO, "Searching user by Email Id.......");
		manageUsersPage.searchUser(email);

		String adminNameObj = adminFirstNameObj + " " + adminLastNameObj;

		SoftAssert assertion = new SoftAssert();

		getExtTest().log(Status.INFO, "Validating user name.......");
		Assert.assertEquals(
				driver.findElement(By.xpath("//th[text()='name']/following::span[1]")).getAttribute("innerHTML"),
				adminNameObj, "Not matched");

		getExtTest().log(Status.INFO, "Validating that status is unconfirmed.......");
		Assert.assertEquals(
				driver.findElement(By.xpath("//th[text()='name']/following::span[6]")).getAttribute("innerHTML"),
				"Unconfirmed", "Not matched");

		assertion.assertAll();
	}
}
