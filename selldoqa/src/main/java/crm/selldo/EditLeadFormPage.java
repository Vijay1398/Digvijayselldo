package crm.selldo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility.SetUp;

public class EditLeadFormPage extends SetUp{

	By salutation = By.xpath("//select[@name='salutation']");
	By firstName = By.xpath("//label[text()='Salutation & name']/following::input[1]");
	By lastName = By.xpath("//label[text()='Salutation & name']/following::input[2]");
	By primaryEmail = By.xpath("//input[@name='primary_email_email']");
	By saveButton = By.xpath("//button[@class='pull-right btn btn-primary save btn-sm lead_submit']"); //button[text()='Save'][@class='pull-right btn btn-primary save btn-sm']");

	WebDriver driver = null;
	
	

	public EditLeadFormPage(WebDriver driver) {

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		this.driver = driver;// Calling Browser
	}

	// ................Methods for Basic profile section.....................

	public void clickOnMeetingLink() {
		driver.findElement(salutation).click();
	}
	
	public void clickOnSaveButton() {
		driver.findElement(saveButton).click();
	}

	
}
