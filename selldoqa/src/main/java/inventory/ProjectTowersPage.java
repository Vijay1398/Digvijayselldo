package inventory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.SetUp;

public class ProjectTowersPage extends SetUp {

	By newTower = By.xpath("//a[text()='New Tower']");
	By actionBar = By.xpath("//th[text()='Actions']/following::a[2]");
	By editLink = By.xpath("//th[text()='Actions']/following::a[text()='Edit'][1]");
	By funnelIcon = By.xpath("//i[@class='ion-funnel']");
	By projectTowerSpan = By.xpath("//span[text()='Select a Project Tower']");
	By projectTowerInputField = By.xpath("//*[@id='select2-drop']//div//input");
	By projectSpan = By.xpath("//span[text()='Select a Project']");
	By projectInputField = By.xpath("//*[@id='select2-drop']//div//input");
	By applyButton = By.xpath("//a[text()='Apply']");
	By clearAllLink = By.xpath("//a[text()='Clear All']");
	By crossIcon = By.xpath("//input[@value='Apply']/preceding::i[@class='ion-android-close'][1]");

	WebDriver driver = null;
	WebDriverWait wait;

	public ProjectTowersPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
	}

	public void clickOnNewTower() {
		driver.findElement(newTower).click();
	}

	public void clickOnFunnelIcon() {
		driver.findElement(funnelIcon).click();
	}

	public void enterProjectTowerName(String tower) throws InterruptedException {
		driver.findElement(projectTowerSpan).click();
		driver.findElement(projectTowerInputField).sendKeys(tower);
		Thread.sleep(2000);
		driver.findElement(projectTowerInputField).sendKeys(Keys.ENTER);
	}

	public void clickOnApplyButton() {
		driver.findElement(applyButton).click();
	}

	public void clickOnClearAllLink() {
		driver.findElement(clearAllLink).click();
	}

	public void clickOnCrossIcon() {
		driver.findElement(crossIcon).click();
	}
}
