package crm.selldo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.SetUp;

public class MergeLeadsPage extends SetUp{
	
	By SearchField = By.xpath("//input[@placeholder='Enter lead_id#/email/phone']");
	By SearchLeadsButton = By.xpath("//span[text()='Search Leads']");
	By CloseButton = By.xpath(".//*[@id='merge_leads_form']/div/div/div[3]/button[1]");
	By MergeThisLink = By.xpath("//a[text()='Merge this']	");
	By AddNote = By.xpath("//label[text()='Please add notes as you merge these leads?']/following::textarea[1]");
	By MergeLeadsButton = By.xpath("//button[text()='Merge leads']");

	WebDriver driver = null;

	public MergeLeadsPage(WebDriver driver) {

		this.driver = driver;
	}

	public void searchingLeadToBeMerged(String lead_m) throws InterruptedException {
		driver.findElement(SearchField).sendKeys(lead_m);
		Thread.sleep(2000);
		driver.findElement(SearchLeadsButton).click();
	}

	public void clickOnMergeThisButton() {
		driver.findElement(MergeThisLink).click();
	}

	public void enteringSomeNotes(String note) {
		driver.findElement(AddNote).sendKeys(note);
	}

	public void clickOnMergeLeadsButton() {
		driver.findElement(MergeLeadsButton).click();
	}

	public void clickOnCloseButton() {
		driver.findElement(CloseButton).click();
	}


}
