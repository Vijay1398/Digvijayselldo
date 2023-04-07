package otherLeadActivitiesPack;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;

import adminPages.AdminDashboardPage;
import crm.selldo.LoginPage;
import crm.selldo.SalesPresalesDashboardPage;
import utility.SetUp;

public class MarkingOnBreakTest extends SetUp {

	final static Logger logger = Logger.getLogger(MarkingOnBreakTest.class);

	// Description: Marking on break for client with different break types

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
		login.login(property.getProperty("name") + "+" + property.getProperty("user_email_markingOnBreakTest"),
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

	public void markingOnBreakTest() throws Exception {

		Thread.sleep(3000);

		test = extent.createTest("markingOnBreakTest");
		setExtentTest(test);

		SalesPresalesDashboardPage salesPresalesDashboardPage = new SalesPresalesDashboardPage(driver);

		if (!driver.findElement(By.cssSelector("span.status")).getText().contains("Available")) {

			getExtTest().log(Status.INFO, "check availability of user.......");
			salesPresalesDashboardPage.availableForCall();
			salesPresalesDashboardPage.pageRefresh();
			Thread.sleep(2000);
		}

		getExtTest().log(Status.INFO, "Selecting Mark On Break.......");
		salesPresalesDashboardPage.selectMarkOnBreak();

		salesPresalesDashboardPage.pageRefresh();

		Thread.sleep(2000);

		getExtTest().log(Status.INFO, "Verifying You are on break link.......");
		salesPresalesDashboardPage.validatingOnBreakLink();

		salesPresalesDashboardPage.pageRefresh();

		Thread.sleep(2000);

		getExtTest().log(Status.INFO, "Clicking on You are on break link......");
		salesPresalesDashboardPage.availableForCall();

		salesPresalesDashboardPage.pageRefresh();

		Thread.sleep(2000);

		getExtTest().log(Status.INFO, "Verifying User Available link.......");
		salesPresalesDashboardPage.validatingAvailableLink();

	}

}
