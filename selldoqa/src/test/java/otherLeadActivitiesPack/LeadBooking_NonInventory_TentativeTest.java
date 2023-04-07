package otherLeadActivitiesPack;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import adminPack.BookingALead_ConfirmedTest;
import adminPages.AdminDashboardPage;
import crm.selldo.LeadBookingFormPage;
import crm.selldo.LeadProfilePage;
import crm.selldo.LoginPage;
import crm.selldo.SalesPresalesDashboardPage;
import utility.SetUp;

public class LeadBooking_NonInventory_TentativeTest extends SetUp {

	final static Logger logger = Logger.getLogger(BookingALead_ConfirmedTest.class);

	// Description: Booking a lead of Non inventory client by marking booked from
	// opportunity and filling the booking form

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
		login.login(property.getProperty("name") + "+" + property.getProperty("user_email_nonInventory_TentativeTest"),
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

	public void bookingALead_ConfirmedTest() throws Exception {

		test = extent.createTest("Tentative Booking of Non inventory lead");
		setExtentTest(test);

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		JavascriptExecutor jse = (JavascriptExecutor) driver;

		LeadProfilePage leadProfilePage = new LeadProfilePage(driver);

		SalesPresalesDashboardPage salesPresalesDashboard = new SalesPresalesDashboardPage(driver);

		getExtTest().log(Status.INFO, "Clicking on All Leads.......");
		salesPresalesDashboard.goToAllLeadsList();

		getExtTest().log(Status.INFO, "Selecting Opportunity list......");
		salesPresalesDashboard.SelectList("Opportunity");

		getExtTest().log(Status.INFO, "Opening Lead Details page of a lead under Opportunity stage......");
		salesPresalesDashboard.openLeadDetails();

		getExtTest().log(Status.INFO, "Getting Lead Id whose stage is to be changed.......");
		String leadIdObj = driver.findElement(By.cssSelector("span[name='lead_id']")).getText().replaceAll("\\s+", "");
		System.out.println(leadIdObj);

		Thread.sleep(4000);

		getExtTest().log(Status.INFO, "Changing stage from Opportunity to Booked.......");
		leadProfilePage.changing_Stage("Booked");

		LeadBookingFormPage leadBooking = new LeadBookingFormPage(driver);

		getExtTest().log(Status.INFO, "Adding Phone Number.......");
		leadBooking.addPhoneNumber(property.getProperty("Applicant_Phone_Number"));

		Thread.sleep(3000);

		jse.executeScript("window.scrollBy(0,250)", "");

		getExtTest().log(Status.INFO, "Selecting Project....");
		leadBooking.selectProject(property.getProperty("Project_BookingALead"));

		getExtTest().log(Status.INFO, "Selecting Unit Type from drop.......");
		leadBooking.selectUnitType(property.getProperty("UnitType_BookingALead"));

		getExtTest().log(Status.INFO, "Taking Floor number.......");
		leadBooking.inputFloorNumber(property.getProperty("FloorNumber_BookingALead"));

		getExtTest().log(Status.INFO, "Taking Unit number.......");
		leadBooking.inputUnitNumber(property.getProperty("UnitNumber_BookingALead"));

		getExtTest().log(Status.INFO, "Taking Building name.......");
		leadBooking.inputBuildingName(property.getProperty("BuildingName_BookingALead"));

		getExtTest().log(Status.INFO, "Taking Carpet area.......");
		leadBooking.inputCarpetArea(property.getProperty("CarpetArea_BookingALead"));

		getExtTest().log(Status.INFO, "Taking Saleable area.......");
		leadBooking.inputSaleableArea(property.getProperty("SaleableArea_BookingALead"));

		getExtTest().log(Status.INFO, "Taking Parking number.......");
		leadBooking.inputParkingNumber(property.getProperty("ParkingNumber_BookingALead"));

		getExtTest().log(Status.INFO, "Selecting Bedroom preferences.......");
		leadBooking.inputBedroomPreferences(property.getProperty("BedroomPreferences_BookingALead"));

		getExtTest().log(Status.INFO, "Selecting Pan Number.......");
		leadBooking.inputPanNumber(property.getProperty("PanNumber_BookingALead"));

		getExtTest().log(Status.INFO, "Input Basic rate.......");
		leadBooking.inputBasicRate(property.getProperty("BasicRate_BookingALead"));

		getExtTest().log(Status.INFO, "Input Effective rate.......");
		leadBooking.inputEffectiveRate(property.getProperty("EffectiveRate_BookingALead"));

		getExtTest().log(Status.INFO, "Input offer.........");
		leadBooking.inputOffer(property.getProperty("InputOffer_BookingALead"));

		getExtTest().log(Status.INFO, "Selecting Address and Street.......");
		leadBooking.inputAddressAndStreet(property.getProperty("Address_BookingALead"),
				property.getProperty("Street_BookingALead"));

		getExtTest().log(Status.INFO, "Selecting City and State.......");
		leadBooking.inputCityAndState(property.getProperty("City_BookingALead"),
				property.getProperty("State_BookingALead"));

		getExtTest().log(Status.INFO, "Selecting Country and Zip.......");
		leadBooking.inputCountryAndZip(property.getProperty("Zip_BookingALead"));

		/*
		 * getExtTest().log(Status.INFO, "Adding Cost Details.......");
		 * leadBooking.addingCostDetails();
		 */

		getExtTest().log(Status.INFO, "Clicking on Save button............");
		leadBooking.clickOnSaveButton();

		Thread.sleep(4000);

		getExtTest().log(Status.INFO, "Selecting Bookings from more.......");
		leadProfilePage.selectBookings();

		Thread.sleep(2000);

		getExtTest().log(Status.INFO, "Validating Booking.......");
		SoftAssert assertion = new SoftAssert();
		System.out.println("Started verification");
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='badge badge-success']")).getText(), "tentative",
				"Not matched");
		Assert.assertEquals(driver.findElement(By.xpath("//button[text()='Add new Booking']")).getText(),
				"Add new Booking", "Not matched");
		Assert.assertEquals(
				driver.findElement(By.xpath("//button[@class='btn btn-default close_profile_form']")).getText(),
				"Close", "Not matched");
		System.out.println("Completed verification");

		Thread.sleep(1000);

		getExtTest().log(Status.INFO, "Clicking on Close button of Booking details page.......");
		leadProfilePage.clickOnCloseButton_Bookingdetails();

		Thread.sleep(3000);

		getExtTest().log(Status.INFO, "Searching lead by Id.......");
		salesPresalesDashboard.searchLead(leadIdObj);

		getExtTest().log(Status.INFO, "Verifying stage changed from Opportunity to Booked.......");

		String textDropdownObj = driver
				.findElement(By.xpath("//button[@class='btn dropdown-toggle btn-sm btn-outline-primary']//span"))
				.getAttribute("innerHTML").trim();
		System.out.println(textDropdownObj);

		Assert.assertEquals(textDropdownObj, "Booked", "Not matched");

		assertion.assertAll();
	}

}
