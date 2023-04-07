package adminPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.SetUp;

public class SettingsPage extends SetUp {

	By userManagement = By.xpath("//*[@id=\"user-mngmnt\"]");
	By partners = By.xpath("//span[text()='partners']");
	By managePartners = By.xpath("//span[text()='manage partners']");
	By export = By.xpath("//span[text()='export']");
	By importTab = By.xpath("//span[text()='import']");
	By searchList = By.xpath("//span[text()='search lists']");
	By bookingList = By.xpath("//span[text()='Booking lists']");

	WebDriver driver = null;
	WebDriverWait wait;

	public SettingsPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
	}

	public void clickOnUserManagementTab() {
		driver.findElement(userManagement).click();
	}

	public void clickOnPartnersTab() {
		driver.findElement(partners).click();
	}
	
	public void clickOnManagePartnersTab() {
		driver.findElement(managePartners).click();
	}
	
	public void clickOnExportab() {
		driver.findElement(export).click();
	}
	
	public void clickOnImportTab() {
		driver.findElement(importTab).click();
	}
	
	public void clickOnSearchListTab() {
		driver.findElement(searchList).click();
	}
	
	public void clickOnBookingListTab() {
		driver.findElement(bookingList).click();
	}
}
