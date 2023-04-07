package inventory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.SetUp;

public class UnitsPage extends SetUp {

	By newUnitButton = By.xpath("//a[text()='New Unit']");
	By actionBar = By.xpath("//th[text()='Actions']/following::a[1]");
	By editLink = By.xpath("//th[text()='Actions']/following::a[text()='Edit'][1]");
	By funnelIcon = By.cssSelector("i.ion-funnel");
	By unit = By.xpath("//span[text()='Select Unit']");
	By unitInputField = By.xpath("//*[@id='select2-drop']//div//input");
	By developer = By.xpath("//span[text()='Select a Developer']");
	By developerInputField = By.xpath("//*[@id='select2-drop']//div//input");
	By project = By.xpath("//span[text()='Select a Project']");
	By projectInputField = By.xpath("//*[@id='select2-drop']//div//input");
	By projectTower = By.xpath("//span[text()='Select a Project Tower']");
	By projectTowerField = By.xpath("//*[@id='select2-drop']//div//input");
	By floorPlan = By.xpath("//span[text()='Select a Floor Plan']");
	By floorPlanInputField = By.xpath("//*[@id='select2-drop']//div//input");
	By applyButton = By.xpath("//a[text()='Apply']");
	By clearAllLink = By.xpath("//a[text()='Clear All']");
	By crossIcon = By.xpath("//input[@value='Apply']/preceding::i[@class='ion-android-close'][1]");

	WebDriver driver = null;
	WebDriverWait wait;

	public UnitsPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
	}

	public void clickOnNewUnitButton() {
		driver.findElement(newUnitButton).click();
	}

	public void clickOnFunnelIcon() {
		driver.findElement(funnelIcon).click();
	}

	public void enterUnitName(String unt) throws InterruptedException {
		driver.findElement(unit).click();
		driver.findElement(unitInputField).sendKeys(unt);
		Thread.sleep(2000);
		driver.findElement(unitInputField).sendKeys(Keys.ENTER);
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
