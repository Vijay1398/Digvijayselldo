package inventory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.SetUp;

public class EditDeveloperFormPage extends SetUp {

	By developerNameSpan = By.xpath("//label[@for='s2id_autogen2']/following::span[1]");
	By developerNameInputField = By.xpath("//*[@id='select2-drop']//div//input");
	By saveButton = By.xpath("//input[@type='submit']");
	By address = By.xpath("//a[text()='Address']");
	By contacts = By.xpath("//a[text()='Contacts']");
	By customFields = By.xpath("//a[text()='Custom Fields']");
	By allDevelopers = By.xpath("//a[text()='All developers']");
	By addDeveloper = By.xpath("//a[text()='Add developer']");

	WebDriver driver = null;
	WebDriverWait wait;

	public EditDeveloperFormPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
	}

	public void changeDeveloperName(String developer) throws InterruptedException {
		driver.findElement(developerNameSpan).click();
		driver.findElement(developerNameInputField).sendKeys(developer);
		Thread.sleep(2000);
		driver.findElement(developerNameInputField).sendKeys(Keys.ENTER);
	}

	public void clickOnAddressLink() {
		driver.findElement(address).click();
	}

	public void clickOnContactsLink() {
		driver.findElement(contacts).click();
	}

	public void clickOnCustomFields() {
		driver.findElement(customFields).click();
	}

	public void clickOnAllDevelopers() {
		driver.findElement(allDevelopers).click();
	}

	public void clickOnAddDeveloper() {
		driver.findElement(addDeveloper).click();
	}

	public void clickOnSaveButton() {
		driver.findElement(saveButton).click();
	}

}
