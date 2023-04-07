package adminPages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import utility.GetTestData;
import utility.SetUp;

public class AdminDashboardPage extends SetUp {

	By leadsIcon = By.cssSelector("i.ion-ios-people");
	By listField = By.cssSelector("#s2id_autogen2 > a > span.select2-chosen");
	By listSearch = By.cssSelector("#select2-drop > div > input");
	By chatIcon = By.cssSelector("i.ion-chatbubbles");
	By calenderIcon = By.cssSelector("i.ion-android-calendar");
	By pieGraphIcon = By.cssSelector("i.ion-pie-graph");
	By inventoryIcon = By.cssSelector("i.ion-cube");
	By wandIcon = By.cssSelector("i.ion-ios-color-wand");
	By documentIcon = By.cssSelector("i.ion-ios-folder");
	By settingIcon = By.cssSelector("i.ion-android-settings");
	By unlockIcon = By.cssSelector("i.ion-android-unlock");
	By loginAsField = By.xpath("//h5[text()='Login as']/following::span[1]");
	By enterUserName = By.cssSelector("#select2-drop div input");
	By userLoginButton = By.cssSelector("#login_as_sales div.modal-footer a");
	By userAccountIcon = By.xpath("//*[@id=\"user-account-icon\"]");
	By editProfile = By.id("item-edit-profile");
	By changePassword = By.id("item-change-password");
	By logout = By.xpath("//a[text()='Logout']");
	By userManagementTab = By.id("user-mngmnt");
	By manageUsersTab = By.id("btn-manage-user");
	By searchField = By.xpath("//span[text()='Search...']");
	By searchTextBox = By.cssSelector("#select2-drop > div > input");
	By developers = By.xpath("//span[text()='Developers']");
	By clientName = By.cssSelector("div.pending-invoices-alert.small.float-right.mr-2");
	

	WebDriver driver = null;

	WebDriverWait wait;

	public AdminDashboardPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
	}
	
	GetTestData getTestData = new GetTestData();

	// To Search Lead
	public void searchLead(String lead) throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(searchField).click();
		WebElement webElement = driver.findElement(searchTextBox);
		webElement.sendKeys(lead);
		Thread.sleep(4000);
		webElement.sendKeys(Keys.ENTER);// Clicking enter
	}

	public void clickOnDocument() {
		driver.findElement(documentIcon).click();
	}

	// Login as User from Admin Dashboard
	public void loginAsUser(String userName) throws Exception {
		driver.findElement(unlockIcon).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(loginAsField));
		driver.findElement(loginAsField).click();
		driver.findElement(enterUserName).sendKeys(userName);
		driver.findElement(enterUserName).sendKeys(Keys.ENTER);
		driver.findElement(userLoginButton).click();

	}

	public void clickOnSettingIcon() {
		driver.findElement(settingIcon).click();
	}

	// To Select values from Lead Management Dropdown
	public void selectLeadActions(int value) {
		driver.findElement(leadsIcon).click();
		driver.findElement(leadsIcon).click();
		driver.findElement(By.cssSelector("body > div.main-nav-container > div > div:nth-child(2) > div > div:nth-child(" + value + ") > a"))
				.click();
	}

	public static void HoverAndClick(WebDriver driver, WebElement elementToHover, WebElement elementToClick) {
		Actions action = new Actions(driver);
		action.moveToElement(elementToHover).click(elementToClick).build().perform();
	}

	public void selectInventoryActions(int value) {
		driver.findElement(inventoryIcon).click();
		driver.findElement(By.cssSelector(
				"body > div.main-nav-item > div > div:nth-child(1) > div > div:nth-child(" + value + ") > a")).click();
	}

	public void loggingOut() {
		driver.findElement(userAccountIcon).click();
		driver.findElement(logout).click();
	}
	
	public void adminlogOut() {
		driver.findElement(logout).click();
	}

	public void SelectList(String reassign) throws Exception {
		Thread.sleep(6000);
		driver.findElement(listField).click();
		driver.findElement(listSearch).sendKeys(reassign);
		driver.findElement(listSearch).sendKeys(Keys.ENTER);
	}

	public void openManageUsersPage() {
		driver.findElement(userManagementTab).click();
		driver.findElement(manageUsersTab).click();
	}

	public void verifyingClientLogin() {
		SoftAssert assertion = new SoftAssert();
		System.out.println("Started verification");
		Assert.assertEquals(driver.getTitle(), "Sell.Do - Market Smarter. Sell Faster.", "Title Not Matched");
		System.out.println("Completed verification");
		assertion.assertAll();
	}
	
	public void verifyingClientCreation	() {
		
		String businessName = getTestData.location;
		SoftAssert assertion = new SoftAssert();
		System.out.println("Started verification");
		Assert.assertEquals(driver.findElement(clientName).getText(), businessName);
		System.out.println("Completed verification");
		assertion.assertAll();
	}

	public void clickOnWandIcon() {
		driver.findElement(wandIcon).click();
	}

	public void clickOnDevelopers() {
		driver.findElement(developers).click();
	}

}
