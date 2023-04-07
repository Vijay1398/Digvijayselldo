package inventory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import adminPages.AdminDashboardPage;
import crm.selldo.ClientLoginPage;
import crm.selldo.LoginPage;
import utility.SetUp;

public class EditingProjectTowerTest extends SetUp {

	final static Logger logger = Logger.getLogger(EditingProjectTowerTest.class);

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

		logger.info("Logging in to client page.......");
		login.login(property.getProperty("superadmin_name") + property.getProperty("superadmin_email"),
				property.getProperty("password"));

		ClientLoginPage clientLogin = new ClientLoginPage(driver);

		logger.info("Logging in to Admin/Support Home Page......");
		clientLogin.clientLogin(property.getProperty("client_name"));

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

	public void editingProjectTowerTest() throws Exception {

		test = extent.createTest("editingProjectTowerTest");
		
		setExtentTest(test);

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		Thread.sleep(3000);

		ProjectTowersPage projectTowersPage = new ProjectTowersPage(driver);

		EditProjectTowerFormPage editProjectTowerFormPage = new EditProjectTowerFormPage(driver);

		SoftAssert assertion = new SoftAssert();

		test.log(Status.INFO, "Selecting Project Towers by mouse hovering over Inventory icon.......");
		WebElement element = driver.findElement(By.cssSelector("i.ion-cube"));
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		driver.findElement(By.linkText("Project Towers")).click();

		test.log(Status.INFO, "Cicking on Funnel Icon.....");
		projectTowersPage.clickOnFunnelIcon();

		String projectTowerName = property.getProperty("projectTower_name_editingProjectTowerTest");

		test.log(Status.INFO, "Entering Project Tower's name to be searched......");
		projectTowersPage.enterProjectTowerName(projectTowerName);

		test.log(Status.INFO, "Cicking on Apply button.....");
		projectTowersPage.clickOnApplyButton();

		test.log(Status.INFO, "Verifying that Project Tower is searched......");
		Assert.assertEquals(
				driver.findElement(By.xpath("//a[text()='All Project Towers']/following::label[1]")).getText(),
				projectTowerName, "Not matched");

		Thread.sleep(3000);

		String changedProjectTowerName = property.getProperty("changedProjectTower_name_editingProjectTowerTest");

		test.log(Status.INFO, "Changing Project Tower's name......");
		editProjectTowerFormPage.changeProjectTowerName(changedProjectTowerName);

		test.log(Status.INFO, "Clicking on Save Button......");
		editProjectTowerFormPage.clickOnSaveButton();

		Thread.sleep(2000);

		test.log(Status.INFO, "Verifying that Project Tower is changed by new name......");
		Assert.assertEquals(
				driver.findElement(By.xpath("//a[text()='All Project Towers']/following::label[1]")).getText(),
				changedProjectTowerName, "Not matched");

		test.log(Status.INFO, "Clicking on Area and Costing link......");
		editProjectTowerFormPage.clickOnAreaAndCostingLink();

		test.log(Status.INFO, "Verifying Area and Costing link.......");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@for='project_tower_total_builtup_area']"))
				.getAttribute("innerHTML").trim(), "Total builtup area", "Not matched");

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on Other Details link......");
		editProjectTowerFormPage.clickOnOtherDetailsLink();

		test.log(Status.INFO, "Verifying Other Details link.......");
		Assert.assertEquals(
				driver.findElement(By.xpath("//label[@for='s2id_autogen2']")).getAttribute("innerHTML").trim(),
				"Approval", "Not matched");

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on Cost Template link......");
		editProjectTowerFormPage.clickOnCostTemplateLink();

		/*test.log(Status.INFO, "Verifying Cost Template link.......");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='col-md-10 col-lg-10 page-container']//a"))
				.getAttribute("innerHTML").trim(), "Add Extra Cost", "Not matched"); */

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on Payment Schedules link......");
		editProjectTowerFormPage.clickOnPaymentScheduleLink();

		test.log(Status.INFO, "Verifying Payment Schedules link.......");
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='New Schedule']")).getAttribute("innerHTML").trim(),
				"New Schedule", "Not matched");

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on Demand letters link......");
		editProjectTowerFormPage.clickOnDemandLettersLink();

		test.log(Status.INFO, "Verifying Demand letters link.......");
		Assert.assertEquals(
				driver.findElement(By.xpath("//button[@class='btn btn-primary send_demand_letters disabled']")).getAttribute("innerHTML").trim(),
				"Send demand letters", "Not matched");

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on View Available Floor plans link......");
		editProjectTowerFormPage.clickOnViewAvailableFloorPlansLink();

		test.log(Status.INFO, "Verifying View Available Floor plans link.......");
		Assert.assertEquals(driver.findElement(By.xpath("//a[@href='/client/unit_configurations/new']"))
				.getAttribute("innerHTML").trim(), "New Floor Plan", "Not matched");

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on All Project Towers link......");
		editProjectTowerFormPage.clickOnviewAvailableProjectTowersLink();

		test.log(Status.INFO, "Verifying All Project Towers link.......");
		Assert.assertEquals(driver.findElement(By.xpath("//a[@href='/client/project_towers/new']"))
				.getAttribute("innerHTML").trim(), "New Tower", "Not matched");

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on Add Project Tower link......");
		editProjectTowerFormPage.clickOnAddProjectTowersLink();

		test.log(Status.INFO, "Verifying Add Project Tower link.......");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@for='project_tower_tower_name']"))
				.getAttribute("innerHTML").trim(), "Tower name", "Not matched");

		driver.navigate().back();

		assertion.assertAll();
	}
}
