package crm.selldo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utility.SetUp;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class FollowupsPage extends SetUp {
	By ScheduleOnDateField = By.cssSelector("input[name=\"scheduled_date\"]");
	By ScheduleOnTimeField = By.cssSelector("		");
	By selectToday = By.cssSelector("div.datepicker-days > table > tbody > tr > td.active.day");
	By FollowupType = By.cssSelector("#s2id_autogen26 > a > span.select2-chosen");
	By callFollowup = By.cssSelector("#select2-drop ul li:nth-child(1)");
	By emailFollowup = By.cssSelector("#select2-drop ul li:nth-child(2)");
	By subjectText = By.cssSelector("[placeholder=\"Subject\"]");
	By noteText = By.cssSelector("[placeholder=\"Notes / Agenda\"]");
	By ScheduleFollowupButton = By.cssSelector("button.btn.btn-primary.pull-right.schedule_followup");
	By closeButton = By.cssSelector("button.close.text-white.lead-index");
	By CancellationReasonDropdown = By.cssSelector("div.select2-container.form-control.cancellation_reason a span");
	By cancellationReason = By.cssSelector("#select2-drop > ul > li:nth-child(1) > div");
	By cancelNoteText = By.cssSelector("textarea[placeholder=\"Notes\"]");
	By cancelFollowupButton = By.xpath("//button[text()='Cancel Followup']");
	By ignoreAndScheduleButton = By.id("btn-ignore-schedule");
	By cancelFollowupForPostSalesButton = By.xpath("//button[text()='Cancel']");
	By ScheduleFollowupButtonForPostSales = By.xpath("//button[contains(@class,'btn btn-primary submit_form')]");

	WebDriver driver = null;

	public FollowupsPage(WebDriver driver) {

		this.driver = driver;
	}

	// Selecting current date from calendar
	public void selectDate() throws Exception {
		Thread.sleep(2000);
		driver.findElement(ScheduleOnDateField).click();
		driver.findElement(selectToday).click();
	}

	// Clicking on Schedule On Time field
	public void clickOnScheduleOnTimeField() {
		driver.findElement(ScheduleOnTimeField).click();
	}

	// Selecting type of follow up either call/email

	public void selectFollowupType(String type) {
		driver.findElement(FollowupType).click();
		if (type.equalsIgnoreCase("Call")) {
			driver.findElement(callFollowup).click();
		} else {
			driver.findElement(emailFollowup).click();
		}

	}

	// adding Subject
	public void addSubject(String subject) {
		driver.findElement(noteText).sendKeys(subject);

	}

	// adding Notes
	public void addingNotes(String notes) {
		driver.findElement(subjectText).sendKeys(notes);

	}

	// Clicking on Schedule Followup button
	public void clickOnScheduleFollowupButton() {
		driver.findElement(ScheduleFollowupButton).click();
	}
	
	public void clickOnIgnoreAndSchedule() {
		driver.findElement(ignoreAndScheduleButton).click();
	}

	// Closing Follow up Page
	public void closeWindow() {
		driver.findElement(closeButton).click();

	}

	// Clicking on Cancellation Reason drop down
	public void clickCancellationReasonDropdown() throws Exception {
		Thread.sleep(4000);
		driver.findElement(CancellationReasonDropdown).click();
	}

	// Selecting Cancellation Reason as Incorrect follow up date
	public void selectCancellationReason() {
		driver.findElement(cancellationReason).click();
	}

	// adding Cancellation Notes
	public void cancelNotes(String textNotes) {
		driver.findElement(cancelNoteText).sendKeys(textNotes);

	}

	// Clicking on Cancel Follow up button
	public void clickCancelFollowupButton() {
		driver.findElement(cancelFollowupButton).click();
	}
	
	// Clicking on Schedule Followup button for post sales
			public void clickOnScheduleFollowupButtonForPostSales() {
				driver.findElement(ScheduleFollowupButtonForPostSales).click();
			}
	
		
		// Clicking on Cancel Follow up button PostSales
			public void clickCancelFollowupForPostSalesButton() {
				driver.findElement(cancelFollowupForPostSalesButton).click();
			}
	

}
