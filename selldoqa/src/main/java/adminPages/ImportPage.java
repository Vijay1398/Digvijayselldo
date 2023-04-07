package adminPages;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.SetUp;

public class ImportPage extends SetUp {
	
	Actions actions;

	By importLeadsTab = By.xpath("//span[text()='import leads']");
	By newUploadButton = By.xpath("//a[text()='New Upload']");
	By continueButton = By.xpath("//a[text()='Continue']");
	By uploadButton = By.xpath("//input[@class='bulk_upload_file']");
	By campaignField = By.xpath("//span[text()='Choose Campaign']");
	By dropDown = By.xpath("//div[@id='select2-drop']//ul//li");
	By emailField = By.xpath("//div[@id='s2id_bulk_upload_emails']");
	By nameField = By.xpath("//div[@id='s2id_bulk_upload_field_order_0']");
	By leadEmailField = By.xpath("//div[@id='s2id_bulk_upload_field_order_1']");
	By userId = By.xpath("//div[@id='s2id_bulk_upload_field_order_2']");
	By stageField = By.xpath("//div[@id='s2id_bulk_upload_field_order_3']");
	By emailId = By.cssSelector("input.select2-input");
	By continuebutton = By.xpath("//input[@type='submit']");
	public By sucessMessage = By.xpath("//span[@class='text text-success']");
	public By importStatus = By.xpath("/html/body/div[4]/div/div[2]/div/div/div[2]/div/div[1]/table/tbody/tr[1]/td[6]");
	public By assignedTo = By.xpath("/html/body/div[4]/div/div[2]/div/div/div[2]/div/div[1]/table/tbody/tr[1]/td[4]");
	public By leadUploadCount = By.xpath("/html/body/div[4]/div/div[2]/div/div/div[2]/div/div[1]/table/tbody/tr[1]/td[2]");

	
	
	WebDriver driver = null;
	WebDriverWait wait;

	public ImportPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
		actions = new Actions(driver);
	}

	public void clickOnImportLeadsTab() {
		driver.findElement(importLeadsTab).click();
	}

	public void clickOnNewUploadButton() {
		driver.findElement(newUploadButton).click();
	}

	public void clickOnContinueButton() {
		driver.findElement(continueButton).click();
	}

	public void enterEmail(String email) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)", "");
		
		actions.moveToElement(driver.findElement(emailField));
		actions.click();
		actions.sendKeys(email);
		actions.sendKeys(Keys.ENTER);
		actions.build().perform();
	}

	public void enterName() {
		
		actions.moveToElement(driver.findElement(nameField));
		actions.click();
		actions.sendKeys(Keys.ENTER);
		actions.build().perform();
	}
	
	public void clickOnContinue() {
		driver.findElement(continuebutton).click();
	}

	public void enterLeadEmail() {
		
		actions.moveToElement(driver.findElement(leadEmailField));
		actions.click();
		actions.sendKeys(Keys.ENTER);
		actions.build().perform();
	}

	public void enterStage() {

		driver.findElement(stageField).click();

		List<WebElement> list = driver.findElements(dropDown);

		for (WebElement ele : list)

		{
			// for every elements it will print the name using innerHTML

			System.out.println("Values " + ele.getAttribute("innerHTML"));

			// Here we will verify if link (item) is equal to Last 7 Days

			if (ele.getAttribute("innerHTML").contains("Lead Stages")) {

				// if yes then click on link

				ele.click();

				// break the loop or come out of loop

				break;
			}
		}

	}

	public void enterSalesId() {

		driver.findElement(userId).click();

		List<WebElement> list = driver.findElements(dropDown);

		for (WebElement ele : list)

		{
			// for every elements it will print the name using innerHTML

			System.out.println("Values " + ele.getAttribute("innerHTML"));

			// Here we will verify if link (item) is equal to Last 7 Days

			if (ele.getAttribute("innerHTML").contains("Sales Ids")) {

				// if yes then click on link

				ele.click();

				// break the loop or come out of loop

				break;
			}
		}

	}

	public void clickOnUploadButton() {
		WebElement browse = driver.findElement(uploadButton);
		// pass the path of the file to be uploaded using Sendkeys method
		browse.sendKeys("/home/yash/Documents/Smoke Test/smoke_test.xls");
	}

	public void selectCampaign() {

		WebDriverWait wait = new WebDriverWait(driver, 30);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Choose Campaign']")));

		driver.findElement(campaignField).click();

		List<WebElement> list = driver.findElements(dropDown);

		for (WebElement ele : list)

		{
			// for every elements it will print the name using innerHTML

			System.out.println("Values " + ele.getAttribute("innerHTML"));

			// Here we will verify if link (item) is equal to Last 7 Days

			if (ele.getAttribute("innerHTML").contains("organic")) {

				// if yes then click on link

				ele.click();

				System.out.println("Clicked on organic");

				// break the loop or come out of loop

				break;
			}
		}
	}
	
	public void pageRefresh() {

		driver.navigate().refresh();
	}

}
