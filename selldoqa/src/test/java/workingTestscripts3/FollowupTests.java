package workingTestscripts3;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import adminPages.AdminDashboardPage;
import crm.selldo.FollowupsPage;
import crm.selldo.LeadProfilePage;
import crm.selldo.LoginPage;
import crm.selldo.SalesPresalesDashboardPage;
import utility.SetUp;

public class FollowupTests extends SetUp {

	WebDriverWait wait;

	final static Logger logger = Logger.getLogger(FollowupTests.class);

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
				property.getProperty("name") + "+" + property.getProperty("user_email_sendingEmailWithCCandBCCTest"),
				property.getProperty("password"));
	}

	@Test(priority = 1)

	public void scheduleFollowup() throws Exception {
		wait = new WebDriverWait(driver, 8);
		Properties p = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		p.load(fileInputObj);

		test = extent.createTest("Schedule Followup Script");
		setExtentTest(test);

		SalesPresalesDashboardPage salesPresalesDashboard = new SalesPresalesDashboardPage(driver);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		getExtTest().log(Status.INFO, "Searching lead by Id.......");
		adminDashboardPage.searchLead(p.getProperty("Changing_SiteVisit_Status_lead_id"));

		WebElement Lead1 = driver.findElement(By.cssSelector("span[name='lead_id']"));
		String leadtext1 = Lead1.getText().replaceAll("\\s+", "");
		System.out.println(leadtext1);

		getExtTest().log(Status.INFO, "Scheduling Followup.......");
		LeadProfilePage leadProfilePage = new LeadProfilePage(driver);
		leadProfilePage.followupLink();

		FollowupsPage followupsPage = new FollowupsPage(driver);

		getExtTest().log(Status.INFO, "Selecting Date.......");
		followupsPage.selectDate();
		
		getExtTest().log(Status.INFO, "Adding Custom Field Data.......");
		followupsPage.selectDate();

		getExtTest().log(Status.INFO, "Clicking on Schedule Followup Button .......");
		followupsPage.clickOnScheduleFollowupButton();

		Thread.sleep(3000);

		if (!driver.findElements(By.xpath("//button[text()=' Ignore & Schedule ']")).isEmpty()) {

			getExtTest().log(Status.INFO, "Clicking on Ignore and schedule button.......");
			followupsPage.clickOnIgnoreAndSchedule();

		}

		getExtTest().log(Status.INFO, "Validatiton for Scheduled followup .......");
		Thread.sleep(5000);

		SoftAssert assertion = new SoftAssert();

		String expectedText = p.getProperty("scheduleExpectedText");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"#tab-activity > div.activities_list > div:nth-child(1) div.card div:nth-child(1) > div.col-lg-11")));
		Assert.assertTrue(driver.findElement(By.cssSelector(
				"#tab-activity > div.activities_list > div:nth-child(1) div.card div:nth-child(1) > div.col-lg-11"))
				.getText().trim().contains(expectedText), "Followup is not scheduled");

		// cancel followup

		salesPresalesDashboard.searchLead(leadtext1);
		Thread.sleep(2000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(leadProfilePage.followupLink));

		getExtTest().log(Status.INFO, "Cancelling Followup.......");
		leadProfilePage.followupLink();

		getExtTest().log(Status.INFO, "Selecting Cancelation reason.......");
		followupsPage.clickCancellationReasonDropdown();
		followupsPage.selectCancellationReason();

		getExtTest().log(Status.INFO, "Clicking on Cancel Followup Button .......");
		followupsPage.clickCancelFollowupButton();

		getExtTest().log(Status.INFO, "Validatiton for Cancelled follow up .......");
		Thread.sleep(5000);

		String expectedText1 = p.getProperty("cancelExpectedText");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"#tab-activity > div.activities_list > div:nth-child(1) div.card div:nth-child(1) > div.col-lg-11")));
		Assert.assertTrue(driver.findElement(By.cssSelector(
				"#tab-activity > div.activities_list > div:nth-child(1) div.card div:nth-child(1) > div.col-lg-11"))
				.getText().trim().contains(expectedText1), "Followup is not cancelled");

		assertion.assertAll();

	}

	@AfterTest

	public void endingTest() throws Exception {

		Thread.sleep(3000);

		logger.info("browser closed.......");
		driver.close();

	}

}
