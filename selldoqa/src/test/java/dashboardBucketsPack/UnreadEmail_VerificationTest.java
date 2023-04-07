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
import crm.selldo.LoginPage;
import crm.selldo.SalesPresalesDashboardPage;
import utility.SetUp;

public class UnreadEmail_VerificationTest extends SetUp {

	WebDriverWait wait;

	final static Logger logger = Logger.getLogger(UnreadEmail_VerificationTest.class);

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
				property.getProperty("name") + "+" + property.getProperty("user_email_unreadEmail_VerificationTest"),
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

	public void unreadEmail_VerificationTest() throws Exception {

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		test = extent.createTest("unreadEmail_VerificationTest");
		setExtentTest(test);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		SalesPresalesDashboardPage salesPresalesDashboard = new SalesPresalesDashboardPage(driver);

		getExtTest().log(Status.INFO, "Refreshing the User Dashboard.......");
		salesPresalesDashboard.refreshDashboardStats();

		Thread.sleep(3000);

		getExtTest().log(Status.INFO, "Getting count of Unread Email bucket......");
		WebElement count_unreadEmailBucket = driver.findElement(By.cssSelector("div[id='pending-emails'] span[class='title-item-body-count']"));
		String leadCount_unreadEmailBucket = count_unreadEmailBucket.getText();
		System.out.println(leadCount_unreadEmailBucket);

		getExtTest().log(Status.INFO, "Going to All Lead List.......");
		salesPresalesDashboard.goToAllLeadsList();

		getExtTest().log(Status.INFO, "Selecting Pending Emails list......");
		adminDashboardPage.SelectList("Pending Emails");

		Thread.sleep(3000);

		getExtTest().log(Status.INFO, "Getting count of leads under pending email list......");
		String leadCount_pendingEmail = driver.findElement(By.cssSelector("div[id='pending-emails'] span[class='title-item-body-count']")).getText();
		System.out.println(leadCount_pendingEmail);

		// Find the length of string.
		System.out.println("Length Of leadId -> " + leadCount_pendingEmail.length());

		// Print the character at zero index of the string
		String count_pendingEmail = leadCount_pendingEmail.substring(0, +leadCount_pendingEmail.length() - 33);
		System.out.println("Retrieving sub string from string -> " + count_pendingEmail);

		getExtTest().log(Status.INFO,
				"Validating that the count of Ureaad Email bucket is same as that of the count of leads under Pending Email list.......");
		SoftAssert assertion = new SoftAssert();
		System.out.println("Started verification");
		Assert.assertEquals(leadCount_unreadEmailBucket, count_pendingEmail, "Counts are different");
		System.out.println("Completed verification");
		assertion.assertAll();
	}

}
