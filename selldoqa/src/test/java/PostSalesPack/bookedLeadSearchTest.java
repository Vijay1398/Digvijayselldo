package PostSalesPack;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import adminPages.AdminDashboardPage;
import crm.selldo.ClientLoginPage;
import crm.selldo.EditLeadFormPage;
import crm.selldo.LeadProfilePage;
import crm.selldo.LoginPage;
import crm.selldo.SalesPresalesDashboardPage;
import otherLeadActivitiesPack.SearchingLeadByAdminTest;
import postSales.PostSalesDashboardPage;
import utility.SetUp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class bookedLeadSearchTest extends SetUp {
  
	final static Logger logger = Logger.getLogger(SearchingLeadByAdminTest.class);

	// Description: Post Sales User will search existing lead by name, email,booking Id,lead Id and phone number

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

	public void searchingLeadByPostSalesManagerTest() throws Exception {
		
		
		test =  extent.createTest("searchingLeadByPostSalesManagerTest");
		setExtentTest(test);
		
		Properties property = new Properties(); FileInputStream fileInputObj = new
		FileInputStream( System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);
				 
		Thread.sleep(3000);
		
		PostSalesDashboardPage postsalesDashboardPage = new PostSalesDashboardPage(driver);
		
		getExtTest().log(Status.INFO, "Searching lead by Id.......");
		postsalesDashboardPage.searchLead(property.getProperty("postsales_booking_id"));
		
		String leadId = driver.findElement(By.xpath("//span[@name='lead_id']")).getAttribute("innerHTML")
				.replaceAll("\\s+", "");
		System.out.println(leadId);

		String leadFirstName = driver.findElement(By.cssSelector("span[name=\"first_name\"]")).getAttribute("innerHTML")
				.trim();
		System.out.println(leadFirstName);
		
		String leadLastName = driver.findElement(By.cssSelector("span[name=\"last_name\"]")).getAttribute("innerHTML")
				.trim();
		System.out.println(leadLastName);
				
		String leadName = leadFirstName+" "+leadLastName;
		
		getExtTest().log(Status.INFO, "Clicking on Edit Icon to open lead edit form.......");
		postsalesDashboardPage.clickOnEditIcon();

		
		String leadEmail = driver.findElement(By.xpath("//input[@name='primary_email_email']"))
				.getAttribute("value");
		System.out.println(leadEmail);

		String leadPhoneNumber = driver.findElement(By.xpath("//input[@name='primary_phone_ph_number']"))
				.getAttribute("placeholder");
		System.out.println(leadPhoneNumber);

		getExtTest().log(Status.INFO, "Closing Lead edit form.......");
		postsalesDashboardPage.clickOnCloseButton();
		
		getExtTest().log(Status.INFO, "Searching lead by Phone number......");
		postsalesDashboardPage.searchLead(leadPhoneNumber);

		Thread.sleep(2000);
		
		SoftAssert assertion = new SoftAssert();

		getExtTest().log(Status.INFO, "Validating Lead name......");
		Assert.assertEquals(driver.findElement(By.xpath("//tr[2]//td[3]//span[1]")).getAttribute("innerHTML").trim(),
				leadName, "Not matched");
		System.out.println(leadName);

		getExtTest().log(Status.INFO, "Searching lead by Email......");
		postsalesDashboardPage.searchLead(leadEmail);

		getExtTest().log(Status.INFO, "Validating Lead name......");
		Assert.assertEquals(driver.findElement(By.xpath("//tr[2]//td[3]//span[1]")).getAttribute("innerHTML").trim(),
				leadName, "Not matched");
		System.out.println(leadName);

		getExtTest().log(Status.INFO, "Searching lead by name......");
		postsalesDashboardPage.searchLead(leadFirstName);
		System.out.println(leadName);

		Thread.sleep(2000);

		getExtTest().log(Status.INFO, "Validating Lead name......");
		Assert.assertEquals(driver.findElement(By.xpath("//tr[2]//td[3]//span[1]")).getAttribute("innerHTML").trim(),
				leadName, "Not matched");

		assertion.assertAll();

		
	
	
	}

}
