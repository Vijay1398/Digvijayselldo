package leadActivities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import adminPages.AdminDashboardPage;
import crm.selldo.LeadProfilePage;
import crm.selldo.LoginPage;
import crm.selldo.SalesPresalesDashboardPage;
import crm.selldo.SiteVisitPage;
import utility.SetUp;

public class MarkingSiteVisitConductedTest extends SetUp {

	final static Logger logger = Logger.getLogger(MarkingSiteVisitConductedTest.class);

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
		login.login(
				property.getProperty("name") + "+" + property.getProperty("user_email_markingSiteVisitConductedTest"),
				property.getProperty("password"));
	}

	@Test

	public void markingSiteVisitConductedTest() throws Exception {

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		test = extent.createTest("markingSiteVisitConductedTest");
		setExtentTest(test);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		getExtTest().log(Status.INFO, "Searching lead by Id.......");
		adminDashboardPage.searchLead(property.getProperty("Marking_SiteVisit_Conducted_lead_id"));

		LeadProfilePage leadProfilePage = new LeadProfilePage(driver);

		getExtTest().log(Status.INFO, "Clicking on Meeting Link.......");
		leadProfilePage.clickOnMeetingLink();

		Thread.sleep(2000);

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
		
		getExtTest().log(Status.INFO, "Selecting Mark As Conducted from Action Bar.......");
		leadProfilePage.selectMarkAsConducted();

		getExtTest().log(Status.INFO, "Selecting date on which Site visit was conducted.......");
		siteVisitPage.sitevisitConductedOnDate();

		getExtTest().log(Status.INFO, "Writing some notes for conducted site visit.......");
		siteVisitPage.enterNotesForConductedSiteVisit(property.getProperty("notes_markingSiteVisitConductedTest"));

		getExtTest().log(Status.INFO, "Clicking on Mark as Conducted button.......");
		siteVisitPage.clickOnMarkAsConductedButton();//Click on Save button

	}

	@AfterTest

	public void endingTest() throws Exception {

		Thread.sleep(3000);
		getExtTest().log(Status.INFO, "browser closed.......");
		driver.close();

	}

}
