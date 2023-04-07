package crm.selldo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.SetUp;

public class QuotesPage extends SetUp {

	By Subject = By.xpath("//input[@name='subject']");
	By Body = By.xpath("//div[@class='note-editable card-block']");
	By UploadAttachments = By.xpath(".//*[@id='form_container']/div/form/div[8]/div/a");
	By sendPriceQuoteButton = By.xpath("//button[text()='Send Price quote'][@type='submit']");
	By SelectProduct = By.cssSelector("select.form-control.project");
	By SelectProduct_All = By.cssSelector("select.form-control.project option");
	By ChooseTemplateDropdown = By.xpath("//label[text()='Choose Template']/following::span[@class='select2-chosen']");
	By ChooseTemplate_All = By.xpath("//ul[@class='select2-results']//li");

	WebDriver driver = null;

	public QuotesPage(WebDriver driver) {

		this.driver = driver;
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

	// Clicking on Send Price Quote button
	public void clickOnSendPriceQuoteButton() {
		driver.findElement(sendPriceQuoteButton).click();
	}

	// Select Product
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
