package adminPack;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import inventory.AddingNewUnitTest;
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

import adminPages.AdminDashboardPage;
import crm.selldo.LeadBookingFormPage;
import crm.selldo.LeadProfilePage;
import crm.selldo.LoginPage;
import crm.selldo.SalesPresalesDashboardPage;
import utility.SetUp;

public class BookingALead_TentativeTest extends SetUp {

	final static Logger logger = Logger.getLogger(BookingALead_TentativeTest.class);

	// Description: Booking a lead by marking booked from opportunity and
	// filling the booking form

	@BeforeTest

	public void appLogin() throws Exception {

		// Add new unit before booking a lead
		AddingNewUnitTest addingNewUnitTest = new AddingNewUnitTest();

		logger.info("Logging into client page............");
		addingNewUnitTest.sales_presalesLogin();

		logger.info("Adding new unit..........");
		addingNewUnitTest.addingNewUnitTest();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		logger.info("Logging out with this user..........");
		addingNewUnitTest.endingTest();
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

	public void bookingALead_TentativeTest() throws Exception {

		mysetUp();

		test = extent.createTest("bookingALead_TentativeTest");

		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);

		JavascriptExecutor jse = (JavascriptExecutor) driver;

		LeadProfilePage leadProfilePage = new LeadProfilePage(driver);

		SalesPresalesDashboardPage salesPresalesDashboard = new SalesPresalesDashboardPage(driver);


		LoginPage login = new LoginPage(driver);
		logger.info("Logging in with another user......");
		login.login(property.getProperty("name") + "+" + property.getProperty("user_email_bookingALead_ConfirmedTest"),
				property.getProperty("password"));
		test.log(Status.INFO, "Clicking on All Leads.......");
		salesPresalesDashboard.goToAllLeadsList();

		test.log(Status.INFO, "Selecting Booked list......");
		salesPresalesDashboard.SelectList("Opportunity");

		test.log(Status.INFO, "Opening Lead Details page of a lead under Booked stage......");
		salesPresalesDashboard.openLeadDetails();

		test.log(Status.INFO, "Getting Lead Id whose stage is to be changed.......");
		String leadIdObj = driver.findElement(By.cssSelector("span[name='lead_id']")).getText().replaceAll("\\s+", "");
		System.out.println(leadIdObj);

		Thread.sleep(4000);

		jse.executeScript("window.scrollBy(0,250)", "");

		test.log(Status.INFO, "Adding Inventory of a project.......");
		leadProfilePage.addingInventory();

		jse.executeScript("window.scrollBy(0,-250)", "");

		Thread.sleep(2000);

		test.log(Status.INFO, "Changing filters.......");
		leadProfilePage.changeFilters();

		test.log(Status.INFO, "Selecting unit to be booked.......");
		leadProfilePage.selectingUnit();

		Thread.sleep(2000);

		test.log(Status.INFO, "Marking booked.......");
		leadProfilePage.bookingUnit();

		LeadBookingFormPage leadBooking = new LeadBookingFormPage(driver);

		test.log(Status.INFO, "Adding Cost Details.................");
		leadBooking.addingCostDetails();

		test.log(Status.INFO, "Adding Basic Details...........");
		leadBooking.clickBasicDetails();

		test.log(Status.INFO, "Adding and removing secondary booking name.......");
		leadBooking.addingAndRemovingSecondaryBookingName(property.getProperty("SecondaryBookingName_BookingALead"));

		Thread.sleep(3000);

		jse.executeScript("window.scrollBy(0,250)", "");

		test.log(Status.INFO, "Taking Floor number.......");
		leadBooking.inputFloorNumber(property.getProperty("FloorNumber_BookingALead"));

		test.log(Status.INFO, "Taking Building name.......");
		leadBooking.inputBuildingName(property.getProperty("BuildingName_BookingALead"));

		test.log(Status.INFO, "Taking Carpet area.......");
		leadBooking.inputCarpetArea(property.getProperty("CarpetArea_BookingALead"));

		test.log(Status.INFO, "Taking Saleable area.......");
		leadBooking.inputSaleableArea(property.getProperty("SaleableArea_BookingALead"));

		test.log(Status.INFO, "Taking Parking number.......");
		leadBooking.inputParkingNumber(property.getProperty("ParkingNumber_BookingALead"));

		test.log(Status.INFO, "Selecting Bedroom preferences.......");
		leadBooking.inputBedroomPreferences(property.getProperty("BedroomPreferences_BookingALead"));

		test.log(Status.INFO, "Selecting Pan Number.......");
		leadBooking.inputPanNumber(property.getProperty("PanNumber_BookingALead"));

		test.log(Status.INFO, "Selecting Address and Street.......");
		leadBooking.inputAddressAndStreet(property.getProperty("Address_BookingALead"),
				property.getProperty("Street_BookingALead"));

		test.log(Status.INFO, "Selecting City and State.......");
		leadBooking.inputCityAndState(property.getProperty("City_BookingALead"),
				property.getProperty("State_BookingALead"));

		test.log(Status.INFO, "Selecting Country and Zip.......");
		leadBooking.inputCountryAndZip(property.getProperty("Zip_BookingALead"));

		test.log(Status.INFO, "Clicking on Save button............");
		leadBooking.clickOnSaveButton();

		Thread.sleep(4000);

		test.log(Status.INFO, "Selecting Bookings from more.......");
		leadProfilePage.selectBookings();

		Thread.sleep(2000);

		test.log(Status.INFO, "Validating Booking status .......");
		SoftAssert assertion = new SoftAssert();
		System.out.println("Started verification");
		String status  = driver.findElement(By.xpath("//span[@class='badge badge-success']")).getText().toLowerCase();
		Assert.assertEquals(status, "tentative",
				"Not matched");
		System.out.println("Booking status-" +  status);

		test.log(Status.INFO, "Validating 'Add new button' is present..........");
		Assert.assertEquals(driver.findElement(By.xpath("//button[text()='Add new Booking']")).getText(),
				"Add new Booking", "Not matched");

		test.log(Status.INFO, "Validating 'Close' button is present.........");
		Assert.assertEquals(
				driver.findElement(By.xpath("//button[@class='btn btn-default close_profile_form']")).getText().toLowerCase(),
				"close", "Not matched");
		System.out.println("Completed verification");

		test.log(Status.INFO, "Clicking on Close button of Booking details page.......");
		leadProfilePage.clickOnCloseButton_Bookingdetails();

		Thread.sleep(3000);

		test.log(Status.INFO, "Searching lead by Id.......");
		salesPresalesDashboard.searchLead(leadIdObj);

		test.log(Status.INFO, "Verifying stage changed from Incoming to Prospect.......");

		String textDropdownObj = driver
				.findElement(By.xpath("//button[@class='btn dropdown-toggle btn-sm btn-outline-primary']//span"))
				.getAttribute("innerHTML").trim();
		System.out.println(textDropdownObj);

		Assert.assertEquals(textDropdownObj, "Booked", "Not matched");

		assertion.assertAll();
	}
}
