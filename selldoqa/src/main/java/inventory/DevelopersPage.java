package inventory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.SetUp;

public class DevelopersPage extends SetUp {

	By newDeveloperButton = By.xpath("//a[text()='New Developer']");
	By funnelIcon = By.cssSelector("i.ion-funnel");
	By deveoperSpan = By.xpath("//label[text()=' Developer ']/following::span[1]");
	By developerInputField = By.xpath("//*[@id='select2-drop']//div//input");
	By actionBar = By.xpath("//th[text()='Actions']/following::a[2]");
	By editLink = By.xpath("//th[text()='Actions']/following::a[text()='Edit'][1]");
	By applyButton = By.xpath("//a[text()='Apply']");
	By clearAllLink = By.xpath("//a[text()='Clear All']");
	By crossIcon = By.xpath("//input[@value='Apply']/preceding::i[@class='ion-android-close'][1]");

	WebDriver driver = null;
	WebDriverWait wait;

	public DevelopersPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
	}

	public void clickOnNewDevekoperButton() {
		driver.findElement(newDeveloperButton).click();
	}
	
	public void selectEdit() {
		driver.findElement(actionBar).click();
		driver.findElement(editLink).click();
	}

	public void clickOnFunnelIcon() {
		driver.findElement(funnelIcon).click();
	}

	public void enterDeveloperName(String developer) throws InterruptedException {
		driver.findElement(deveoperSpan).click();
		driver.findElement(developerInputField).sendKeys(developer);
		Thread.sleep(2000);
		driver.findElement(developerInputField).sendKeys(Keys.ENTER);
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
