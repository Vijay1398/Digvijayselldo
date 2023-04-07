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
import utility.SetUp;

public class AddingANoteTest extends SetUp {

	final static Logger logger = Logger.getLogger(AddingANoteTest.class);

	// Description: Adding note for Lead

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
		login.login(property.getProperty("name") + "+" + property.getProperty("user_email_schedulingSiteVisitTest"),
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

	public void addingNoteTest() throws Exception {

		test = extent.createTest("AddingANoteTest");
		setExtentTest(test);

		SalesPresalesDashboardPage salesPresalesDashboard = new SalesPresalesDashboardPage(driver);

		getExtTest().log(Status.INFO, "Clicking on All Leads link.......");
		salesPresalesDashboard.goToAllLeadsList();

		getExtTest().log(Status.INFO, "Opening the Lead Details page.......");
		salesPresalesDashboard.openLeadDetails();

		LeadProfilePage leadProfilePage = new LeadProfilePage(driver);

		getExtTest().log(Status.INFO, "Clicking on Add a Note Link.......");
		leadProfilePage.clickOnAddANoteLink();

		String noteText = "Note added Successfully";
		
		getExtTest().log(Status.INFO, "Entering some text in textarea.......");
		leadProfilePage.enterNote(noteText);

		getExtTest().log(Status.INFO, "Clicking on Save Note Button.......");
		leadProfilePage.clickOnsaveNoteButton();
		
		Thread.sleep(2000);
		
		String noteText_AfterAddition = driver.findElement(By.cssSelector("#tab-activity > div.activities_list > div:nth-child(1) > div > div.card > div > div > div > span:nth-child(1)")).getText();

		SoftAssert assertion = new SoftAssert();

		getExtTest().log(Status.INFO, "Verifying that Note is added successfully....");
		Assert.assertEquals(noteText_AfterAddition, noteText, "Text are not matching");

		assertion.assertAll();

	}

}
