package inventory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.SetUp;

public class EditProjectTowerFormPage extends SetUp {

	By projectTower = By.xpath("//input[@name='project_tower[name]']");
	By saveButton = By.xpath("//input[@value='Save']");
	By areaAndCostingLink = By.xpath("//a[text()='Area and Costing']");
	By otherDetailsLink = By.xpath("//a[text()='Other Details']");
	By costTemplateLink = By.xpath("//a[text()='Cost template']");
	By paymentScheduleLink = By.xpath("//a[text()='Payment Schedules']");
	By demandLettersLink = By.xpath("//a[text()='Payment Schedules']/following::a[1]");
	By viewAvailableFloorPlansLink = By.xpath("//a[text()=' View Available Floor Plans']");
	By viewAvailableProjectTowersLink = By.xpath("//a[text()=' View Available Project Towers']");
	By addProjectTowersLink = By.xpath("//a[text()='Add Project Tower']");

	WebDriver driver = null;
	WebDriverWait wait;

	public EditProjectTowerFormPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
	}

	public void changeProjectTowerName(String project) throws InterruptedException {
		driver.findElement(projectTower).clear();
		driver.findElement(projectTower).sendKeys(project);
	}

	public void clickOnAreaAndCostingLink() {
		driver.findElement(areaAndCostingLink).click();
	}

	public void clickOnOtherDetailsLink() {
		driver.findElement(otherDetailsLink).click();
	}

	public void clickOnCostTemplateLink() {
		driver.findElement(costTemplateLink).click();
	}

	public void clickOnPaymentScheduleLink() {
		driver.findElement(paymentScheduleLink).click();
	}

	public void clickOnDemandLettersLink() {
		driver.findElement(demandLettersLink).click();
	}

	public void clickOnViewAvailableFloorPlansLink() {
		driver.findElement(viewAvailableFloorPlansLink).click();
	}

	public void clickOnviewAvailableProjectTowersLink() {
		driver.findElement(viewAvailableProjectTowersLink).click();
	}

	public void clickOnAddProjectTowersLink() {
		driver.findElement(addProjectTowersLink).click();
	}

	public void clickOnSaveButton() {
		driver.findElement(saveButton).click();
	}
}
