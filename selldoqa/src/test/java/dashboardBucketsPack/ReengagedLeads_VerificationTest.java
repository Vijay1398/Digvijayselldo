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

public class ReengagedLeads_VerificationTest extends SetUp{
	
	WebDriverWait wait;

	final static Logger logger = Logger.getLogger(ReengagedLeads_VerificationTest.class);

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
				property.getProperty("name") + "+" + property.getProperty("user_email_reengagedLeads_VerificationTest"),
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

	public void reengagedLeads_VerificationTest() throws Exception {

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		test = extent.createTest("reengagedLeads_VerificationTest");
		setExtentTest(test);

		SalesPresalesDashboardPage salesPresalesDashboard = new SalesPresalesDashboardPage(driver);

		getExtTest().log(Status.INFO, "Refreshing the User Dashboard.......");
		salesPresalesDashboard.refreshDashboardStats();

		Thread.sleep(3000);

		getExtTest().log(Status.INFO, "Getting count of Reengaged Leads bucket......");
		WebElement count_reengagedLeadsBucket = driver
				.findElement(By.cssSelector("div[id='reengaged-leads'] span[class='title-item-body-count']"));
		String leadCount_ReengagedLeadsBucket = count_reengagedLeadsBucket.getText().replaceAll("[^0-9]", "");
		System.out.println(leadCount_ReengagedLeadsBucket);

		getExtTest().log(Status.INFO, "Clicking on Reengaged Leads bucket......");
		salesPresalesDashboard.clickOnReengagedLeadsBucket();
		
		Thread.sleep(2000);
		
		getExtTest().log(Status.INFO, "Getting count of leads under Reengaged Leads list......");
		String leadCount_ReengagedLeadsList = driver.findElement(By.cssSelector("div[id='reengaged-leads'] span[class='title-item-body-count']")).getText();
		System.out.println(leadCount_ReengagedLeadsList);

		// Find the length of string.
		System.out.println("Length Of leadId -> " + leadCount_ReengagedLeadsList.length());

		// Print the character at zero index of the string
		String count_ReengagedLeadsList = leadCount_ReengagedLeadsList.substring(0, +leadCount_ReengagedLeadsList.length() - 46).replaceAll("[^0-9]", "");
		System.out.println("Retrieving sub string from string -> " + count_ReengagedLeadsList);

		getExtTest().log(Status.INFO, "Validating that the count of Reengaged bucket is same as that of the count of leads under Reengaged Leads list......");
		SoftAssert assertion = new SoftAssert();
		Assert.assertEquals(count_ReengagedLeadsList, leadCount_ReengagedLeadsBucket, "Counts are different");
		assertion.assertAll();
	}
}
