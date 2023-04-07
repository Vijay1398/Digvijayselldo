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

import adminPages.AdminDashboardPage;
import crm.selldo.LoginPage;
import dashboardBucketsPack.NewEnquiries_VerificationTest;
import postSales.PostSalesDashboardPage;
import utility.*;

public class cancelledBookingsBucketCheckTest extends SetUp {
	WebDriverWait wait;

	final static Logger logger = Logger.getLogger(NewEnquiries_VerificationTest.class);

	@BeforeTest

	public void postsales_PostsalesManagerLogin() throws Exception {

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

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		logger.info("Logging out of Selldo......");
		adminDashboardPage.loggingOut();

		logger.info("Closing Browser......");
		driver.close();
	}

	@Test

	public void cancelledBookingsCount_VerificationTest() throws Exception {

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		test = extent.createTest("cancelledBookingsCount_VerificationTest");
		setExtentTest(test);

		PostSalesDashboardPage postsalesDashboardPage = new PostSalesDashboardPage(driver);

		getExtTest().log(Status.INFO, "Refreshing the User Dashboard.......");
		postsalesDashboardPage.refreshDashboardStats();

		Thread.sleep(3000);

		getExtTest().log(Status.INFO, "Getting count of cancelled bookings bucket......");
		WebElement count_b = driver.findElement(By.xpath("//div[text()='Cancelled Booking Details']/following::span[1]"));
		String leadCount_b = count_b.getText();
		System.out.println(leadCount_b);

		getExtTest().log(Status.INFO, "Clicking on cancelled bookings.......");
		postsalesDashboardPage.clickOnCancelledBookingsBucket();

		// ..........Getting Confirmed Bookings Count from the label under List DropDownList.....
		
		Thread.sleep(3000);

		

		getExtTest().log(Status.INFO, "getting Cancelled Bookings Count from the label under List DropDownList...");
		WebElement count_a = driver.findElement(By.xpath("//span[@class='bookings-count-note']"));
		String leadCount_a = count_a.getText();
		System.out.println(leadCount_a);

		SoftAssert assertion = new SoftAssert();

		getExtTest().log(Status.INFO,
				"Validating count is not same after clicking on cancelled booking bucket......"); 
		
		Assert.assertNotEquals(leadCount_b,leadCount_a, "Count is same as before sending email");

		assertion.assertAll();

	}

}
