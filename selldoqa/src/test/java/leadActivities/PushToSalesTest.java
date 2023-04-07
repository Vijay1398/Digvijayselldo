package leadActivities;

import java.io.FileInputStream;
import java.io.IOException;
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
import crm.selldo.ClientLoginPage;
import crm.selldo.LeadProfilePage;
import crm.selldo.LoginPage;
import crm.selldo.PushToSalesPage;
import crm.selldo.SalesPresalesDashboardPage;
import utility.SetUp;

public class PushToSalesTest extends SetUp {

	WebDriverWait wait;

	final static Logger logger = Logger.getLogger(PushToSalesTest.class);

	// Description: Pushing Lead from pre-sales to sales

	@BeforeTest

	public void adminLogin() throws Exception {

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

		ClientLoginPage clientLoginPage = new ClientLoginPage(driver);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		logger.info("Logging in to Admin/Support Home Page......");
		clientLoginPage.clientLogin(property.getProperty("client_name"));

		Thread.sleep(3000);

		logger.info("Verifying that client login is successfull.......");
		SoftAssert assertion = new SoftAssert();
		System.out.println("Started verification");
		Assert.assertEquals(driver.getTitle(), "Sell.Do - Market Smarter. Sell Faster.", "Title Not Matched");
		System.out.println("Completed verification");
		assertion.assertAll();
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

	public void pushingLeadTest_ForwardingOff() throws Exception {

		test = extent.createTest("pushingLeadTest_ForwardingOff");
		setExtentTest(test);

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		SalesPresalesDashboardPage salesPresalesDashboard = new SalesPresalesDashboardPage(driver);

		LeadProfilePage leadProfilePage = new LeadProfilePage(driver);

		getExtTest().log(Status.INFO, "Signing in as a sales user to which lead is to be pushed.......");
		adminDashboardPage.loginAsUser(property.getProperty("signInAs_sales_pushingLeadTest_ForwardingOff"));

		getExtTest().log(Status.INFO, "Refreshing the User Dashboard.......");
		salesPresalesDashboard.refreshDashboardStats();

		getExtTest().log(Status.INFO, "Getting count of Lead from Presales bucket......");
		WebElement count_b = driver.findElement(By.cssSelector(
				"#todays-performance-label > div.card-body > div.row.pt-2 > div > div > div:nth-child(1) > div.tile-item-body > span"));
		String leadCount_b = count_b.getText();
		System.out.println(leadCount_b);

		getExtTest().log(Status.INFO, "Going back to Admin page.......");
		leadProfilePage.backToAdmin();

		getExtTest().log(Status.INFO, "Signing in as a Presales user from which lead is to be pushed......");
		adminDashboardPage.loginAsUser(property.getProperty("signInAs_presales_pushingLeadTest_ForwardingOff"));

		getExtTest().log(Status.INFO, "Going to All Lead List.......");
		salesPresalesDashboard.goToAllLeadsList();

		Thread.sleep(2000);
		
		getExtTest().log(Status.INFO, "Selecting Prospect list......");
		adminDashboardPage.SelectList("Prospect");

		getExtTest().log(Status.INFO, "Opening Lead Deatils Page.......");
		salesPresalesDashboard.openLeadDetails();

		getExtTest().log(Status.INFO, "Getting Lead Id before Pushing.......");
		WebElement leadid_b = driver.findElement(By.cssSelector("span[name='lead_id']"));
		String leadId_b = leadid_b.getText();
		System.out.println(leadId_b);

		getExtTest().log(Status.INFO, "Selecting push from more dropdown......");
		leadProfilePage.selectPush();

		PushToSalesPage pushToSalesPage = new PushToSalesPage(driver);

		Thread.sleep(4000);

		getExtTest().log(Status.INFO, "Selecting Team of user to which lead is to be pushed......");
		pushToSalesPage.selectTeam(property.getProperty("team_pushingLeadTest_ForwardingOff"));

		Thread.sleep(3000);

		getExtTest().log(Status.INFO, "Selecting User to which lead is to be pushed......");
		pushToSalesPage.selectUser(property.getProperty("signInAs_sales_pushingLeadTest_ForwardingOff"));

		getExtTest().log(Status.INFO, "Clicking on Push Button.......");
		pushToSalesPage.clickOnPushButton();

		Thread.sleep(3000);

		getExtTest().log(Status.INFO, "Going back to Admin page.......");
		leadProfilePage.backToAdmin();

		getExtTest().log(Status.INFO, "Signing in as a Presales user.......");
		adminDashboardPage.loginAsUser(property.getProperty("signInAs_sales_pushingLeadTest_ForwardingOff"));

		getExtTest().log(Status.INFO, "Going to All Lead List.......");
		salesPresalesDashboard.goToAllLeadsList();

		getExtTest().log(Status.INFO, "Selecting From Presales list.......");
		adminDashboardPage.SelectList("From Pre Sales");
		
		Thread.sleep(2000);

		getExtTest().log(Status.INFO, "Opening Lead Deatils Page.......");
		salesPresalesDashboard.openLeadDetails();

		getExtTest().log(Status.INFO, "Getting Lead Id after pushing.......");
		WebElement leadid_a = driver.findElement(By.cssSelector("span[name='lead_id']"));
		String leadId_a = leadid_a.getText();
		System.out.println(leadId_a);

		SoftAssert assertion = new SoftAssert();

		getExtTest().log(Status.INFO, "Validating lead is pushed......."); 

		Assert.assertEquals(leadId_a, leadId_b, "Leads are not matching"); 

		getExtTest().log(Status.INFO, "Going to Users Dashboard.......");
		salesPresalesDashboard.selectLeadActions(2);

		getExtTest().log(Status.INFO, "Refreshing the User Dashboard.......");
		salesPresalesDashboard.refreshDashboardStats();
		
		Thread.sleep(3000);
		
		getExtTest().log(Status.INFO, "Getting count of Lead from Presales......");
		WebElement count_a = driver.findElement(By.cssSelector(
				"#todays-performance-label > div.card-body > div.row.pt-2 > div > div > div:nth-child(5) > div.tile-item-body > span"));
		String leadCount_a = count_a.getText();
		System.out.println(leadCount_a);

		getExtTest().log(Status.INFO, "Validating that count is not same after lead push.......");
		Assert.assertNotEquals(leadCount_b, leadCount_a, "Count is same as before pushing lead");

		assertion.assertAll();
	}
}
