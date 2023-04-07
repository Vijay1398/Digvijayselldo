package inventory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.SetUp;

public class ProjectsPage extends SetUp {

	By newProjectButton = By.xpath("//a[text()='New Project']");
	By actionBar = By.xpath("//th[text()='Actions']/following::a[1]");
	By editLink = By.xpath("//th[text()='Actions']/following::a[text()='Edit'][1]");
	By funnelIcon = By.cssSelector("i.ion-funnel");
	By projectSpan = By.xpath("//label[text()='Project']/following::span[1]");
	By projectInputField = By.xpath("//*[@id='select2-drop']//div//input");
	By developerSpan = By.xpath("//span[text()='Select a Developer']");
	By developerInputField = By.xpath("//*[@id='select2-drop']//div//input");
	By constructionStatus = By.xpath("//span[text()='Construction Status']");
	By constructionStatusTextField = By.xpath("//*[@id='select2-drop']//div//input");
	By typeSpan = By.xpath("//span[text()='Project Type']");
	By type_All = By.xpath("//ul[@class='select2-results']//li");
	By status = By.xpath("//label[text()='Status']/following::span[1]");
	By status_All = By.xpath("//ul[@class='select2-results']//li");
	By filterButton = By.xpath("//a[text()='Filter']");
	By clearAllLink = By.xpath("//a[text()='Clear All']");
	By crossIcon = By.xpath("//input[@value='Apply']/preceding::i[@class='ion-android-close'][1]");

	WebDriver driver = null;
	WebDriverWait wait;

	public ProjectsPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
	}

	public void clickOnNewProjectButton() {
		driver.findElement(newProjectButton).click();
	}

	public void clickOnFunnelIcon() {
		driver.findElement(funnelIcon).click();
	}

	public void enterProjectName(String pro) throws InterruptedException {
		driver.findElement(projectSpan).click();
		driver.findElement(projectInputField).sendKeys(pro);
		Thread.sleep(2000);
		driver.findElement(projectInputField).sendKeys(Keys.ENTER);
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
