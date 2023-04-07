package crm.selldo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.SetUp;

public class SiteVisitPage extends SetUp {

	// ...................Schedule Site visit form .........................

	By projectSpan = By.xpath("//label[@for='project_id']/following::span[1]");
	By project_dd = By.xpath("//input[@class='select2-input']/following::ul[@class='select2-results']//li[3]");
	By scheduleOnDate = By.xpath("//input[@name='scheduled_date']");
	By conductedOnDate = By.xpath("//input[@name='conducted_date']");
	By wholeCalender = By.xpath(
			"//div[@class='datepicker datepicker-dropdown dropdown-menu datepicker-orient-left datepicker-orient-top']//tr//td");
	By selectToday = By.cssSelector("div.datepicker-days > table > tbody > tr > td.active.day");
	By scheduleOnTime = By.xpath("//label[text()='Schedule on (date & time)']/following::span[2]");
	By scheduleSiteVisitButton = By.xpath("//button[normalize-space()='Schedule Site visit']");
	public By siteVisitConfirmation = By.xpath("//label[text()='Site visit Confirmation']/following::span[1]");
	By tentative_dd = By.cssSelector("#select2-drop > div > input");
	By tentative_d2 = By.xpath("//*[text()='Tentative']");
	By ignoreAndScheduleButton = By.xpath("//button[text()=' Ignore & Schedule ']");
	By siteVisitNotes_addConducted = By.xpath("//textarea[@class='form-control notes']");
	By addPreviouslyConductedSiteVisitButton = By.xpath("//button[@type=\"submit\"]");

	// ...................Reschedule Site visit form........................

	By rescheduleButton = By.xpath("//button[text()='Reschedule']");

	// ....................Mark Site visit Conducted.........................

	By scheduleOnDate_ConductedOn = By.xpath("//label[text()='Conducted on (date & time)']/following::input[1]");
	By calender = By.xpath(
			"//div[@class='datepicker datepicker-dropdown dropdown-menu datepicker-orient-left datepicker-orient-top']//tr//td");
	By conductedNotes = By.xpath("//textarea[@name='notes']");
	By markAsConductedButton = By.xpath("//button[@type=\"submit\"][text()='Save']");

	// ......................Mark Did Not Visit.................................

	By didNotVisitNotes = By.xpath("//label[text()='Site visit notes']/following::textarea");
	By didNotVisitButton = By.xpath("//button[text()='Did not visit']");

	// ......................Mark Not Interested................................

	By notInterestedNotes = By.xpath("//label[text()='Site visit notes']/following::textarea");
	By markNotInterestedButton = By.xpath("//button[text()='Mark not interested']");

	By confirmButton = By.xpath("//button[text()='Confirm']");

	/*
	 * WebDriver driver = null; WebDriverWait wait;
	 * 
	 * public DocumentsPage(WebDriver driver) {
	 * 
	 * this.driver = driver; wait = new WebDriverWait(driver, 8); }
	 */

	WebDriver driver = null;

	WebDriverWait wait;

	public SiteVisitPage(WebDriver driver) {

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		this.driver = driver;// Calling Browser

	}
	
	public void selectProject(){
		driver.findElement(projectSpan).click();
		driver.findElement(project_dd).click();
	}

	public void sitevisitScheduleDate() throws Exception {
		Thread.sleep(2000);
		driver.findElement(scheduleOnDate).click();
		List<WebElement> dates = driver.findElements(wholeCalender);
		int total_node = dates.size();
		for (int i = 0; i < total_node; i++) {
			String date = dates.get(i).getText();
			if (date.equals("6")) {
				dates.get(i).click();// Clicking on above selected date
				break;
			}
		}
	}

	public void selectDate() throws Exception {
		Thread.sleep(2000);
		driver.findElement(scheduleOnDate).click();
		driver.findElement(selectToday).click();
	}

	public void clickOnScheduleSiteVisitButton() {
		driver.findElement(scheduleSiteVisitButton).click();
	}

	public void clickOnConfirmButton() throws Exception {
		Thread.sleep(5000);
		driver.findElement(confirmButton).click();
	}

	public void clickOnIgnoreAndSchedule() {
		driver.findElement(ignoreAndScheduleButton).click();
	}

	// ...................Methods for Reschedule............................

	public void sitevisitRescheduleDate() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(scheduleOnDate).click();
		List<WebElement> dates = driver.findElements(wholeCalender);
		int total_node = dates.size();
		for (int i = 0; i < total_node; i++) {
			String date = dates.get(i).getText();
			if (date.equals("28")) {
				dates.get(i).click();// Clicking on above selected date
				break;
			}
		}
	}

	public void clickOnRescheduleButton() throws Exception {
		Thread.sleep(4000);
		driver.findElement(rescheduleButton).click();
	}

	// .................Methods for Mark As Conducted.......................

	public void sitevisitConductedOnDate() throws Exception {
		Thread.sleep(2000);
		driver.findElement(scheduleOnDate_ConductedOn).click();
		List<WebElement> dates = driver.findElements(calender);
		int total_node = dates.size();
		for (int i = 0; i < total_node; i++) {
			String date = dates.get(i).getText();
			if (date.equals("10")) {
				dates.get(i).click();// Clicking on above selected date
				break;
			}
		}
	}

	public void enterNotesForConductedSiteVisit(String notes) {
		driver.findElement(conductedNotes).sendKeys(notes);
	}

	public void clickOnMarkAsConductedButton() throws Exception {
		Thread.sleep(3000);
		driver.findElement(markAsConductedButton).click();
	}

	// ..................Methods for Mark Did Not Visit........................

	public void enterNotesForDidNotVisit(String notes) {
		driver.findElement(didNotVisitNotes).sendKeys(notes);
	}

	public void clickOnDidNotVisitButton() throws Exception {

		Thread.sleep(5000);
		driver.findElement(didNotVisitButton).click();
	}

	// ..................Methods for Mark Not Interested.......................

	public void enterNotesForNotInterested(String notes) throws Exception {
		Thread.sleep(2000);
		driver.findElement(notInterestedNotes).sendKeys(notes);
	}

	public void clickOnMarkNotInterestedButton() throws Exception {
		Thread.sleep(5000);
		driver.findElement(markNotInterestedButton).click();
	}

	// ........................Selecting
	// Tentative................................

	public void selectTentative() {
		driver.findElement(siteVisitConfirmation).click();
		driver.findElement(tentative_dd).sendKeys("Tentative");
		driver.findElement(tentative_d2).click();

	}

	// ...........Methods for adding conducted site visit..................

	public void sitevisitConductedDate(String dt) throws Exception {
		Thread.sleep(2000);
		driver.findElement(conductedOnDate).click();
		List<WebElement> dates = driver.findElements(wholeCalender);
		int total_node = dates.size();
		for (int i = 0; i < total_node; i++) {
			String date = dates.get(i).getText();
			if (date.equals(dt)) {
				dates.get(i).click();// Clicking on above selected date
				break;
			}
		}
	}

	public void addNotes(String notes) {
		driver.findElement(siteVisitNotes_addConducted).sendKeys(notes);
	}

	public void clickOnAddPreviouslyConductedSvButton() {
		driver.findElement(addPreviouslyConductedSiteVisitButton).click();
	}

}
