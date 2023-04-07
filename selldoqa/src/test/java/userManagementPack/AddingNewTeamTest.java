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
import adminPages.ManageTeamsPage;
import adminPages.NewTeamFormPage;
import adminPages.SettingsPage;
import adminPages.UserManagementPage;
import crm.selldo.ClientLoginPage;
import crm.selldo.LoginPage;
import utility.GetTestData;
import utility.SetUp;

public class AddingNewTeamTest extends SetUp {

	final static Logger logger = Logger.getLogger(AddingNewTeamTest.class);

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

		Thread.sleep(5000);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		logger.info("Logging out of Selldo......");
		adminDashboardPage.loggingOut();

		logger.info("Closing Browser......");
		driver.close();
	}

	@Test

	public void addingNewTeamTest() throws Exception {

		test = extent.createTest("addingNewTeamTest");
		setExtentTest(test);

		GetTestData getTestData = new GetTestData();

		String teamName = getTestData.firstName;

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		getExtTest().log(Status.INFO, "Clicking on Setting Icon.......");
		adminDashboardPage.clickOnSettingIcon();

		SettingsPage settingsPage = new SettingsPage(driver);

		getExtTest().log(Status.INFO, "Clicking on User Management tab.......");
		settingsPage.clickOnUserManagementTab();

		UserManagementPage userManagementPage = new UserManagementPage(driver);

		getExtTest().log(Status.INFO, "Clicking on Manage Team tab.......");
		userManagementPage.clickOnManageTeam();

		ManageTeamsPage manageTeamPage = new ManageTeamsPage(driver);

		getExtTest().log(Status.INFO, "Clicking on Clicking on New Team Button.......");
		manageTeamPage.clickOnNewTeam();

		NewTeamFormPage newTeamFormPage = new NewTeamFormPage(driver);

		getExtTest().log(Status.INFO, "Entering Team Name.......");
		newTeamFormPage.enterTeamName(teamName);

		getExtTest().log(Status.INFO, "Entering Location.......");
		newTeamFormPage.enterLocation(getTestData.location);

		getExtTest().log(Status.INFO, "Selecting Team Hierarchy.......");
		newTeamFormPage.selectTeamHierarchy();

		getExtTest().log(Status.INFO, "Clicking on Save Button.......");
		newTeamFormPage.clickOnSaveButton();

		Thread.sleep(3000);

		getExtTest().log(Status.INFO, "Searching Team by name.......");
		manageTeamPage.searchTeam(teamName);

		SoftAssert assertion = new SoftAssert();

		getExtTest().log(Status.INFO, "Validating new Team creation.......");
		Assert.assertEquals(
				driver.findElement(By.xpath("//th[text()='name']/following::span[1]")).getAttribute("innerHTML"),
				teamName, "Not matched");

		assertion.assertAll();

	}

}
