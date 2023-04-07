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
import crm.selldo.EmailPage;
import crm.selldo.LeadProfilePage;
import crm.selldo.LoginPage;
import crm.selldo.SmsPage;
import utility.SetUp;

public class SendSmsTest extends SetUp{
	
	
	final static Logger logger = Logger.getLogger(SendSmsTest.class);

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
		login.login(property.getProperty("name") + "+" + property.getProperty("user_sendingSmsTest"),
				property.getProperty("password"));
	}

	@AfterTest

	public void endingTest() throws Exception {

		Thread.sleep(3000);

		logger.info("browser closed.......");
		driver.close();

	}

	@Test

	public void sendingSmsTest() throws Exception {

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		test = extent.createTest("Send Sms to lead");
		setExtentTest(test);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		getExtTest().log(Status.INFO, "Searching lead by Id.......");
		adminDashboardPage.searchLead(property.getProperty("sending_Sms_lead_id"));

		LeadProfilePage leadProfilePage = new LeadProfilePage(driver);

		getExtTest().log(Status.INFO, "Clicking on Sms Link.......");
		leadProfilePage.clickOnSmsLink();
		
		Thread.sleep(2000);

		SmsPage smsPage = new SmsPage(driver);

		getExtTest().log(Status.INFO, "Selecting SMS template from dropdown.......");
		smsPage.selectSmsTemplate(property.getProperty("sms_template"));

		Thread.sleep(1000);
		
		getExtTest().log(Status.INFO, "Clicking on Send SMS Button.......");
		smsPage.clickOnSendSmsButton();
		
		Thread.sleep(2000);
		
		getExtTest().log(Status.INFO, "Clicking on SMS link under activities section.......");
		leadProfilePage.clickSms_d();
		
		Thread.sleep(3000);


		getExtTest().log(Status.INFO, "Fetching the text appeared after sending email....");
		String text = driver.findElement(By.cssSelector(
				"#tab-activity > div.activities_list > div:nth-child(1) > div > div.card > div > div:nth-child(3) > div:nth-child(2) > span"))
				.getText();
		
		
		SoftAssert assertion = new SoftAssert();

		getExtTest().log(Status.INFO, "Verifying the text under Email activities....");
		Assert.assertEquals(text, "Outgoing Success");

		assertion.assertAll();
		
		
	}
	

}
