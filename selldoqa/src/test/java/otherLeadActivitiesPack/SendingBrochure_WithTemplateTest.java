package otherLeadActivitiesPack;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import adminPages.AdminDashboardPage;
import crm.selldo.BrochurePage;
import crm.selldo.EmailPage;
import crm.selldo.LeadProfilePage;
import crm.selldo.LoginPage;
import crm.selldo.SalesPresalesDashboardPage;
import utility.SetUp;

public class SendingBrochure_WithTemplateTest extends SetUp {

	WebDriverWait wait;

	final static Logger logger = Logger.getLogger(SendingBrochure_WithTemplateTest.class);

	@BeforeTest

	public void appLogin() throws Exception {

		mysetUp();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		LoginPage login = new LoginPage(driver);
		logger.info("Logging in.......");
		login.login(
				property.getProperty("name") + "+"
						+ property.getProperty("user_email_sendingBrochure_WithTemplateTest"),
				property.getProperty("password"));

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

	public void sendingBrochure_WithTemplateTest() throws Exception {

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		test = extent.createTest("sendingBrochure_WithTemplateTest");
		setExtentTest(test);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		test.log(Status.INFO, "Searching lead by Id.......");
		adminDashboardPage.searchLead(property.getProperty("BrochureTest_lead_id"));

		LeadProfilePage leadProfilePage = new LeadProfilePage(driver);

		test.log(Status.INFO, "Clicking on Email Link.......");
		leadProfilePage.clickOnEmailLink();

		Thread.sleep(1000);

		EmailPage emailPage = new EmailPage(driver);

		test.log(Status.INFO, "Select brochure from dropdown.......");
		emailPage.selectBrochureOption();

		BrochurePage brochurePage = new BrochurePage(driver);

		test.log(Status.INFO, "Selecting Project from dropdown.......");
		brochurePage.selectProduct(property.getProperty("project_name_SendingBrochure_WithTemplateTest"));

		test.log(Status.INFO, "Selecting Template.......");
		brochurePage.selectTemplate(property.getProperty("template_name_SendingBrochure_WithTemplateTest"));

		test.log(Status.INFO, "Fetching  subject of selected template.......");
		String subjectText = property.getProperty("template_subject_SendingBrochure_WithTemplateTest");

		Thread.sleep(2000);

		test.log(Status.INFO, "Clicking on Send Brochure Button.......");
		brochurePage.clickOnSendBrochureButton();

		Thread.sleep(2000);

		test.log(Status.INFO, "Clicking on Email Link under Activities section.......");
		leadProfilePage.openEmailActivities();
		
		Thread.sleep(2000);

		test.log(Status.INFO, "Verifying that Brochure is sent.......");

		SoftAssert assertion = new SoftAssert();
		Assert.assertEquals(driver.findElement(By.cssSelector(
				"#tab-activity > div.activities_list > div:nth-child(1) > div > div.card > div > div:nth-child(1) > div.col-lg-11 > span"))
				.getText(), subjectText, "Not matched");
		assertion.assertAll();

	}

}
