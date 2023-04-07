package inventory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.SetUp;

public class NewProjectFormPage extends SetUp {

	By projectNameSpan = By.xpath("//label[@for='project_project_name']/following::span[1]");
	By projectNameInputField = By.xpath("//*[@id='select2-drop']//div//input");
	By developerSpan = By.xpath("//span[text()='Developer']");
	By developerInputField = By.xpath("//*[@id='select2-drop']//div//input");
	By descriptionTextArea = By.xpath("//div[@class='note-editable card-block']");
	By salesSpan = By.xpath("//label[text()='Sales']/following::input[1]");
	By sales_All = By.xpath("//ul[@class='select2-results']//li");
	By presalesSpan = By.xpath("//label[text()='Pre Sales']/following::input[1]");
	By presales_All = By.xpath("//ul[@class='select2-results']//li");
	By postsalesSpan = By.xpath("//label[@for='project_project_post_sales']/following::input[1]");
	By postsales_All = By.xpath("//ul[@class='select2-results']//li");
	By possession = By.xpath("//input[@id='project_possession']");
	By possession_All = By.cssSelector("div.datepicker.datepicker-dropdown.dropdown-menu.datepicker-orient-left.datepicker-orient-top tbody tr td");
	By projectTypeSpan = By.cssSelector("#s2id_project_type > a > span.select2-chosen");
	By projectType_All = By.xpath("//ul[@class='select2-results']//li");
	By localityInputField = By.xpath("//input[@name='project[micro_market]']");
	By latitudeInptField = By.xpath("//input[@name='project[lat]']");
	By longitudeInputField = By.xpath("//input[@name='project[lng]']");
	By addressInputField = By.xpath("//input[@id='project_address_attributes_address1']");
	By streetInputField = By.xpath("//input[@id='project_address_attributes_address2']");
	By cityInputField = By.xpath("//input[@id='project_address_attributes_city']");
	By stateDropdown = By.cssSelector("select[name=\"project[address_attributes][state]\"]");
	By countryDropdown = By.xpath("//select[@id='project_address_attributes_country']");
	By zipInputField = By.xpath("//input[@id='project_address_attributes_zip']");
	By saveButton = By.xpath("//input[@type='submit']");
	By allProjectsLink = By.xpath("//a[text()='All Projects']");
	By selectToday = By.cssSelector("div.datepicker-days > table > tbody > tr > td.active.day");

	WebDriver driver = null;
	WebDriverWait wait;

	public NewProjectFormPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
	}

	public void enterProjectName(String project) throws InterruptedException {
		
		
		driver.findElement(projectNameSpan).click();
		driver.findElement(projectNameInputField).sendKeys(project);
		Thread.sleep(2000);
		driver.findElement(projectNameInputField).sendKeys(Keys.ENTER);
	}

	public void enterDeveloperName(String project) throws InterruptedException {
		driver.findElement(developerSpan).click();
		driver.findElement(developerInputField).sendKeys(project);
		Thread.sleep(2000);
		driver.findElement(developerInputField).sendKeys(Keys.ENTER);
	}

	public void enterDescription(String des) {
		driver.findElement(descriptionTextArea).sendKeys(des);
	}

	public void selectSales() throws InterruptedException {
		driver.findElement(salesSpan).click();
		List<WebElement> list = driver.findElements(sales_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Tanmay Ghawate")) {
				Thread.sleep(1000);
				ele.click();
				System.out.println("Clicked on Tanmay Ghawate");
				break;
			}
		}
	}

	public void selectPresales() throws InterruptedException {
		driver.findElement(presalesSpan).click();
		List<WebElement> list = driver.findElements(presales_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Bhushan Authankar")) {
				Thread.sleep(1000);
				ele.click();
				System.out.println("Clicked on Bhushan Authankar");
				break;
			}
		}
	}

	public void selectPostsales() throws InterruptedException {
		driver.findElement(postsalesSpan).click();
		List<WebElement> list = driver.findElements(postsales_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Srinath Post Sales")) {
				Thread.sleep(1000);
				ele.click();
				System.out.println("Clicked on Srinath Post Sales");
				break;
			}
		}
	}

	public void selectPossessionDate() throws Exception {
		Thread.sleep(3000);
		driver.findElement(possession).click();
		driver.findElement(selectToday).click();
	}
	

	public void selectProjectType() throws InterruptedException {
		driver.findElement(projectTypeSpan).click();
		List<WebElement> list = driver.findElements(projectType_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("apartments")) {
				Thread.sleep(1000);
				ele.click();
				System.out.println("Clicked on apartments");
				break;
			}
		}
	}

	public void enterLocality(String loc) {
		driver.findElement(localityInputField).sendKeys(loc);
	}

	public void enterLatitude(String lat) {
		driver.findElement(latitudeInptField).sendKeys(lat);
	}

	public void enterLongitude(String lon) {
		driver.findElement(longitudeInputField).sendKeys(lon);
	}

	public void enterAddress(String address) {
		driver.findElement(addressInputField).sendKeys(address);
	}

	public void enterStreet(String street) {
		driver.findElement(streetInputField).sendKeys(street);
	}

	public void enterCity(String city) {
		driver.findElement(cityInputField).sendKeys(city);
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
		driver.findElement(zipInputField).sendKeys(zp);
	}

	public void clickOnSaveButton() {
		driver.findElement(saveButton).click();
	}

	public void clickOnAllProjectsLink() {
		driver.findElement(allProjectsLink).click();
	}
}
