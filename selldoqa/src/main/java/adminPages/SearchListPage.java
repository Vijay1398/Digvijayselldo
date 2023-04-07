package adminPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.SetUp;

import static java.awt.SystemColor.window;

public class SearchListPage extends SetUp {

	By newListButton = By.xpath("//a[text()='New List']");
	By name = By.xpath("//input[@id='search_criterium_name']");
	By scheduledActivitySpan = By.cssSelector("#s2id_search_criterium_date_range_field a span:nth-child(1)");
	By scheduledActivity_All = By.xpath("//ul[@class='select2-results']//li");
	By scheduledActivityRangeSpan = By.xpath("//*[@id=\"list-basics\"]/div[2]/div/div[3]/div/div/a");
	By scheduledActivityRange_CalendarStartDate = By.xpath("//div[@class='daterangepicker dropdown-menu single opensright show-calendar']//tr//td");
	By purposeSpan = By.xpath("//*[@id=\"s2id_autogen31\"]");
	By purpose_All = By.xpath("//ul[@class='select2-results']//li");
	By saveButton = By.xpath("//*[@id=\"modal-remote-form-inner\"]/div/div/div[3]/input");
	By actionbar = By.xpath("//th[text()='Actions']/following::i[1]");
	By editLink = By.xpath("//th[text()='Actions']/following::a[text()='Edit'][1]");
	By scheduledActivityRange_CalendarEndDate = By.xpath("//div[@class='daterangepicker dropdown-menu single opensright show-calendar']//tr//td");
	By startDate =  By.xpath("//input[@class='datepicker starts_on input-normal']");
	By endDate = By.xpath("//input[@class='datepicker ends_on input-normal']");
	By applyButton = By.xpath("//button[@class='btn btn-sm btn-primary applyBtn']"); //html/body/div[11]/button[1]");
	By order= By.xpath("//*[@id=\"s2id_search_criterium_date_range_order\"]/a/span[1]"); 
	By orderList = By.xpath("//ul[@class='select2-results']//li");

	WebDriver driver = null;
	WebDriverWait wait;

	public SearchListPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
	}

	public void clickOnNewListButton() {
		driver.findElement(newListButton).click();
	}

	public void enterListName(String listName) {
		driver.findElement(name).sendKeys(listName);
	}

	public void selectScheduledActivity() {
		driver.findElement(scheduledActivitySpan).click();
		List<WebElement> list = driver.findElements(scheduledActivity_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Lead received on")) {
				ele.click();
				System.out.println("Clicked on Lead received on");
				break;
			}
		}
	}

	// Select the order as it is a mandatory field
	public void selectOrder(){
		driver.findElement(order).click();
		List<WebElement> list = driver.findElements(orderList);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Ascending")) {
				ele.click();
				System.out.println("Clicked on Lead received on");
				break;
			}
		}
	}

	public void selectScheduledActivityRange() {

		// Click Scheduled activity range field
		driver.findElement(scheduledActivityRangeSpan).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Click Start date field and select the date
		driver.findElement(startDate).click();
		List<WebElement> dates = driver.findElements(scheduledActivityRange_CalendarStartDate);
		int total_node = dates.size();
		for (int i = 0; i < total_node; i++) {
			String date = dates.get(i).getText();
			if (date.equals("2")) {
				dates.get(i).click();// Clicking on above selected date
				break;
			}
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Click end date field and select the select date
		driver.findElement(endDate).click();
		List<WebElement> dates2 = driver.findElements(scheduledActivityRange_CalendarEndDate);
		int total_node2 = dates2.size();
		for (int i = 0; i < total_node2; i++) {
			String date = dates2.get(i).getText();
			if (date.equals("2")) {
				dates2.get(i).click();// Clicking on above selected date
				break;
			}
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Click Apply button
		driver.findElement(applyButton).click();
	}

	public void selectPurpose() {
		WebElement element = driver.findElement(purposeSpan);

		// Scroll the page to the element
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();", element);
		driver.findElement(purposeSpan).click();try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		List<WebElement> list = driver.findElements(purpose_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Export")) {
				ele.click();
				System.out.println("Clicked on Export");
				break;
			}
		}
	}

	public void clickOnSaveButton() {
		driver.findElement(saveButton).click();
	}

	public void selectEditLink() {
		driver.findElement(actionbar).click();
		driver.findElement(editLink).click();
	}

	public void changeScheduledActivity() {
		driver.findElement(scheduledActivitySpan).click();
		List<WebElement> list = driver.findElements(scheduledActivity_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Next scheduled Site visit")) {
				ele.click();
				System.out.println("Clicked on Next scheduled Site visit");
				break;
			}
		}
	}

	public void changePurpose() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(purposeSpan));
		WebElement element  = driver.findElement(purposeSpan);
		element.click();
		List<WebElement> list = driver.findElements(purpose_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Sales")) {
				ele.click();
				System.out.println("Clicked on Sales");
				break;
			}
		}
	}

	public void changeListName(String listName) {
		driver.findElement(name).clear();
		driver.findElement(name).sendKeys(listName);
	}
}
