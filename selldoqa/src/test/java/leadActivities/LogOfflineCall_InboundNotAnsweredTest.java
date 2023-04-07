package leadActivities;

import java.io.FileInputStream;
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
import crm.selldo.LogOfflineCallPage;
import crm.selldo.LoginPage;
import utility.SetUp;

public class LogOfflineCall_InboundNotAnsweredTest extends SetUp {

	final static Logger logger = Logger.getLogger(LogOfflineCall_InboundNotAnsweredTest.class);

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
						+ property.getProperty("user_email_logOfflineCall_InboundNotAnsweredTest"),
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

	public void logOfflineCall_InboundNotAnsweredTest() throws Exception {

		Properties p = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		p.load(fileInputObj);

		test = extent.createTest("logOfflineCall_Inbound_Not_Answered_Test");
		setExtentTest(test);

		LeadProfilePage leadProfilePage = new LeadProfilePage(driver);

		LogOfflineCallPage logOfflineCallPage = new LogOfflineCallPage(driver);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		getExtTest().log(Status.INFO, "Searching lead by Id.......");
		adminDashboardPage.searchLead(p.getProperty("Log_OfflineCall_lead_id"));

		getExtTest().log(Status.INFO, "Selecting Conducted sitevisit from more.........");
		leadProfilePage.selectLogOfflineCalls();

		getExtTest().log(Status.INFO, "Selecting Direction as Inbound.......");
		logOfflineCallPage.selectDirection_Inbound();

		getExtTest().log(Status.INFO, "Selecting Status as Not Answered.......");
		logOfflineCallPage.selectStatus_NotAnswered();

		getExtTest().log(Status.INFO, "Selecting Date from calender.......");
		logOfflineCallPage.selectDate();
		
		getExtTest().log(Status.INFO, "Selecting Time.......");
		logOfflineCallPage.selectTime();


		getExtTest().log(Status.INFO, "Clicking on Save button.......");
		logOfflineCallPage.clickOnSaveButton();

		Thread.sleep(2000);

		getExtTest().log(Status.INFO, "Clicking on Call link under activities section button.......");
		leadProfilePage.clickOnCall_d();

		Thread.sleep(2000);

		getExtTest().log(Status.INFO, "Fetching the text appeared after adding log offline call....");
		String text = driver.findElement(By.cssSelector(
				"#tab-activity > div.activities_list > div:nth-child(1) > div > div.card > div > div:nth-child(1) > div.col-lg-11 > span"))
				.getText();
		System.out.println(text);
		System.out.println("Length Of leadId -> " + text.length());
		String croppedText = text.substring(0, +text.length() - 43);
		System.out.println(croppedText);

		SoftAssert assertion = new SoftAssert();

		getExtTest().log(Status.INFO, "Verifying the text under Call activities....");
		Assert.assertEquals(croppedText, "You missed cal", "text not matching");

		getExtTest().log(Status.INFO, "Fetching Incoming text appeared after adding Inbound call....");
		String directionText = driver.findElement(By.cssSelector(
				"#tab-activity > div.activities_list > div:nth-child(1) > div > div.card > div > div:nth-child(3) > div:nth-child(2) > span"))
				.getText();
		System.out.println(directionText);
		System.out.println("Length Of leadId -> " + directionText.length());
		String croppedDirectionText = directionText.substring(0, +directionText.length() - 5);
		System.out.println(croppedText);

		getExtTest().log(Status.INFO, "Verifying that Incoming text appeared after adding Inbound call....");
		Assert.assertEquals(croppedDirectionText, "Incoming  |  Offline  |  Not Ans", "text not matching");

		getExtTest().log(Status.INFO, "Fetching text which shows it is an offline call....");
		String recordingText = driver.findElement(By.cssSelector(
				"#tab-activity > div.activities_list > div:nth-child(1) > div > div.card > div > div:nth-child(1) > div.col-lg-11 > div:nth-child(2) > span"))
				.getText();
		System.out.println(recordingText);

		getExtTest().log(Status.INFO, "Verifying text which shows it is an offline call....");
		Assert.assertEquals(recordingText, "No recording available", "text not matching");

		assertion.assertAll();

	}

}
