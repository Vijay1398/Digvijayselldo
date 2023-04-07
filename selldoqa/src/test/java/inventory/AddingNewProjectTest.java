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

public class AddingNewProjectTest extends SetUp {

	final static Logger logger = Logger.getLogger(AddingNewProjectTest.class);
	String projectName;

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

	public void addingNewProjectTest() throws Exception {

		test = extent.createTest("addingNewProjectTest");
		setExtentTest(test);

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		Thread.sleep(3000);

		ProjectsPage projectsPage = new ProjectsPage(driver);

		NewProjectFormPage newProjectFormPage = new NewProjectFormPage(driver);

		SoftAssert assertion = new SoftAssert();

		test.log(Status.INFO, "Selecting Projects by mouse hovering over Inventory icon.......");
		WebElement element = driver.findElement(By.cssSelector("i.ion-cube"));
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		driver.findElement(By.linkText("Projects")).click();

		test.log(Status.INFO, "Cicking on New Project button......");
		projectsPage.clickOnNewProjectButton();

		NewDeveloperFormPage newDeveloperFormPage = new NewDeveloperFormPage(driver);
		
		projectName = property.getProperty("project_name_addingNewProjectTest");
		projectName = (projectName + newDeveloperFormPage.generateRandomString()).toString();
		System.out.println(projectName);

		Thread.sleep(4000);
		test.log(Status.INFO, "Entering Project name......");
		newProjectFormPage.enterProjectName(projectName);

		String developerName = property.getProperty("developer_name_addingNewProjectTest");

		test.log(Status.INFO, "Entering Developer name to which Project belongs......");
		newProjectFormPage.enterDeveloperName(developerName);

		test.log(Status.INFO, "Entering some description......");
		newProjectFormPage.enterDescription(property.getProperty("description_addingNewProjectTest"));

		Thread.sleep(3000);

		test.log(Status.INFO, "Selecting Sales user......");
		newProjectFormPage.selectSales();

		test.log(Status.INFO, "Selecting presales user......");
		newProjectFormPage.selectPresales();

		//test.log(Status.INFO, "Selecting Postsales user.....");
		//newProjectFormPage.selectPostsales();

		test.log(Status.INFO, "Selecting Possession date......");
		newProjectFormPage.selectPossessionDate();

		test.log(Status.INFO, "Selecting Project type......");
		newProjectFormPage.selectProjectType();

		test.log(Status.INFO, "Entering Locality.....");
		newProjectFormPage.enterLocality(property.getProperty("locality_addingNewProjectTest"));

		test.log(Status.INFO, "Entering Latitude.....");
		newProjectFormPage.enterLatitude(property.getProperty("latitude_addingNewProjectTest"));

		test.log(Status.INFO, "Entering longitude......");
		newProjectFormPage.enterLongitude(property.getProperty("longitude_addingNewProjectTest"));

		test.log(Status.INFO, "Entering Address......");
		newProjectFormPage.enterAddress(property.getProperty("address_addingNewProjectTest"));

		test.log(Status.INFO, "Entering Street......");
		newProjectFormPage.enterStreet(property.getProperty("street_addingNewProjectTest"));

		test.log(Status.INFO, "Entering City......");
		newProjectFormPage.enterCity(property.getProperty("city_addingNewProjectTest"));
		
		test.log(Status.INFO, "Selecting Country......");
		newProjectFormPage.selectCountry(property.getProperty("country_addingNewProjectTest"));

		test.log(Status.INFO, "Selecting State......");
		newProjectFormPage.selectState(property.getProperty("state_addingNewProjectTest"));

		test.log(Status.INFO, "Entering Zip......");
		newProjectFormPage.enterZip(property.getProperty("zip_addingNewProjectTest"));

		test.log(Status.INFO, "Clicking on Save button......");
		newProjectFormPage.clickOnSaveButton();

		Thread.sleep(2000);

		test.log(Status.INFO, "Verifying that project is added.......");
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='All Projects']/following::label[1]")).getText(),
				projectName, "Not matched");
		assertion.assertAll();

	}

}
