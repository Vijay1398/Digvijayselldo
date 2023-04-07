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

public class AddingNewDeveloperTest extends SetUp {

	final static Logger logger = Logger.getLogger(AddingNewDeveloperTest.class);
	String developerName;

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

	public void addingNewDeveloperTest() throws Exception {

		test = extent.createTest("addingNewDeveloperTest");
		setExtentTest(test);

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		Thread.sleep(3000);

		DevelopersPage developersPage = new DevelopersPage(driver);

		NewDeveloperFormPage newDeveloperFormPage = new NewDeveloperFormPage(driver);

		SoftAssert assertion = new SoftAssert();

		test.log(Status.INFO, "Selecting Developres by mouse hovering over Inventory icon.......");
		WebElement element = driver.findElement(By.cssSelector("i.ion-cube"));
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		driver.findElement(By.linkText("Developers")).click();

		test.log(Status.INFO, "Cicking on New Developer button......");
		developersPage.clickOnNewDevekoperButton();

		Thread.sleep(2000);

		developerName = property.getProperty("developer_name_addingNewDeveloperTest");

		test.log(Status.INFO, "Entering Developer name......");
		newDeveloperFormPage.enterDeveloperName(developerName);
		
		
		Thread.sleep(2000);

		test.log(Status.INFO, "Entering some description about developer......");
		newDeveloperFormPage.enterDescription(property.getProperty("description_addingNewDeveloperTest"));
		
		Thread.sleep(2000);

		test.log(Status.INFO, "Entering Address......");
		newDeveloperFormPage.enterAddress(property.getProperty("address_addingNewDeveloperTest"));

		test.log(Status.INFO, "Entering Street......");
		newDeveloperFormPage.enterStreet(property.getProperty("street_addingNewDeveloperTest"));

		Thread.sleep(1000);

		String city = property.getProperty("city_addingNewDeveloperTest");

		test.log(Status.INFO, "Entering City......");
		newDeveloperFormPage.enterCity(city);
		
		test.log(Status.INFO, "Selecting Country......");
		newDeveloperFormPage.selectCountry(property.getProperty("country_addingNewDeveloperTest"));

		test.log(Status.INFO, "Selecting State......");
		newDeveloperFormPage.selectState(property.getProperty("state_addingNewDeveloperTest"));

		test.log(Status.INFO, "Entering Zip......");
		newDeveloperFormPage.enterZip(property.getProperty("zip_addingNewDeveloperTest"));

		Thread.sleep(1000);
		
		test.log(Status.INFO, "Selecting Salutation......");
		newDeveloperFormPage.selectSalutation();

		String name = property.getProperty("firstName_addingNewDeveloperTest");

		test.log(Status.INFO, "Entering First Name......");
		newDeveloperFormPage.enterFirstName(name);

		test.log(Status.INFO, "Entering Last Name......");
		newDeveloperFormPage.enterLastName(property.getProperty("lastName_addingNewDeveloperTest"));

		test.log(Status.INFO, "Entering Phone number......");
		newDeveloperFormPage.enterPhoneNumber(" " + property.getProperty("phone_addingNewDeveloperTest"));

		test.log(Status.INFO, "Entering Email......");
		newDeveloperFormPage.enterEmail(name + "@" + "gmail.com");

		test.log(Status.INFO, "Clicking on Save button......");
		newDeveloperFormPage.clickOnSaveButton();
		
		
		Thread.sleep(2000);
		
		developerName = driver
				.findElement(By.xpath("//div[@class='page-container secondary-nav']/label[1]")).getText();


		test.log(Status.INFO, "Verifying that developer is added from Edit form.......");
		Assert.assertEquals(driver.findElement(By.xpath("/html[1]/body[1]/div[5]/div[1]/div[1]/div[1]/nav[1]/ol[1]/li[4]")).getText(),
				developerName, "Not matched");
		System.out.println(developerName);
		

//		assertion.assertAll();
	}
}
