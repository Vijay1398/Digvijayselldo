package dashboardBucketsPack;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import adminPages.AdminDashboardPage;
import crm.selldo.LeadProfilePage;
import crm.selldo.LoginPage;
import crm.selldo.SalesPresalesDashboardPage;
import crm.selldo.SiteVisitPage;
import utility.SetUp;

public class TodaysSitevisit_VerificationTest extends SetUp {

	WebDriverWait wait;

	final static Logger logger = Logger.getLogger(TodaysSitevisit_VerificationTest.class);

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
						+ property.getProperty("user_email_todaysSitevisit_VerificationTest"),
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

	public void todaysSitevisit_VerificationTest() throws Exception {

		Properties p = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		p.load(fileInputObj);

		test = extent.createTest("todaysSitevisit_VerificationTest");
		setExtentTest(test);

		SalesPresalesDashboardPage salesPresalesDashboard = new SalesPresalesDashboardPage(driver);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		getExtTest().log(Status.INFO, "Searching lead by Id.......");
		adminDashboardPage.searchLead(p.getProperty("TodaysSitevisitTest_lead_id"));

		getExtTest().log(Status.INFO, "Getting Lead Id on which Sitevisit is to be scheduled for today.......");
		String leadId_profileObj = driver.findElement(By.cssSelector("span[name='lead_id']")).getText()
				.replaceAll("\\s+", "");
		System.out.println(leadId_profileObj);

		LeadProfilePage leadProfilePage = new LeadProfilePage(driver);

		getExtTest().log(Status.INFO, "Clicking on Meeting Link.......");
		leadProfilePage.clickOnMeetingLink();

		Thread.sleep(3000);

		SiteVisitPage siteVisitPage = new SiteVisitPage(driver);

		getExtTest().log(Status.INFO, "Selecting Project from dropdown.........");
		siteVisitPage.selectProject();

		getExtTest().log(Status.INFO, "Selecting today's date from Calender.........");
		siteVisitPage.selectDate();

		getExtTest().log(Status.INFO, "Clicking on Site visit button.........");
		siteVisitPage.clickOnScheduleSiteVisitButton();

		Thread.sleep(2000);

		if (!driver.findElements(By.xpath("//button[text()=' Ignore & Schedule ']")).isEmpty()) {

			getExtTest().log(Status.INFO, "Clicking on Ignore and schedule button.......");
			siteVisitPage.clickOnIgnoreAndSchedule();

		}

		Thread.sleep(3000);

		getExtTest().log(Status.INFO, "Going back to dashboard......");
		salesPresalesDashboard.selectLeadActions(2);
		Thread.sleep(2000);

		getExtTest().log(Status.INFO, "Refreshing the User Dashboard.......");
		salesPresalesDashboard.refreshDashboardStats();
		Thread.sleep(2000);

		getExtTest().log(Status.INFO, "Filter scheduled Sitevisits.......");
		salesPresalesDashboard.applySitevisitFilter();
		
		Thread.sleep(2000);

		getExtTest().log(Status.INFO, "Getting Lead Id from toadys sitevisit bucket......");
		String leadId_dashboardObj = driver.findElements(By.cssSelector(".navigation.clearfix.todays_stat_show_lead")).get(1)
				.getText().replaceAll("[^0-9]+", " ").trim();
		System.out.println("#" + leadId_dashboardObj);

		SoftAssert assertion = new SoftAssert();

		getExtTest().log(Status.INFO, "Verifying Sitevisit scheduled for today from dashboard......");
		Assert.assertEquals("#" + leadId_dashboardObj, leadId_profileObj, "Leads are not matching");

		assertion.assertAll();
	}

}
