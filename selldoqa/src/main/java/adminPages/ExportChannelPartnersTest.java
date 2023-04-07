package adminPages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import crm.selldo.ClientLoginPage;
import crm.selldo.LoginPage;
import utility.SetUp;

public class ExportChannelPartnersTest extends SetUp {

	final static Logger logger = Logger.getLogger(ExportChannelPartnersTest.class);

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

	public void exportChannelPartnersTest() throws Exception {

		test = extent.createTest("exportChannelPartnersTest");

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		SettingsPage settingsPage = new SettingsPage(driver);

		ExportPage exportPage = new ExportPage(driver);

		test.log(Status.INFO, "Clicking on Export tab.......");
		settingsPage.clickOnExportab();

		test.log(Status.INFO, "Clicking on Export Channel Partners tab.......");
		exportPage.clickOnExportBookingsTab();

		test.log(Status.INFO, "Selecting duration.......");
		exportPage.selectDuration();

		test.log(Status.INFO, "Entering Email.......");
		exportPage.enterEmail("ferhat@amuratech.com");

		test.log(Status.INFO, "Clicking on Export Button.......");
		exportPage.clickOnExportButton();

		Thread.sleep(4000);

		test.log(Status.INFO, "Clicking on Export History tab.......");
		exportPage.clickOnExportHistoryTab();

		test.log(Status.INFO, "Clicking on Funnel icon.......");
		exportPage.clickOnFunnelIcon();

		test.log(Status.INFO, "Selecting Created at date range.......");
		exportPage.selectCreatedAtDateRange();

		test.log(Status.INFO, "Clicking on Apply button.......");
		exportPage.clickOnApplyButton();
	}

}
