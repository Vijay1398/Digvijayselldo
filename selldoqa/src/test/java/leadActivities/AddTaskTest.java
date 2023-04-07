package leadActivities;

import utility.SetUp;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import adminPages.AdminDashboardPage;
import crm.selldo.LoginPage;
import crm.selldo.SalesPresalesDashboardPage;
import crm.selldo.TaskPage;

public class AddTaskTest extends SetUp {
  
final static Logger logger = Logger.getLogger(ChangingTaskStatusTest.class);
	
	WebDriverWait wait;

	// Description:Changing Task status from Open to Completed.
	
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
		login.login(property.getProperty("name") + "+" + property.getProperty("user_email_changingTaskStatusTest"),
				property.getProperty("password"));
	}
	
	@Test

	public void changingTaskStatusTest() throws Exception {

		wait = new WebDriverWait(driver, 8);
		Properties p = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		p.load(fileInputObj);

		test = extent.createTest("addTaskTest");
		setExtentTest(test);

		SalesPresalesDashboardPage salesPresalesDashboard = new SalesPresalesDashboardPage(driver);

		AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);

		getExtTest().log(Status.INFO, "Searching lead by Id.......");
		adminDashboardPage.searchLead(p.getProperty("Changing_Task_Status_lead_id"));

		getExtTest().log(Status.INFO, "Getting Lead Id before Adding Task.......");
		String leadId = driver.findElement(By.xpath("//span[@name='lead_id']")).getAttribute("innerHTML");
		System.out.println(leadId);


		TaskPage taskPage = new TaskPage(driver); 

		getExtTest().log(Status.INFO, "Opening Task Page.........");
		taskPage.AddTask();
		Thread.sleep(2000);
		System.out.println("Task Page Opened");
		
		String taskName = p.getProperty("taskName");
		
		getExtTest().log(Status.INFO, "Add Task Title.........");
		taskPage.AddTaskTitle(taskName);
		Thread.sleep(2000);
		System.out.println("Task Title Added");
		
		String taskDescription = p.getProperty("taskNameDescription");
		
		getExtTest().log(Status.INFO, "Add Task Description.........");
		taskPage.addTaskDescription(taskDescription);
		Thread.sleep(2000);
		System.out.println("Task Description Added");
		
				
		getExtTest().log(Status.INFO, "Clicking on Save button.........");
		taskPage.clickOnSaveButton();
		Thread.sleep(1000);

	}
	
	@AfterTest

	public void endingTest() throws Exception {

		Thread.sleep(3000);

		getExtTest().log(Status.INFO, "browser closed.......");
		driver.close();

	}
	

	
}
