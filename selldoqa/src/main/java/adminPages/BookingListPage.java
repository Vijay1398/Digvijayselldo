package adminPages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.SetUp;

public class BookingListPage extends SetUp {

	By newBookingSearchCriteriaButton = By.xpath("//a[text()='New Booking Search Criteria']");
	By name = By.id("bookings_search_criterium_name");
	By projectsSpan = By.xpath("//input[@id='s2id_autogen2']");
	By selectDropdownValue = By.cssSelector("div#select2-drop ul li:nth-child(1) div");
	By project_All = By.xpath("//ul[@class='select2-results']//li");
	By campaignSpan = By.xpath("//*[@id=\"s2id_autogen3\"]");
	By campaign_All = By.xpath("//ul[@class='select2-results']//li");
	By publishersSpan = By.xpath("//*[@id=\"s2id_autogen4\"]");
	By publishers_All = By.xpath("//ul[@class='select2-results']/li");
	By subCampaign = By.xpath("//input[@id='bookings_search_criterium_sub_sources']");
	By teamSpan = By.xpath("//*[@id=\"s2id_autogen6\"]");
			//"input[@id='s2id_autogen5']");
	By team_All = By.xpath("//ul[@class='select2-results']//li");
	By salesSpan = By.xpath("//*[@id=\"s2id_autogen8\"]"); //input[@id='s2id_autogen6']");
	By sales_All = By.xpath("//ul[@class='select2-results']//li");
	By refundStatusSpan = By.cssSelector("#bookings_search_criterium_booking_custom_attributes_refund_status");
	By refundStatus_All = By.xpath("//ul[@class='select2-results']/li/div");
	By modeOfRefundSpan = By.cssSelector("#bookings_search_criterium_booking_custom_attributes_mode_of_refund");
	By modeOfRefund_All = By.xpath("//ul[@class='select2-results']/li");
	By saveButton = By.xpath("//input[@type='submit']");
	By actionbar = By.xpath("//th[text()='Actions']/following::i[1]");
	By editLink = By.xpath("//th[text()='Actions']/following::a[text()='Edit'][1]");

	WebDriver driver = null;
	WebDriverWait wait;

	public BookingListPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
	}

	public void clickOnNewBookingSearchCriteriaButton() {
		driver.findElement(newBookingSearchCriteriaButton).click();
	}

	public void enterBookingListName(String listName) {
		driver.findElement(name).sendKeys(listName);
	}

	public void selectProject() {
		driver.findElement(projectsSpan).click();
		driver.findElement(selectDropdownValue).click();
	}

	public void selectCampaign() {
		driver.findElement(campaignSpan).click();
		driver.findElement(selectDropdownValue).click();
	}

	public void selectPublishers() {
		driver.findElement(publishersSpan).click();
		driver.findElement(selectDropdownValue).click();
	}

	public void selectTeams() {
		driver.findElement(teamSpan).click();
		driver.findElement(selectDropdownValue).click();
	}

	public void selectSales() {
		//Scroll to bottom of page
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(salesSpan));
		
		driver.findElement(salesSpan).click();
		driver.findElement(selectDropdownValue).click();
	}

	public void selectRefundStatus() {
		driver.findElement(refundStatusSpan).click();
		List<WebElement> list = driver.findElements(refundStatus_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Pending")) {
				ele.click();
				System.out.println("Clicked on Pending");
				break;
			}
		}
	}

	public void selectModeOfRefund() {
		driver.findElement(modeOfRefundSpan).click();
		List<WebElement> list = driver.findElements(modeOfRefund_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("RTGS")) {
				ele.click();
				System.out.println("Clicked on RTGS");
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

	public void changeBookingListName(String listName) {
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(name));
		actions.click().perform();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(name).clear();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		driver.findElement(name).sendKeys(listName);
	}

	public void changeProject() {
		driver.findElement(projectsSpan).click();

		List<WebElement> list = driver.findElements(project_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("jagan")) {
				ele.click();
				System.out.println("Clicked on jagan");
				break;
			}
		}
	}

	public void changeCampaign() {
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(campaignSpan)));
        driver.findElement(campaignSpan).click();
		List<WebElement> list = driver.findElements(campaign_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("walkin")) {
				ele.click();
				System.out.println("Clicked on walkin");
			}
		}
	}

	public void changePublishers() {
		driver.findElement(publishersSpan).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WebElement> list = driver.findElements(publishers_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("DNA - Online")) {
				ele.click();
				System.out.println("Clicked on DNA online");
				break;
			}
		}
	}
	public void changeTeams() {
		driver.findElement(teamSpan).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WebElement> list = driver.findElements(team_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Teamwed")) {
				ele.click();
				System.out.println("Clicked on Teamwed");
				break;
			}
		}
	}

	public void changeSales() {
		//Scroll to bottom of page
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(salesSpan));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(salesSpan).click();
		List<WebElement> list = driver.findElements(sales_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Sales Manager")) {
				ele.click();
				System.out.println("Clicked on Sales Manager");
				break;
			}
		}
	}

	public void changeRefundStatus() {
		driver.findElement(refundStatusSpan).click();
		List<WebElement> list = driver.findElements(refundStatus_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Refund Done")) {
				ele.click();
				System.out.println("Clicked on Refund Done");
				break;
			}
		}
	}

	public void changeModeOfRefund() {
		driver.findElement(modeOfRefundSpan).click();
		List<WebElement> list = driver.findElements(modeOfRefund_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Cheque")) {
				ele.click();
				System.out.println("Clicked on Cheque");
				break;
			}
		}
	}

}
