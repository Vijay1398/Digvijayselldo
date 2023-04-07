package otherLeadActivitiesPack;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class AddingInterestedPropertyTest extends SetUp {

	final static Logger logger = Logger.getLogger(AddingInterestedPropertyTest.class);

	// Description:

	@BeforeTest

	public void sales_presalesLogin() throws Exception {

		mysetUp();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		LoginPage login = new LoginPage(driver);

		logger.info("Logging in.......");
		login.login(property.getProperty("name") + "+" + property.getProperty("user_add_project_Test"),
				property.getProperty("password"));
	}

	@AfterTest

	public void endingTest() throws Exception {

		Thread.sleep(3000);

		logger.info("browser closed.......");
		driver.close();

	}

	@Test

	public void addingInterestedPropertyTest() throws Exception {

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		test = extent.createTest("Adding Project on Lead Test");
		setExtentTest(test);

		JavascriptExecutor jse = (JavascriptExecutor) driver;

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		SalesPresalesDashboardPage salesPresalesDashboard = new SalesPresalesDashboardPage(driver);

		LeadProfilePage leadProfilePage = new LeadProfilePage(driver);

		getExtTest().log(Status.INFO, "Going to All Lead List.......");
		salesPresalesDashboard.goToAllLeadsList();

		Thread.sleep(2000);

		getExtTest().log(Status.INFO, "Selecting Incoming list......");
		adminDashboardPage.SelectList("Incoming");

		getExtTest().log(Status.INFO, "Opening Lead Deatils Page.......");
		salesPresalesDashboard.openLeadDetails();
		
		Thread.sleep(2000);
		
		jse.executeScript("window.scrollBy(0,600)", "");

		getExtTest().log(Status.INFO, "selecting project to add on lead.......");
		leadProfilePage.selectProject();

	getExtTest().log(Status.INFO, "capturig project to be added on lead.......");
		String projectName = driver.findElement(leadProfilePage.projectSelected).getText();
		System.out.println(projectName);

		Thread.sleep(2000);

		getExtTest().log(Status.INFO, "Adding Project on lead.......");
		driver.findElement(leadProfilePage.addButton).click();

		Thread.sleep(2000);

		jse.executeScript("window.scrollTo(0,0)", "");

		getExtTest().log(Status.INFO, "Clicking on Feed tab.......");
		leadProfilePage.clickFeedTab();

		getExtTest().log(Status.INFO, "Fetching the text appeared after adding project....");
		String text = driver.findElement(By.cssSelector(
				"#tab-activity > div.activities_list > div:nth-child(1) > div > div.card > div > div > div > span:nth-child(1)"))
				.getText().trim(); 

		SoftAssert assertion = new SoftAssert();

		getExtTest().log(Status.INFO, "Verifying that Project " + projectName + " added successfully on lead....");
		Assert.assertEquals("Lead showed interest in " + projectName + "", text); 
		assertion.assertAll(); 

	}

}
