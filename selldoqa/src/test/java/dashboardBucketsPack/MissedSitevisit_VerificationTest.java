package dashboardBucketsPack;

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

import crm.selldo.LeadProfilePage;
import crm.selldo.LoginPage;
import crm.selldo.SalesPresalesDashboardPage;
import crm.selldo.SiteVisitPage;
import utility.SetUp;

public class MissedSitevisit_VerificationTest extends SetUp {

	final static Logger logger = Logger.getLogger(MissedSitevisit_VerificationTest.class);

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
				property.getProperty("name") + "+"
						+ property.getProperty("user_email_MissedSitevisit_VerificationTest"),
				property.getProperty("password"));
	}

	@AfterTest

	public void endingTest() throws Exception {

		Thread.sleep(3000);

		getExtTest().log(Status.INFO, "browser closed.......");
		driver.close();
	}

	@Test

	public void missedSitevisit_VerificationTest() throws Exception {

		test = extent.createTest("missedSitevisit_VerificationTest");
		setExtentTest(test);

		SalesPresalesDashboardPage salesPresalesDashboard = new SalesPresalesDashboardPage(driver);

		LeadProfilePage leadProfilePage = new LeadProfilePage(driver);

		SiteVisitPage siteVisitPage = new SiteVisitPage(driver);

		driver.findElement(salesPresalesDashboard.openTasksLink).click();
		String count_missedSiteVisitBefore = driver.findElement(salesPresalesDashboard.missedSiteVisits)
				.getAttribute("innerHTML");
		System.out.println(count_missedSiteVisitBefore);

		getExtTest().log(Status.INFO, "Clicking on Missed Sitevisit bucket......");
		salesPresalesDashboard.clickOnMissedSitevisitBucket();

		getExtTest().log(Status.INFO, "Opening Lead Deatils Page.......");
		salesPresalesDashboard.openLeadProfile();

		getExtTest().log(Status.INFO, "Selecting Pending sitevisit from more under activity details.......");
		leadProfilePage.clickSitvisit_d();

		getExtTest().log(Status.INFO, "Applying pending sitevisit filter.......");
		leadProfilePage.selectPending_Sitevisit();

		getExtTest().log(Status.INFO, "Selecting Mark As Conducted from Action Bar.......");
		leadProfilePage.selectMarkAsConducted();

		getExtTest().log(Status.INFO, "Selecting date on which Site visit was conducted.......");
		siteVisitPage.sitevisitConductedOnDate();

		getExtTest().log(Status.INFO, "Writing some notes for conducted site visit.......");
		siteVisitPage.enterNotesForConductedSiteVisit("Already conducted");

		getExtTest().log(Status.INFO, "Clicking on Mark as Conducted button.......");
		siteVisitPage.clickOnMarkAsConductedButton();

		getExtTest().log(Status.INFO, "Going back to dsahboard.......");
		salesPresalesDashboard.selectLeadActions(2);

		Thread.sleep(3000);

		getExtTest().log(Status.INFO, "Refreshing the User Dashboard.......");
		driver.findElement(salesPresalesDashboard.openTasksLink).click();
		salesPresalesDashboard.refreshDashboardStats();

		Thread.sleep(5000);

		String count_missedSiteVisitAfter = driver.findElement(salesPresalesDashboard.missedSiteVisits)
				.getAttribute("innerHTML");
		System.out.println(count_missedSiteVisitAfter);

		SoftAssert assertion = new SoftAssert();

		getExtTest().log(Status.INFO, "Validating count is not same after scheduling site visit.......");
		Assert.assertNotEquals(count_missedSiteVisitAfter, count_missedSiteVisitBefore,
				"Count is same as before scheduling site visit");

		assertion.assertAll();
	}
}
