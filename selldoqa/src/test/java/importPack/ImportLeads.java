package importPack;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import adminPages.AdminDashboardPage;
import adminPages.ExportPage;
import adminPages.ImportPage;
import adminPages.SettingsPage;
import crm.selldo.ClientLoginPage;
import crm.selldo.LoginPage;
import exportPackage.ExportBookingsTest;
import utility.SetUp;

public class ImportLeads extends SetUp {

	final static Logger logger = Logger.getLogger(ExportBookingsTest.class);

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

		logger.info("Logging in to Support page.......");
		login.login(property.getProperty("name") + "+" + property.getProperty("support_email"),
				property.getProperty("password"));

		ClientLoginPage clientLogin = new ClientLoginPage(driver);

		logger.info("Logging in to Admin/Support Home Page......");
		clientLogin.clientLogin(property.getProperty("test_client_name"));

	}

	// @AfterTest

	public void endingTest() throws Exception {

		Thread.sleep(3000);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		logger.info("Logging out of Selldo......");
		adminDashboardPage.loggingOut();

		logger.info("Closing Browser......");
		driver.close();
	}

	@Test

	public void exportLeadsTest() throws Exception {

		test = extent.createTest("importLeadsTest");
		setExtentTest(test);

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		SettingsPage settingsPage = new SettingsPage(driver);

		ImportPage importPage = new ImportPage(driver);

		getExtTest().log(Status.INFO, "Clicking on Settings Icon.......");
		adminDashboardPage.clickOnSettingIcon();

		SoftAssert assertion = new SoftAssert();

		getExtTest().log(Status.INFO, "Clicking on Import tab.......");
		settingsPage.clickOnImportTab();

		getExtTest().log(Status.INFO, "Clicking on Import Leads tab.......");
		importPage.clickOnImportLeadsTab();

		getExtTest().log(Status.INFO, "Clicking on New Upload button.......");
		importPage.clickOnNewUploadButton();

		getExtTest().log(Status.INFO, "Clicking on Upload button.......");
		importPage.clickOnUploadButton();

		getExtTest().log(Status.INFO, "selecting campaign.......");
		importPage.selectCampaign();

		String emailId = property.getProperty("import_email");
		importPage.enterEmail(emailId);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

		getExtTest().log(Status.INFO, "Selecting fields under Bulk parsed file section......");
		importPage.enterName();
		importPage.enterLeadEmail();
		importPage.enterSalesId();
		importPage.enterStage();

		getExtTest().log(Status.INFO, "Clicking on Continue button......");
		importPage.clickOnContinueButton();

		String expectedMessage = property.getProperty("success_message");
		getExtTest().log(Status.INFO, "Validating Good to proceed message......");
		Assert.assertEquals(driver.findElement(importPage.sucessMessage).getText(), expectedMessage, "Not matched");

		getExtTest().log(Status.INFO, "Clicking on Continue button......");
		importPage.clickOnContinue();

		getExtTest().log(Status.INFO, "Accepting javascript alert......");
		Alert alert = driver.switchTo().alert();
		alert.accept();

		importPage.pageRefresh();

		String expectedStatus = property.getProperty("import_status");
		getExtTest().log(Status.INFO, "Validating import status......");
		Assert.assertEquals(driver.findElement(importPage.importStatus).getText(), expectedStatus, "Not matched");

		String expectedUserName = property.getProperty("userName");
		getExtTest().log(Status.INFO, "Validating Assigned to user name......");
		Assert.assertEquals(driver.findElement(importPage.assignedTo).getText(), expectedUserName, "Not matched");

		String uploadData = driver.findElement(importPage.leadUploadCount).getText();
		System.out.println(uploadData);

		assertion.assertAll();
	}

}
