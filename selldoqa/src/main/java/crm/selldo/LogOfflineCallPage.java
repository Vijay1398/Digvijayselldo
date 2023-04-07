package crm.selldo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class LogOfflineCallPage {

	By direction = By.xpath("//select[@class='form-control direction select2']");
	By status = By.xpath("//select[@class='form-control status select2']");
	By date = By.cssSelector("input[name='time_stamp_date']");
	By selectToday = By.cssSelector(
			"body > div.datepicker.datepicker-dropdown.dropdown-menu.datepicker-orient-left.datepicker-orient-top > div.datepicker-days > table > tbody > tr > td.active.day");
	By time = By.cssSelector("input[name='time_stamp_time']");
	By time_down = By.xpath("//input[@class='bootstrap-timepicker-hour']/following::i[1]");
	By duration = By.cssSelector("input[name='duration']");
	By durationUnit = By.cssSelector("select[class='form-control duration_unit select2']");
	By noteTextArea = By.xpath("//textarea[@placeholder='Add note']");
	By saveButton = By.xpath("//button[text()='Save'][@type='submit']");
	By viewNotes = By.cssSelector("#tab-activity > div.activities_list > div:nth-child(1) > div > div.card > div > div:nth-child(1) > div.col-lg-11 > div.row > div > a");
	
	WebDriver driver = null;

	public LogOfflineCallPage(WebDriver driver) {

		this.driver = driver;
	}

	public void selectDirection_Inbound() {
		Select oSelect = new Select(driver.findElement(direction));
		oSelect.selectByVisibleText("Inbound");
	}

	public void selectDirection_Outbound() {
		Select oSelect = new Select(driver.findElement(direction));
		oSelect.selectByVisibleText("Outbound");
	}

	public void selectStatus_Answered() {
		Select oSelect = new Select(driver.findElement(status));
		oSelect.selectByVisibleText("Answered");
	}

	public void selectStatus_NotAnswered() {
		Select oSelect = new Select(driver.findElement(status));
		oSelect.selectByVisibleText("Not Answered");
	}

	// Selecting current date from calendar
	public void selectDate() {
		driver.findElement(date).click();
		driver.findElement(selectToday).click();
	}

	public void selectTime() throws InterruptedException {
		driver.findElement(time).click();
		Thread.sleep(1000);
		driver.findElement(time_down).click();
		/*WebElement textbox = driver
				.findElement(By.xpath("//label[text()='Duration']/following::div[@class='form-group'][1]"));
		textbox.sendKeys(Keys.ENTER);*/
	}

	public void enterDuration(String dur) {
		driver.findElement(duration).sendKeys(dur);
	}

	public void selectDurationUnit_Minutes() {
		Select oSelect = new Select(driver.findElement(durationUnit));
		oSelect.selectByVisibleText("Minute(s)");
	}

	public void selectDurationUnit_Seconds() {
		driver.findElement(durationUnit).click();
		Select oSelect = new Select(driver.findElement(durationUnit));
		oSelect.selectByVisibleText("Second(s)");
	}

	public void enterNotes(String note) {
		driver.findElement(noteTextArea).sendKeys(note);
	}

	public void clickOnSaveButton() {
		driver.findElement(saveButton).click();
	}
	
	public void clickOnViewNotesLink() {
		driver.findElement(viewNotes).click();
	}
}
