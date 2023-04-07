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

public class EditingUnitTest extends SetUp {

	final static Logger logger = Logger.getLogger(EditingUnitTest.class);

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

	public void editingUnitTest() throws Exception {

		test = extent.createTest("editingUnitTest");
		setExtentTest(test);

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		Thread.sleep(3000);

		UnitsPage unitsPage = new UnitsPage(driver);

		EditUnitPage editUnitPage = new EditUnitPage(driver);

		SoftAssert assertion = new SoftAssert();

		test.log(Status.INFO, "Selecting Units by mouse hovering over Inventory icon.......");
		WebElement element = driver.findElement(By.cssSelector("i.ion-cube"));
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		driver.findElement(By.linkText("Units")).click();

		test.log(Status.INFO, "Cicking on Funnel Icon.....");
		unitsPage.clickOnFunnelIcon();

		String unitName = property.getProperty("unit_name_editingUnitTest");

		test.log(Status.INFO, "Entering Units's name to be searched......");
		unitsPage.enterUnitName(unitName);

		test.log(Status.INFO, "Cicking on Filter button.....");
		unitsPage.clickOnApplyButton();

		test.log(Status.INFO, "Verifying that Unit is searched.......");
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='All Units']/following::label[1]")).getText(),
				unitName, "Not matched");

		Thread.sleep(3000);

		String changedUnitName = property.getProperty("changedUnit_name_editingUnitTest");

		test.log(Status.INFO, "Changing Unit's name......");
		editUnitPage.changeUnitName(changedUnitName);

		test.log(Status.INFO, "Clicking on Save Button......");
		editUnitPage.clickOnSaveButton();

		Thread.sleep(2000);

		test.log(Status.INFO, "Verifying that Unit is changed by new name......");
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='All Units']/following::label[1]")).getText(),
				changedUnitName, "Not matched");

		test.log(Status.INFO, "Clicking on Details link......");
		editUnitPage.clickOnDetailsLink();

		test.log(Status.INFO, "Verifying Details link.......");
		Assert.assertEquals(
				driver.findElement(By.xpath("//label[@for='s2id_autogen4']")).getAttribute("innerHTML").trim(),
				"Bedrooms", "Not matched");

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on Area and Costing link......");
		editUnitPage.clickOnAreaAndCostingLink();

		test.log(Status.INFO, "Verifying Area and Costing link.......");
		Assert.assertEquals(driver.findElement(By.xpath("//button[@id='dropdownMenu1']")).getText().trim(), "Add cost",
				"Not matched");

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on Extras link......");
		editUnitPage.clickOnExtrasLink();

		test.log(Status.INFO, "Verifying Extras link.......");
		Assert.assertEquals(
				driver.findElement(By.xpath("//label[@for='s2id_autogen2']")).getAttribute("innerHTML").trim(),
				"Category", "Not matched");

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on Cost Template link......");
		editUnitPage.clickOnCostTemplateLink();

		test.log(Status.INFO, "Verifying Cost Template link.......");
		Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Preview']")).getAttribute("innerHTML").trim(),
				"Preview Cost Sheet", "Not matched");

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on View Floor Plan link......");
		editUnitPage.clickOnViewFloorPlanLink();

		test.log(Status.INFO, "Verifying View Floor Plan link.......");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@for='unit_configuration_developer']"))
				.getAttribute("innerHTML").trim(), "Developer", "Not matched");

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on All Units link......");
		editUnitPage.clickOnAllUnitsLink();

		test.log(Status.INFO, "Verifying All Units link.......");
		Assert.assertEquals(
				driver.findElement(By.xpath("//a[@href='/client/project_units/new']")).getAttribute("innerHTML").trim(),
				"New Unit", "Not matched");

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on Add Unit link......");
		editUnitPage.clickOnAddUnitLink();

		test.log(Status.INFO, "Verifying Add Unit link.......");
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Add Another Unit']"))
				.getAttribute("innerHTML").trim(), "Add Another Unit", "Not matched");

		driver.navigate().back();

		assertion.assertAll();
	}
}
