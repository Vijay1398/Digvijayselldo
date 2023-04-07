package crm.selldo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.SetUp;

public class EmailPage extends SetUp {

	By subjectInputField = By.xpath("//label[text()='Subject']/following::input[1]");
	By textBody = By.xpath("//div[@class='note-editable card-block']");
	By sendEmailButton = By.xpath("//button[text()='Send Email']");
	By templateDropdown = By.cssSelector(".select2-container.email_template_select.form-control");
	By template_dd = By.cssSelector("#select2-drop > ul > li > div");
	By addCCLink = By.cssSelector("a.btn.btn-light.mt-3.addCC.float-right.mr-1");
	By addBCCLink = By.linkText("Add BCC");
	By CCInputField = By.xpath("//label[text()='CC']/following::input[1]");
	By BCCInputField = By.xpath("//label[text()='BCC']/following::input[1]");
	By generalEmailLink = By.cssSelector("select[name=\"email_template_type\"]");
	By composeEmailLink = By.cssSelector("//a[text()=\"Compose Email\"]\"");
	By selectBrochure = By.cssSelector("select[name=\"email_template_type\"] option:nth-child(2)");
	By selectQuote = By.cssSelector("select[name=\"email_template_type\"] option:nth-child(3)");

	WebDriver driver = null;

	public EmailPage(WebDriver driver) {

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		this.driver = driver;// Calling Browser

	}

	public void enterSubject(String subject) {
		driver.findElement(subjectInputField).sendKeys(subject);
	}

	public void entertextInBody(String body) {
		driver.findElement(textBody).sendKeys(body);
	}

	public void clickOnSendEmailButton() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", driver.findElement(sendEmailButton));
	}

	/*
	 * public void selectTemplate() throws Exception { Thread.sleep(2000);
	 * driver.findElement(templateDropdown).click();
	 * driver.findElement(template_dd).click(); }
	 */

	public void selectTemplate(String templateName) {

		driver.findElement(templateDropdown).click();
		List<WebElement> list = driver.findElements(template_dd);

		for (WebElement ele : list) {
			// for every elements it will print the name using innerHTML

			System.out.println("Values " + ele.getAttribute("innerHTML"));

			// Here we will verify if link (item) is equal to Template

			if (ele.getAttribute("innerHTML").contains(templateName)) {

				// if yes then click on link

				ele.click();

				System.out.println("Clicked on Template");

				// break the loop or come out of loop

				break;
			}
		}

	}

	public void enterCCEmail(String cc) throws Exception {

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", driver.findElement(addCCLink));
		driver.findElement(CCInputField).sendKeys(cc);
		Thread.sleep(1000);
		driver.findElement(CCInputField).sendKeys(Keys.RETURN);
	}

	public void enterBCCEmail(String bcc) throws Exception {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", driver.findElement(addBCCLink));
		driver.findElement(BCCInputField).sendKeys(bcc);
		Thread.sleep(1000);
		driver.findElement(BCCInputField).sendKeys(Keys.RETURN);
	}

	public void selectBrochureOption() throws Exception {

		driver.findElement(generalEmailLink).click();
		//driver.findElement(selectBrochure).click();
		driver.findElement(selectBrochure).click();

	}

	public void selectPriceQuoteOption() throws Exception {

		driver.findElement(generalEmailLink).click();
		//driver.findElement(composeEmailLink).click();
		driver.findElement(selectQuote).click();

	}

}
