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

public class AddingNewFloorPlanTest extends SetUp {

	final static Logger logger = Logger.getLogger(AddingNewFloorPlanTest.class);

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

	public void addingNewFloorPlanTest() throws Exception {

		test = extent.createTest("addingNewFloorPlanTest");
		setExtentTest(test);

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		Thread.sleep(3000);

		FloorPlansPage floorPlansPage = new FloorPlansPage(driver);

		NewFloorPlanFormPage newFloorPlanFormPage = new NewFloorPlanFormPage(driver);

		SoftAssert assertion = new SoftAssert();

		test.log(Status.INFO, "Selecting Floor Plans by mouse hovering over Inventory icon.......");
		WebElement element = driver.findElement(By.cssSelector("i.ion-cube"));
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		driver.findElement(By.linkText("Floor Plans")).click();

		test.log(Status.INFO, "Clicking on New Floor Plan button......");
		floorPlansPage.clickOnNewFloorPlanButton();

		test.log(Status.INFO, "Entering Developer name to which floor plan belongs......");
		newFloorPlanFormPage.enterDeveloperName(property.getProperty("developer_name_addingNewFloorPlanTest"));

		test.log(Status.INFO, "Entering Project name to which floor plan belongs.....");
		newFloorPlanFormPage.enterProjectName(property.getProperty("project_name_addingNewFloorPlanTest"));

		Thread.sleep(2000);

		test.log(Status.INFO, "Entering Project Tower name to which floor plan belongs.....");
		newFloorPlanFormPage.enterProjectTowerName(property.getProperty("tower_name_addingNewFloorPlanTest"));
		
		Thread.sleep(2000);

		String floorPlanName = property.getProperty("floorPlan_name_addingNewFloorPlanTest");

		test.log(Status.INFO, "Entering Floor Plan name.....");
		newFloorPlanFormPage.enterFloorPlanName(floorPlanName);
		Thread.sleep(2000);
		test.log(Status.INFO, "Selecting type......");
		newFloorPlanFormPage.selectType();
		Thread.sleep(2000);
		test.log(Status.INFO, "Selecting number of bedrooms......");
		newFloorPlanFormPage.selectNumberOfBedrooms();
		Thread.sleep(2000);
		test.log(Status.INFO, "Selecting number of bathrooms......");
		newFloorPlanFormPage.selectNumberOfBathrooms();
		Thread.sleep(2000);
		test.log(Status.INFO, "Selecting Category......");
		newFloorPlanFormPage.selectCategory();

	

		test.log(Status.INFO, "Entering Carpet area......");
		newFloorPlanFormPage.enterCarpetArea(property.getProperty("carpetArea_addingNewFloorPlanTest"));

		test.log(Status.INFO, "Entering Saleable area......");
		newFloorPlanFormPage.enterSaleableArea(property.getProperty("saleableArea_addingNewFloorPlanTest"));

		Thread.sleep(2000);

		test.log(Status.INFO, "Entering Covered area......");
		newFloorPlanFormPage.enterCoveredArea(property.getProperty("coveredArea_addingNewFloorPlanTest"));
		
		Thread.sleep(2000);
		
		test.log(Status.INFO, "Entering Terrace area......");
		newFloorPlanFormPage.enterTerraceArea("3476");

		Thread.sleep(2000);

		test.log(Status.INFO, "Entering Base rate.....");
		newFloorPlanFormPage.enterBaseRate(property.getProperty("baserate_addingNewFloorPlanTest"));

		test.log(Status.INFO, "......");
		newFloorPlanFormPage.clickOnSaveButton();

		Thread.sleep(2000);

		test.log(Status.INFO, "Verifying that project is added.......");
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='All Floor Plans']/following::label[1]")).getText(),
				floorPlanName, "Not matched");
		assertion.assertAll();

	}

}
