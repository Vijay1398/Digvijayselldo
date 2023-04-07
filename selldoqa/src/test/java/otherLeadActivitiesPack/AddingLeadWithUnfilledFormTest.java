package otherLeadActivitiesPack;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import adminPages.AdminDashboardPage;
import crm.selldo.AddLeadFormPage;
import crm.selldo.LoginPage;
import crm.selldo.SalesPresalesDashboardPage;
import utility.SetUp;

public class AddingLeadWithUnfilledFormTest extends SetUp {

	final static Logger logger = Logger.getLogger(AddingLeadWithUnfilledFormTest.class);

	// Description: Verifying error messages when Lead form without filling data
	// is submitted

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
				property.getProperty("name") + "+" + property.getProperty("user_email_addingLeadWithUnfilledFormTest"),
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

	public void addingLeadWithUnfilledFormTest() throws Exception {

		test = extent.createTest("addingLeadWithUnfilledFormTest");
		setExtentTest(test);

		SalesPresalesDashboardPage salesPresalesDashboard = new SalesPresalesDashboardPage(driver);

		getExtTest().log(Status.INFO, "Clicking on New Lead .......");
		salesPresalesDashboard.selectLeadActions(3);
		
		Thread.sleep(2000);

		AddLeadFormPage addLeadForm = new AddLeadFormPage(driver);

		getExtTest().log(Status.INFO, "Clicking on Save button.......");
		addLeadForm.clickOnSaveButton();

		SoftAssert assertion = new SoftAssert();

		getExtTest().log(Status.INFO, "Verifying error message below Primary Email Field.......");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Primary Email']/following::span[1]"))
				.getAttribute("innerHTML"), "Please set atleast a phone or email for this customer", "Not matched");

		getExtTest().log(Status.INFO, "Verifying error message below Primary Phone Field.......");
		Assert.assertEquals(
				driver.findElement(By.xpath("//label[text()='Tags']/preceding::p[1]")).getAttribute("innerHTML"),
				"Please set atleast a phone or email for this customer", "Not matched");
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,250)", "");

		getExtTest().log(Status.INFO, "Verifying error message below Project Field.......");
		
		Assert.assertEquals(driver
				.findElement(
						By.cssSelector("#basic_info > div.form-group.has-error > span"))
				.getAttribute("innerHTML"), "Please add a Project as its a walkin lead.", "Not matched");

		assertion.assertAll();

		getExtTest().log(Status.INFO, "Clicking on Close button.......");
		addLeadForm.clickOnCloseButton();

		driver.switchTo().alert().accept();

	}
}
