package otherLeadActivitiesPack;

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
import crm.selldo.MergeLeadsPage;
import crm.selldo.SalesPresalesDashboardPage;
import utility.SetUp;

public class MergingTwoLeadsFromSameStagesTest extends SetUp {

	// Description: merging two leads from same stages

	final static Logger logger = Logger.getLogger(MergingTwoLeadsFromSameStagesTest.class);

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
						+ property.getProperty("user_email_mergingTwoLeadsFromSameStagesTest"),
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

	public void mergingTwoLeadsFromSameStagesTest() throws Exception {

		test = extent.createTest("mergingTwoLeadsFromSameStagesTest");
		setExtentTest(test);

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		SalesPresalesDashboardPage salesPresalesDashboard = new SalesPresalesDashboardPage(driver);

		LeadProfilePage leadProfilePage = new LeadProfilePage(driver);

		getExtTest().log(Status.INFO, "Going to All Lead List.......");
		salesPresalesDashboard.goToAllLeadsList();
		
		Thread.sleep(2000);

		getExtTest().log(Status.INFO, "Selecting Prospect list......");
		adminDashboardPage.SelectList("Prospect");

		getExtTest().log(Status.INFO, "Opening Lead Deatils Page.......");
		salesPresalesDashboard.openLeadDetails2();

		WebElement Lead1 = driver.findElement(By.cssSelector("span[name='lead_id']"));
		String leadtext1 = (Lead1.getText().replaceAll("\\s+", "").substring(1))+"#";
		
		System.out.println(leadtext1);

		getExtTest().log(Status.INFO, "Going to All Lead List.......");
		salesPresalesDashboard.goToAllLeadsList();

		getExtTest().log(Status.INFO, "Selecting Prospect list......");
		adminDashboardPage.SelectList("Prospect");

		getExtTest().log(Status.INFO, "Opening Lead Deatils Page.......");
		salesPresalesDashboard.openLeadDetails();

		getExtTest().log(Status.INFO, "Selecting Merge Leads from more.......");
		leadProfilePage.selectMergeLeads();

		MergeLeadsPage mergeLeadsPage = new MergeLeadsPage(driver);

		getExtTest().log(Status.INFO, "Searching lead to be merged.......");
		mergeLeadsPage.searchingLeadToBeMerged(leadtext1);

		Thread.sleep(2000);

		getExtTest().log(Status.INFO, "Clicking on Merge This Button.......");
		mergeLeadsPage.clickOnMergeThisButton();

		getExtTest().log(Status.INFO, "Writing some notes.......");
		mergeLeadsPage.enteringSomeNotes(property.getProperty("note_mergingTwoLeadsFromDiffStagesTest"));

		getExtTest().log(Status.INFO, "Clicking on Merge Leads Button.......");
		mergeLeadsPage.clickOnMergeLeadsButton();

		Thread.sleep(3000);

		salesPresalesDashboard.searchLead(leadtext1);
		Thread.sleep(2000);
		getExtTest().log(Status.INFO, "Validating that stage of second lead changed to unqualified..............");
		SoftAssert assertion = new SoftAssert();
		System.out.println("Started verification");
		Assert.assertEquals(driver
				.findElement(By.cssSelector("div.dropdown.stage.float-left.mr-1 button span[data-title=\"label\"]"))
				.getText().trim(), "Unqualified", "Merged lead not found under unqualified list");
		System.out.println("Completed verification");
		assertion.assertAll();

	}

}