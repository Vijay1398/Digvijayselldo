package inventory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.SetUp;

public class NewUnitFormPage extends SetUp {

	By developer = By.xpath("//span[text()='Select a Developer']");
	By developerInputField = By.xpath("//*[@id='select2-drop']//div//input");
	By project = By.xpath("//span[text()='Select a Project']");
	By projectInputField = By.xpath("//*[@id='select2-drop']//div//input");
	By projectTower = By.xpath("//span[text()='Select a Project Tower']");
	By projectTowerField = By.xpath("//*[@id='select2-drop']//div//input");
	By unitConfiguration = By.xpath("//span[text()='Select a Floor Plan']");
	By unitConfigurationInputField = By.xpath("//*[@id='select2-drop']//div//input");
	By nameInputField = By.xpath("//input[@id='project_unit_name']");
	By floorInputField = By.xpath("//input[@id='project_unit_floor']");
	//By baseRateInputField = By.xpath("//input[@id='project_unit_base_rate']");
	By baseRatePlaceHolder = By.cssSelector("div.currency2-container.placeholder");
	By baseRateInputField = By.id("project_unit_base_rate");
	
	By basePriceInputField = By.xpath("//input[@id='project_unit_base_price']");
	By bedrooms = By.xpath("//div[@id='s2id_project_unit_bedrooms']");
	By bedrooms_All = By.xpath("//ul[@class='select2-results']//li");
	By bathrooms = By.xpath("//div[@id='s2id_project_unit_bathrooms']");
	By bathrooms_All = By.xpath("//ul[@class='select2-results']//li");
	By category = By.xpath("//div[@id='s2id_project_unit_category']");
	By category_All = By.xpath("//ul[@class='select2-results']//li");
	By type = By.xpath("//div[@id='s2id_project_unit_type']");
	By type_All = By.xpath("//ul[@class='select2-results']//li");
	By saleableArea = By.xpath("//input[@id='project_unit_saleable']");
	By carpetArea = By.xpath("//input[@id='project_unit_carpet']");
	By saveButton = By.xpath("//input[@type='submit']");

	WebDriver driver = null;
	WebDriverWait wait;

	public NewUnitFormPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
	}

	public void enterDeveloperName(String project) throws InterruptedException {
		driver.findElement(developer).click();
		driver.findElement(developerInputField).sendKeys(project);
		Thread.sleep(2000);
		driver.findElement(developerInputField).sendKeys(Keys.ENTER);
	}

	public void enterProjectName(String pro) throws InterruptedException {
		driver.findElement(project).click();
		driver.findElement(projectInputField).sendKeys(pro);
		Thread.sleep(2000);
		driver.findElement(projectInputField).sendKeys(Keys.ENTER);
	}

	public void enterProjectTowerName(String tower) throws InterruptedException {
		driver.findElement(projectTower).click();
		//Thread.sleep(2000);
		driver.findElement(projectTowerField).sendKeys(tower);
		Thread.sleep(2000);
		driver.findElement(projectTowerField).sendKeys(Keys.ENTER);
	}

	public void enterFloorPlanName(String tower) throws InterruptedException {
		driver.findElement(unitConfiguration).click();
		driver.findElement(unitConfigurationInputField).sendKeys(tower);
		Thread.sleep(2000);
		driver.findElement(unitConfigurationInputField).sendKeys(Keys.ENTER);
	}

	public void enterUnitName(String ut) {
		driver.findElement(nameInputField).sendKeys(ut);
	}

	public void enterNumberOfFloors(String cArea) {
		driver.findElement(floorInputField).clear();
		driver.findElement(floorInputField).sendKeys(cArea);
		driver.findElement(floorInputField).sendKeys(Keys.ENTER);
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
		driver.findElement(carpetArea).sendKeys(cArea);
	}

	public void enterSaleableArea(String sArea) {
		driver.findElement(saleableArea).sendKeys(sArea);
	}

	/*public void enterBaseRate(String base) {
		driver.findElement(baseRateInputField).sendKeys(base);
	}*/
	
	public void enterUnitBaseRate(String base) throws Exception {
		driver.findElements(baseRatePlaceHolder).get(0).click();
		driver.findElement(baseRateInputField).sendKeys(base);
	}

	public void clickOnSaveButton() {
		driver.findElement(saveButton).click();
	}

}
