package PostSalesPack;

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
import crm.selldo.LoginPage;
import crm.selldo.SalesPresalesDashboardPage;
import utility.GetTestData;
import utility.SetUp;
import postSales.*;

public class addingNewTaskTest extends SetUp {

	final static Logger logger = Logger.getLogger(addingNewTaskTest.class);

	
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
		login.login(property.getProperty("psManager_email"),
				property.getProperty("password"));
	}

	@AfterTest

	public void endingTest() throws Exception {

		Thread.sleep(3000);

		logger.info("browser closed.......");
		driver.close();

	}

	@Test

	public void addingNewTaskTest() throws Exception {

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		test = extent.createTest("Adding New Task on Lead Test");
		setExtentTest(test);

		JavascriptExecutor jse = (JavascriptExecutor) driver;

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		SalesPresalesDashboardPage salesPresalesDashboard = new SalesPresalesDashboardPage(driver);
		PostSalesDashboardPage postsalesDashboardPage = new PostSalesDashboardPage(driver);

		getExtTest().log(Status.INFO, "Clicking on confrimed bookings.......");
		postsalesDashboardPage.clickOnConfirmedBookingsBucket();

		Thread.sleep(2000);


		getExtTest().log(Status.INFO, "Opening Lead Deatils Page.......");
		postsalesDashboardPage.openLeadProfile();
		
		
		Thread.sleep(2000);
		
		jse.executeScript("window.scrollBy(0,600)", "");
		
		TaskPage taskPage = new TaskPage(driver);

		getExtTest().log(Status.INFO, "selecting task to add on lead.......");
		taskPage.addingTask();
		
		GetTestData getTestData = new GetTestData();
		
		getExtTest().log(Status.INFO, "Taking Task Title.......");
		String titleNameObj = getTestData.title;
		System.out.println(titleNameObj);
		taskPage.inputTasktitle(titleNameObj);

		getExtTest().log(Status.INFO, "Taking Task Description.......");
		String descriptionNameObj = getTestData.description;
		System.out.println(descriptionNameObj);
		taskPage.inputTaskDescription(descriptionNameObj);
		Thread.sleep(2000);
		getExtTest().log(Status.INFO, "Selecting Due Date.......");
		taskPage.selectDate();
		
		getExtTest().log(Status.INFO, "Selecting Sales User.......");
		taskPage.selectSalesUser(property.getProperty("task_sales_User"));
		
		
		/*
		 * getExtTest().log(Status.INFO, " Adding attachment for the task.......");
		 * taskPage.clickOnUploadAttachments();
		 */
		
		getExtTest().log(Status.INFO, "Clicking on Save Button.......");
		taskPage.clickOnSaveButton();

		
		Thread.sleep(2000);

		driver.navigate().refresh();
		
		Thread.sleep(5000);

		getExtTest().log(Status.INFO, "Fetching the text appeared after adding task....");
		String text = driver.findElement(By.xpath(
				"//span[contains(text(),'Get Document')]"))
				.getText().trim();
		text= text.replaceAll( "'Get Document'", "Get Document");
		System.out.println(text);
		
		
		String expectedString = "The task " + titleNameObj + " is assigned to "+property.getProperty("task_sales_User")+".";

		SoftAssert assertion = new SoftAssert();

		getExtTest().log(Status.INFO, "Verifying that Title " + titleNameObj + " added successfully on lead....");
		Assert.assertEquals(text,titleNameObj);
		assertion.assertAll();

	}

}