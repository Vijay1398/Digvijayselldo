package inventory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class EditingProjectTest extends SetUp {

	final static Logger logger = Logger.getLogger(EditingProjectTest.class);

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

	public void editingProjectTest() throws Exception {

		test = extent.createTest("editingProjectTest");
		setExtentTest(test);

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		Thread.sleep(3000);

		ProjectsPage projectsPage = new ProjectsPage(driver);

		EditProjectFormPage editProjectFormPage = new EditProjectFormPage(driver);

		SoftAssert assertion = new SoftAssert();

		test.log(Status.INFO, "Selecting Projects by mouse hovering over Inventory icon.......");
		WebElement element = driver.findElement(By.cssSelector("i.ion-cube"));
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		driver.findElement(By.linkText("Projects")).click();

		test.log(Status.INFO, "Cicking on Funnel Icon.....");
		projectsPage.clickOnFunnelIcon();

		String projectName = property.getProperty("project_name_editingProjectTest");

		test.log(Status.INFO, "Entering Projects's name to be searched......");
		projectsPage.enterProjectName(projectName);

		test.log(Status.INFO, "Cicking on Filter button.....");
		projectsPage.clickOnFilterButton();

		test.log(Status.INFO, "Verifying that Project is searched......");
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='All Projects']/following::label[1]")).getText(),
				projectName, "Not matched");

		Thread.sleep(3000);

		String changedProjectName = property.getProperty("changedProject_name_editingProjectTest");

		test.log(Status.INFO, "Changing Project's name......");
		editProjectFormPage.changeProjectName(changedProjectName);

		test.log(Status.INFO, "Clicking on Save Button......");
		editProjectFormPage.clickOnSaveButton();

		Thread.sleep(2000);

		test.log(Status.INFO, "Verifying that Project is changed by new name......");
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='All Projects']/following::label[1]")).getText(),
				changedProjectName, "Not matched");

		test.log(Status.INFO, "Clicking on Address link......");
		editProjectFormPage.clickOnAddressLink();

		test.log(Status.INFO, "Verifying Address link.......");
		Assert.assertEquals(driver.findElement(By.xpath("//label[@for='project_address_attributes_address1']"))
				.getAttribute("innerHTML").trim(), "Address", "Not matched");

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on Virtual Walkthrough link......");
		editProjectFormPage.clickOnVirtualWalkThroughLink();

		test.log(Status.INFO, "Verifying Virtual Walkthrough link.......");
		Assert.assertEquals(
				driver.findElement(By.xpath("//label[@for='project_vr_id']")).getAttribute("innerHTML").trim(), "Vr",
				"Not matched");

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on Details link......");
		editProjectFormPage.clickOnDetailsLink();

		test.log(Status.INFO, "Verifying Details link.......");
		Assert.assertEquals(
				driver.findElement(By.xpath("//label[@for='s2id_autogen2']")).getAttribute("innerHTML").trim(),
				"Construction Status", "Not matched");

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on Specifications and Ameneties link......");
		editProjectFormPage.clickOnSpecificationAndAmenitiesLink();

		test.log(Status.INFO, "Verifying Address link.......");
		Assert.assertEquals(driver.findElement(By.xpath("//a[@class='add_spec btn btn-outline-primary']"))
				.getAttribute("innerHTML").trim(), "Add Specification", "Not matched");

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on Edit Price Quotes and Brochure link......");
		editProjectFormPage.clickOnEditPriceQuotesAndBrochureLink();

		test.log(Status.INFO, "Verifying Edit Price Quotes and Brochure link.......");
		Assert.assertEquals(
				driver.findElement(By.xpath("//label[text()='Brochure templates']")).getAttribute("innerHTML").trim(),
				"Brochure templates", "Not matched");

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on Upload Images link......");
		editProjectFormPage.clickOnUploadImagesLink();

		test.log(Status.INFO, "Verifying Upload Images link.......");
		Assert.assertEquals(
				driver.findElement(By.xpath("//a[@href='#elevations']	")).getAttribute("innerHTML").trim(),
				"Elevations", "Not matched");

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on Portal Integration Codes link......");
		editProjectFormPage.clickOnPortalIntegrationCodesLink();

		test.log(Status.INFO, "Verifying Portal Integration Codes link.......");
		Assert.assertEquals(
				driver.findElement(By.xpath("//label[@for='project_portals']")).getAttribute("innerHTML").trim(),
				"Portals", "Not matched");

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on Cost Templates link......");
		editProjectFormPage.clickOnCostTemplateLink();

		test.log(Status.INFO, "Verifying Cost Templates link.......");
		Assert.assertEquals(
				driver.findElement(By.xpath("//label[text()='Base rate']")).getAttribute("innerHTML").trim(),
				"Base rate", "Not matched");

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on Email Template link......");
		editProjectFormPage.clickOnEmailTemplateLink();

		test.log(Status.INFO, "Verifying Email Template link.......");
		Assert.assertEquals(driver.findElement(By.xpath("//a[@title='New']")).getAttribute("innerHTML").trim(),
				"New Template", "Not matched");

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on Sms Template link......");
		editProjectFormPage.clickOnSmsTemplateLink();

		test.log(Status.INFO, "Verifying Sms Template link.......");
		Assert.assertEquals(driver.findElement(By.xpath("//a[@title='New']")).getAttribute("innerHTML").trim(),
				"New Template", "Not matched");

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on Previous Demand Letters link......");
		editProjectFormPage.clickOnPreviousDemandLettersLink();

		test.log(Status.INFO, "Verifying send_demand_letters button.......");
		Assert.assertEquals(
				driver.findElement(By.xpath("//button[@class='btn btn-primary send_demand_letters disabled']"))
						.getAttribute("innerHTML").trim(),
				"Send demand letters", "Not matched");

		driver.navigate().back();
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");

		test.log(Status.INFO, "Clicking on View Developer link......");
		editProjectFormPage.clickOnViewDeveloperLink();

		test.log(Status.INFO, "Verifying View Developer link.......");
		Assert.assertEquals(
				driver.findElement(By.xpath("//div[@class='card-footer']//a")).getAttribute("innerHTML").trim(),
				"Add Project", "Not matched");

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on View Available Project Towers link......");
		editProjectFormPage.clickOnViewAvailableProjectTowersLink();

		test.log(Status.INFO, "Verifying View Towers in this project link.......");
		Assert.assertEquals(
				driver.findElement(By.xpath("//div[@class='btn-group']//a")).getAttribute("innerHTML").trim(),
				"New Tower", "Not matched");

		driver.navigate().back();

		test.log(Status.INFO, "Clicking on View Available floor plans link......");
		editProjectFormPage.clickOnViewAvailableFloorPlansLink();

		test.log(Status.INFO, "Verifying View Available floor plans link.......");
		Assert.assertEquals(
				driver.findElement(By.xpath("//div[@class='btn-group']//a")).getAttribute("innerHTML").trim(),
				"New Floor Plan", "Not matched");

		assertion.assertAll();

	}

}
