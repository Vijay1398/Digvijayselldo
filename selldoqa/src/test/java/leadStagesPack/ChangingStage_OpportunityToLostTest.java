package leadStagesPack;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class ChangingStage_OpportunityToLostTest extends SetUp {

	final static Logger logger = Logger.getLogger(ChangingStage_OpportunityToLostTest.class);

	// Description: Changing stage from Opportunity to Lost

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
				property.getProperty("name") + "+"
						+ property.getProperty("user_email_changingStage_OpportunityToLostTest"),
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

	public void changingStage_OpportunityToLostTest() throws Exception {

		test = extent.createTest("changingStage_OpportunityToLostTest");
		setExtentTest(test);
		
		WebDriverWait wait = new WebDriverWait(driver, 12);

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		SalesPresalesDashboardPage salesPresalesDashboard = new SalesPresalesDashboardPage(driver);

		getExtTest().log(Status.INFO, "Clicking on All Leads.......");
		salesPresalesDashboard.goToAllLeadsList();

		getExtTest().log(Status.INFO, "Selecting Opportunity list......");
		salesPresalesDashboard.SelectList("Opportunity");

		getExtTest().log(Status.INFO, "Opening Lead Details page of a lead under Opportunity stage......");
		salesPresalesDashboard.openLeadDetails();

		getExtTest().log(Status.INFO, "Getting Lead Id whose stage is to be changed.......");
		String leadIdObj = driver.findElement(By.cssSelector("span[name='lead_id']")).getText().replaceAll("\\s+", "");
		System.out.println(leadIdObj);

		LeadProfilePage leadProfilePage = new LeadProfilePage(driver);

		getExtTest().log(Status.INFO, "Changing stage from Opportunity to Lost.......");
		leadProfilePage.changing_Stage("Lost");
		
		getExtTest().log(Status.INFO, "Selecting reason for lost.......");
		leadProfilePage.selectReasonForLostOrUnqualified();

		Thread.sleep(2000);

		getExtTest().log(Status.INFO, "Going to dashboard.......");
		salesPresalesDashboard.selectLeadActions(2);

		wait.until(ExpectedConditions.presenceOfElementLocated(salesPresalesDashboard.searchField));

		getExtTest().log(Status.INFO, "Searching lead by Id.......");
		salesPresalesDashboard.searchLead(leadIdObj);
		
		Thread.sleep(2000);

		getExtTest().log(Status.INFO, "Verifying stage changed from Opportunity to Lost.......");

		String textDropdownObj = driver
				.findElement(By.xpath("//button[@class='btn dropdown-toggle btn-sm btn-outline-primary']//span"))
				.getAttribute("innerHTML").trim();
		System.out.println(textDropdownObj);

		SoftAssert assertion = new SoftAssert();
		System.out.println("Started verification");
		Assert.assertEquals(textDropdownObj, "Lost", "Not matched");
		System.out.println("Completed verification");
		assertion.assertAll();

	}

}
