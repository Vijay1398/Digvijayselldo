package dashboardBucketsPack;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

public class NFA_VerificationTest extends SetUp {

	WebDriverWait wait;

	final static Logger logger = Logger.getLogger(NFA_VerificationTest.class);

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
		login.login(property.getProperty("name") + "+" + property.getProperty("user_email_nFA_VerificationTest"),
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

	public void nFA_VerificationTest() throws Exception {

		Properties p = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		p.load(fileInputObj);

		test = extent.createTest("nFA_VerificationTest");
		setExtentTest(test);

		SalesPresalesDashboardPage salesPresalesDashboard = new SalesPresalesDashboardPage(driver);

		getExtTest().log(Status.INFO, "Refreshing the User Dashboard.......");
		salesPresalesDashboard.refreshDashboardStats();

		Thread.sleep(3000);

		getExtTest().log(Status.INFO, "Getting count of No Future Activity bucket......");
		WebElement count_b = driver.findElement(By.cssSelector("div[id='nfa-leads'] span[class='title-item-body-count']"));
		String leadCount_b = count_b.getText();
		System.out.println(leadCount_b);

		getExtTest().log(Status.INFO, "Clicking on NFA bucket.......");
		salesPresalesDashboard.clickOnNFAbucket();

		getExtTest().log(Status.INFO, "Opening Lead Deatils Page.......");
		salesPresalesDashboard.openLeadProfile();

		// ................Scheduling site visit on a lead under NFA
		// list...................

		LeadProfilePage leadProfilePage = new LeadProfilePage(driver);

		getExtTest().log(Status.INFO, "Clicking on site visit Link.......");
		leadProfilePage.selectSiteVisit();

		Thread.sleep(3000);

		SiteVisitPage siteVisitPage = new SiteVisitPage(driver);

		getExtTest().log(Status.INFO, "Selecting Project from dropdown.........");
		siteVisitPage.selectProject();

		getExtTest().log(Status.INFO, "Selecting date from Calender.........");
		siteVisitPage.sitevisitScheduleDate();
		Thread.sleep(2000);

		getExtTest().log(Status.INFO, "Clicking on Site visit button.........");
		siteVisitPage.clickOnScheduleSiteVisitButton();
		
		Thread.sleep(2000);
		
		if (!driver.findElements(By.xpath("//button[text()=' Ignore & Schedule ']")).isEmpty()) {

			getExtTest().log(Status.INFO, "Clicking on Ignore and schedule button.......");
			siteVisitPage.clickOnIgnoreAndSchedule();

		}
		
		Thread.sleep(2000);

		// ..........Going back to dashboard after scheduling site
		// visit.......................

		getExtTest().log(Status.INFO, "Going back to dashboard......");
		salesPresalesDashboard.selectLeadActions(2);

		Thread.sleep(3000);

		getExtTest().log(Status.INFO, "Refreshing the User Dashboard.......");
		salesPresalesDashboard.refreshDashboardStats();

		Thread.sleep(3000);

		getExtTest().log(Status.INFO, "Getting count of No Future Activity bucket......");
		WebElement count_a = driver.findElement(By.cssSelector("div[id='nfa-leads'] span[class='title-item-body-count']"));
		String leadCount_a = count_a.getText();
		System.out.println(leadCount_a);

		SoftAssert assertion = new SoftAssert();

		getExtTest().log(Status.INFO, "Validating count is not same after scheduling site visit.......");
		Assert.assertNotEquals(leadCount_b, leadCount_a, "Count is same as before scheduling site visit");

		assertion.assertAll();

	}
}
