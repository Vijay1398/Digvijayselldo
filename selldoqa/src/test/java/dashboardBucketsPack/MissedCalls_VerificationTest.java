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

public class MissedCalls_VerificationTest extends SetUp {

	WebDriverWait wait;

	final static Logger logger = Logger.getLogger(MissedCalls_VerificationTest.class);

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
				property.getProperty("name") + "+" + property.getProperty("user_email_missedCalls_VerificationTest"),
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

	public void missedCalls_VerificationTest() throws Exception {

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		test = extent.createTest("missedCalls_VerificationTest");
		setExtentTest(test);

		SalesPresalesDashboardPage salesPresalesDashboard = new SalesPresalesDashboardPage(driver);

		getExtTest().log(Status.INFO, "Refreshing the User Dashboard.......");
		salesPresalesDashboard.refreshDashboardStats();

		Thread.sleep(3000);

		getExtTest().log(Status.INFO, "Getting count of Missed Calls bucket......");
		WebElement count_missedCallsBucket = driver
				.findElement(By.cssSelector("div[id='missed-calls'] span[class='title-item-body-count']"));
		String leadCount_missedCallsBucket = count_missedCallsBucket.getText();
		System.out.println(leadCount_missedCallsBucket);

		getExtTest().log(Status.INFO, "Clicking on Missed Calls bucket......");
		salesPresalesDashboard.clickOnMissedCallsBucket();

		Thread.sleep(3000);

		getExtTest().log(Status.INFO, "Getting count of leads under Missed Calls list......");
		String leadCount_MissedCalls = driver.findElement(By.xpath("//span[@class='leads-count-note']")).getText();
		System.out.println(leadCount_MissedCalls);

		// Get count of leads from string
		String count_MissedCalls = leadCount_MissedCalls.replaceAll("\\D+", "");

		getExtTest().log(Status.INFO,
				"Validating that the count of Missed Calls bucket is same as that of the count of leads under Missed Calls list.......");
		SoftAssert assertion = new SoftAssert();
		System.out.println("Started verification");
		Assert.assertEquals(leadCount_missedCallsBucket, count_MissedCalls, "Counts are different");
		System.out.println("Completed verification");
		assertion.assertAll();
	}
}
