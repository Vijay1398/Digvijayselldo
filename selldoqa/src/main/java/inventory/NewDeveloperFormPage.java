package inventory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;	
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.SetUp;

public class NewDeveloperFormPage extends SetUp {

	By developerName = By.xpath("//span[text()='Developer name']");
	By developerNameInputField = By.xpath("//*[@id='select2-drop']//div//input");
	By description = By.xpath("//div[@class='note-editable card-block']");
	By address = By.xpath("//input[@id='developer_address_address1']");
	By street = By.xpath("//input[@id='developer_address_address2']");
	By city = By.xpath("//input[@id='developer_address_city']");
	By stateDropdown = By.cssSelector("select[name=\"developer[address][state]\"]");
	By countryDropdown = By.xpath("//select[@id='developer_address_country']");
	By zip = By.xpath("//input[@id='developer_address_zip']");
	By salutation = By.xpath("//div[@id='s2id_developer_contact_salutation']");
	By firstName = By.xpath("//input[@id='developer_contact_first_name']");
	By lastName = By.xpath("//input[@id='developer_contact_last_name']");
	By phone = By.xpath("//label[@for='developer_contact_phone']/following::input[1]");
	By email = By.xpath("//input[@id='developer_contact_email']");
	By saveButton = By.xpath("//input[@type='submit']");
	By allDevelopersLink = By.xpath("//a[text()='All Developers']");
	By salutation_textbox =By.xpath("//body/div[@id='select2-drop']/ul[1]/li[1]/div[1]");

	WebDriverWait wait;

	public NewDeveloperFormPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
	}

	public void enterDeveloperName(String developer) throws InterruptedException {
		driver.findElement(developerName).click();
		driver.findElement(developerNameInputField).sendKeys(developer + generateRandomString());
		Thread.sleep(2000);
		driver.findElement(developerNameInputField).sendKeys(Keys.ENTER);
	}
	
	
	public String generateRandomString() {
		String CHAR_LIST =
				"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		int RANDOM_STRING_LENGTH = 5;
		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
			int number = getRandomNumber();
			char ch = CHAR_LIST.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}
	
	private int getRandomNumber() {
		String CHAR_LIST =
				"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(CHAR_LIST.length());
		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}
	

	public void enterDescription(String des) {
		driver.findElement(description).sendKeys(des);
	}

	public void enterAddress(String add) {
		driver.findElement(address).sendKeys(add);
	}

	public void enterStreet(String str) {
		driver.findElement(street).sendKeys(str);
	}

	public void enterCity(String ct) {
		driver.findElement(city).sendKeys(ct);
	}

	public void selectState(String state) {
		Select oSelect = new Select(driver.findElement(stateDropdown));
		oSelect.selectByVisibleText(state);
	}
	
	public void selectCountry(String country) {
		Select oSelect = new Select(driver.findElement(countryDropdown));
		oSelect.selectByVisibleText(country);
	}

	public void enterZip(String zp) {
		driver.findElement(zip).sendKeys(zp);
	}

	public void selectSalutation() throws IOException {
		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);		
		driver.findElement(salutation).click();
		List<WebElement> list = driver.findElements(salutation_textbox);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML")
					.contains(property.getProperty("Select_Salutation"))) {
				ele.click();
				System.out.println("Selected");
				break;
			}
		}
		
		
		
		/*Select oSelect = new Select(driver.findElement(salutation));
		oSelect.selectByVisibleText("Ms."); */
	}

	public void enterFirstName(String fName) {
		driver.findElement(firstName).sendKeys(fName);
	}

	public void enterLastName(String lName) {
		driver.findElement(lastName).sendKeys(lName);
	}

	public void enterPhoneNumber(String ph) {
		driver.findElement(phone).sendKeys(ph);
	}

	public void enterEmail(String eml) {
		driver.findElement(email).sendKeys(eml);
	}

	public void clickOnSaveButton() {
			driver.findElement(saveButton).click();
		}

	public void clickOnAllDevelopersLink() {
		driver.findElement(allDevelopersLink).click();
	}

}
