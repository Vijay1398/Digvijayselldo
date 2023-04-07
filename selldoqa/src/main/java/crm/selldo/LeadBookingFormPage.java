package crm.selldo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import utility.SetUp;

public class LeadBookingFormPage extends SetUp {

	By addAnotherLink = By.xpath("//a[text()='+ add another']");
	By removeLink = By.xpath("//a[text()='- remove']");
	By phoneNumber = By.name("phone");
	By secondaryBookingNameField = By.xpath("//label[text()='Secondary booking name']/following::input[1]");
	By stageDropdown = By.xpath("//select[@name='stage']");
	By projectDropdown = By.xpath("//label[text()='Stage']/following::span[1]");
	By projectDropdownInputField = By.xpath(".//*[@id='select2-drop']/div/input");
	By project_dd = By.xpath(".//*[@id='select2-drop']/ul/li[1]/div");
	By projectDropdown_All = By.xpath(".//*[@id='select2-drop']");
	By unitTypeDropdown = By.xpath("//label[text()='Floor number']/preceding::span[2]");
	By unitTypeInputField = By.xpath(".//*[@id='select2-drop']/div/input");
	By unitType_dd = By.xpath(".//*[@id='select2-drop']/ul/li[1]/div");
	By floorNumberField = By.xpath("//input[@name='floor_number']");
	By unitNumberField = By.xpath("//input[@name='unit_number']");
	By buildingName = By.xpath("//input[@name='building_name']");
	By carpetAreaField = By.xpath("//input[@name='carpet_area']");
	By saleableAreaField = By.xpath("//input[@name='saleable_area']");
	By parkingNumberField = By.xpath("//label[text()='Parking number']/following::input[1]");
	By parkingNumber_dd = By.xpath(".//*[@id='select2-drop']/ul/li/div");
	By bedroomPreferencesDropdown = By.xpath("//label[text()='Bedroom Preferences']/following::input[1]");
	By bedroomPreferences_dd = By.xpath(".//*[@id='select2-drop']/ul/li/div/span");
	By cancelScheduledOrPendingFollowupOnThisLead_Checkbox = By.xpath("//input[@class='cancel_followups']");
	By cancelUnattendedSiteVisitsOnAllProjectsForThisLead_Checkbox = By.xpath("//input[@class='cancel_site_visits']");
	By bookingDate = By.xpath("//input[@name='booking_date']");
	By bookingDate_All = By.xpath(
			"//div[@class='datepicker datepicker-dropdown dropdown-menu datepicker-orient-left datepicker-orient-bottom']//td");
	By panNumber = By.xpath("//input[@name='pan_number']");
	By basicRateFeild = By.xpath("//input[@name='basic_rate']");
	By effectiveRate = By.xpath("//input[@name='effective_rate']");
	By offerField = By.xpath("//input[@name='offer']");
	By addressDropdown = By.xpath("//select[@name='address_type']");
	By addressField = By.xpath("//input[@name='address1']");
	By streetField = By.xpath("//input[@name='address2']");
	By cityField = By.xpath("//input[@name='city']");
	By stateField = By.xpath("//select[@name='state']");
	By countryDropdown = By.xpath("//select[@name='country']");
	By zipField = By.xpath("//input[@name='zip']");
	By addCostDetailsDropdown = By.xpath("//button[@class='btn btn-primary dropdown-toggle']");
	By addCDD_All = By.xpath("//ul[@class='dropdown-menu add_link cost_details']//li");
	By parking = By.xpath("//label[text()='Parking']");
	By parking_a = By.xpath("//label[text()='Parking']/following::input[@name='booking[cost_details][amount]']");
	By parking_r = By.xpath("//label[text()='Parking']/following::input[@name='booking[cost_details][remark]']");
	By premim_a = By.xpath("//label[text()='Premium']/following::input[1]");
	By costDetails = By.cssSelector("#booking-detail-modal > div > div > div.modal-body.mt-4.booking_form > ul > li.show_lead_profile.nav-item");
	By selectPaymentSchedule = By.xpath("//span[text()='Select payment schedule']");
	By selectPaymentSchedule_dd = By.xpath(
			"//*[@id=\"select2-drop\"]/ul/li");
	By closeButton = By.xpath("//button[@class='pull-right btn btn-primary save']/preceding::button[1]");
	By saveButton = By.xpath("//button[@class='pull-right btn btn-primary save save-booking-detail']");
	By basicDetails = By.xpath("//*[@id=\"booking-detail-modal\"]/div/div/div[2]/ul/li[1]/a");


	WebDriver driver = null;

	public LeadBookingFormPage(WebDriver driver) {

		this.driver = driver;
	}
	// Methods for searching User

	public void addingAndRemovingSecondaryBookingName(String bookingName) throws InterruptedException {
		driver.findElement(addAnotherLink).click();
		Thread.sleep(2000);
		SoftAssert assertion = new SoftAssert();
		System.out.println("Started verification");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Secondary booking name']")).getText(),
				"Secondary booking name", "Not matched");
		System.out.println("Completed verification");
		assertion.assertAll();
		driver.findElement(secondaryBookingNameField).sendKeys(bookingName);
		// Thread.sleep(2000);
		driver.findElement(secondaryBookingNameField).clear();
		// Thread.sleep(2000);
		driver.findElement(removeLink).click();
	}

	public void selectStage(String bookingType) {
		Select oSelect = new Select(driver.findElement(stageDropdown));
		oSelect.selectByVisibleText("Confirmed");
	}
	
	public void addPhoneNumber(String phone) {
		driver.findElement(phoneNumber).sendKeys(phone);
	}

	public void selectProject(String project) {
		driver.findElement(projectDropdown).click();
		driver.findElement(projectDropdownInputField).sendKeys(project);
		driver.findElement(project_dd).click();
	}

	public void selectUnitType(String unit) {
		driver.findElement(unitTypeDropdown).click();
		driver.findElement(unitTypeInputField).sendKeys(unit);
		driver.findElement(unitType_dd).click();
	}

	public void inputFloorNumber(String floor) {
		driver.findElement(floorNumberField).sendKeys(floor);
	}

	public void inputUnitNumber(String unit) {
		driver.findElement(unitNumberField).sendKeys(unit);
	}

	public void inputBuildingName(String building) {
		driver.findElement(buildingName).sendKeys(building);
	}

	public void inputCarpetArea(String carpet) {
		driver.findElement(carpetAreaField).sendKeys(carpet);
	}

	public void inputSaleableArea(String saleable) {
		driver.findElement(saleableAreaField).sendKeys(saleable);
	}

	public void inputParkingNumber(String parking) {
		driver.findElement(parkingNumberField).sendKeys(parking);
		driver.findElement(parkingNumber_dd).click();
	}

	public void inputBedroomPreferences(String parking) {
		driver.findElement(bedroomPreferencesDropdown).sendKeys(parking);
		driver.findElement(bedroomPreferences_dd).click();
	}

	public void selectBookingDate() throws InterruptedException {
		driver.findElement(bookingDate).click();
		Thread.sleep(2000);
		// It will store all web elements in List
		List<WebElement> dates = driver.findElements(bookingDate_All);
		System.out.println("a");
		int total_node = dates.size();
		System.out.println("b");
		for (int i = 0; i < total_node; i++) {
			String date = dates.get(i).getText();
			System.out.println("c");
			if (date.equals("20")) {
				dates.get(i).click();// Clicking on date selected above
				break;
			}
		}
	}

	public void inputPanNumber(String pan) {
		driver.findElement(panNumber).sendKeys(pan);
	}

	public void inputBasicRate(String basicRate) {
		driver.findElement(basicRateFeild).sendKeys(basicRate);
	}

	public void inputEffectiveRate(String efectiveRate) {
		driver.findElement(effectiveRate).sendKeys(efectiveRate);
	}

	public void inputOffer(String offer) {
		driver.findElement(offerField).sendKeys(offer);
	}

	public void selectAddressType() {
		Select oSelect = new Select(driver.findElement(addressDropdown));
		oSelect.selectByVisibleText("Home");
	}

	public void inputAddressAndStreet(String address, String street) {
		driver.findElement(addressField).sendKeys(address);
		driver.findElement(streetField).sendKeys(street);
	}

	public void inputCityAndState(String city, String state) {
		driver.findElement(cityField).sendKeys(city);
		driver.findElement(stateField).sendKeys(state);
	}

	public void inputCountryAndZip(String zip) {
		Select oSelect = new Select(driver.findElement(countryDropdown));
		oSelect.selectByVisibleText("India");
		driver.findElement(zipField).sendKeys(zip);
	}

	public void addingParkingCost(String par_a, String par_r) {

		driver.findElement(addCostDetailsDropdown).click();
		List<WebElement> list = driver.findElements(addCDD_All);

		for (WebElement ele : list) {
			// for every elements it will print the name using innerHTML

			System.out.println("Values " + ele.getAttribute("innerHTML"));

			// Here we will verify if link (item) is equal to Parking

			if (ele.getAttribute("innerHTML").contains("Parking")) {

				// if yes then click on link

				ele.click();

				System.out.println("Clicked on Parking");

				// break the loop or come out of loop

				break;
			}
		}

		driver.findElement(parking_a).sendKeys(par_a);
		driver.findElement(parking_r).sendKeys(par_r);
	}

	public void addingPremiumCost(String pre_a, String pre_r) {

		driver.findElement(addCostDetailsDropdown).click();
		List<WebElement> list = driver.findElements(addCDD_All);

		for (WebElement ele : list) {
			// for every elements it will print the name using innerHTML

			System.out.println("Values " + ele.getAttribute("innerHTML"));

			// Here we will verify if link (item) is equal to Premium

			if (ele.getAttribute("innerHTML").contains("Premium")) {

				// if yes then click on link

				ele.click();

				System.out.println("Clicked on Premium");

				// break the loop or come out of loop

				break;
			}
		}

	}

	public void clickOnSaveButton() {
		driver.findElement(saveButton).click();
	}
	
	public void addingCostDetails() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(costDetails).click();
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(selectPaymentSchedule));
		actions.click().perform();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		actions.moveToElement(driver.findElement(selectPaymentSchedule_dd));
		actions.click().perform();

	}
	public void clickBasicDetails(){
		driver.findElement(basicDetails).click();
	}
}
