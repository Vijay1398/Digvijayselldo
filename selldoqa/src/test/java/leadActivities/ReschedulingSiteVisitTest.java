package leadActivities;

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
import crm.selldo.LeadProfilePage;
import crm.selldo.LoginPage;
import crm.selldo.SalesPresalesDashboardPage;
import crm.selldo.SiteVisitPage;
import utility.SetUp;

public class ReschedulingSiteVisitTest extends SetUp {

	final static Logger logger = Logger.getLogger(ReschedulingSiteVisitTest.class);

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

		logger.info("Logging in.......");
		login.login(property.getProperty("name") + "+" + property.getProperty("user_email_reschedulingSiteVisitTest"),
				property.getProperty("password"));
	}

	@Test

	public void reschedulingSiteVisitTest() throws Exception {

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		test = extent.createTest("reschedulingSiteVisitTest");
		setExtentTest(test);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		getExtTest().log(Status.INFO, "Searching lead by Id.......");
		adminDashboardPage.searchLead(property.getProperty("Rescheduling_SiteVisit_lead_id"));

		LeadProfilePage leadProfilePage = new LeadProfilePage(driver);

		getExtTest().log(Status.INFO, "Clicking on Meeting Link.......");
		leadProfilePage.clickOnMeetingLink();

		Thread.sleep(3000);

		SiteVisitPage siteVisitPage = new SiteVisitPage(driver);

		getExtTest().log(Status.INFO, "Selecting Project from dropdown.........");
		siteVisitPage.selectProject();

		getExtTest().log(Status.INFO, "Selecting date from Calender.........");
		siteVisitPage.sitevisitScheduleDate();

		getExtTest().log(Status.INFO, "Clicking on Site visit button.........");
		siteVisitPage.clickOnScheduleSiteVisitButton();

		Thread.sleep(2000);

		if (!driver.findElements(By.xpath("//button[text()=' Ignore & Schedule ']")).isEmpty()) {

			getExtTest().log(Status.INFO, "Clicking on Ignore and schedule button.......");
			siteVisitPage.clickOnIgnoreAndSchedule();

		}

		Thread.sleep(2000);

		getExtTest().log(Status.INFO, "Selecting Reschedule from Action bar.........");
		leadProfilePage.selectReschedule();

		String projectName = driver.findElement(
				By.cssSelector("div.select2-container.project_id.form-control.project_select a span:nth-child(1)"))
				.getText();
		System.out.println(projectName);

		getExtTest().log(Status.INFO, "Changing date to reschedule site visit.........");
		siteVisitPage.sitevisitRescheduleDate();

		getExtTest().log(Status.INFO, "Clicking on Reschedule button.......");
		siteVisitPage.clickOnRescheduleButton();

		Thread.sleep(2000);

		if (!driver.findElements(By.xpath("//button[text()=' Ignore & Schedule ']")).isEmpty()) {

			getExtTest().log(Status.INFO, "Clicking on Ignore and schedule button.......");
			siteVisitPage.clickOnIgnoreAndSchedule();

		}
		Thread.sleep(4000);

		getExtTest().log(Status.INFO, "Clicking on Feed tab.......");
		leadProfilePage.clickFeedTab();

		getExtTest().log(Status.INFO, "Fetching the text appeared under Feed after scheduling site visit....");
		String text = driver.findElement(By.cssSelector(
				"#tab-activity > div.activities_list > div:nth-child(1) > div > div.card > div > div > div > span:nth-child(1)"))
				.getText().trim();

		SoftAssert assertion = new SoftAssert();

		getExtTest().log(Status.INFO, "Verifying that Interested property Feed is generated successfully....");
		Assert.assertEquals(text, "Lead showed interest in " + projectName + "");
		assertion.assertAll();

	}

	@AfterTest

	public void endingTest() throws Exception {

		Thread.sleep(3000);
		getExtTest().log(Status.INFO, "browser closed.......");
		driver.close();

	}
}
