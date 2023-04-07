package adminPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.SetUp;

public class PartnersPage extends SetUp {

	By managePartners = By.xpath("//span[text()='manage partners']");
	By newPartner = By.xpath("//a[text()='New Partner']");
	By funnelIcon = By.xpath("//i[@class='ion-funnel']");
	By searchField = By.id("search");
	By applyButton = By.xpath("//input[@type='submit']");
	By clearAllLink = By.xpath("//a[text()='Clear All']");
	By actionBar = By.xpath("//th[text()='Actions']/following::a[1]");
	By edit = By.xpath("//th[text()='Actions']/following::a[text()='Edit'][1]");
	By closeFilter = By.cssSelector("div.index-filters.full-height.col-lg-3 .ion-android-close");
	
	

	WebDriver driver = null;
	WebDriverWait wait;

	public PartnersPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
	}

	public void clickOnManagePartnerstTab() {
		driver.findElement(managePartners).click();
	}

	public void clickOnNewPartnerButton() {
		driver.findElement(newPartner).click();
	}

	public void searchPartner(String partner) throws Exception {
		Thread.sleep(1000);
		driver.findElement(funnelIcon).click();
		driver.findElement(searchField).clear();
		driver.findElement(searchField).sendKeys(partner);
		driver.findElement(applyButton).click();
	//	driver.findElement(closeFilter).click();
		
	}

	public void clearFilter() {
		driver.findElement(clearAllLink).click();
	}

	public void selectEdit() {
		driver.findElement(actionBar).click();
		driver.findElement(edit).click();
	}

}
