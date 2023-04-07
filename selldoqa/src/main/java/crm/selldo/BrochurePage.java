package crm.selldo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.SetUp;

public class BrochurePage extends SetUp {

	By AddCC = By.xpath("//a[text()='Add CC']");
	By AddBCC = By.xpath("//a[text()='Add BCC']");
	By Subject = By.xpath("//input[@name='subject']");
	By Body = By.xpath("//div[@class='note-editable card-block']");
	By UploadAttachments = By.xpath(".//*[@id='form_container']/div/form/div[8]/div/a");
	By SendBrochure = By.xpath("//button[@type='submit']");
	By SelectProduct = By.cssSelector("select.form-control.project");
	By SelectProduct_All = By.cssSelector("select.form-control.project option");
	By ChooseTemplateDropdown = By.xpath("//label[text()='Choose Template']/following::span[@class='select2-chosen']");
	By ChooseTemplate_All = By.xpath("//ul[@class='select2-results']//li");

	WebDriver driver = null;

	public BrochurePage(WebDriver driver) {

		this.driver = driver;
	}

	// Clicking on Add CC link
	public void clickOnAddCC() {
		driver.findElement(AddCC).click();
	}

	// Clicking on Add BCC link
	public void clickOnAddBCC() {
		driver.findElement(AddBCC).click();
	}

	// Writing the subject for email
	public void writingSubject(String mysubject) {
		driver.findElement(Subject).sendKeys(mysubject);
	}

	// Writing some text in the body of email
	public void writingSomeTextInBody(String contentOfMail) {
		driver.findElement(Body).sendKeys(contentOfMail);
	}

	// Clicking on Upload Attachments button
	public void ClickOnUploadAttachmentsButton() {
		driver.findElement(UploadAttachments).click();
	}

	// Clicking on Send Brochure button
	public void clickOnSendBrochureButton() {
		driver.findElement(SendBrochure).click();
	}
	//Select Product
	public void selectProduct(String projectName) {
		driver.findElement(SelectProduct).click();
		
		List<WebElement> list = driver.findElements(SelectProduct_All);

		for (WebElement ele : list) {
			// for every elements it will print the name using innerHTML

			System.out.println("Values " + ele.getAttribute("innerHTML"));

			// Here we will verify if link (item) is equal to Template1

			if (ele.getAttribute("innerHTML").contains(projectName)) {

				// if yes then click on link

				ele.click();

				System.out.println("Clicked on Template");

				// break the loop or come out of loop

				break;
			}
		}
	}
     //Select Template
	public void selectTemplate(String templateName) {

		driver.findElement(ChooseTemplateDropdown).click();

		List<WebElement> list = driver.findElements(ChooseTemplate_All);

		for (WebElement ele : list) {
			// for every elements it will print the name using innerHTML

			System.out.println("Values " + ele.getAttribute("innerHTML"));

			// Here we will verify if link (item) is equal to Template1

			if (ele.getAttribute("innerHTML").contains(templateName)) {

				// if yes then click on link

				ele.click();

				System.out.println("Clicked on Template");

				// break the loop or come out of loop

				break;
			}
		}
	}

}
