package inventory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.SetUp;

public class EditUnitPage extends SetUp {

	By unitName = By.xpath("//input[@name='project_unit[name]']");
	By saveButton = By.xpath("//input[@type='submit']");
	By detailsLink = By.xpath("//a[text()='Details']");
	By areaAndCostingLink = By.xpath("//a[text()='Area and Costing']");
	By extrasLink = By.xpath("//a[text()='Extras']");
	By costTemplateLink = By.xpath("//a[text()='Cost template']");
	By viewFloorPlanLink = By.xpath("//a[text()='View Floor Plan']");
	By allUnitsLink = By.xpath("//a[text()='View Floor Plan']/following::a[2]");
	By addUnitLink = By.xpath("//a[@href='/client/project_units/new']");

	WebDriver driver = null;
	WebDriverWait wait;

	public EditUnitPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
	}

	public void changeUnitName(String project) throws InterruptedException {
		driver.findElement(unitName).clear();
		driver.findElement(unitName).sendKeys(project);
	}

	public void clickOnDetailsLink() {
		driver.findElement(detailsLink).click();
	}

	public void clickOnAreaAndCostingLink() {
		driver.findElement(areaAndCostingLink).click();
	}

	public void clickOnExtrasLink() {
		driver.findElement(extrasLink).click();
	}

	public void clickOnCostTemplateLink() {
		driver.findElement(costTemplateLink).click();
	}

	public void clickOnViewFloorPlanLink() {
		driver.findElement(viewFloorPlanLink).click();
	}

	public void clickOnAllUnitsLink() {
		driver.findElement(allUnitsLink).click();
	}

	public void clickOnAddUnitLink() {
		driver.findElement(addUnitLink).click();
	}

	public void clickOnSaveButton() {
		driver.findElement(saveButton).click();
	}

}
