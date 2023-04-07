package leadActivities;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

public class AddConductedSiteVisitTest extends SetUp {

	final static Logger logger = Logger.getLogger(AddConductedSiteVisitTest.class);

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
		login.login(property.getProperty("name") + "+" + property.getProperty("user_email_addConductedSiteVisitTest"),
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

	public void addConductedSiteVisitTest() throws Exception {

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		test = extent.createTest("addConductedSiteVisitTest");
		setExtentTest(test);

		SalesPresalesDashboardPage salesPresalesDashboard = new SalesPresalesDashboardPage(driver);

		LeadProfilePage leadProfilePage = new LeadProfilePage(driver);

		SiteVisitPage siteVisitPage = new SiteVisitPage(driver);

		getExtTest().log(Status.INFO, "Clicking on All Leads.......");
		salesPresalesDashboard.goToAllLeadsList();

		getExtTest().log(Status.INFO, "Opening Lead Deatils Page.......");
		salesPresalesDashboard.openLeadDetails();

		getExtTest().log(Status.INFO, "Selecting Conducted sitevisit from more.........");
		leadProfilePage.selectConductedSiteVisit();
		
		Thread.sleep(2000);

		getExtTest().log(Status.INFO, "Selecting project for which sitevisit conducted.......");
		siteVisitPage.selectProject();

		getExtTest().log(Status.INFO, "Selecting date on which sitevisit conducted.......");
		siteVisitPage.sitevisitConductedDate(property.getProperty("date_addConductedSiteVisitTest"));

		getExtTest().log(Status.INFO, "Adding notes regarding conducted sitevisit....");
		siteVisitPage.addNotes("Site visit was conducted previously");

		getExtTest().log(Status.INFO, "Clicking on Add previously conducted sitevisit button......");
		siteVisitPage.clickOnAddPreviouslyConductedSvButton();
		
		Thread.sleep(3000);
		
		WebElement text = driver.findElement(By.cssSelector(
				"#tab-activity > div.activities_list > div:nth-child(1) > div > div.card > div > div:nth-child(3) > div > div.row > div:nth-child(2) > span"));
		String text_conducted = text.getText();

		SoftAssert assertion = new SoftAssert();

		getExtTest().log(Status.INFO, "Verifying that previously conducted site visit is added....");
		Assert.assertEquals(text_conducted, "Conducted   |   Visit", "Previously conducted site visit is not added");

		assertion.assertAll();

	}
}
