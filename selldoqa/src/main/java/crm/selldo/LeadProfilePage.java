package crm.selldo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.support.ui.WebDriverWait;
import utility.SetUp;

public class LeadProfilePage extends SetUp {

	By addANote = By.xpath("//a[@class=\"nav-link px-2 active lead-add-note\"]");
	By addNote_Textarea = By.xpath("//textarea[@name='content']");
	By saveNoteButton = By.xpath("//button[text()='Save Note']");
	By leadid = By.cssSelector("span[name='lead_id']");
	By call = By.id("call-dropdown");
	By email = By.xpath("//*[@id=\"email-dropdown\"]");
	By ComposeEmail = By.xpath("//a[text()='Compose Email']");
	By SMS = By.xpath("//*[@class=\"nav-link px-2  lead-send-sms\"]");
	By meeting = By.xpath("//i[@class='ion-android-calendar mr-2']");
	By more = By.xpath("//i[@class='ion-quote mr-2']/following::a[@class='nav-link dropdown-toggle'][1]");
	By reassign = By.xpath("//a[@class='nav-link lead-reassign']");
	By followup = By.xpath("//a[@class='nav-link lead-schedule-followup']");
	By push = By.cssSelector("a[title='Push to sales']");
	By pull = By.cssSelector(".nav-link.active.lead_pull_to_sales");
	By bookings = By.xpath("//a[@class='nav-link lead-booking-forms']");
	By conductedSiteVisit = By.xpath("//a[@class='nav-link px-2 lead-add-conducted-site-visit']");
	By siteVisit = By.xpath("//a[@class='nav-link px-2  lead-schedule-site-visit']");
	By mergeLeads = By.xpath("//a[@class='nav-link lead-merge']");
	By logOflineCall = By.xpath("//a[@class='nav-link px-2 offline-call-form']");
	By activityTab = By.xpath("//a[text()='Activity'][@href='#tab-activity']");
	By feedTab = By.xpath("//a[text()='Feed'][@href='#tab-activity']");
	By actionBar_Sitevisit = By.cssSelector(
			"#tab-activity > div.activities_list > div:nth-child(1) > div > div.card > div > div:nth-child(1) > div.col-lg-1 > div > a");
	By actionBar_Options = By.cssSelector("ul.dropdown-menu.dropdown-menu-right.show li a");
	By actionBar_Followup = By.cssSelector(
			"#tab-activity > div.activities_list > div:nth-child(1) > div > div.card > div > div:nth-child(1) > div.col-lg-1 > div > div > a");
	public By followupLink = By.cssSelector("a.nav-link.lead-schedule-followup");
	By userIcon = By
			.cssSelector("body > nav > div.top-navigation-right-panel > div > div.dropdown.float-right > a > i");
	By logout = By.cssSelector("[data-method=\"delete\"]");
	By backToAdmin = By.cssSelector("a[href='/client/signout']");
	By more_d = By.xpath("//div[@class='col-sm-12 activity-responsive-tabs col-lg-5 pr-0']//a[@role='button'][normalize-space()='More']");
	By sitevisit_d = By.xpath("//*[@data-value=\"SiteVisit\"]");
	By sitevisit_d_actionbar = By.xpath("//div[@class='col-lg-1']");
	By sitevisit_confirm = By.xpath("//a[text()='Confirm']");
	By brochure = By.xpath("//span[text()='Campaign Responses']/preceding::a[text()='Send Brochure'][1]");
	By sendQuickQuote = By.xpath("//a[text()='Send Quick Quote']");
	By editIcon = By.xpath("//i[@class='ion-edit ml-2']");
	By followup_d = By.xpath("//a[text()='Followups'][@href='#tab-activity']");
	By followup_d_actionbar = By.cssSelector(
			"#tab-activity > div.activities_list > div:nth-child(1) > div > div.card > div > div:nth-child(1) > div.col-lg-1 > div > div > a");
	By email_FollowupActivity = By.xpath("//span[text()='Email']");
	By email_d = By.xpath("//a[@role='tab'][normalize-space()='Emails']");
	// By call_d =
	// By.xpath("//*[@id=\"activity_container\"]/div/div[1]/div[1]/nav/li[3]/a");
	By call_d = By.xpath("//a[@role='tab'][@data-value=\"Call\"]");
	By sms_d = By.xpath("//a[@data-value=\"Sms\"]");
	By subfilter_Sitevisit = By.xpath("//span[text()='Sub filter']");
	By subfilter_Sitevisit_All = By.xpath("//ul[@class='dropdown-menu dropdown-menu-right type show']//li");
	By email_Activity = By.xpath("//a[@class='nav-link'][@data-value='Email']");
	
	//Close Filter
	By closeFilterIcon = By.xpath("//button[@class='close text-white close-filters']//i[@class='ion-android-close']");

	// Stage
	By stageDropdown = By.xpath("//button[@class='btn dropdown-toggle btn-sm btn-outline-primary']");

	By stageDropdown_All = By.xpath("//div[@class='dropdown-menu type show']//a");

	By saveButton = By.xpath("//a[@class='save_stage_and_status btn float-right btn-primary btn-sm mr-2']");

	// Why was this customer Lost? popup appeared when stage changed to Lost
	By selectReasonDropdown = By.xpath("//*[@id=\"stage-change-modal\"]/div/div/div[2]/div[1]/select");   //label[text()='Select reason']/following::select"); 
	By saveChangesButton = By.xpath("//button[text()='Save changes']");

	// Add a Product
	By addaProductButton = By.xpath("//a[@id='add-product']");
	By addProjectsField = By.xpath("//input[@id='s2id_autogen10']");
	By addProjectsField1 = By.xpath("//div[@id='s2id_autogen10']//ul[@class='select2-choices']");
	By addProjectsField2 = By.xpath("//div[@id='s2id_autogen10']//ul[@class='select2-choices']");
	//div[@id='s2id_autogen10']//input[@class='select2-input select2-default']
	
	By addfirstProject = By.cssSelector("ul.select2-results li:nth-child(3)");
	public By projectSelected = By.cssSelector("ul.select2-choices li:nth-child(1) div");
	public By addButton = By.xpath("//button[text()='Add']");

	By actionBar_addProject = By.xpath("//span[text()='Campaign Responses']/preceding::a[@class='btn btn-light']");
	By actionBar_addProject_dd = By.xpath("//ul[@class='dropdown-menu dropdown-menu-right show']//li");

	// Add Inventory
	By funnelIcon_addInventory = By.xpath("//i[@class='ion-funnel']");
	By unitsTab = By.xpath("//a[text()='Units'][@class='nav-link new_resale_div']");
	By actionBar_Unit = By.xpath("//th[text()='Actions']/following::i[@class='fa fa-ellipsis-v'][1]");
	By details_actionBar_Unit = By.xpath("//th[text()='Actions']/following::button[text()='Details'][1]");

	// Inventory details
	By addButton_inventoryDetails = By
			.xpath("//*[@id='right_pane']/div/div/div[1]/div/div/div[2]/div[2]/div[1]/div[1]/a");
	By addButton_inventoryDetails_All = By.xpath("//div[@class='dropdown-menu dropdown-menu-right show']//a");

	// .........Booking Details.........
	By closeButton_Bookingdetails = By.xpath("//button[@class='btn btn-default close_profile_form']");
	By enterUnit = By.xpath("//*[@id=\"s2id_autogen58\"]/a/span[2]");
	By selectUnit = By.xpath("//*[@id=\"select2-drop\"]/ul/li[1]");
	By filters = By.xpath("//*[@id=\"center_pane_container\"]/div/div/div/div[1]/div[2]/div/div/button/i");
	By clearFilters = By.xpath("//*[@id=\"leads-list\"]/div/div/div/div[2]/div/div[2]/div/a");
	By applyButton = By.xpath("//*[@id=\"leads-list\"]/div/div/div/div[2]/div/div[2]/div/div/button");
	By projectFilter = By.xpath("//*[@id=\"s2id_autogen53\"]");
	By projectSelect = By.xpath("//*[@id=\"select2-drop\"]/ul/li[2]");
	By status = By.xpath("//*[@id=\"s2id_autogen51\"]");
	By statusSelect = By.xpath("//*[@id=\"select2-drop\"]/ul/li[1]/div");
	By tower = By.xpath("//*[@id=\"s2id_autogen57\"]");
	By towerSelect = By.xpath("//*[@id=\"select2-drop\"]/ul/li");
	By closeFilter = By.xpath("//*[@id=\"leads-list\"]/div/div/div/div[2]/div/div[1]/div/span/a/i");
	By addAProduct = By
			.xpath("//span[text()='Campaign Responses']/preceding::a[@class='float-right small pt-2 open_form']");
	By enterProduct = By.xpath("//input[@class='select2-input select2-default']");
	By clickAdd = By.xpath("//button[@class='w-100 btn btn-primary add_interested_property']");
	public By noAccessMessageText = By.cssSelector(
			"#leads-overview > div:nth-child(1) > div:nth-child(2) > div.alert.alert-info > div:nth-child(1)");

	WebDriver driver = null;

	public LeadProfilePage(WebDriver driver) {

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		this.driver = driver;// Calling Browser
	}

	public void clickOnFollowup() {
		driver.findElement(more).click();
		driver.findElement(followup).click();
	}

	public void followupLink() {
		//driver.findElement(more).click();
		driver.findElement(followupLink).click();
	}

	// ................Methods for Scheduling Site visit....................

	public void clickOnMeetingLink() {
		//driver.findElement(more).click();
		driver.findElement(siteVisit).click();
	}

	public void selectReschedule() {
		driver.findElement(actionBar_Sitevisit).click();

		List<WebElement> list = driver.findElements(actionBar_Options);
		for (WebElement ele : list) {
			// for every elements it will print the name using innerHTML
			System.out.println("Values " + ele.getAttribute("innerHTML"));

			// Here we will verify if link (item) is equal to Mark as conducted
			if (ele.getAttribute("innerHTML").contains("Reschedule")) {

				// if yes then click on link
				ele.click();
				System.out.println("Clicked on Reschedule");

				// break the loop or come out of loop
				break;
			}
		}
	}

	public void selectMarkAsConducted() {
		driver.findElement(actionBar_Sitevisit).click();

		List<WebElement> list = driver.findElements(actionBar_Options);
		for (WebElement ele : list) {
			// for every elements it will print the name using innerHTML
			System.out.println("Values " + ele.getAttribute("innerHTML"));

			// Here we will verify if link (item) is equal to Mark as conducted
			if (ele.getAttribute("innerHTML").contains("Mark as conducted")) {

				// if yes then click on link
				ele.click();
				System.out.println("Clicked on Mark as conducted");

				// break the loop or come out of loop
				break;
			}
		}
	}

	public void selectMarkDidNotVisit() {
		driver.findElement(actionBar_Sitevisit).click();
		List<WebElement> list = driver.findElements(actionBar_Options);
		for (WebElement ele : list) {
			// for every elements it will print the name using innerHTML
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			// Here we will verify if link (item) is equal to Mark as conducted
			if (ele.getAttribute("innerHTML").contains("Mark did not visit")) {
				// if yes then click on link
				ele.click();
				System.out.println("Clicked on Mark did not visit");
				// break the loop or come out of loop
				break;
			}
		}
	}

	public void selectMarkNotInterested() {
		driver.findElement(actionBar_Sitevisit).click();
		List<WebElement> list = driver.findElements(actionBar_Options);
		for (WebElement ele : list) {
			// for every elements it will print the name using innerHTML
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			// Here we will verify if link (item) is equal to Mark as conducted
			if (ele.getAttribute("innerHTML").contains("Mark not interested")) {
				// if yes then click on link
				ele.click();
				System.out.println("Clicked on Mark not interested");
				// break the loop or come out of loop
				break;
			}
		}
	}

	public void selectConfirm() throws Exception {
		Thread.sleep(2000);
		driver.findElement(actionBar_Sitevisit).click();

		List<WebElement> list = driver.findElements(actionBar_Options);
		for (WebElement ele : list) {
			// for every elements it will print the name using innerHTML
			System.out.println("Values " + ele.getAttribute("innerHTML"));

			// Here we will verify if link (item) is equal to Mark as conducted
			if (ele.getAttribute("innerHTML").contains("Confirm")) {

				// if yes then click on link
				ele.click();
				System.out.println("Clicked on Confirm");

				// break the loop or come out of loop
				break;
			}
		}
	}

	public void selectCancelFollowup() {
		driver.findElement(actionBar_Followup).click();

		List<WebElement> list = driver.findElements(actionBar_Options);
		for (WebElement ele : list) {
			// for every elements it will print the name using innerHTML
			System.out.println("Values " + ele.getAttribute("innerHTML"));

			// Here we will verify if link (item) is equal to Mark as conducted
			if (ele.getAttribute("innerHTML").contains("Cancel Followup")) {

				// if yes then click on link
				ele.click();
				System.out.println("Clicked on Cancel Followup");

				// break the loop or come out of loop
				break;
			}
		}
	}

	public void selectActivity(int tabNumber) {

		driver.findElement(By.cssSelector("#activity_container > div > nav > li:nth-child(" + tabNumber + ") > a"))
				.click();

	}

	public void userLogout() {
		driver.findElement(userIcon).click();
		driver.findElement(logout).click();

	}

	public void backToAdmin() throws Exception {
		Thread.sleep(3000);
		driver.findElement(userIcon).click();
		driver.findElement(backToAdmin).click();

	}

	public void clickOnEmailLink() {
		driver.findElement(email).click();
		driver.findElement(ComposeEmail).click();
	}

	public void clickOnSmsLink() {
		driver.findElement(SMS).click();
	}

	// .........................Methods for adding a Note.......................

	public void clickOnAddANoteLink() {
		driver.findElement(addANote).click();
	}

	public void enterNote(String note) {
		driver.findElement(addNote_Textarea).sendKeys(note);
	}

	public void clickOnsaveNoteButton() {
		driver.findElement(saveNoteButton).click();
	}

	// ......................Methods for Activities Details......................

	public void clickSitvisit_d() {
		driver.findElement(more_d).click();
		driver.findElement(sitevisit_d).click();
	}

	public void openEmailActivities() {
		driver.findElement(more_d).click();
		driver.findElement(email_Activity).click();

	}

	public void selectPending_Sitevisit() throws InterruptedException {
		driver.findElement(subfilter_Sitevisit).click();
		List<WebElement> list = driver.findElements(subfilter_Sitevisit_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Pending")) {
				Thread.sleep(1000);
				ele.click();
				System.out.println("Clicked on Pending");
				break;
			}
		}
	}

	public void clickFollowup_d() {
		driver.findElement(more_d).click();
		driver.findElement(followup_d).click();
	}

	public void clickOnCall_d() {
		//driver.findElement(more_d).click();
		driver.findElement(call_d).click();
	}

	public void clickEmail_d() {
		driver.findElement(more_d).click();
		driver.findElement(email_d).click();
	}

	public void clickSms_d() {
		driver.findElement(more_d).click();
		driver.findElement(sms_d).click();
	}

	public void clickActivityTab() {
		driver.findElement(activityTab).click();
	}

	public void clickFeedTab() {
		//driver.findElement(more_d).click();
		driver.findElement(feedTab).click();
	}

	public void clickOnActionBar() {
		driver.findElement(followup_d_actionbar).click();
	}

	public void clickOnEmail_FollowupActivity() {
		driver.findElement(email_FollowupActivity).click();
	}

	public void selectPush() {
		driver.findElement(more).click();
		driver.findElement(push).click();
	}

	public void selectPull() throws Exception {
		Thread.sleep(5000);
		driver.findElement(pull).click();
	}

	public void selectReassign() {
		driver.findElement(more).click();
		driver.findElement(reassign).click();
	}

	public void selectConductedSiteVisit() {
		//driver.findElement(more).click();
		driver.findElement(conductedSiteVisit).click();
	}

	public void selectSiteVisit() {
		driver.findElement(more).click();
		driver.findElement(siteVisit).click();
	}

	public void selectMergeLeads() {
		driver.findElement(more).click();
		driver.findElement(mergeLeads).click();
	}

	public void clickOnBrochure() {
		driver.findElement(brochure).click();
	}

	public String getLeadId() {
		String leadId = driver.findElement(leadid).getText();
		return leadId;
	}

	public void clickOnSendQuickQuote() {
		driver.findElement(sendQuickQuote).click();
	}

	// ...........Method for stage change..............

	public void changing_Stage(String stageName) throws InterruptedException {

		driver.findElement(stageDropdown).click();

		List<WebElement> list = driver.findElements(stageDropdown_All);

		for (WebElement ele : list) {

			// Here we will verify if link (item) is equal to Prospect
			if (ele.getAttribute("innerHTML").contains(stageName)) {
				// if yes then click on link
				Thread.sleep(1000);
				ele.click();
				// break the loop or come out of loop
				break;
			}
		}

		Thread.sleep(1000);
		driver.findElement(saveButton).click();
	}

	public void selectReasonForLostOrUnqualified() {
		Select oSelect = new Select(driver.findElement(selectReasonDropdown));
		oSelect.selectByIndex(4);
		driver.findElement(saveChangesButton).click();
	}
	// ........Methods for adding project on lead................

	public void addingProject() throws InterruptedException {
		driver.findElement(addaProductButton).click();
		driver.findElements(addProjectsField).get(2).click();
		Thread.sleep(2000);

		driver.findElement(addfirstProject).click();

	}

	public void selectProject() throws InterruptedException {
		driver.findElement(addaProductButton).click();
		driver.findElement(addProjectsField1).click();
		List<WebElement> list = driver.findElements(addProjectsField);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Swapnil Villa")) {
				Thread.sleep(1000);
				ele.click();
				System.out.println("Clicked on Swapnil Villa");
				break;
			}
		}
	}
	
	
	
	
	public void addingInventory() throws InterruptedException {
		driver.findElement(actionBar_addProject).click();
		List<WebElement> list = driver.findElements(actionBar_addProject_dd);
		for (WebElement ele : list) {
			// for every elements it will print the name using innerHTML
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			// Here we will verify if link (item) is equal to Add Inventory
			if (ele.getAttribute("innerHTML").contains("add_inventory_property")) {
				// if yes then click on link
				ele.click();
				System.out.println("Add inventory");
				// break the loop or come out of loop
				break;
			}
		}
	}

	public void selectingUnit() throws InterruptedException {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(actionBar_Unit).click();
		Thread.sleep(1000);
		driver.findElement(details_actionBar_Unit).click();
	}

	public void bookingUnit() throws InterruptedException {

		driver.findElement(addButton_inventoryDetails).click();
		List<WebElement> list = driver.findElements(addButton_inventoryDetails_All);
		for (WebElement ele : list) {
			// for every elements it will print the name using innerHTML
			System.out.println("Values " + ele.getAttribute("innerHTML"));

			// Here we will verify if link (item) is equal to Booked
			if (ele.getAttribute("innerHTML").contains("Booked")) {

				// if yes then click on link
				ele.click();

				System.out.println("Booked");

				// break the loop or come out of loop
				break;
			}
		}
	}

	public void selectBookings() {
		// To scroll Up
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-450)", "");
		driver.findElement(more).click();
		driver.findElement(bookings).click();
	}

	public void searchAndSelectProjectUnit() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(enterUnit));
		actions.click().perform();
		actions.sendKeys("ps unit 75").perform();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(selectUnit).click();
	}

	public void selectLogOfflineCalls() {
		//driver.findElement(more).click();
		driver.findElement(logOflineCall).click();
	}

	public void clickOnCloseButton_Bookingdetails() {
		driver.findElement(closeButton_Bookingdetails).click();
	}

	public void clickOnEditIcon() {
		driver.findElement(editIcon).click();
	}

	public void changeFilters() {
		Actions actions = new Actions(driver);

		// Selecting filter and clear the default filters
		actions.moveToElement(driver.findElement(filters));
		actions.click().perform();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		actions.moveToElement(driver.findElement(clearFilters));
		actions.click().perform();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		actions.moveToElement(driver.findElement(applyButton)).click().perform();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Project filter
		actions.moveToElement(driver.findElement(projectFilter));
		actions.click();
		actions.sendKeys("Project Secure");
		actions.build().perform();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		actions.moveToElement(driver.findElement(projectSelect)).click().perform();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Status filter
		actions.moveToElement(driver.findElement(status)).click().perform();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		actions.moveToElement(driver.findElement(statusSelect)).click().perform();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Tower filter
		actions.moveToElement(driver.findElement(tower)).click();
		actions.sendKeys("Inventory1");
		actions.build().perform();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		actions.moveToElement(driver.findElement(towerSelect)).click().perform();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Click Apply button
		actions.moveToElement(driver.findElement(applyButton)).click().perform();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		JavascriptExecutor jse = (JavascriptExecutor) (driver);
		jse.executeScript("window.scrollBy(0,-300)", "");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Close the filter popup
		actions.moveToElement(driver.findElement(closeFilter)).click().perform();

	}
	
	public void closeFilter() {
		driver.findElement(closeFilterIcon).click();
	}

	public void addAProduct() {
		driver.findElement(addAProduct).click();
		List<WebElement> list = driver.findElements(enterProduct);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));

			// Here we will verify if link (item) is equal to Booked
			if (ele.getAttribute("innerHTML").contains("Testing")) {

				// if yes then click on link
				ele.click();
				System.out.println("Testing");
				break;
			}
		}
		driver.findElement(clickAdd).click();
	}
}
