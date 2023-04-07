package adminPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.SetUp;

public class NewPartnerFormPage extends SetUp {

	By name = By.id("channel_partner_name");
	By dateOfJoining = By.id("channel_partner_date_of_joining");
	By wholeCalender = By.xpath(
			"//div[@class='datepicker datepicker-dropdown dropdown-menu datepicker-orient-left datepicker-orient-top']//tr//td");
	By sourceOfRequirement = By.id("channel_partner_source_of_recruitment");
	By contactDetails = By.xpath("//a[@href='#channel-partner-contact']");
	By salutation = By.id("channel_partner_contact_salutation");
	By firstName = By.id("channel_partner_contact_first_name");
	By lastName = By.id("channel_partner_contact_last_name");
	By phoneField = By.xpath("//label[@for='channel_partner_contact_phone']/following::input[1]");
	By email = By.xpath("//label[@for='channel_partner_contact_email']/following::input[1]");
	By alternatePhone = By.xpath("//label[text()='Alternate phone']/following::input[1]");
	By alternateEmail = By.xpath("//label[text()='Alternate email']/following::input[1]");
	By designation = By.xpath("//input[@id='channel_partner_contact_designation']");
	By panNumber = By.xpath("//input[@id='channel_partner_contact_pan']");
	By specialization = By.xpath("//a[@href='#channel-partner-specialization']");
	By channelPartnerType = By.xpath("//label[text()='Channel partner type']/following::span[1]");
	By channelPartnerType_All = By.xpath("//ul[@class='select2-results']//li//div");
	By reraNumber = By.xpath("//input[@name='channel_partner[rera_number]']");
	By propertyType = By.xpath("//label[text()='Property type']/following::input[1]");
	By propertyType_All = By.xpath("//ul[@class='select2-results']//li//div");
	By minBudget = By.xpath("//label[text()='Min budget']/following::input[1]");
	By maxBudget = By.xpath("//label[text()='Max budget']/following::input[1]");
	By saveButton = By.xpath("//input[@data-disable-with='Saving']");

	WebDriver driver = null;
	WebDriverWait wait;

	public NewPartnerFormPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
	}

	public void enterPartnerName(String partner) {
		driver.findElement(name).sendKeys(partner);
	}

	public void selectDateOfJoining() {
		driver.findElement(dateOfJoining).click();
		List<WebElement> dates = driver.findElements(wholeCalender);
		int total_node = dates.size();
		for (int i = 0; i < total_node; i++) {
			String date = dates.get(i).getText();
			if (date.equals("9")) {
				dates.get(i).click();// Clicking on above selected date
				break;
			}
		}
	}

	public void enterSourceOfRecruitment(String source) {
		driver.findElement(sourceOfRequirement).sendKeys(source);
	}

	public void clickOnContactDetailsTab() {
		driver.findElement(contactDetails).click();
	}

	public void selectSalutation() {
		Select oSelect = new Select(driver.findElement(salutation));
		oSelect.selectByIndex(1);
	}

	public void enterFirstName(String fName) {
		driver.findElement(firstName).sendKeys(fName);
	}

	public void enterLastName(String lName) {
		driver.findElement(lastName).sendKeys(lName);
	}

	public void enterPhone(String phone) {
		driver.findElement(phoneField).sendKeys(phone);
	}

	public void enterEmail(String pEmail) {
		driver.findElement(email).sendKeys(pEmail);
	}

	public void enterAlternatePhone(String aPhone) {
		driver.findElement(alternatePhone).sendKeys(aPhone);
	}

	public void enterAlternateEmail(String aEmail) {
		driver.findElement(alternateEmail).sendKeys(aEmail);
	}

	public void enterDesignation(String des) {
		driver.findElement(designation).sendKeys(des);
	}

	public void enterPanNumber(String pan) {
		driver.findElement(panNumber).sendKeys(pan);
	}

	public void clickOnSpecialization() {
		driver.findElement(specialization).click();
	}

	public void selectChannelPartnerType() {
		driver.findElement(channelPartnerType).click();
		List<WebElement> list = driver.findElements(channelPartnerType_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Local")) {
				ele.click();
				System.out.println("Clicked on Local");
				break;
			}
		}
	}

	public void enterReraNumber() {
		driver.findElement(reraNumber).sendKeys("123456");
	}
	
	public void selectPropertyType() {
		driver.findElement(propertyType).click();
		List<WebElement> list = driver.findElements(propertyType_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Studio")) {
				ele.click();
				System.out.println("Clicked on Studio");
				break;
			}
		}
	}

	public void enterMinBudget(String min) {
		driver.findElement(minBudget).sendKeys(min);
	}

	public void enterMaxBudget(String max) {
		driver.findElement(maxBudget).sendKeys(max);
	}

	public void clickOnSaveButton() {
		driver.findElement(saveButton).click();
	}

}
