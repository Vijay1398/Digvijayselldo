package postSales;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.GetTestData;
import utility.SetUp;

public class PostSalesDashboardPage extends SetUp {
	
	
	By searchTextBox = By.cssSelector("#select2-drop > div > input");
	By searchField = By.xpath("//span[text()='Search...']");
	By editIcon = By.xpath("//i[@class='ion-edit mr-0']");
	By primaryApplicant = By.xpath("//a[@class='nav-link'][contains(text(),'Onkar Gosavi')]");
	By refreshIcon = By.xpath("//i[@class='ion-refresh refresh-post-sales-dashboard']");
	By closeButton = By.xpath("//button[@class='btn btn-primary submit_form']");
	
	// ........ Booking Details Bucket .........
	
	By confirmedBookingsBucket = By.xpath("//div[text()='Confirmed Booking Details']/following::span[1]");
	By tentativeBookingsBucket = By.xpath("//div[text()='Tentative Booking Details']/following::span[1]");
	By cancelledBookingsBucket = By.xpath("//div[text()='Cancelled Booking Details']/following::span[1]");
	
	// ........ Demand Letters Bucket .........
	
	By draftDemandLetterBucket = By.xpath("//div[text()='Draft']/following::span[1]");
	By deliveredDemandLetterBucket = By.xpath("//div[text()='Delivered']/following::span[1]");
	By partiallyPaidDemandLetterBucket = By.xpath("//div[text()='Partially Paid']/following::span[1]");
	By paidDemandLetterBucket = By.xpath("//div[text()='Paid']/following::span[1]");
	By dueDemandLetterBucket = By.xpath("//div[text()='Due']/following::span[1]");
	
	// ........ Receipts Bucket .........
	
	By clearancePendingReceiptBucket = By.xpath("//div[text()='Clearance Pending']/following::span[1]");
	By successfulReceiptBucket = By.xpath("//div[text()='Successful']/following::span[1]");
	By failedReceiptBucket = By.xpath("//div[text()='Failed']/following::span[1]");
	By availableForRefundReceiptBucket = By.xpath("//div[text()='Available for Refund']/following::span[1]");
	By refundedReceiptBucket = By.xpath("//div[text()='Refunded']/following::span[1]");
	
	// ........ Missed FollowUps Bucket .........
	
	By missedFollowups = By.xpath("//div[text()='missed followups']");
	public By missedFollowUps = By.xpath("//div[@class='dashboard-right-pane col-sm-3 col-md-3 col-lg-3 full-height']//div[3]//div[1]//div[1]//div[3]//h4");
	public By openTasksLink = By.xpath("//a[contains(text(),'Open Tasks')]");
	By actionBar = By.xpath("//a[@class='table-action-btn dropdown-toggle btn btn-light btn-sm']");
	By details = By.xpath("//button[@class='dropdown-item booking-details-view link']");
	public By followupLink = By.xpath("//a[contains(@class,'followup')]");
	By homeButton = By.xpath("//a[contains(text(),'Home')]");
	
	
	
		
	
	
	WebDriver driver = null;

	WebDriverWait wait;
	
	public PostSalesDashboardPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
	
	}

	GetTestData getTestData = new GetTestData();
	
	
	// To Search Lead
		public void searchLead(String lead) throws Exception {
			Thread.sleep(2000);
			driver.findElement(searchField).click();
			Thread.sleep(2000);
			WebElement webElement = driver.findElement(searchTextBox);
			webElement.sendKeys(lead);
			Thread.sleep(4000);
			webElement.sendKeys(Keys.ENTER);// Clicking enter
		}

		public void clickOnEditIcon() {
			
			driver.findElement(editIcon).click();
			
			// To click the primary applicant tab
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("arguments[0].scrollIntoView(false);",driver.findElement(primaryApplicant));
		    driver.findElement(primaryApplicant).click();
		 
		}
		
		public void clickOnCloseButton() {
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("arguments[0].scrollIntoView(false);",driver.findElement(closeButton));
			driver.findElement(closeButton).click();
		}
		
		public void refreshDashboardStats() {
			driver.findElement(refreshIcon).click();
		}
		
		public void clickOnConfirmedBookingsBucket() {
			driver.findElement(confirmedBookingsBucket).click();
		}
		
		public void clickOnTentativeBookingsBucket() {
			driver.findElement(tentativeBookingsBucket).click();
		}
		
		public void clickOnCancelledBookingsBucket() {
			driver.findElement(cancelledBookingsBucket).click();
		}
		
		public void clickOnDraftDemandLetterBucket() {
			driver.findElement(draftDemandLetterBucket).click();
		}
		
		public void clickOnDeliveredDemandLetterBucket() {
			driver.findElement(deliveredDemandLetterBucket).click();
		}
		
		public void clickOnPartiallyPaidBucket() {
			driver.findElement(partiallyPaidDemandLetterBucket).click();
		}
		
		public void clickOnPaidDemandLetterBucket() {
			driver.findElement(paidDemandLetterBucket).click();
		}
		
		public void clickOnDueDemandLetterBucket() {
			driver.findElement(dueDemandLetterBucket).click();
		}
		
		public void clickOnClearancePendingReceiptBucket() {
			driver.findElement(clearancePendingReceiptBucket).click();
		}
		public void clickOnSuccessfulReceiptBucket() {
			driver.findElement(successfulReceiptBucket).click();
		}
		public void clickOnFailedReceiptBucket() {
			driver.findElement(failedReceiptBucket).click();
		}
		public void clickOnAvailableForRefundReceiptBucket() {
			driver.findElement(availableForRefundReceiptBucket).click();
		}
		public void clickOnRefundedReceiptBucket() {
			driver.findElement(refundedReceiptBucket).click();
		}
		
		public void clickOnMissedFollowupBucket() {
			driver.findElement(openTasksLink).click();
			driver.findElement(missedFollowups).click();
		}
		
		public void openLeadProfile() throws Exception {
			Thread.sleep(3000);
			driver.findElement(actionBar).click();
			
			driver.findElement(details).click();
			
		}
		
		public void followupLink() {
			driver.findElement(followupLink).click();
		}
		
		public void clickOnHomeButton() {
			driver.findElement(homeButton).click();
		}
}
