package otherLeadActivitiesPack;

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

import adminPages.AdminDashboardPage;
import crm.selldo.EditLeadFormPage;
import crm.selldo.LeadProfilePage;
import crm.selldo.LoginPage;
import crm.selldo.SalesPresalesDashboardPage;
import utility.SetUp;

public class SearchingLeadByUserTest extends SetUp {

	WebDriverWait wait;

	final static Logger logger = Logger.getLogger(SearchingLeadByUserTest.class);
	
	// Description: User will search existing lead by name, email, lead Id and phone number

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
		login.login(property.getProperty("name") + "+" + property.getProperty("email_user_searchingLeadByUserTest"),
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

	public void searchingLeadByUserTest() throws Exception {

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		test = extent.createTest("searchingLeadByUserTest");
		setExtentTest(test);

		SalesPresalesDashboardPage salesPresalesDashboardPage = new SalesPresalesDashboardPage(driver);

		LeadProfilePage leadProfilePage = new LeadProfilePage(driver);

		EditLeadFormPage editLeadFormPage = new EditLeadFormPage(driver);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		getExtTest().log(Status.INFO, "Searching lead by Id.......");
		adminDashboardPage.searchLead(property.getProperty("lead_id_to_Search"));

		getExtTest().log(Status.INFO, "Clicking on Edit Icon to open lead edit form.......");
		leadProfilePage.clickOnEditIcon();

		String leadId = driver.findElement(By.xpath("//span[@name='lead_id']")).getAttribute("innerHTML")
				.replaceAll("\\s+", "");
		System.out.println(leadId);

		String leadName = driver.findElement(By.cssSelector("span[name=\"first_name\"]")).getAttribute("innerHTML")
				.trim();
		System.out.println(leadName);

		String leadEmail = driver.findElement(By.xpath("//input[@name='primary_email_email']"))
				.getAttribute("placeholder");
		System.out.println(leadEmail);

		String leadPhoneNumber = driver.findElement(By.xpath("//input[@name='primary_phone_ph_number']"))
				.getAttribute("placeholder");
		System.out.println(leadPhoneNumber);

		getExtTest().log(Status.INFO, "Closing Lead edit form.......");
		editLeadFormPage.clickOnSaveButton();

		getExtTest().log(Status.INFO, "Searching lead by Id.......");
		salesPresalesDashboardPage.searchLead(leadId);

		SoftAssert assertion = new SoftAssert();

		getExtTest().log(Status.INFO, "Validating Lead Id......");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@name='lead_id']")).getAttribute("innerHTML")
				.replaceAll("\\s+", ""), leadId, "Not matched");

		getExtTest().log(Status.INFO, "Searching lead by Phone number......");
		salesPresalesDashboardPage.searchLead(leadPhoneNumber);

		Thread.sleep(2000);

		getExtTest().log(Status.INFO, "Validating Lead name......");
		Assert.assertEquals(driver.findElement(By.cssSelector("a.td-bold.goto_details")).getAttribute("innerHTML"),
				leadName, "Not matched");

		getExtTest().log(Status.INFO, "Searching lead by Email......");
		salesPresalesDashboardPage.searchLead(leadEmail);

		getExtTest().log(Status.INFO, "Validating Lead name......");
		Assert.assertEquals(driver.findElement(By.cssSelector("a.td-bold.goto_details")).getAttribute("innerHTML"),
				leadName, "Not matched");

		getExtTest().log(Status.INFO, "Searching lead by name......");
		salesPresalesDashboardPage.searchLead(leadName);

		Thread.sleep(2000);

		getExtTest().log(Status.INFO, "Validating Lead name......");
		Assert.assertEquals(driver.findElement(By.cssSelector("a.td-bold.goto_details")).getAttribute("innerHTML"),
				leadName, "Not matched");

		assertion.assertAll();

	}

}
