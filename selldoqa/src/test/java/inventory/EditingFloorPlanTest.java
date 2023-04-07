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

import crm.selldo.ClientLoginPage;
import crm.selldo.LoginPage;
import utility.SetUp;

public class EditingFloorPlanTest extends SetUp {

	final static Logger logger = Logger.getLogger(EditingFloorPlanTest.class);

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

		logger.info("Closing Browser......");
		driver.close();
	}

	@Test

	public void editingFloorPlanTest() throws Exception {

		test = extent.createTest("editingFloorPlanTest");
		setExtentTest(test);

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		Thread.sleep(3000);

		FloorPlansPage floorPlansPage = new FloorPlansPage(driver);

		EditFloorPlanPage editFloorPlanPage = new EditFloorPlanPage(driver);

		SoftAssert assertion = new SoftAssert();

		test.log(Status.INFO, "Selecting Floor Plans by mouse hovering over Inventory icon.......");
		WebElement element = driver.findElement(By.cssSelector("i.ion-cube"));
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		driver.findElement(By.linkText("Floor Plans")).click();

		test.log(Status.INFO, "Cicking on Funnel Icon.....");
		floorPlansPage.clickOnFunnelIcon();

		String floorPlanName = property.getProperty("floorPlan_name_editingFloorPlanTest");

		test.log(Status.INFO, "Entering Floor Plan's name to be searched......");
		floorPlansPage.enterFloorPlanName(floorPlanName);

		test.log(Status.INFO, "Cicking on Filter button.....");
		floorPlansPage.clickOnFilterButton();

		test.log(Status.INFO, "Verifying that Floor plan is searched.......");
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='All Floor Plans']/following::label[1]")).getText(),
				floorPlanName, "Not matched");

		Thread.sleep(3000);

		String changedFloorPlanName = property.getProperty("changedFloorPlan_name_editingFloorPlanTest");

		test.log(Status.INFO, "Changing Project Tower's name......");
		editFloorPlanPage.changeProjectTowerName(changedFloorPlanName);

		test.log(Status.INFO, "Clicking on Save Button......");
		editFloorPlanPage.clickOnSaveButton();

		Thread.sleep(2000);

		test.log(Status.INFO, "Verifying that Project Tower is changed by new name......");
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='All Floor Plans']/following::label[1]")).getText(),
				changedFloorPlanName, "Not matched");

		test.log(Status.INFO, "Clicking on Area and Costing link......");
		editFloorPlanPage.clickOnAreaAndCostingLink();

		test.log(Status.INFO, "Verifying Area and Costing link.......");

		Assert.assertEquals(driver.findElement(By.xpath("//div[text()='Areas']")).getText().trim(), "Areas",
				"Not matched");


		driver.navigate().back();

		test.log(Status.INFO, "Clicking on Extras link......");
		editFloorPlanPage.clickOnExtrasLink();

		test.log(Status.INFO, "Verifying Extras link.......");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Category']")).getText().trim(), "CATEGORY",
				"Not matched");

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on Floor Plan Images link......");
		editFloorPlanPage.clickOnFloorPlanImagesLink();

		test.log(Status.INFO, "Verifying Floor Plan Images link.......");
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='image[]']")).getText().trim(), "",
				"Not matched");

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on Price Quotes and Brochure link......");
		editFloorPlanPage.clickOnPriceQuotesAndBrochureLink();

		test.log(Status.INFO, "Verifying Price Quotes and Brochure link.......");
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Add Brochure']")).getText().trim(), "Add Brochure",
				"Not matched");

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on Regions link......");
		editFloorPlanPage.clickOnRegionsLink();

		test.log(Status.INFO, "Verifying Regions link.......");
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='New Region']")).getText().trim(), "New Region",
				"Not matched");

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on Cost Template link......");
		editFloorPlanPage.clickOnCostTemplateLink();

		test.log(Status.INFO, "Verifying Cost Template link.......");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Project cost template']")).getText().trim(),
				"Project cost template", "Not matched");

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on View Available Project Units link......");
		editFloorPlanPage.clickOnViewUnitsLink();

		test.log(Status.INFO, "Verifying View Available Project Units link.......");
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='New Unit']")).getText().trim(), "New Unit",
				"Not matched");

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on Add Unit link......");
		editFloorPlanPage.clickOnAddUnitLink();

		test.log(Status.INFO, "Verifying Add Unit link.......");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@for='project_unit_developer']")).getText().trim(),
				"DEVELOPER", "Not matched");

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on All Floor Plans link......");
		editFloorPlanPage.clickOnAllFloorPlans();

		test.log(Status.INFO, "Verifying All Floor Plans link.......");
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='New Floor Plan']")).getText().trim(),
				"New Floor Plan", "Not matched");

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on Add Floor Plan link......");
		editFloorPlanPage.clickOnAddFloorPlan();

		test.log(Status.INFO, "Verifying Add Floor Plan link.......");
		Assert.assertEquals(
				driver.findElement(By.xpath("//a[text()='Home']/following::a[3]")).getText().trim(),
				"All Floor Plans", "Not matched");
		
		driver.navigate().back();

		//driver.switchTo().alert().accept();

		assertion.assertAll();
	}

}
