package inventory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.SetUp;

public class NewFloorPlanFormPage extends SetUp {

	By developerSpan = By.xpath("//span[text()='Select a Developer']");
	By developerInputField = By.xpath("//*[@id='select2-drop']//div//input");
	By projectSpan = By.xpath("//span[text()='Select a Project']");
	By projectInputField = By.xpath("//*[@id='select2-drop']//div//input");
	By projectTowerSpan = By.xpath("//span[text()='Select a Project Tower']");
	By projectTowerInputField = By.xpath("//*[@id='select2-drop']//div//input");
	By floorPlanNameInputField = By.xpath("//input[@id='unit_configuration_name']");
	By bedrooms = By.xpath("//div[@id='s2id_unit_configuration_bedrooms']");
	By bedrooms_All = By.xpath("//ul[@class='select2-results']//li");
	By bathrooms = By.xpath("//div[@id='s2id_unit_configuration_bathrooms']");
	By bathrooms_All = By.xpath("//ul[@class='select2-results']//li");
	By category = By.xpath("//div[@id='s2id_unit_configuration_category']");
	By category_All = By.xpath("//ul[@class='select2-results']//li");
	By type = By.xpath("//div[@id='s2id_unit_configuration_type']");
	By type_All = By.xpath("//ul[@class='select2-results']//li");
	By carpet = By.xpath("//input[@id='unit_configuration_carpet']");
	By saleable = By.xpath("//input[@id='unit_configuration_saleable']");
	By coveredArea = By.xpath("//input[@id='unit_configuration_covered_area']");

	

	By terraceArea = By.xpath("//input[@name='unit_configuration[terrace_area]']");
	//By baseRate = By.xpath("//label[@for='unit_configuration_base_rate']/following::div[1]");
	By baseRatePlaceHolder = By.cssSelector("div.currency2-container.placeholder");
	By baseRate = By.id("unit_configuration_base_rate");

	By saveButton = By.xpath("//input[@type='submit']");

	WebDriver driver = null;
	WebDriverWait wait;

	public NewFloorPlanFormPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
	}

	public void enterDeveloperName(String project) throws InterruptedException {
		driver.findElement(developerSpan).click();
		driver.findElement(developerInputField).sendKeys(project);
		Thread.sleep(2000);
		driver.findElement(developerInputField).sendKeys(Keys.ENTER);
	}

	public void enterProjectName(String project) throws InterruptedException {
		driver.findElement(projectSpan).click();
		driver.findElement(projectInputField).sendKeys(project);
		Thread.sleep(2000);
		driver.findElement(projectInputField).sendKeys(Keys.ENTER);
	}

	public void enterProjectTowerName(String tower) throws InterruptedException {
		driver.findElement(projectTowerSpan).click();
		Thread.sleep(2000);
		driver.findElement(projectTowerInputField).sendKeys(tower);
		Thread.sleep(2000);
		driver.findElement(projectTowerInputField).sendKeys(Keys.ENTER);
	}

	public void enterFloorPlanName(String address) {
		driver.findElement(floorPlanNameInputField).sendKeys(address);
	}

	public void selectNumberOfBedrooms() throws InterruptedException {
		driver.findElement(bedrooms).click();
		List<WebElement> list = driver.findElements(bedrooms_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("3")) {
				Thread.sleep(1000);
				ele.click();
				System.out.println("Clicked on 3");
				break;
			}
		}
	}

	public void selectNumberOfBathrooms() throws InterruptedException {
		driver.findElement(bathrooms).click();
		List<WebElement> list = driver.findElements(bathrooms_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("3")) {
				Thread.sleep(1000);
				ele.click();
				System.out.println("Clicked on 3");
				break;
			}
		}
	}

	public void selectCategory() throws InterruptedException {
		driver.findElement(category).click();
		List<WebElement> list = driver.findElements(category_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("facing")) {
				Thread.sleep(1000);
				ele.click();
				System.out.println("Clicked on facing");
				break;
			}
		}
	}

	public void selectType() throws InterruptedException {
		driver.findElement(type).click();
		List<WebElement> list = driver.findElements(type_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("penthouse")) {
				Thread.sleep(1000);
				ele.click();
				System.out.println("Clicked on penthouse");
				break;
			}
		}
	}

	public void enterCarpetArea(String cArea) {
		driver.findElement(carpet).sendKeys(cArea);
	}

	public void enterSaleableArea(String sArea) {
		driver.findElement(saleable).sendKeys(sArea);
	}

	public void enterCoveredArea(String covArea) {
		driver.findElement(coveredArea).sendKeys(covArea);
		driver.findElement(coveredArea).sendKeys(Keys.TAB);
	}

	public void enterTerraceArea(String tArea) {
		driver.findElement(terraceArea).sendKeys(tArea);
	}

	public void enterBaseRate(String base) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(2000);
		driver.findElements(baseRatePlaceHolder).get(0).click();
		driver.findElement(baseRate).sendKeys(base);
	}

	public void clickOnSaveButton() {
		driver.findElement(saveButton).click();
	}

}
