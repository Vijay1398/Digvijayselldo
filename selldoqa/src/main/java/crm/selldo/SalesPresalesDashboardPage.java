package crm.selldo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import utility.SetUp;

public class SalesPresalesDashboardPage extends SetUp {

	By leadsIcon = By.cssSelector("i.ion-ios-people");
	By allLeadsLink = By.xpath("//a[normalize-space()='All Leads']"); 
	By inventoryCubeIcon = By.cssSelector("i.ion-cube");
	By saveAsButton = By.xpath("//button[text()='Save As']");

	By listField = By.cssSelector("#s2id_autogen2 > a > span.select2-chosen");
	By listSearch = By.cssSelector("#select2-drop > div > input");
	By stageDropdown = By.xpath("//button[@class='btn dropdown-toggle btn-sm btn-outline-primary']");
	By stageDropdown_All = By.xpath("//div[@class='dropdown-menu type show']");
	By saveButton = By.xpath("//a[@class='save_stage_and_status btn float-right btn-primary btn-sm mr-2']");
	By actionBar_Lead1 = By.xpath("//i[@class='fa fa-ellipsis-v']");
	By actionBar_Lead2 = By.cssSelector("#leads-container > tbody > tr:nth-child(2) > td.text-right > div > a");
	By actionBar = By.cssSelector("#leads-container > tbody > tr:nth-child(1) > td.text-right > div > a");
	By preview = By.xpath("//button[text()='Preview']");
	By viewProfileButton = By.xpath("//button[text()='View Profile']");
	By details = By.cssSelector("div.dropdown-menu.dropdown-menu-right.show button.dropdown-item.goto_details");
	public By searchField = By.xpath("//span[text()='Search...']");
	By newEnquiryBucket = By.xpath("//div[@id='new-enquires']//div[@class='tile-item-body text-success']");
	By reengagedLeadsBucket = By.xpath("//div[@id='reengaged-leads']//div[@class='tile-item-body text-success']");
	By missedCallsBucket = By.xpath("//div[@id='missed-calls']//div[@class='tile-item-body text-danger']"); 
	By nFABucket = By.xpath("//div[@id='nfa-leads']//div[@class='tile-item-body text-danger']");
	By userAccountIcon = By.cssSelector("i#user-account-icon");
	By availableLink = By.cssSelector("span.status");
	By editProfile = By.xpath("//a[text()='Edit Profile']");
	By crossIcon_EditProfile = By.xpath("//h5[text()='Edit User']/following::i[1]");
	By saveButton_EditProfile = By.xpath("//input[@value='Save']");
	By markOnBreak = By
			.cssSelector("div.dropdown-menu.dropdown-menu-right.call-center-availability-dropdown.show a:nth-child(2)");
	public By onBreakLink = By.cssSelector("span.status");
	By markOnlineLink = By
			.cssSelector("div.dropdown-menu.dropdown-menu-right.call-center-availability-dropdown.show a:nth-child(1)");
	By searchTextBox = By.cssSelector("#select2-drop > div > input");
	By refreshIcon = By.cssSelector("i.ion-refresh.refresh-sales-dashboard");
	public By missedFollowups = By.xpath("//span[text()='missed Followups']/following::div[1]");
	public By openTasksLink = By.xpath("//a[@href='#open_tasks']");
	public By missedSiteVisits = By.xpath("//span[text()='missed Site visits']/following::div[1]");
	By filterIcon = By.cssSelector("#dropdownMenuButton");
	By siteVisitFilter = By.cssSelector("div.dropdown-menu.dropdown-menu-right.show a:nth-child(2)");
	By followupFilter = By.cssSelector("div.dropdown-menu.dropdown-menu-right.show a:nth-child(3)");

	WebDriver driver = null;

	public SalesPresalesDashboardPage(WebDriver driver) {

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		this.driver = driver;// Calling Browser
	}

	// To Search Lead
	public void searchLead(String lead) throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(searchField).click();
		WebElement webElement = driver.findElement(searchTextBox);
		webElement.sendKeys(lead);
		Thread.sleep(4000);
		webElement.sendKeys(Keys.ENTER);// Clicking enter
	}

	public void clickOnSaveAsButton() {
		driver.findElement(saveAsButton).click();
	}

	// To Select values from Lead Management Drop down
	public void selectLeadActions(int value) throws Exception {

		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(leadsIcon));
		actions.build().perform();
		
		 driver.findElement(By.cssSelector(
		 "body > div.main-nav-container > div.sub-navigation-bar > div:nth-child(1) > div:nth-child(" + value+ ") > a")).click();
	}
	
	public void goToAllLeadsList() {
		
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(leadsIcon));
		actions.build().perform();
		driver.findElement(allLeadsLink).click();
	}

	public void openLeadProfile() {
		driver.findElement(actionBar).click();
		driver.findElement(preview).click();
		driver.findElement(viewProfileButton).click();
	}

	public void openLeadDetails() throws Exception {
		Thread.sleep(6000);
		driver.findElement(actionBar).click();
		driver.findElement(details).click();

	}

	public void openLeadDetails2() throws Exception {
		Thread.sleep(4000);
		driver.findElement(actionBar_Lead2).click();
		driver.findElement(details).click();

	}

	public void refreshDashboardStats() {
		driver.findElement(refreshIcon).click();
	}

	public void clickOnnewEnquiryBucket() {
		driver.findElement(newEnquiryBucket).click();
	}

	public void clickOnReengagedLeadsBucket() {
		driver.findElement(reengagedLeadsBucket).click();
	}

	public void clickOnMissedCallsBucket() {
		driver.findElement(missedCallsBucket).click();
	}

	public void clickOnNFAbucket() {
		driver.findElement(nFABucket).click();
	}

	public void selectMarkOnBreak() {
		driver.findElement(availableLink).click();
		driver.findElement(markOnBreak).click();
	}

	public void validatingOnBreakLink() {
		SoftAssert assertion = new SoftAssert();
		System.out.println("Started verification");
		Assert.assertEquals(driver.findElement(onBreakLink).getText(), "You are on break", "Not matched");
		System.out.println("Completed verification");
		assertion.assertAll();
	}
	
	public void validatingOnBreakStatus() {
		SoftAssert assertion = new SoftAssert();
		System.out.println("Started verification");
		Assert.assertEquals(driver.findElement(onBreakLink).getText(), "You are on break (LunchBreak)", "Not matched");
		System.out.println("Completed verification");
		assertion.assertAll();
	}

	public void validatingAvailableLink() {
		SoftAssert assertion = new SoftAssert();
		System.out.println("Started verification");
		Assert.assertEquals(driver.findElement(availableLink).getText(), "Available", "Not matched");
		System.out.println("Completed verification");
		assertion.assertAll();
	}

	// Clicking on Mark Online Link
	public void availableForCall() {
		driver.findElement(onBreakLink).click();
		driver.findElement(markOnlineLink).click();
	}

	public void SelectList(String reassign) throws Exception {
		Thread.sleep(12000);
		driver.findElement(listField).click();
		driver.findElement(listSearch).sendKeys(reassign);
		driver.findElement(listSearch).sendKeys(Keys.ENTER);
	}

	public void changing_BookedToProspect() throws InterruptedException {

		driver.findElement(stageDropdown).click();

		List<WebElement> list = driver.findElements(stageDropdown_All);

		for (WebElement ele : list)

		{
			// for every elements it will print the name using innerHTML

			System.out.println("Values " + ele.getAttribute("innerHTML"));

			// Here we will verify if link (item) is equal to Prospect

			if (ele.getAttribute("innerHTML").contains("Prospect")) {

				// if yes then click on link

				Thread.sleep(3000);

				ele.click();

				System.out.println("Clicked on Prospect");

				// break the loop or come out of loop

				break;
			}
		}
		driver.findElement(saveButton).click();
	}

	public void editUserProfile() {
		driver.findElement(userAccountIcon).click();
		driver.findElement(editProfile).click();
	}

	public void closeEditProfile() {
		driver.findElement(saveButton_EditProfile).click();
	}

	public void clickOnMissedSitevisitBucket() {
		driver.findElement(openTasksLink).click();
		driver.findElement(missedSiteVisits).click();

	}

	public void clickOnMissedFollowupBucket() {
		driver.findElement(openTasksLink).click();
		driver.findElement(missedFollowups).click();
	}

	public void applySitevisitFilter() {

		driver.findElement(filterIcon).click();
		driver.findElement(siteVisitFilter).click();
	}

	public void applyFollowupFilter() {

		driver.findElement(filterIcon).click();
		driver.findElement(followupFilter).click();
	}

	public void pageRefresh() {

		driver.navigate().refresh();
	}

}
