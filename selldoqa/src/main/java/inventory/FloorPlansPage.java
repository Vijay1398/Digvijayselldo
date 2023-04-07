package inventory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.SetUp;

public class FloorPlansPage extends SetUp {

	By newFloorPlanButton = By.xpath("//a[text()='New Floor Plan']");
	By funnelIcon = By.cssSelector("i.ion-funnel");
	By floorPlanSpan = By.xpath("//span[text()='Select a Floor Plan']");
	By floorPlanInputField = By.xpath("//*[@id='select2-drop']//div//input");
	By projectSpan = By.xpath("//span[text()='Select a Project']");
	By projectInputField = By.xpath("//*[@id='select2-drop']//div//input");
	By projectTowerSpan = By.xpath("//span[text()='Select a Project Tower']");
	By projectTowerInputField = By.xpath("//*[@id='select2-drop']//div//input");
	By filterButton = By.xpath("//a[text()='Filter']");
	By clearAllLink = By.xpath("//a[text()='Clear All']");
	By crossIcon = By.xpath("//input[@value='Apply']/preceding::i[@class='ion-android-close'][1]");
	By actionBar = By.xpath("//th[text()='Actions']/following::a[1]");
	By editLink = By.xpath("//th[text()='Actions']/following::a[text()='Edit'][1]");

	WebDriver driver = null;
	WebDriverWait wait;

	public FloorPlansPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
	}

	public void clickOnNewFloorPlanButton() {
		driver.findElement(newFloorPlanButton).click();
	}

	public void clickOnFunnelIcon() {
		driver.findElement(funnelIcon).click();
	}

	public void enterFloorPlanName(String floor) throws InterruptedException {
		driver.findElement(floorPlanSpan).click();
		driver.findElement(floorPlanInputField).sendKeys(floor);
		Thread.sleep(2000);
		driver.findElement(floorPlanInputField).sendKeys(Keys.ENTER);
	}

	public void clickOnFilterButton() {
		driver.findElement(filterButton).click();
	}

	public void clickOnClearAllLink() {
		driver.findElement(clearAllLink).click();
	}

	public void clickOnCrossIcon() {
		driver.findElement(crossIcon).click();
	}
}
