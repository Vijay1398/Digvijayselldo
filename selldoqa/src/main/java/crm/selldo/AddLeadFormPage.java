package crm.selldo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import utility.SetUp;

public class AddLeadFormPage extends SetUp {

	By BasicProfileTab = By
			.cssSelector("#lead_profile > div > div > div.modal-body.mt-4 > form > ul > li:nth-child(1) > a");
	By FistNameField = By.name("first_name");
	By LastNameField = By.name("last_name");
	By PrimaryEmail = By.name("primary_email_email");
	By PrimaryPhone = By.name("primary_phone_ph_number");
	By ScheduleAndConductSiteVisitForProjectField = By.xpath("//div[@id='s2id_autogen50']/ul");
	
	By NewLeadCreationLeadStage = By.xpath("//div[@id='s2id_autogen64']");
	By NewLeadStage = By.xpath("//body/div[@id='select2-drop']/ul[1]");
	
	
	By Project_dd = By
			.cssSelector("div.select2-drop.select2-drop-multi.select2-display-none.select2-drop-active ul li");
	By Salutation = By.xpath(".//*[@id='basic_info']/div/div[1]/div[1]/div[1]/");
	By addAnotherLink_pe = By.xpath("//label[text()='Primary Email']/following::a[1]");
	By removeAnotherLink_se = By.xpath("//label[text()='Secondary Email']/following::a[1]");
	By addAnotherLink_pp = By.xpath("//label[text()='Primary Phone']/following::a[1]");
	By removeAnotherLink_sp = By.xpath("//label[text()='Secondary Phone']/following::a[1]");
	By TeamsField = By.id("btn-select-team");
	By SelectTeamInputField = By.xpath(".//*[@id='select2-drop']/div/input");
	By TeamFrom_dd = By.xpath("//*[@id='select2-drop']/ul/li/div/span");
	By AssignToField = By.xpath("//label[text()='Assign to']/following::span[1]");
	By AssignToInputField = By.xpath("//label[text()='Assign to']/following::span[1]/following::input[1]");
	By AssignTo_dd = By.xpath("//label[text()='Assign to']/following::input[1]/ul");
	By SourceDropdown = By.xpath("//label[text()='Source']/following::span[text()='Select']");
	By SourceFrom_dd = By.xpath(".//*[@id='select2-drop']/ul/li[2]/div");
	By CampaignDropdown = By.xpath("//label[text()='Campaign']/following::span[text()='Walkin']");
	By Campaign_dd = By.cssSelector("#select2-drop ul li");
	By LeadStageField = By.xpath("//span[text()='Lead Stage']");
	By LeadStages_dd = By.cssSelector("#select2-drop ul li");
	By AddressField = By.xpath("//input[@name='address1']");
	By StreetField = By.xpath("//input[@name='address2']");
	By CityField = By.xpath("//input[@name='city']");
	By StateField = By.xpath("//select[@name='state']");
	By CountryDropdown = By.xpath("//select[@name='country']");
	By CountryFrom_dd = By.xpath("");
	By ZipField = By.xpath("//input[@name='zip']");
	public By ProjectsOfInterest = By.xpath("//label[text()='Interested projects']/following::input[1]");
	By ProjectOfInterestFrom_dd = By.cssSelector("#select2-drop ul li:nth-child(1) div");
	By MinBudgetInputField = By.id("id-min-budget");
	By MaxBudgetInputField = By.id("id-max-budget");
	By RequirementTab = By
			.cssSelector("[href=\"#requirement\"]");
	By MinPossession = By.xpath("//span[contains(text(),'Min possession')]");
	By MinPossession_dd = By.xpath(".//*[@id='select2-drop']/ul/li[2]/div");
	By MaxPossession = By.xpath("//span[contains(text(),'Max possession')]");
	By MaxPossession_dd = By.xpath(".//*[@id='select2-drop']/ul/li[3]/div");
	By PropertyTypes = By.xpath("//label[text()='Property types']/following::input[1]");
	By PropertyTypes_dd = By.xpath(".//*[@id='select2-drop']/ul/li[3]/div");
	By BedroomPreferences = By.xpath("//input[@id='s2id_autogen78']");
	By BedroomPreferences_dd = By.xpath(".//*[@id='select2-drop']/ul/li[4]/div");
	By LocationPreferencesInputField = By.xpath("//label[text()='Location preferences']/following::input[1]");
	By LocationPreferences_dd = By.xpath(".//*[@id='select2-drop']/ul/li/div/span");
	By SaveButton = By.xpath("//button[text()='Save'][@class='pull-right btn btn-primary save btn-sm']");
	By CloseButton = By.xpath("//button[text()='Save']/preceding::button[2]");
	By closeButton_popup = By.xpath("//button[@id='button-0']");

	WebDriver driver = null;

	public AddLeadFormPage(WebDriver driver) {

		this.driver = driver;
	}

	public void inputFirstName(String firstname) {
		driver.findElement(FistNameField).sendKeys(firstname);
	}

	public void inputLastName(String lastname) {
		driver.findElement(LastNameField).sendKeys(lastname);
	}

	public void inputPrimaryEmail(String pemail) {
		driver.findElement(PrimaryEmail).sendKeys(pemail);
	}

	public void inputPrimaryPhone(String pphone) {
		driver.findElement(PrimaryPhone).sendKeys(pphone);
	}

	public void selectTeam(String teamname) throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(false);",
				driver.findElement(ScheduleAndConductSiteVisitForProjectField));

		driver.findElement(TeamsField).click();
		driver.findElement(SelectTeamInputField).sendKeys(teamname);
		Thread.sleep(2000);
		driver.findElement(TeamFrom_dd).click();
	}

	public void selecAssignTo(String assign) throws InterruptedException {
		driver.findElement(AssignToField).click();
		driver.findElement(AssignToInputField).sendKeys(assign);
		Thread.sleep(2000);
		driver.findElement(AssignTo_dd).click();
	}

	public void selectProject(String projectname) throws InterruptedException {
		driver.findElement(ScheduleAndConductSiteVisitForProjectField).sendKeys(projectname);
		Thread.sleep(2000);
		driver.findElement(Project_dd).click();
	}

	public void selectProject() {

		// This will scroll into view
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(ScheduleAndConductSiteVisitForProjectField));

		driver.findElement(ScheduleAndConductSiteVisitForProjectField).click();

		List<WebElement> list = driver.findElements(Project_dd);

		for (WebElement ele : list)

		{
			// for every elements it will print the name using innerHTML

			System.out.println("Values " + ele.getAttribute("innerHTML"));

			// Here we will verify if link (item) is equal Srujan

			if (ele.getAttribute("innerHTML").contains("Srujan")) {

				// if yes then click on link

				ele.click();

				System.out.println("Clicked on Srujan");

				// break the loop or come out of loop

				break;
			}
		}

	}
	
	
	public void selectLeadStageNewLead() {

		driver.findElement(NewLeadCreationLeadStage).click();

		List<WebElement> list = driver.findElements(NewLeadStage);

		for (WebElement ele : list)

		{
			
			System.out.println("Values " + ele.getAttribute("innerHTML"));

			if (ele.getAttribute("innerHTML").contains("CUSTOM 1")) {

				ele.click();
				System.out.println("Clicked on CUSTOM 1"); 


				break;
			}  
		}

	}
	
	
	

	public void selectCampaign() {

		driver.findElement(CampaignDropdown).click();

		List<WebElement> list = driver.findElements(Campaign_dd);

		for (WebElement ele : list)

		{
			// for every elements it will print the name using innerHTML

			System.out.println("Values " + ele.getAttribute("innerHTML"));

			// Here we will verify if link (item) is equal Srujan

			if (ele.getAttribute("innerHTML").contains("Final Destination 2")) {

				// if yes then click on link

				ele.click();

				// break the loop or come out of loop

				break;
			}
		}

	}
	
	public void selectLeadStage() {

		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(false);",
				driver.findElement(ScheduleAndConductSiteVisitForProjectField));
		
		driver.findElement(LeadStageField).click();

		List<WebElement> list = driver.findElements(LeadStages_dd);

		for (WebElement ele : list)

		{
			// for every elements it will print the name using innerHTML

			System.out.println("Values " + ele.getAttribute("innerHTML"));

			// Here we will verify if link (item) is equal Srujan

			if (ele.getAttribute("innerHTML").contains("Incoming")) {

				// if yes then click on link

				ele.click();

				// break the loop or come out of loop

				break;
			}
		}

	}


	public void selectSource() {

		driver.findElement(SourceDropdown).click();
		driver.findElement(SourceFrom_dd).click();
	}

	public void inputAddress(String address) {
		driver.findElement(AddressField).sendKeys(address);
	}

	public void inputStreet(String street) {
		driver.findElement(StreetField).sendKeys(street);
	}

	public void inputCity(String city) {
		driver.findElement(CityField).sendKeys(city);
	}

	public void inputState(String state) {
		driver.findElement(StateField).sendKeys(state);
	}

	public void inputZip(String zip) {
		driver.findElement(ZipField).sendKeys(zip);
	}

	public void selectingProjectsOfInterest() {
		driver.findElement(ProjectsOfInterest).click();
		driver.findElement(ProjectOfInterestFrom_dd).click();

	}

	public void inputBudget(String min, String max) throws InterruptedException {

		// This will scroll up the web page
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(false);", driver.findElement(RequirementTab));

		driver.findElement(RequirementTab).click();
		driver.findElement(MinBudgetInputField).sendKeys(min);
		driver.findElement(MaxBudgetInputField).sendKeys(max);
	}

	public void selectPossession() {
		driver.findElement(MinPossession).click();
		driver.findElement(MinPossession_dd).click();
		driver.findElement(MaxPossession).click();
		driver.findElement(MaxPossession_dd).click();
	}

	public void selectPropertyTypes() {
		
		// This will scroll down the web page
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(PropertyTypes));
		driver.findElement(PropertyTypes).click();
		driver.findElement(PropertyTypes_dd).click();
	}

	public void selectBedroomPreferences() {

		// This will scroll down the web page
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(LocationPreferencesInputField));
		driver.findElement(BedroomPreferences).click();
		driver.findElement(BedroomPreferences_dd).click();
	}

	public void inputLocatioPreferences(String pref) {
		driver.findElement(LocationPreferencesInputField).sendKeys(pref);
		driver.findElement(LocationPreferences_dd).click();
	}

	public void clickOnSaveButton() {
		new Actions(driver).moveToElement(driver.findElement(SaveButton)).click().perform();
	}

	public void clickOnCloseButton() {
		driver.findElement(CloseButton).click();
	}

	public void closeButton_popup() {
		driver.findElement(closeButton_popup).click();
	}

	public void clickOnSalutation_dd() {
		driver.findElement(Salutation).click();
	}
}
