package workingTestscripts1;

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
import crm.selldo.ClientLoginPage;
import crm.selldo.EditLeadFormPage;
import crm.selldo.LeadProfilePage;
import crm.selldo.LoginPage;
import crm.selldo.SalesPresalesDashboardPage;
import utility.SetUp;

public class SearchingLeadByAdminTest extends SetUp {

	final static Logger logger = Logger.getLogger(SearchingLeadByAdminTest.class);

	// Description: Admin will search existing lead by name, email, lead Id and phone number

	@BeforeTest

	public void sales_presalesLogin() throws Exception {

		mysetUp();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		LoginPage login = new LoginPage(driver);

		logger.info("Logging in to client page.......");
		login.login(property.getProperty("superadmin_name") + property.getProperty("superadmin_email"),
				property.getProperty("password"));

		ClientLoginPage clientLogin = new ClientLoginPage(driver);

		logger.info("Logging in to Admin/Support Home Page......");
		clientLogin.clientLogin(property.getProperty("test_client_name"));

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

	public void searchingLeadByAdminTest() throws Exception {

		test = extent.createTest("searchingLeadByAdminTest");
		setExtentTest(test);

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		Thread.sleep(3000);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		SalesPresalesDashboardPage salesPresalesDashboardPage = new SalesPresalesDashboardPage(driver);

		LeadProfilePage leadProfilePage = new LeadProfilePage(driver);

		EditLeadFormPage editLeadFormPage = new EditLeadFormPage(driver);

		getExtTest().log(Status.INFO, "Signing in as a user to fetch lead email, name, Id and phone number.......");
		adminDashboardPage.loginAsUser(property.getProperty("signInAs_user_searchingLeadByAdminTest"));

		getExtTest().log(Status.INFO, "Searching lead by Id.......");
		adminDashboardPage.searchLead(property.getProperty("lead_id_to_Search"));

		getExtTest().log(Status.INFO, "Clicking on Edit Icon to open lead edit form.......");
		leadProfilePage.clickOnEditIcon();

		String leadId = driver.findElement(By.xpath("//span[@name='lead_id']")).getAttribute("innerHTML")
				.replaceAll("\\s+", "");
		System.out.println(leadId);

		String leadName = driver.findElement(By.xpath("//span[@name='first_name']")).getAttribute("innerHTML").trim();
		System.out.println(leadName);

		String leadEmail = driver.findElement(By.xpath("//input[@name='primary_email_email']"))
				.getAttribute("placeholder");
		System.out.println(leadEmail);

		String leadPhoneNumber = driver.findElement(By.xpath("//input[@name='primary_phone_ph_number']"))
				.getAttribute("placeholder");
		System.out.println(leadPhoneNumber);

		getExtTest().log(Status.INFO, "Closing Lead edit form.......");
		editLeadFormPage.clickOnSaveButton();

		getExtTest().log(Status.INFO, "Going back to Admin page.......");
		leadProfilePage.backToAdmin();

		getExtTest().log(Status.INFO, "Searching lead by Id.......");
		adminDashboardPage.searchLead(leadId);

		SoftAssert assertion = new SoftAssert();

		getExtTest().log(Status.INFO, "Validating Lead Id......");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@name='lead_id']")).getAttribute("innerHTML")
				.replaceAll("\\s+", ""), leadId, "Not matched");

		getExtTest().log(Status.INFO, "Searching lead by Phone number......");
		adminDashboardPage.searchLead(leadPhoneNumber);

		Thread.sleep(2000);

		getExtTest().log(Status.INFO, "Validating Lead name......");
		Assert.assertEquals(driver.findElement(By.cssSelector("a.td-bold.goto_details")).getAttribute("innerHTML"),
				leadName, "Not matched");

		getExtTest().log(Status.INFO, "Searching lead by Email......");
		adminDashboardPage.searchLead(leadEmail);

		getExtTest().log(Status.INFO, "Validating Lead name......");
		Assert.assertEquals(driver.findElement(By.cssSelector("a.td-bold.goto_details")).getAttribute("innerHTML"),
				leadName, "Not matched");

		getExtTest().log(Status.INFO, "Searching lead by name......");
		adminDashboardPage.searchLead(leadName);

		Thread.sleep(2000);

		getExtTest().log(Status.INFO, "Validating Lead name......");
		Assert.assertEquals(driver.findElement(By.cssSelector("a.td-bold.goto_details")).getAttribute("innerHTML"),
				leadName, "Not matched");
		
		getExtTest().log(Status.INFO, "Exiting from filter form......");
		leadProfilePage.closeFilter();

		getExtTest().log(Status.INFO, "Opening Lead Deatils Page.......");
		salesPresalesDashboardPage.openLeadDetails();
		

		getExtTest().log(Status.INFO, "Validating text which says admin have no access to edit.....");
		Assert.assertEquals(driver.findElement(leadProfilePage.noAccessMessageText).getAttribute("innerHTML"),
				"You do not have access to make any changes to this lead.", "Not matched");

		assertion.assertAll();
		
	}
}
