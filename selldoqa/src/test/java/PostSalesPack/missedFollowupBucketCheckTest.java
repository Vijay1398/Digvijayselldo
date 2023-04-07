package PostSalesPack;

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

import crm.selldo.FollowupsPage;
import crm.selldo.LeadProfilePage;
import crm.selldo.LoginPage;
import crm.selldo.SalesPresalesDashboardPage;
import dashboardBucketsPack.MissedFollowup_VerificationTest;
import postSales.PostSalesDashboardPage;
import utility.*;

public class missedFollowupBucketCheckTest extends SetUp {
	
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
		login.login(property.getProperty("psManager_email"),
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

		PostSalesDashboardPage postsalesDashboardPage = new PostSalesDashboardPage(driver);
		
		LeadProfilePage leadProfilePage = new LeadProfilePage(driver);

		driver.findElement(postsalesDashboardPage.openTasksLink).click();
		String count_missedFollowupBefore = driver.findElement(postsalesDashboardPage.missedFollowUps)
				.getAttribute("innerHTML");
		System.out.println(count_missedFollowupBefore);
		
		int count = Integer.parseInt(count_missedFollowupBefore);
		
		if(count == 0) {
			
			getExtTest().log(Status.INFO, "Clicking on Missed Followup bucket......");
			postsalesDashboardPage.clickOnMissedFollowupBucket();
			
			Thread.sleep(3000);

			getExtTest().log(Status.INFO, "Getting Count from the label under List DropDownList for the missed followup stage...");
			
			WebElement count_a = driver.findElement(By.xpath("//span[@class='bookings-count-note']"));
			String leadCount_a = count_a.getText();
			System.out.println(leadCount_a);

			SoftAssert assertion = new SoftAssert();

			getExtTest().log(Status.INFO,
					"Validating count is not same after clicking on missed followup bucket......"); 
			
			Assert.assertNotEquals(count_missedFollowupBefore,leadCount_a, "Count is same as before clicking bucket");

			assertion.assertAll();
			
			
		}
		else {

		getExtTest().log(Status.INFO, "Clicking on Missed Followup bucket......");
		postsalesDashboardPage.clickOnMissedFollowupBucket();

		Thread.sleep(3000);
		  getExtTest().log(Status.INFO, "Opening Lead Deatils Page.......");
		  postsalesDashboardPage.openLeadProfile();
		
		 
		  getExtTest().log(Status.INFO, "Scheduling Followup.......");
		  postsalesDashboardPage.followupLink();
		  
		  FollowupsPage followupsPage = new FollowupsPage(driver);
		  
		  if (!driver.findElements(By.cssSelector("div.select2-container.form-control.cancellation_reason a span")).isEmpty()){
	            followupsPage.clickCancellationReasonDropdown();
	            followupsPage.selectCancellationReason();
	            followupsPage.clickCancelFollowupForPostSalesButton();
	        }
		  
		  Thread.sleep(3000);
		  
		  getExtTest().log(Status.INFO, "Selecting Date.......");
		  followupsPage.selectDate();
		  
		  
		  getExtTest().log(Status.INFO,
		  "Clicking on Schedule Followup Button .......");
		  followupsPage.clickOnScheduleFollowupButtonForPostSales();
		  
		  Thread.sleep(3000);
		  		  
		  getExtTest().log(Status.INFO, "Going back to dsahboard.......");
		  postsalesDashboardPage.clickOnHomeButton();
		  
		  Thread.sleep(1000);
		  
		  getExtTest().log(Status.INFO, "Refreshing the User Dashboard.......");
		  driver.findElement(postsalesDashboardPage.openTasksLink).click();
		  postsalesDashboardPage.refreshDashboardStats();
		  
		  Thread.sleep(5000);
		  
		  
		  String count_missedFollowupAfter =
		  driver.findElement(postsalesDashboardPage.missedFollowUps)
		  .getAttribute("innerHTML"); 
		  System.out.println(count_missedFollowupAfter);
		  
		  SoftAssert assertion = new SoftAssert();
		  
		  getExtTest().log(Status.INFO,
		  "Validating count is not same after scheduling Followup.......");
		  Assert.assertNotEquals(count_missedFollowupAfter, count_missedFollowupBefore,
		  "Count is same as before scheduling Followup");
		  
		  assertion.assertAll();
		 

	}
		
	}
}
