package adminPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.SetUp;

public class EditPartnerFormPage extends SetUp {

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
	By alternateEmail = By.xpath("//*[contains(@id,'_alternate_email')]	");
	By designation = By.xpath("//*[@id=\"channel_partner_contact_designation\"]");
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
	By closeForm = By.cssSelector("#modal-remote-form-inner > div > div > div.modal-header > button > i");

	WebDriver driver = null;
	WebDriverWait wait;

	public EditPartnerFormPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
	}

	public void changePartnerName(String partner) {
		driver.findElement(name).clear();
		driver.findElement(name).sendKeys(partner);
	}

	public void selectDateOfJoining() {
		driver.findElement(dateOfJoining).clear();
		List<WebElement> dates = driver.findElements(wholeCalender);
		int total_node = dates.size();
		for (int i = 0; i < total_node; i++) {
			String date = dates.get(i).getText();
			if (date.equals("10")) {
				dates.get(i).click();// Clicking on above selected date
				break;
			}
		}
	}

	public void changeSourceOfRecruitment(String source) {
		driver.findElement(sourceOfRequirement).clear();
		driver.findElement(sourceOfRequirement).sendKeys(source);
	}

	public void clickOnContactDetailsTab() {
		driver.findElement(contactDetails).click();
	}

	public void changeSalutation() {
		Select oSelect = new Select(driver.findElement(salutation));
		oSelect.selectByIndex(2);
	}

	public void changeFirstName(String fName) {
		driver.findElement(firstName).clear();
		driver.findElement(firstName).sendKeys(fName);
	}

	public void changeLastName(String lName) {
		driver.findElement(lastName).clear();
		driver.findElement(lastName).sendKeys(lName);
	}

	public void changePhone(String phone) {
		WebElement element = driver.findElement(phoneField);
		element.clear();
		element.sendKeys(phone);
	}

	public void changeEmail(String pEmail) throws InterruptedException {
		driver.findElement(email).clear();
		Thread.sleep(1000);
		WebElement webElement = driver.findElement(email);
		webElement.sendKeys(pEmail);
	}

	public void changeAlternatePhone(String aPhone) {
		WebElement webElement = driver.findElement(alternatePhone);
		webElement.clear();
		webElement.sendKeys(aPhone);
		
	}

	public void changeAlternateEmail(String aEmail) {
		driver.findElement(alternateEmail).clear();
		driver.findElement(alternateEmail).sendKeys(aEmail);
	}

	public void changeDesignation(String des) {
		driver.findElement(designation).clear();
		driver.findElement(designation).sendKeys(des);
	}

	public void changePanNumber(String pan) {
		driver.findElement(panNumber).sendKeys(pan);
	}

	public void clickOnSpecialization() {
		driver.findElement(specialization).click();
	}

	public void changeChannelPartnerType() {
		driver.findElement(channelPartnerType).click();
		List<WebElement> list = driver.findElements(channelPartnerType_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("National")) {
				ele.click();
				System.out.println("Clicked on Local");
				break;
			}
		}
	}

	public void changeReraNumber() {
		driver.findElement(reraNumber).clear();
		driver.findElement(reraNumber).sendKeys("200123457");
	}

	public void changePropertyType() {
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


	public void changeMinBudget(String min) {
		driver.findElement(minBudget).clear();
		driver.findElement(minBudget).sendKeys(min);
	}

	public void changeMaxBudget(String max) {
		driver.findElement(maxBudget).clear();
		driver.findElement(maxBudget).sendKeys(max);
	}

	public void clickOnSaveButton() {
		driver.findElement(saveButton).click();
	}
	
	public void closeForm() {
		driver.findElement(closeForm).click();
	}
}
