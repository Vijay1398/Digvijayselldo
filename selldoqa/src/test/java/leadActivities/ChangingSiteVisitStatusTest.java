package leadActivities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class ChangingSiteVisitStatusTest extends SetUp {

	final static Logger logger = Logger.getLogger(ChangingSiteVisitStatusTest.class);

	WebDriverWait wait;

	// Description:Changing site visit status from Tentative to confirm.

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
		login.login(property.getProperty("name") + "+" + property.getProperty("user_email_changingSiteVisitStatusTest"),
				property.getProperty("password"));
	}

	@Test

	public void changingSiteVisitStatusTest() throws Exception {

		wait = new WebDriverWait(driver, 8);
		Properties p = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		p.load(fileInputObj);

		test = extent.createTest("changingSiteVisitStatusTest");
		setExtentTest(test);

		SalesPresalesDashboardPage salesPresalesDashboard = new SalesPresalesDashboardPage(driver);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		getExtTest().log(Status.INFO, "Searching lead by Id.......");
		adminDashboardPage.searchLead(p.getProperty("Changing_SiteVisit_Status_lead_id"));

		getExtTest().log(Status.INFO, "Getting Lead Id before scheduling Site Visit.......");
		
		
		
		String leadId = driver.findElement(By.xpath("//span[@name='lead_id']")).getAttribute("innerHTML");
		System.out.println(leadId);

		LeadProfilePage leadProfilePage = new LeadProfilePage(driver);
		
		Thread.sleep(2000); // Added Newly Just Remove after passing the test in local
		
		getExtTest().log(Status.INFO, "Clicking on Meeting Link.......");
		leadProfilePage.clickOnMeetingLink();

		Thread.sleep(2000);

		SiteVisitPage siteVisitPage = new SiteVisitPage(driver);

		getExtTest().log(Status.INFO, "Selecting Project from dropdown.........");
		siteVisitPage.selectProject();

		// To Scroll till SV Confirmation field
//		WebElement statusField = driver.findElement(siteVisitPage.siteVisitConfirmation);
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", statusField);

		getExtTest().log(Status.INFO, "Selecting Tentative status from dropdown.........");
		siteVisitPage.selectTentative();

		getExtTest().log(Status.INFO, "Clicking on Site visit button.........");
		siteVisitPage.clickOnScheduleSiteVisitButton();
		Thread.sleep(1000);

		if (!driver.findElements(By.xpath("//button[text()=' Ignore & Schedule ']")).isEmpty()) {

			getExtTest().log(Status.INFO, "Clicking on Ignore and schedule button.......");
			siteVisitPage.clickOnIgnoreAndSchedule();

		}

		getExtTest().log(Status.INFO, "Selecting Confirm from Action Bar.......");
		leadProfilePage.selectConfirm();

		getExtTest().log(Status.INFO, "Clicking on Confirm Button.......");
		siteVisitPage.clickOnConfirmButton();

	}

	@AfterTest

	public void endingTest() throws Exception {

		Thread.sleep(3000);

		getExtTest().log(Status.INFO, "browser closed.......");
		driver.close();

	}
}
