package crm.selldo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.SetUp;

public class SmsPage extends SetUp {

	By sendSmsButton = By.xpath("//button[text()='Send SMS']");
	By templateDropdown = By.xpath("//label[text()='Compose using template']/following::span[1]");
	By template_dd = By.cssSelector("#select2-drop > ul > li > div");

	WebDriver driver = null;

	public SmsPage(WebDriver driver) {

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		this.driver = driver;// Calling Browser

	}

	public void selectSmsTemplate(String templateName) {

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

	public void clickOnSendSmsButton() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click()", driver.findElement(sendSmsButton));
	}

}
