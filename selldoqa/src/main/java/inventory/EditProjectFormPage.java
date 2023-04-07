package inventory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.SetUp;

public class EditProjectFormPage extends SetUp {

	By projectNameSpan = By.xpath("//label[@for='project_project_name']/following::span[1]");
	By projectNameInputField = By.xpath("//*[@id='select2-drop']//div//input");
	By saveButton = By.xpath("//input[@type='submit']");
	By addressLink = By.xpath("//a[text()='Address']");
	By virtualWalkthroughLink = By.xpath("//a[text()='Virtual Walkthrough']");
	By detailsLink = By.xpath("//a[text()='Details']");
	By specificationsAndAmeneties = By.xpath("//a[text()='Specifications and amenities']");
	By editPriceQuotesAndBrochureLink = By.xpath("//a[text()='Edit price quote and brochure']");
	By uploadImageLink = By.xpath("//a[text()='Upload Images']");
	By portalIntegrationCodesLink = By.xpath("//a[text()='Portal Integration Codes']");
	By costTemplateLink = By.xpath("//a[text()='Cost template']");
	By emailTemplatesLink = By.xpath("//a[text()='Email Templates']");
	By smsTemplatesLink = By.xpath("//a[text()='Sms Templates']");
	By previousDemandLettersLink = By.xpath("//a[text()='Previous demand letters']");
	By viewDeveloperLink = By.xpath("//a[text()='View Developer']");
	By viewAvailableProjectTowersLink = By.xpath("//a[text()='View Available Project Towers']");
	By viewAvailableFloorPlansLink = By.xpath("//a[text()='View Available Floor Plans']");

	WebDriver driver = null;
	WebDriverWait wait;

	public EditProjectFormPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
	}

	public void changeProjectName(String project) throws InterruptedException {
		driver.findElement(projectNameSpan).click();
		driver.findElement(projectNameInputField).sendKeys(project);
		Thread.sleep(2000);
		driver.findElement(projectNameInputField).sendKeys(Keys.ENTER);
	}

	public void clickOnAddressLink() {
		driver.findElement(addressLink).click();
	}

	public void clickOnVirtualWalkThroughLink() {
		driver.findElement(virtualWalkthroughLink).click();
	}

	public void clickOnDetailsLink() {
		driver.findElement(detailsLink).click();
	}

	public void clickOnSpecificationAndAmenitiesLink() {
		driver.findElement(specificationsAndAmeneties).click();
	}

	public void clickOnEditPriceQuotesAndBrochureLink() {
		driver.findElement(editPriceQuotesAndBrochureLink).click();
	}

	public void clickOnUploadImagesLink() {
		driver.findElement(uploadImageLink).click();
	}

	public void clickOnPortalIntegrationCodesLink() {
		driver.findElement(portalIntegrationCodesLink).click();
	}

	public void clickOnCostTemplateLink() {
		driver.findElement(costTemplateLink).click();
	}

	public void clickOnEmailTemplateLink() {
		driver.findElement(emailTemplatesLink).click();
	}

	public void clickOnSmsTemplateLink() {
		driver.findElement(smsTemplatesLink).click();
	}

	public void clickOnPreviousDemandLettersLink() {
		driver.findElement(previousDemandLettersLink).click();
	}

	public void clickOnViewDeveloperLink() {
		driver.findElement(viewDeveloperLink).click();
	}

	public void clickOnViewAvailableProjectTowersLink() {
		driver.findElement(viewAvailableProjectTowersLink).click();
	}

	public void clickOnViewAvailableFloorPlansLink() {
		driver.findElement(viewAvailableFloorPlansLink).click();
	}

	public void clickOnSaveButton() {
		driver.findElement(saveButton).click();
	}

}
