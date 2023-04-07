package dashboardBucketsPack;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import crm.selldo.EmailPage;
import crm.selldo.FollowupsPage;
import crm.selldo.LeadProfilePage;
import crm.selldo.LoginPage;
import crm.selldo.SalesPresalesDashboardPage;
import utility.SetUp;

public class MissedFollowup_VerificationTest extends SetUp {

	WebDriverWait wait;

	final static Logger logger = Logger.getLogger(MissedFollowup_VerificationTest.class);

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
				property.getProperty("name") + "+" + property.getProperty("user_email_MissedFollowup_VerificationTest"),
				property.getProperty("password"));
	}

	@AfterTest

	public void endingTest() throws Exception {

		Thread.sleep(3000);

		logger.info("browser closed.......");
		driver.close();

	}

	@Test()

	public void missedFollowup_VerificationTest() throws Exception {

		wait = new WebDriverWait(driver, 10);

		test = extent.createTest("missedFollowup_VerificationTest");
		setExtentTest(test);

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		SalesPresalesDashboardPage salesPresalesDashboard = new SalesPresalesDashboardPage(driver);

		LeadProfilePage leadProfilePage = new LeadProfilePage(driver);

		driver.findElement(salesPresalesDashboard.openTasksLink).click();
		String count_missedFollowupBefore = driver.findElement(salesPresalesDashboard.missedFollowups)
				.getAttribute("innerHTML");
		System.out.println(count_missedFollowupBefore);

		getExtTest().log(Status.INFO, "Clicking on Missed Followup bucket......");
		salesPresalesDashboard.clickOnMissedFollowupBucket();

		getExtTest().log(Status.INFO, "Opening Lead Deatils Page.......");
		salesPresalesDashboard.openLeadProfile();

		getExtTest().log(Status.INFO, "Scheduling Followup.......");
		leadProfilePage.followupLink();

		FollowupsPage followupsPage = new FollowupsPage(driver);

		getExtTest().log(Status.INFO, "Selecting Date.......");
		followupsPage.selectDate();

		getExtTest().log(Status.INFO, "Clicking on Schedule Followup Button .......");
		followupsPage.clickOnScheduleFollowupButton();

		Thread.sleep(3000);

		if (!driver.findElements(By.xpath("//button[text()=' Ignore & Schedule ']")).isEmpty()) {

			getExtTest().log(Status.INFO, "Clicking on Ignore and schedule button.......");
			followupsPage.clickOnIgnoreAndSchedule();

		}

		Thread.sleep(1000);

		getExtTest().log(Status.INFO, "Going back to dsahboard.......");
		salesPresalesDashboard.selectLeadActions(2);

		Thread.sleep(1000);

		getExtTest().log(Status.INFO, "Refreshing the User Dashboard.......");
		driver.findElement(salesPresalesDashboard.openTasksLink).click();
		salesPresalesDashboard.refreshDashboardStats();

		Thread.sleep(5000);

		
		String count_missedFollowupAfter = driver.findElement(salesPresalesDashboard.missedFollowups)
				.getAttribute("innerHTML");
		System.out.println(count_missedFollowupAfter);

		SoftAssert assertion = new SoftAssert();

		getExtTest().log(Status.INFO, "Validating count is not same after scheduling Followup.......");
		Assert.assertNotEquals(count_missedFollowupAfter, count_missedFollowupBefore,
				"Count is same as before scheduling Followup");

		assertion.assertAll();

	}
}
