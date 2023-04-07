package dashboardBucketsPack;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
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

public class TodaysFollowup_VerificationTest extends SetUp {

	WebDriverWait wait;

	final static Logger logger = Logger.getLogger(TodaysFollowup_VerificationTest.class);

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
				property.getProperty("name") + "+" + property.getProperty("user_email_todaysFollowup_VerificationTest"),
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

	public void todaysFollowup_VerificationTest() throws Exception {

		wait = new WebDriverWait(driver, 8);
		Properties p = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		p.load(fileInputObj);

		test = extent.createTest("todaysFollowup_VerificationTest");
		setExtentTest(test);

		SalesPresalesDashboardPage salesPresalesDashboard = new SalesPresalesDashboardPage(driver);
		
		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);
		
		getExtTest().log(Status.INFO, "Searching lead by Id.......");
		adminDashboardPage.searchLead(p.getProperty("TodaysFollowupTest_lead_id"));

		getExtTest().log(Status.INFO, "Getting Lead Id on which followup is to be scheduled for today.......");
		String leadId_profileObj = driver.findElement(By.cssSelector("span[name='lead_id']")).getText()
				.replaceAll("\\s+", "");
		System.out.println(leadId_profileObj);

		// .............Scehduling Followup for today...................

		LeadProfilePage leadProfilePage = new LeadProfilePage(driver);

		getExtTest().log(Status.INFO, "Clicking on Followup link......");
		leadProfilePage.followupLink();

		FollowupsPage followupsPage = new FollowupsPage(driver);

		getExtTest().log(Status.INFO, "Selecting today's Date from calander......");
		followupsPage.selectDate();

		getExtTest().log(Status.INFO, "Clicking on Schedule Followup Button .......");
		followupsPage.clickOnScheduleFollowupButton();
		
		Thread.sleep(2000);
		
		if (!driver.findElements(By.xpath("//button[text()=' Ignore & Schedule ']")).isEmpty()) {

			getExtTest().log(Status.INFO, "Clicking on Ignore and schedule button.......");
			followupsPage.clickOnIgnoreAndSchedule();

		}

		getExtTest().log(Status.INFO, "Validation for Scheduled followup .......");
		Thread.sleep(5000);

		SoftAssert assertion = new SoftAssert();

		String expectedText = p.getProperty("scheduleExpectedText");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
				"#tab-activity > div.activities_list > div:nth-child(1) div.card div:nth-child(1) > div.col-lg-11")));
		Assert.assertTrue(driver
				.findElement(By.cssSelector(
						"#tab-activity > div.activities_list > div:nth-child(1) div.card div:nth-child(1) > div.col-lg-11"))
				.getText().trim().contains(expectedText), "Followup is not scheduled");

		Thread.sleep(3000);

		getExtTest().log(Status.INFO, "Going back to dashboard......");
		salesPresalesDashboard.selectLeadActions(2);
		Thread.sleep(2000);

		getExtTest().log(Status.INFO, "Refreshing the User Dashboard.......");
		salesPresalesDashboard.refreshDashboardStats();
		Thread.sleep(2000);
		
		getExtTest().log(Status.INFO, "Filter scheduled Followups.......");
		salesPresalesDashboard.applyFollowupFilter();
		
		Thread.sleep(2000);

		getExtTest().log(Status.INFO, "Getting Lead Id from toadys followup bucket......");
		String leadId_dashboardObj = driver
				.findElement(By
						.xpath("//a[@class='navigation clearfix todays_stat_show_lead']"))
				.getText().replaceAll("[^0-9]+", " ").trim();
		System.out.println("#" + leadId_dashboardObj);

		getExtTest().log(Status.INFO, "Verifying followup scheduled for today from dashboard......");
		Assert.assertEquals("#" + leadId_dashboardObj, leadId_profileObj, "Leads are not matching");

		assertion.assertAll();
	}
}
