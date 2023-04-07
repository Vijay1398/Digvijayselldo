package otherLeadActivitiesPack;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
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
import crm.selldo.ReassignLeadPage;
import crm.selldo.SalesPresalesDashboardPage;
import utility.SetUp;

public class ReassigningLeadTest extends SetUp {

	WebDriverWait wait;
	Properties property;

	final static Logger logger = Logger.getLogger(ReassigningLeadTest.class);

	@BeforeTest

	public void adminLogin() throws Throwable {

		mysetUp();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		property = loadPropertyFile();

		LoginPage login = new LoginPage(driver);

		logger.info("Logging in to client page.......");
		login.login(property.getProperty("superadmin_name") + property.getProperty("superadmin_email"),
				property.getProperty("password"));

		ClientLoginPage clientLoginPage = new ClientLoginPage(driver);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		logger.info("Logging in to Admin/Support Home Page......");
		clientLoginPage.clientLogin(property.getProperty("test_client_name"));

		Thread.sleep(3000);

		logger.info("Verifying that client login is successfull.......");
		SoftAssert assertion = new SoftAssert();
		Assert.assertEquals(driver.getTitle(), "Sell.Do - Market Smarter. Sell Faster.", "Title Not Matched");
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
	public void reassigningLeadTest() throws Exception {

		test = extent.createTest("reassigningLeadTest");
		setExtentTest(test);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		getExtTest().log(Status.INFO, "Signing in as a Presales user.......");
		adminDashboardPage.loginAsUser(property.getProperty("signInAs_presales1_reassigningLeadTest"));

		SalesPresalesDashboardPage salesPresalesDashboard = new SalesPresalesDashboardPage(driver);

		getExtTest().log(Status.INFO, "Going to All Lead List.......");
		salesPresalesDashboard.goToAllLeadsList();

		getExtTest().log(Status.INFO, "Opening Lead Deatils Page.......");
		salesPresalesDashboard.openLeadProfile();

		LeadProfilePage leadProfilePage = new LeadProfilePage(driver);

		getExtTest().log(Status.INFO, "Getting Lead Id before assigning.......");
		String leadId = leadProfilePage.getLeadId();

		getExtTest().log(Status.INFO, "Selecting Reassign from more.......");
		leadProfilePage.selectReassign();

		ReassignLeadPage reassignLeadPage = new ReassignLeadPage(driver);

		Thread.sleep(4000);

		getExtTest().log(Status.INFO, "Selecting Team.......");
		reassignLeadPage.selectTeam(property.getProperty("team_reassigningLeadTest"));

		getExtTest().log(Status.INFO, "Selecting User.......");
		reassignLeadPage.selectUser(property.getProperty("signInAs_presales2_reassigningLeadTest"));

		getExtTest().log(Status.INFO, "Clicking on Reassign button.......");
		reassignLeadPage.clickOnReassignButton();

		Thread.sleep(3000);

		getExtTest().log(Status.INFO, "Going back to Admin page.......");
		leadProfilePage.backToAdmin();

		Thread.sleep(3000);

		getExtTest().log(Status.INFO, "Signing in as a Presales User.......");
		adminDashboardPage.loginAsUser(property.getProperty("signInAs_presales2_reassigningLeadTest"));

		Thread.sleep(3000);

		getExtTest().log(Status.INFO, "Going to All leads list.......");
		salesPresalesDashboard.goToAllLeadsList();

		getExtTest().log(Status.INFO, "Selecting Reassigned to me list.......");

		adminDashboardPage.SelectList("Reassigned To Me");

		getExtTest().log(Status.INFO, "Opening Lead Deatils Page.......");
		salesPresalesDashboard.openLeadDetails();

		getExtTest().log(Status.INFO, "Getting Lead Id after reassigning.......");
		String leadId_after = leadProfilePage.getLeadId();

		getExtTest().log(Status.INFO, "Validating lead is reassigned.......");
		SoftAssert assertion = new SoftAssert();
		Assert.assertEquals(leadId, leadId_after, "Leads are not matching");
		assertion.assertAll();
	}

}
