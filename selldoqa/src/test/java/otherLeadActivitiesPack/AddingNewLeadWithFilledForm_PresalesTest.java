package otherLeadActivitiesPack;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import adminPages.AdminDashboardPage;
import crm.selldo.AddLeadFormPage;
import crm.selldo.LoginPage;
import crm.selldo.SalesPresalesDashboardPage;
import utility.GetTestData;
import utility.SetUp;

public class AddingNewLeadWithFilledForm_PresalesTest extends SetUp {

	final static Logger logger = Logger.getLogger(AddingNewLeadWithFilledForm_PresalesTest.class);

	// Description: Adding a new lead from Pre sales by filling all the fields of
	// Add Lead form

	@BeforeTest

	public void appLogin() throws Exception {

		mysetUp();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		LoginPage login = new LoginPage(driver);
		logger.info("Logging in.......");
		login.login(
				property.getProperty("name") + "+"
						+ property.getProperty("user_email_addingNewLeadWithFilledForm_PresalesTest"),
				property.getProperty("password"));

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

	public void addingNewLeadWithFilledForm_PresalesTest() throws Exception {

		test = extent.createTest("addingNewLeadWithFilledForm_PresalesTest");
		setExtentTest(test);

		GetTestData getTestData = new GetTestData();

		SalesPresalesDashboardPage salesPresalesDashboard = new SalesPresalesDashboardPage(driver);

		getExtTest().log(Status.INFO, "Clicking on New Lead.......");
		salesPresalesDashboard.selectLeadActions(3);

		AddLeadFormPage addLeadForm = new AddLeadFormPage(driver);
		
		Thread.sleep(4000);

		getExtTest().log(Status.INFO, "Taking First Name.......");
		String firstNameObj = getTestData.firstName;
		addLeadForm.inputFirstName(firstNameObj);

		Thread.sleep(3000);

		getExtTest().log(Status.INFO, "Taking Last Name.......");
		String lastNameObj = getTestData.lastName;
		addLeadForm.inputLastName(lastNameObj);

		getExtTest().log(Status.INFO, "Taking Primary Email.......");
		addLeadForm.inputPrimaryEmail(getTestData.email);

		getExtTest().log(Status.INFO, "Taking Primary Phone.......");
		addLeadForm.inputPrimaryPhone(" " + getTestData.phoneNumber);

		Thread.sleep(1000);

		getExtTest().log(Status.INFO, "Selecting Interested project.......");
		// addLeadForm.selectingProjectsOfInterest();

		getExtTest().log(Status.INFO, "Selecting Lead Stage.......");
		addLeadForm.selectLeadStage();

		getExtTest().log(Status.INFO, "Selecting Team from dropdown.......");
		// addLeadForm.selectTeam(property.getProperty("Team_addingNewLeadWithFilledForm_SalesTest"));

		getExtTest().log(Status.INFO, "Selecting Campaign from dropdown.......");
		addLeadForm.selectCampaign();

		getExtTest().log(Status.INFO, "Selecting source from dropdown.......");
		addLeadForm.selectSource();

		getExtTest().log(Status.INFO, "Selecting Project.......");
		addLeadForm.selectProject();

		getExtTest().log(Status.INFO, "Taking Address.......");
		addLeadForm.inputAddress(getTestData.address);

		getExtTest().log(Status.INFO, "Taking Street.......");
		addLeadForm.inputStreet(getTestData.street);

		getExtTest().log(Status.INFO, "Taking City.......");
		addLeadForm.inputCity(getTestData.city);

		getExtTest().log(Status.INFO, "Taking State.......");
		addLeadForm.inputState(getTestData.state);

		getExtTest().log(Status.INFO, "Taking Zip.......");
		addLeadForm.inputZip(getTestData.zip);

		getExtTest().log(Status.INFO, "Taking minimum and maximum budget.......");
		addLeadForm.inputBudget(getTestData.minBudget, getTestData.maxBudget);

		getExtTest().log(Status.INFO, "Selecting minimum and maximum possession.......");
		addLeadForm.selectPossession();

		getExtTest().log(Status.INFO, "Selecting Property types.......");
		addLeadForm.selectPropertyTypes();

		getExtTest().log(Status.INFO, "Selecting Bedroom preferences.......");
		addLeadForm.selectBedroomPreferences();

		getExtTest().log(Status.INFO, "Taking Location preferences.......");
		// addLeadForm.inputLocatioPreferences(property.getProperty("LocationPreferences_addingNewLeadWithFilledForm_PresalesTest"));

		getExtTest().log(Status.INFO, "Clicking on Save button.......");
		addLeadForm.clickOnSaveButton();

		Thread.sleep(3000);

		getExtTest().log(Status.INFO, "Verifying Lead creation.......");
		SoftAssert assertion = new SoftAssert();
		System.out.println("Started verification");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@name='first_name']")).getText(),
				firstNameObj + " " + lastNameObj, "Not matched");
		System.out.println("Completed verification");
		assertion.assertAll();
	}

}
