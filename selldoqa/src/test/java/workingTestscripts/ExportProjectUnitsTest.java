
package workingTestscripts;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
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
import adminPages.SettingsPage;
import crm.selldo.ClientLoginPage;
import crm.selldo.LoginPage;
import utility.SetUp;

public class ExportProjectUnitsTest extends SetUp {

	final static Logger logger = Logger.getLogger(ExportProjectUnitsTest.class);

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

		Thread.sleep(3000);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		logger.info("Logging out of Selldo......");
		adminDashboardPage.loggingOut();

		logger.info("Closing Browser......");
		driver.close();
	}

	@Test

	public void exportProjectUnitsTest() throws Exception {

		test = extent.createTest("exportProjectUnitsTest");
		setExtentTest(test);

		WebDriverWait wait = new WebDriverWait(driver, 10);

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		SettingsPage settingsPage = new SettingsPage(driver);

		ExportPage exportPage = new ExportPage(driver);

		SoftAssert assertion = new SoftAssert();

		getExtTest().log(Status.INFO, "Clicking on Export tab.......");
		settingsPage.clickOnExportab();

		getExtTest().log(Status.INFO, "Clicking on Export ProjectUnits tab.......");
		exportPage.clickOnExportProjectUnitsTab();

		getExtTest().log(Status.INFO, "Selecting Project......");
		exportPage.selectProject();

		String emailToWhichExported = property.getProperty("export_email");

		getExtTest().log(Status.INFO, "Entering Email.......");
		exportPage.enterEmail(emailToWhichExported);
		
		getExtTest().log(Status.INFO, "Clicking on Next Button.......");
		exportPage.clickOnNextButton();
		
		Thread.sleep(2000);

		getExtTest().log(Status.INFO, "Clicking on Export Button.......");
		exportPage.clickOnExportButton();

		Thread.sleep(2000);

		exportPage.clickOnExportCrossIcon();

		Thread.sleep(2000);

		exportPage.scrollToBottom();

		getExtTest().log(Status.INFO, "Clicking on Export History tab.......");
		exportPage.clickOnExportHistoryTab();
		
		Thread.sleep(2000);

		getExtTest().log(Status.INFO, "Clicking on Funnel icon.......");
		exportPage.clickOnFunnelIcon();

		getExtTest().log(Status.INFO, "Selecting Created at date range.......");
		exportPage.selectCreatedAtDateRange();

		getExtTest().log(Status.INFO, "Selecting Export type as Project Units.......");
		exportPage.selectExportType_ExportProjectUnits();

		getExtTest().log(Status.INFO, "Clicking on Apply button.......");
		exportPage.clickOnApplyButton();

		Thread.sleep(1000);

		getExtTest().log(Status.INFO, "Capturing text of the export at top left corner of list.......");
		String ProjectUnit = driver.findElements(exportPage.exportLabel).get(0).getAttribute("innerHTML");
		System.out.println(ProjectUnit);

//		getExtTest().log(Status.INFO, "Validating that the latest export is made for ProjectUnits.......");
//		Assert.assertEquals(ProjectUnit, "Project Units - Export 1", "Not matched");

		wait.until(ExpectedConditions.visibilityOfElementLocated(exportPage.exportStatus));
		String exportStatus = driver.findElement(exportPage.exportStatus).getText();

		getExtTest().log(Status.INFO, "Validating that export completed successfully.......");
		AssertJUnit.assertEquals(exportStatus, "working", "Export is completed successfully");

		getExtTest().log(Status.INFO, "Capturing email under the export details to which export is made.......");
		String emailInHistory = driver.findElement(exportPage.emailIdField).getAttribute("innerHTML").trim();
		System.out.println(emailInHistory);

		getExtTest().log(Status.INFO,
				"Validating that the email to which export is made is same as what shown in history.......");
		AssertJUnit.assertEquals(emailInHistory, emailToWhichExported, "Not matched");

		assertion.assertAll();
	}

}
