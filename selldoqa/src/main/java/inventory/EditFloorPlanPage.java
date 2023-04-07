package inventory;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.SetUp;

public class EditFloorPlanPage extends SetUp {

	By developerSpan = By.xpath("//span[text()='Select a Developer']");
	By developerInputField = By.xpath("//*[@id='select2-drop']//div//input");
	By projectSpan = By.xpath("//span[text()='Select a Project']");
	By projectInputField = By.xpath("//*[@id='select2-drop']//div//input");
	By projectTowerSpan = By.xpath("Select a Project Tower");
	By projectTowerInputField = By.xpath("//*[@id='select2-drop']//div//input");
	By floorPlanNameInputField = By.xpath("//input[@id='unit_configuration_name']");
	By bedrooms = By.xpath("//label[@for='s2id_autogen5']/following::a[1]");
	By bedrooms_All = By.xpath("//ul[@class='select2-results']//li");
	By bathrooms = By.xpath("//label[@for='s2id_autogen6']/following::a[1]");
	By bathrooms_All = By.xpath("//ul[@class='select2-results']//li");
	By saveButton = By.xpath("//input[@type='submit']");
	By areaAndCostingLink = By.xpath("//a[text()='Area and Costing']");
	By extrasLink = By.xpath("//a[text()='Extras']");
	By floorPlanImagesLink = By.xpath("//a[text()='Floorplan Images']");
	By priceQuotesAndBrochureLink = By.xpath("//a[text()='Price Quotes and Brochures']");
	By regionsLink = By.xpath("//a[text()='Regions']");
	By costTemplateLink = By.xpath("//a[text()='Cost template']");
	By viewUnitsLink = By.xpath("//a[text()='View Available Project Units']");
	By addUnitLink = By.xpath("//a[text()='Add Unit']");
	By allFloorPlans = By.xpath("//a[text()='All Floor Plans']");
	By addFloorPlan = By.xpath("//a[text()='Add Floor Plan']");

	WebDriver driver = null;
	WebDriverWait wait;

	public EditFloorPlanPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
	}

	public void changeProjectTowerName(String project) throws InterruptedException {
		driver.findElement(floorPlanNameInputField).clear();
		driver.findElement(floorPlanNameInputField).sendKeys(project);
	}

	public void clickOnAreaAndCostingLink() {
		driver.findElement(areaAndCostingLink).click();
	}

	public void clickOnExtrasLink() {
		driver.findElement(extrasLink).click();
	}

	public void clickOnFloorPlanImagesLink() {
		driver.findElement(floorPlanImagesLink).click();
	}

	public void clickOnPriceQuotesAndBrochureLink() {
		driver.findElement(priceQuotesAndBrochureLink).click();
	}

	public void clickOnRegionsLink() {
		driver.findElement(regionsLink).click();
	}

	public void clickOnCostTemplateLink() {
		driver.findElement(costTemplateLink).click();
	}

	public void clickOnViewUnitsLink() {
		driver.findElement(viewUnitsLink).click();
	}

	public void clickOnAddUnitLink() {
		driver.findElement(addUnitLink).click();
	}

	public void clickOnAllFloorPlans() {
		driver.findElement(allFloorPlans).click();
	}

	public void clickOnAddFloorPlan() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		driver.findElement(addFloorPlan).click();
	}

	public void clickOnSaveButton() {
		driver.findElement(saveButton).click();
	}
}
