package inventory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.SetUp;

public class NewProjectTowerFormPage extends SetUp {

	By towerNameInputField = By.xpath("//input[@id='project_tower_name']");
	By projectSpan = By.xpath("//span[text()='Project Name']");
	By projectInputField = By.xpath("//*[@id='select2-drop']//div//input");
	By totalFloorsInputField = By.xpath("//input[@id='project_tower_total_floors']");
	By saveButton = By.xpath("//input[@value='Save']");

	WebDriver driver = null;
	WebDriverWait wait;

	public NewProjectTowerFormPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
	}

	public void enterProjectTowerName(String pTower) {
		driver.findElement(towerNameInputField).sendKeys(pTower);
	}

	public void enterProjectName(String project) throws InterruptedException {
		driver.findElement(projectSpan).click();
		driver.findElement(projectInputField).sendKeys(project);
		Thread.sleep(2000);
		driver.findElement(projectInputField).sendKeys(Keys.ENTER);
	}

	public void enterTotalFloorsNumber(String floorNumber) {
		driver.findElement(totalFloorsInputField).sendKeys(floorNumber);
	}

	public void clickOnSaveButton() {
		driver.findElement(saveButton).click();
	}
}
