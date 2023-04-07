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

public class AddingNewUnitTest extends SetUp {

	final static Logger logger = Logger.getLogger(AddingNewUnitTest.class);

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

	public void addingNewUnitTest() throws Exception {

		test = extent.createTest("addingNewUnitTest");
		setExtentTest(test);

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		Thread.sleep(3000);

		UnitsPage unitsPage = new UnitsPage(driver);

		NewUnitFormPage newUnitFormPage = new NewUnitFormPage(driver);

		SoftAssert assertion = new SoftAssert();

		test.log(Status.INFO, "Selecting Units by mouse hovering over Inventory icon.......");
		WebElement element = driver.findElement(By.cssSelector("i.ion-cube"));
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		driver.findElement(By.linkText("Units")).click();

		test.log(Status.INFO, "Cicking on New Unit button......");
		unitsPage.clickOnNewUnitButton();

		test.log(Status.INFO, "Entering Developers name to which Unit belongs......");
		newUnitFormPage.enterDeveloperName(property.getProperty("developer_name_addingNewUnitTest"));

		test.log(Status.INFO, "Entering Project name to which Unit belongs......");
		newUnitFormPage.enterProjectName(property.getProperty("project_name_addingNewUnitTest"));

		test.log(Status.INFO, "Entering Project Tower name to which Unit belongs......");
		newUnitFormPage.enterProjectTowerName(property.getProperty("tower_name_addingNewUnitTest"));

		test.log(Status.INFO, "Entering Floor Plan name to which Unit belongs......");
		newUnitFormPage.enterFloorPlanName(property.getProperty("floorPlan_name_addingNewUnitTest"));

		String unitName = property.getProperty("unit_name_addingNewUnitTest");

		test.log(Status.INFO, "Entering Unit name......");
		newUnitFormPage.enterUnitName(unitName);
		
		Thread.sleep(2000);

		test.log(Status.INFO, "Entering number of floors......");
		newUnitFormPage.enterNumberOfFloors(property.getProperty("numberOfFloors_addingNewUnitTest"));

		Thread.sleep(1000);
		
		test.log(Status.INFO, "Entering Base rate......");
		newUnitFormPage.enterUnitBaseRate(property.getProperty("baseRate_addingNewUnitTest"));

		test.log(Status.INFO, "Selecting number of Bedrooms......");
		newUnitFormPage.selectNumberOfBedrooms();

		test.log(Status.INFO, "Selecting number of Bathrooms......");
		newUnitFormPage.selectNumberOfBathrooms();

		test.log(Status.INFO, "Selecting Category......");
		newUnitFormPage.selectCategory();

		test.log(Status.INFO, "Selecting type......");
		newUnitFormPage.selectType();

		test.log(Status.INFO, "Entering Saleable area......");
		newUnitFormPage.enterSaleableArea(property.getProperty("saleableArea_addingNewUnitTest"));

		test.log(Status.INFO, "Entering Carpet area......");
		newUnitFormPage.enterCarpetArea(property.getProperty("carpetArea_addingNewUnitTest"));
		
		Thread.sleep(2000);

		test.log(Status.INFO, "Entering number of floors......");
		newUnitFormPage.enterNumberOfFloors(property.getProperty("numberOfFloors_addingNewUnitTest"));

		test.log(Status.INFO, "Clicking on Save button......");
		newUnitFormPage.clickOnSaveButton();

		Thread.sleep(2000);

		test.log(Status.INFO, "Verifying that project is added.......");
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='All Units']/following::label[1]")).getText(),
				unitName, "Not matched");
		assertion.assertAll();
	}

}
