package adminPages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.SetUp;

public class NewCampaignFormPage extends SetUp {

	By name = By.cssSelector("input[name='campaign[name]']");
	By budgetTextBox = By.cssSelector("div.currency2-container.placeholder");
	By budget = By.id("campaign_budget");
	By duration = By.xpath("//input[@name='campaign_date_range']");
	By project = By.xpath("//span[text()='Project']");
	By selectProject = By.cssSelector("#select2-drop ul li:nth-child(1) div");
	By project_All = By.xpath(
			"//div[@class='select2-drop select2-display-none select2-with-searchbox select2-drop-active select2-drop-above']//ul//li");
	By teamField = By.xpath("//input[@class='select2-input select2-default'][@id='s2id_autogen3']");
	By selectTeam = By.cssSelector("#select2-drop ul li:nth-child(1) div");
	By teamField_All = By.xpath(
			"//div[@class='select2-drop select2-drop-multi select2-display-none select2-drop-active select2-drop-above']//ul[2]//li");
	By salesField = By.cssSelector("#s2id_campaign_sale_ids ul li:nth-child(1) input");
	By selectSales = By.cssSelector("div#select2-drop ul:nth-child(2) li:nth-child(1)");
	By salesField_All = By.xpath(
			"//div[@class='select2-drop select2-drop-multi select2-display-none select2-drop-active select2-drop-above']//ul[2]//li");
	By googleAdwordsAccount = By.xpath("//input[@id='s2id_autogen5']");
	By facebookAdvertsAccount = By.xpath("//input[@id='s2id_autogen6']");
	By googleAdwordsAccounts = By.xpath("//input[@id='s2id_autogen5']");
	By googleAdwordsAccounts_All = By.xpath("//ul[@class='select2-results']//li");
	By facebookAdvertsAccounts = By.xpath("//input[@id='s2id_autogen6']");
	By facebookAdvertsAccounts_All = By.xpath("//ul[@class='select2-results']//li");
	By saveAndNextButton = By.xpath("//a[text()='Save & Next']");	
	
	// Tracking Emails
	By trackingEmailsTab = By.xpath("//span[text()='Tracking Emails']");
	By add_TrackingEmail = By.xpath("//a[text()='Add']");
	By campaignEmail_TrackingEmail = By.xpath("//input[@placeholder='Campaign Email']");
	//By project_TrackingEmail = By.xpath("//*[contains(@id,'_rule_project_id')]/a/span[1]");
	
	//By project_TrackingEmail = By.cssSelector("div[id^='new_rules_div'] span.selectLabel");
	
	//By project_TrackingEmail_All = By.xpath("//ul[@class='select2-results']//li");
	
	By project_TrackingEmail = By.xpath("//span[text()='Project']");
	By project_TrackingEmail_All = By.xpath("//ul[@class='select2-results']//li");
	
	
	By source_TrackingEmail = By.xpath("//span[text()='Select Source']");
	By source_TrackingEmail_All = By.xpath("//ul[@class='select2-results']//li");
	By subSource_TrackingEmail = By.xpath("//input[@id='rule_sub_source']");
	By team_TrackingEmail = By.xpath("//input[@id='s2id_autogen6']");
	By sales_TrackingEmail = By.xpath("//input[@id='s2id_autogen4']");
	By associatedVirtualNumber_TrackingEmail = By.xpath("//span[text()='Associated virtual number']");
	By associatedShortCode_TrackingEmail = By.xpath("//span[text()='Associated Short code']");
	By emailParser_TrackingEmail = By.xpath("//span[text()='Associated Email parser']");
	By saveButton_TrackingEmail = By.xpath("//a[text()='Save']");
	
	// Tracking Numbers
	By trackingNumbersTab_TrackingNumber = By.xpath("//span[text()='Tracking Numbers']");
	By showOtherVirtualNumbers_TrackingNumber = By
			.xpath("//a[@class='btn btn-outline-primary ml-2'][@id='button_show_untagged_vns']");//*[@id="button_show_untagged_vns"]
	By actionBar_TrackingNumber = By.xpath("//*[contains(@id,'tr_vn_')]/td[7]/div/a/i");
	By addLink_TrackingNumber = By
			.xpath("//th[text()='Actions']/following::a[@class='action_button dropdown-item'][1]");
	By saveButton_TrackingNumber = By.xpath("//a[text()='Save']");
	// Input Channels
	By inputChannelTab = By.xpath("//span[text()='Input Channels']");
	By addButton_InputChannel = By.xpath("//a[text()='Add']");
	//By apiChannel_InputChannel = By.xpath("//select[@name='rule[entity_id]']");
	
	By apiChannel_InputChannel = By.xpath("//select[@id='rule_entity_id']");
	
	By saveButton_InputChannel = By.xpath("//a[text()='Save']");
	// SMS Shortcodes
	By smsShortcodesTab = By.xpath("//span[text()='SMS shortcodes']");
	By alertPopup = By.xpath("");
	By addDropdown_SmsShortcode = By.xpath("//button[@id='btn_add_new_rule']");//*[@id="btn_add_new_rule"]
	By add_options_SmsShortcode = By.xpath("//*[@id=\"rules_index_div\"]/div[2]/div[1]/div/ul/li[10]");
	//By saveButton_SmsShortcode = By.xpath("//*[contains(@id,'rule_form_')]/div/div/div[3]/a");
	
	By saveButton_SmsShortcode = By.xpath("//a[text()='Save']");
	
	By nextButton = By.xpath("//a[text()='Next']");
	By finishButton = By.xpath("//a[text()='Finish']");
	By messageShortCode = By.xpath("//input[@id='message']");
	//By project_SmsShortCode = By.xpath("//*[contains(@id,'_rule_project_id')]/a/span[1]");
	
	By project_SmsShortCode =By.xpath("//span[text()='Project']");
	By source_SmsShortCode = By.xpath("//*[@id=\"s2id_rule_source\"]/a/span[1]");
	By project_SmsShortCode_List = By.xpath("//*[@id=\"select2-drop\"]/ul/li[24]/div");
//	By source_SmsShortCode_List = By.xpath("");

	WebDriverWait wait;

	public NewCampaignFormPage(WebDriver driver) {

		this.driver = driver;
		wait = new WebDriverWait(driver, 8);
	}

	public void enterCamapignName(String nameObj) {
		driver.findElement(name).sendKeys(nameObj + generateRandomString());
	}

	public void enterRandomCampaignName(String campaignName ){
		driver.findElement(name).sendKeys(campaignName + generateRandomString());	
			
	}

	public String generateRandomString() {
		String CHAR_LIST =
				"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		int RANDOM_STRING_LENGTH = 10;
		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
			int number = getRandomNumber();
			char ch = CHAR_LIST.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}

	private int getRandomNumber() {
		String CHAR_LIST =
				"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(CHAR_LIST.length());
		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}

	public void enterBudget(String budgetObj) {
		driver.findElement(budgetTextBox).click();
		driver.findElement(budget).sendKeys(budgetObj);
	}

	public void selectProject() throws IOException {
		driver.findElement(project).click();
		driver.findElement(selectProject).click();
	}

	public void selectTeam() throws IOException {
		driver.findElement(teamField).click();
		driver.findElement(selectTeam).click();
	}

	public void selectSales() throws IOException {
		driver.findElement(salesField).click();
		driver.findElement(selectSales).click();

	}

	public void selectGoogleAdwordsAccount() throws IOException {
		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);
		driver.findElement(googleAdwordsAccounts).click();
		List<WebElement> list = driver.findElements(googleAdwordsAccounts_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML")
					.contains(property.getProperty("googleAdwords_creatingCampaign_MandatoryFillTest"))) {
				ele.click();
				System.out.println("Selected");
				break;
			}
		}
	}

	public void selectFacebookAdvertsAccount() throws IOException {
		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);
		driver.findElement(facebookAdvertsAccounts).click();
		List<WebElement> list = driver.findElements(facebookAdvertsAccounts_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML")
					.contains(property.getProperty("facebookAdverts_creatingCampaign_MandatoryFillTest"))) {
				ele.click();
				System.out.println("Selected");
				break;
			}
		}
	}

	// ...................Tracking Emails..........................

	public void clickOnTrackingEmailsTab() {
		driver.findElement(trackingEmailsTab).click();
	}

	public void clickOnAddButton_TrackingEmail() {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(add_TrackingEmail).click();
	}

	public void enterTrackingEmailName(String trackingEmail) {
		driver.findElement(campaignEmail_TrackingEmail).sendKeys(trackingEmail);
	}

	public void selectProject_TrackingEmails() throws IOException {
		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);
		driver.findElement(project_TrackingEmail).click();
		List<WebElement> list = driver.findElements(project_TrackingEmail_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML")
					.contains(property.getProperty("project_trackingEmails_creatingCampaign_MandatoryFillTest"))) {
				ele.click();
				System.out.println("Selected");
				break;
			}
		}		
				
	}

	public void selectSource_TrackingEmails() throws IOException {
		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);
		driver.findElement(source_TrackingEmail).click();
		List<WebElement> list = driver.findElements(source_TrackingEmail_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML")
					.contains(property.getProperty("source_trackingEmails_creatingCampaign_MandatoryFillTest"))) {
				ele.click();
				System.out.println("Selected");
				break;
			}
		}
	}

	public void enterSubSource_TrackingEmails(String sub) {
		driver.findElement(subSource_TrackingEmail).sendKeys(sub);
	}

	public void selectTeam_TrackingEmails() throws IOException {
		Properties property = new Properties();
		FileInputStream fileInputObj = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property.load(fileInputObj);
		driver.findElement(source_TrackingEmail).click();
		List<WebElement> list = driver.findElements(source_TrackingEmail_All);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML")
					.contains(property.getProperty("team_trackingEmails_creatingCampaign_MandatoryFillTest"))) {
				ele.click();
				System.out.println("Selected");
				break;
			}
		}
	}

	public void clickOnSaveButton_TrackingEmails() {
		driver.findElement(saveButton_TrackingEmail).click();
	}

	// ...................Tracking Numbers..........................

	public void clickOnTrackingNumbersTab() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(trackingNumbersTab_TrackingNumber).click();
	}

	public void clickOnShowOtherVirtualNumber_TrackingNumbers() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(showOtherVirtualNumbers_TrackingNumber).click();
	}

	public void clickOnActionBar_TrackingNumbers() {
		driver.findElement(actionBar_TrackingNumber).click();
	}

	public void clickOnAddLink_TrackingNumbers() {
		driver.findElement(addLink_TrackingNumber).click();
	}

	public void clickOnSaveButton_TrackingNumbers() {
		driver.findElement(saveButton_TrackingNumber).click();
	}

	// ...................Input Channels..........................

	public void clickOnInputChannelTab() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(inputChannelTab).click();
	}

	public void clickOnAddButton_InputChannel() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(addButton_InputChannel).click();
	}

	public void selectApiChannel() {
		Select oSelect = new Select(driver.findElement(apiChannel_InputChannel));
		oSelect.selectByVisibleText("Makaan Mailer");
	}

	public void clickOnSaveButton_InputChannel() {
		driver.findElement(saveButton_InputChannel).click();
	}

	// ...................SMS Shortcodes..........................

	public void clickOnSmsShortcodesTab() {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(smsShortcodesTab).click();
	}

	// Accepting an alert popup while clicking on the tab
	public void clickAlertPopup(){
		driver.switchTo().alert().accept();
	}

	// Refactored
	public void selectSmsShortcode_SmsShortcode() throws IOException {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(addDropdown_SmsShortcode).click();
		List<WebElement> list = driver.findElements(add_options_SmsShortcode);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("Send unit to 26266")) {
				ele.click();
				System.out.println("Selected");}
				else{
					System.out.println("Not present");
				break;
				}}

	}

	// Entering message
	public void enterMessage(String enter){
		driver.findElement(messageShortCode).sendKeys(enter);
	}

	// Selecting Project
	public void selectProject_SmsShortCode() throws IOException {
	/*	driver.findElement(project_SmsShortCode).click();
		List<WebElement> list = driver.findElements(project_SmsShortCode_List);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML").contains("gsd")) {
				ele.click();
				System.out.println("Selected");}
			else{
				System.out.println("Not present");
				break;
			}

		} */
		
		Properties property1 = new Properties();
		FileInputStream fileInputObj1 = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//Config File//global.properties");
		property1.load(fileInputObj1);
		driver.findElement(project_SmsShortCode).click();
		List<WebElement> list = driver.findElements(project_SmsShortCode_List);
		for (WebElement ele : list) {
			System.out.println("Values " + ele.getAttribute("innerHTML"));
			if (ele.getAttribute("innerHTML")
					.contains(property1.getProperty("project_trackingEmails_creatingCampaign_MandatoryFillTest"))) {
				ele.click();
				System.out.println("Selected");
				break;
			}
		}
		

	}

	// Entering Source
//	public void selectSource_SmsShortCode(String source){
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		driver.findElement(source_SmsShortCode).sendKeys(source);
//	}
	public void clickOnSaveButton_SmsShortcode() {
		driver.findElement(saveButton_SmsShortcode).click();
	}

	public void clickOnNextButton_SmsShortcode() {
		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(nextButton).click();
	}
	
	
	
	

	public void clickOnFinishButton() {
		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(finishButton).click();
	}
	
	public void clickOnSaveAndNextButton() {
		
		driver.findElement(saveAndNextButton).click();
		
	}
}