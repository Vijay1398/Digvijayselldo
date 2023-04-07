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
import utility.SetUp;

public class NewEnquiries_VerificationTest extends SetUp {

	WebDriverWait wait;

	final static Logger logger = Logger.getLogger(NewEnquiries_VerificationTest.class);

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
				property.getProperty("name") + "+" + property.getProperty("user_email_newEnquiries_VerificationTest"),
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

	public void newEnquiries_VerificationTest() throws Exception {

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		test = extent.createTest("newEnquiries_VerificationTest");
		setExtentTest(test);

		SalesPresalesDashboardPage salesPresalesDashboard = new SalesPresalesDashboardPage(driver);

		getExtTest().log(Status.INFO, "Refreshing the User Dashboard.......");
		salesPresalesDashboard.refreshDashboardStats();

		Thread.sleep(3000);

		getExtTest().log(Status.INFO, "Getting count of New Enquiry bucket......");
		WebElement count_b = driver.findElement(By.cssSelector("div[id='new-enquires'] span[class='title-item-body-count']"));
		String leadCount_b = count_b.getText();
		System.out.println(leadCount_b);

		getExtTest().log(Status.INFO, "Clicking on New Enquiry bucket.......");
		salesPresalesDashboard.clickOnnewEnquiryBucket();

		getExtTest().log(Status.INFO, "Opening Lead Deatils Page.......");
		salesPresalesDashboard.openLeadProfile();

		// ............changing stage to prospect from incoming of lead under New Enquiry Bucket...................

		LeadProfilePage leadProfilePage = new LeadProfilePage(driver);

		getExtTest().log(Status.INFO, "Changing stage from Incoming to Prospect.......");

		leadProfilePage.changing_Stage("Prospect");

		Thread.sleep(3000);

		// ..........Going back to dashboard after sending email.......................

		getExtTest().log(Status.INFO, "Going back to dashboard......");
		salesPresalesDashboard.selectLeadActions(2);

		getExtTest().log(Status.INFO, "Refreshing the User Dashboard.......");
		salesPresalesDashboard.refreshDashboardStats();

		Thread.sleep(4000);

		getExtTest().log(Status.INFO, "Getting count of New Enquiry bucket after sending email......");
		WebElement count_a = driver.findElement(By.cssSelector("div[id='new-enquires'] span[class='title-item-body-count']"));
		String leadCount_a = count_a.getText();
		System.out.println(leadCount_a);

		SoftAssert assertion = new SoftAssert();

		getExtTest().log(Status.INFO,
				"Validating count is not same after sending email to a lead from New Enquiry bucket......");
		Assert.assertNotEquals(leadCount_b, leadCount_a, "Count is same as before sending email");

		assertion.assertAll();

	}
}
