package adminPages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.SetUp;

public class AllCampaignPage extends SetUp {

	By newCampaignButton = By.xpath("//a[@id='btn-new-campaign']");
	By funnelIcon = By.xpath("//i[@class='ion-funnel']");
	By searchCampaignField = By.xpath("//input[@name='search_params[search_string]']");
	By selectStatusField = By.xpath("//span[text()='Select Status']");
	By inactive = By.xpath("");
	By applyButton = By.xpath("//input[@name='commit']");
	By clearAllLink = By.xpath("//a[text()='Clear All']");
	By actionBar = By.xpath("//th[text()='Actions']/following::a[@id='btn-actions'][1]");
	By urls = By.xpath("//th[text()='Actions']/following::a[text()='URLs'][1]");
	By deactivateCampaign = By.xpath("//th[text()='Actions']/following::a[@class='deactivate_link dropdown-item'][1]");
	By reactivateCampaign = By.cssSelector("div.dropdown-menu.dropdown-menu-right.show .reactivate_link.dropdown-item");
	By viewCampaignReport = By.xpath("//th[text()='Actions']/following::a[@title='View Campaign Report'][1]");
	By linkTo2ndLastPage = By.xpath("//a[@class='next page-link']/preceding::a[1]");
	// ROI Configuration
	By roiConfiguration = By.xpath("//th[text()='Actions']/following::a[@title='ROI configurations'][1]");
	By addNewConfigurationButton = By.xpath("//a[text()='Add New Configuration']");
	By source_NewRoiConfig = By.xpath("//span[text()='Select Source']");
	By sourcedd_NewRoiConfig = By.cssSelector(
			"body > div.select2-drop.select2-display-none.select2-with-searchbox.select2-drop-active > ul");
	By subSource_NewRoiConfig = By.xpath("//label[text()='Sub source']/following::input[1]");
	By subSource_EditRoiConfig = By.cssSelector("#s2id_autogen7");
	By closeSubSource_NewRoiConfig = By.xpath("//a[@class='select2-search-choice-close'][1]");
	By subSource_dd_NewRoiConfig = By.cssSelector("#select2-drop > ul > li:nth-child(2) > div");
	By expectedCpl_NewRoiConfig = By.cssSelector("#roi_config_expected_cpl");
	
	By amountSpent_NewRoiConfig = By.xpath("//*[@id=\"roi_config_amount_spend\"]");
	By startDate_NewRoiConfig = By.xpath("//label[text()='Start date']/following::input[1]");
	By wholeCalender_StartDate_NewRoiConfig = By.xpath(
			"//div[@class='datepicker datepicker-dropdown dropdown-menu datepicker-orient-left datepicker-orient-bottom']//tr//td");
	By endDate_NewRoiConfig = By.xpath("//label[text()='End date']/following::input[1]");
	By wholeCalender_EndDate_NewRoiConfig = By.xpath(
			"//div[@class='datepicker datepicker-dropdown dropdown-menu datepicker-orient-left datepicker-orient-bottom']//tr//td");
	By saveButton_NewRoiConfig = By.xpath("//input[@type='submit']");
	By actionBar_NewRoiConfig = By.xpath("//th[text()='Actions']/following::a[1]");
	By edit_NewRoiConfig = By.xpath("//th[text()='Actions']/following::a[@class='dropdown-item edit_roi_link']");
	By delete_NewRoiConfig = By.xpath("//th[text()='Actions']/following::a[text()='Delete'][1]");

	WebDriver driver = null;

	WebDriverWait wait;

	public AllCampaignPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);

	}

	public void clickOnNewCampaignButton() {
		driver.findElement(newCampaignButton).click();
	}

	public void clickOnFunnelIcon() {
		driver.findElement(funnelIcon).click();
	}

	public void enterCampaignName(String campaignName) {
		driver.findElement(searchCampaignField).sendKeys(campaignName);
	}



	public void clickOnApplyButton() {
		driver.findElement(applyButton).click();
	}

	public void deactivateCampaign() {
		driver.findElement(actionBar).click();
		driver.findElement(deactivateCampaign).click();
	}

	public void reactivateCampaign() {
		driver.findElement(actionBar).click();
		driver.findElement(reactivateCampaign).click();
	}

	public void selectRoiConfigution() {
		driver.findElement(actionBar).click();
		driver.findElement(roiConfiguration).click();
	}

	public void clickOnClearAllLink() {
		driver.findElement(clearAllLink).click();
	}

	public void goingTo2ndLastPage() {
		driver.findElement(linkTo2ndLastPage).click();
	}

	// .................Adding new ROI Config.................

	public void clickOnAddNewConfigButton() {
		driver.findElement(addNewConfigurationButton).click();
	}

	public void selectSource() {
		driver.findElement(source_NewRoiConfig).click();
		driver.findElement(sourcedd_NewRoiConfig).click();
	}

	public void selectSubSource() {
		driver.findElement(subSource_NewRoiConfig).click();
		driver.findElement(subSource_dd_NewRoiConfig).click();
	}

	public void enterExpectedCpl(String expCpl) {
		driver.findElement(expectedCpl_NewRoiConfig).clear();
		driver.findElement(expectedCpl_NewRoiConfig).sendKeys(expCpl);
	}

	public void enterAmountSpent(String amount) {
		driver.findElement(amountSpent_NewRoiConfig).clear();
		driver.findElement(amountSpent_NewRoiConfig).sendKeys(amount);
	}

	public void selectStartDate() throws Exception {
		Thread.sleep(2000);
		driver.findElement(startDate_NewRoiConfig).click();
		List<WebElement> dates = driver.findElements(wholeCalender_StartDate_NewRoiConfig);
		int total_node = dates.size();
		for (int i = 0; i < total_node; i++) {
			String date = dates.get(i).getText();
			if (date.equals("2")) {
				dates.get(i).click();// Clicking on above selected date
				break;
			}
		}
	}

	public void selectEndDate() throws Exception {
		Thread.sleep(2000);
		driver.findElement(endDate_NewRoiConfig).click();
		List<WebElement> dates = driver.findElements(wholeCalender_EndDate_NewRoiConfig);
		int total_node = dates.size();
		for (int i = 0; i < total_node; i++) {
			String date = dates.get(i).getText();
			if (date.equals("12")) {
				dates.get(i).click();// Clicking on above selected date
				break;
			}
		}
	}

	public void clickOnSaveButton() {
		driver.findElement(saveButton_NewRoiConfig).click();
	}

	// .................Editing the ROI Config...............

	public void changeSubSource() throws Exception {
		driver.findElement(closeSubSource_NewRoiConfig).click();
		driver.findElement(subSource_EditRoiConfig).click();
		driver.findElement(subSource_dd_NewRoiConfig).click();
	}

	public void changeExpectedCpl(String expCpl) {
		boolean isEnable;
		isEnable = driver.findElement(expectedCpl_NewRoiConfig).isEnabled();
		System.out.println(isEnable);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int size = driver.findElements(expectedCpl_NewRoiConfig).size();
		driver.findElements(expectedCpl_NewRoiConfig).get(size-1).clear();
		driver.findElements(expectedCpl_NewRoiConfig).get(size-1).sendKeys(expCpl);
	}

	public void changeAmountSpent(String amount) {
		boolean isEnable;
		isEnable = driver.findElement(amountSpent_NewRoiConfig).isEnabled();
		System.out.println(isEnable);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Here size -1 need to used to get the element as the element is no visible
		int size = driver.findElements(amountSpent_NewRoiConfig).size();
		driver.findElements(amountSpent_NewRoiConfig).get(size-1).clear();
		driver.findElements(amountSpent_NewRoiConfig).get(size-1).sendKeys(amount);

	}

	public void changeStartDate() throws Exception {
		Thread.sleep(2000);
		int size = driver.findElements(startDate_NewRoiConfig).size();
		driver.findElements(startDate_NewRoiConfig).get(size-1).click();
		int size2 = driver.findElements(wholeCalender_StartDate_NewRoiConfig).size();
		List<WebElement> dates = driver.findElements(wholeCalender_StartDate_NewRoiConfig);
		int total_node = dates.size();
		for (int i = 0; i < total_node; i++) {
			String date = dates.get(i).getText();
			if (date.equals("5")) {
				dates.get(i).click();// Clicking on above selected date
				break;
			}
		}
	}

	public void changeEndDate() throws Exception {
		Thread.sleep(2000);
		int size = driver.findElements(endDate_NewRoiConfig).size();
		driver.findElements(endDate_NewRoiConfig).get(size-1).click();
		List<WebElement> dates = driver.findElements(wholeCalender_EndDate_NewRoiConfig);
		int total_node = dates.size();
		for (int i = 0; i < total_node; i++) {
			String date = dates.get(i).getText();
			if (date.equals("10")) {
				dates.get(i).click();// Clicking on above selected date
				break;
			}
		}
	}

	public void saveEditConfig(){
		int size = driver.findElements(saveButton_NewRoiConfig).size();
		driver.findElements(saveButton_NewRoiConfig).get(size-1).click();
	}
	public void selectEdit_NewRoiConfig() {
		driver.findElement(actionBar_NewRoiConfig).click();
		driver.findElement(edit_NewRoiConfig).click();
	}

	public void selectDelete_NewRoiConfig() {
		driver.findElement(actionBar_NewRoiConfig).click();
		driver.findElement(delete_NewRoiConfig).click();
	}
}
