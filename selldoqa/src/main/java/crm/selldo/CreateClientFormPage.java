package crm.selldo;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;

import utility.GetTestData;
import utility.SetUp;

public class CreateClientFormPage extends SetUp {

	// Client Details

	By clientFirstNameField = By.id("client_first_name");
	By clientLastNameField = By.id("client_last_name");
	By clientPhoneNumber = By.cssSelector("[data-field=\"client[phone]\"]");
	By BusinessNameField = By.xpath("//input[@id='client_name']");
	By BussinessType = By.xpath("//div[@id=\"s2id_client_business_type\"]");
	By selectBussinessType = By.xpath("//*[contains(text(),'Developer')]");
	By TransactionalSmsMask = By.xpath("//input[@id=\"transactional_sms_mask\"]");
 	By ShortNameField = By.xpath("//input[@id='client_short_name']");
	By WebsiteField = By.xpath("//input[@name='client[website]']");
	By EmailField_CD = By.xpath("//input[@id='client_email']");
	By EmailDomainField = By.xpath("//input[@name='client[email_domain]']");
	By SetUpChargesField = By.xpath("//input[@id='client_setup_charges']");
	// By UploadLogoButton = By.xpath("//input[@id='client_image']");
	By UploadLogoButton = By.cssSelector("input#client_image");
	By PhoneField_CD = By.xpath("//h3[text()='Client details']/following::input[8]");
	By clientPhone = By.xpath("//body/div[@class='wrapper']/div[@id='content']/form[@id='client_form']/div[@class='panel panel-default']/div[@class='panel-body']/div[@class='row']/div[@class='col-md-6 col-sm-12']/div[1]/div[1]/input[1]");
	By CityForIndianPRIDropdown = By.xpath("//span[text()='Choose City For Indian PRI(Landline)']");
	By CityForIndianPRIField = By.xpath(".//*[@id='select2-drop']/div/input");
	By CityForIndianPRI_dd = By.xpath(".//*[@id='select2-drop']/ul/li/div");
	By IndianPRIDropdown = By.xpath("//label[text()='Indian PRI (Landline)']/following::span[1]");
	By IndianPRIField = By.xpath(".//*[@id='select2-drop']/div/input");
	By IndianPRI_dd = By.xpath(".//*[@id='select2-drop']/ul/li[1]/div");
	By CircleForIndianMobileNumberDropdown = By
			.xpath("//label[text()='Circle for Indian Mobile Number']/following::span[1]");
	By CircleForIndianMobileNumberField = By.xpath(".//*[@id='select2-drop']/div/input");
	By CircleForIndianMobileNumber_dd = By.xpath(".//*[@id='select2-drop']/ul/li/div");
	By IndianMobileNumbersDropdown = By.xpath("//label[text()='Indian Mobile Numbers']/following::span[1]");
	By IndianMobileNumberField = By.xpath(".//*[@id='select2-drop']/div/input");
	By SMSMaskField = By.xpath("//input[@id='transactional_sms_mask']"); 
	By VirtualNumbersField = By.id("s2id_autogen6");
	By VirtualNumber_dd = By.xpath(".//*[@id='select2-drop']/ul/li/div/span");
	By TimeZoneDropdown_CD = By.xpath("//select[@id='client_time_zone']");
	By ContractDate = By.xpath("//input[@id='client_contract_end_date']");
	By WholeCalender = By.xpath(
			"//div[@class='datepicker datepicker-dropdown dropdown-menu datepicker-orient-left datepicker-orient-bottom']");

	// Client Address

	By Address1Field = By.xpath("//input[@id='client_address_attributes_address1']");
	By Address2Field = By.xpath("//input[@id='client_address_attributes_address2']");
	By CityField = By.xpath("//input[@id='client_address_attributes_city']");
	By StateField = By.xpath("//select[@name='client[address_attributes][state]']");
	By CountryDropdown = By.xpath("//select[@id='client_address_attributes_country']");
	By CountryField = By.xpath(".//*[@id='select2-drop']/div/input");
	By Country_dd = By.xpath(".//*[@id='select2-drop']/ul/li[1]/div");
	By ZipField = By.xpath("//input[@id='client_address_attributes_zip']");

	// Mixpanel Setting

	By MixpanelSettingCheckbox = By.id("show_mix_panel_setting");
	By ApiKeyField = By.id("client_mixpanel_api_key");
	By TokenField = By.id("client_mixpanel_token");
	By SecretField = By.id("client_mixpanel_secret");
	By EnableProfilesCheckbox = By.id("client_mixpanel_enable_profiles");

	// User Details

	By FirstNameField = By.xpath("//input[@id='user_first_name']");
	By LastNameField = By.xpath("//input[@id='user_last_name']");
	By PhoneField_UD = By.xpath("//input[@class='form-control phone_number non_form_field unique_phone']");
	By SecondaryPhoneField = By.xpath("//input[@class='form-control phone_number unique_phone']");
	By TimeZoneDropdown_UD = By.xpath("//select[@id='user_time_zone']");
	By EmailField_UD = By.xpath("//input[@id='user_email']");
	By TeamField = By.xpath("//input[@id='team_name']");

	// Social Urls

	By FacebookField = By.xpath("//input[@id='client_social_urls_facebook']");
	By TwitterField = By.xpath("//input[@id='client_social_urls_twitter']");
	By GooglePlusField = By.xpath("//input[@id='client_social_urls_google_plus']");
	By LinkedInField = By.id("client_social_urls_linked_in");
	By YoutubeField = By.id("client_social_urls_youtube");
	By InstagramField = By.id("client_social_urls_instagram");

	// Vendor Configurations

	By PromotionalEmailDropdown = By.xpath("//label[text()='Promotional Email']/following::span[1]");
	By PromotionalEmail_dd = By.xpath(".//*[@id='select2-drop']/ul/li/div");
	By PromotionalSMSdropdown = By.xpath("//label[text()='Promotional Sms']/following::span[1]");
	By PromotionalSMS_dd = By.xpath(".//*[@id='select2-drop']/ul/li/div");
	By TransactionalEmailDropdown = By.xpath("//label[text()='Transactional Email']/following::span[1]");
	By TransactionalEmail_dd = By.xpath(".//*[@id='select2-drop']/ul/li/div");
	By TransactionalSMSdropdown = By.xpath("//label[text()='Transactional Sms']/following::span[1]");
	By TransactionalSMS_dd = By.xpath(".//*[@id='select2-drop']/ul/li/div");

	By MarketingDomainDropdown = By.xpath("//span[text()='Select domain']");
	By MarketingDomain_dd = By.xpath(".//*[@id='select2-drop']/ul/li[1]/div");
	By SaveButton = By.xpath("//input[@name='commit']");

	// Required Fields
	By BusinessNameRequiredMessage = By.xpath("//label[text()='Business Name']/following::span[1]");
	By ShortNameRequiredMessage = By.xpath("//label[text()='Business Name']/following::span[5]");
	By WebsiteFieldRequiredMessage = By.xpath("//label[text()='Business Name']/following::span[6]");
	By EmailFieldRequiredMessage = By.xpath("//label[text()='Business Name']/following::span[7]");
	By EmailDomainRequiredMessage = By.xpath("//label[text()='Business Name']/following::span[9]");
	By UploadLogoRequiredMessage = By.xpath("//label[text()='Business Name']/following::span[12]");
	By ClientPhoneRequiredMessage = By.xpath("//label[text()='City for Indian PRI (Landline)']/preceding::span[1]");
	By CityForIndianPRIRequiredMessage = By
			.xpath("//label[text()='City for Indian PRI (Landline)']/following::span[3]");
	By IndianPRIRequiredMessage = By.xpath("//label[text()='Indian PRI (Landline)']/following::span[3]");
	By SMSmaskFieldRequiredMessage = By.xpath("//*[@for=\"transactional_sms_mask\"][2]"); 
	By AddressFieldRequiredMessage = By.xpath("//input[@id='client_address_attributes_address1']/following::span[1]");
	By CityFieldRequiredMessage = By.xpath("//input[@id='client_address_attributes_city']/following::span[1]");
	By StateDropdownRequiredMessage = By
			.xpath("//select[@name='client[address_attributes][state]']/following::span[1]");
	By CountryDropdownRequiredMessage = By
			.xpath("//select[@name='client[address_attributes][country]']/following::span[1]");
	By ZipFieldRequiredMessage = By.xpath("//input[@id='client_address_attributes_zip']/following::span[1]");
	By FirstNameRequiredMessage = By.xpath("//input[@id='user_first_name']/following::span[1]");
	By LastNameRequiredMessage = By.xpath("//input[@id='user_last_name']/following::span[1]");
	By UserEmailRequiredMessage = By.xpath("//input[@id='user_email']/following::span[1]");
	By TeamFieldRequiredMessage = By.xpath("//input[@id='team_name']/following::span[1]");
	By MarketingDomainRequiredMessage = By.xpath("//input[@id='client_domains']/following::span[1]");
	public By DomainMessage = By.xpath("//*[@id='client_form']/div[6]/div[2]/div[3]/div/div/p");

	WebDriver driver = null;

	public CreateClientFormPage(WebDriver driver) {

		this.driver = driver;// Calling Browser
	}

	GetTestData getTestData = new GetTestData();

	// -------Filling Client Details--------

	public void enterClientFirstName() {
		String clientFirstNameObj = getTestData.firstName;
		driver.findElement(clientFirstNameField).sendKeys(clientFirstNameObj);
	}

	public void enterClientLastName() {
		String clientLastNameObj = getTestData.lastName;
		driver.findElement(clientLastNameField).sendKeys(clientLastNameObj);
	}

	public void enterClientPhoneNumber() {
		String clientPhoneObj = " " + getTestData.phoneNumber;
		driver.findElement(clientPhoneNumber).sendKeys(clientPhoneObj);
	}

	public void enterFirstName(String firstname) {
		driver.findElement(FirstNameField).sendKeys(firstname);
	}
	
	public void enterLastName(String lastname) {
		driver.findElement(LastNameField).sendKeys(lastname);
	}
	
	public void enterBusinessName(String businessName) {
		// String businessName = getTestData.location;
		driver.findElement(BusinessNameField).sendKeys(businessName);
	}
	
	public void enterBussinessType() {
		driver.findElement(BussinessType).click();
		driver.findElement(selectBussinessType).click();
	}
	
	public void enterTransactionalSmsMask() {
		String tSmsMask= getTestData.transactionalSmsMaskfaker;
		driver.findElement(TransactionalSmsMask).sendKeys(tSmsMask);
		
	}

	public void enterShortName(String shortName) {
		driver.findElement(ShortNameField).sendKeys(shortName);
	}

	public void enterClientWebsite(String businessName) {
		driver.findElement(WebsiteField).sendKeys("http://www." + businessName + "auto.com");
	}

	public void enterClientEmail() {
		String clientEmail = getTestData.clientEmail;
		driver.findElement(EmailField_CD).sendKeys(clientEmail);
	}

	public void enterEmailDomain(String email) {
		driver.findElement(EmailDomainField).sendKeys(email);
	}

	public void enterSetUpCharge(String setup) {
		driver.findElement(SetUpChargesField).sendKeys(setup);
	}

	public void uploadFile() throws InterruptedException, AWTException {

		String claimZIP = System.getProperty("user.dir") + "/DataFile/" + "google-new-logo.png";

		WebElement elem = driver.findElement(By.xpath("//input[@type='file']"));
		// Mention the path of file to do the upload
		elem.sendKeys(claimZIP);

	}

	public void enterClientPhoneNumber(String phone) {
		driver.findElement(PhoneField_CD).sendKeys(phone);
	}

	public void addClientPhoneNumber() {

		String phoneNum = " " + getTestData.phoneNumber;
		driver.findElement(clientPhone).sendKeys(phoneNum);
	}

	public void selectCityForIndianPRI(String pri) {
		driver.findElement(CityForIndianPRIDropdown).click();
		driver.findElement(CityForIndianPRIField).sendKeys(pri);
		driver.findElement(CityForIndianPRI_dd).click();
	}

	public void selectIndianPRInumber() {
		driver.findElement(IndianPRIDropdown).click();
		driver.findElement(IndianPRI_dd).click();
	}

	public void enterSMSmask(String sms) {
		driver.findElement(SMSMaskField).sendKeys(sms);
	}

	public void enterVirtualNumber(String vnum) {
		driver.findElement(VirtualNumbersField).sendKeys(vnum);
		driver.findElement(VirtualNumber_dd).click();
	}

	public void selectTimeZone_CD() {
		Select oSelect = new Select(driver.findElement(StateField));
		oSelect.selectByIndex(1);
	}

	public void clickOnUploadButton() {
		driver.findElement(UploadLogoButton).click();
		

	}

	// -----------Filling Client Address----------------

	public void enterAddress_M(String address1, String city, String zip, String country) {
		driver.findElement(Address1Field).sendKeys(address1);
		driver.findElement(CityField).sendKeys(city);

		Select iSelect = new Select(driver.findElement(CountryDropdown));
		iSelect.selectByVisibleText(country);

		Select oSelect = new Select(driver.findElement(StateField));
		oSelect.selectByVisibleText("Maharashtra");
		driver.findElement(ZipField).sendKeys(zip);
	}

	public void enterAddress_NM(String address1, String address2, String city, String country, String zip) {
		driver.findElement(Address1Field).sendKeys(address1);
		driver.findElement(Address2Field).sendKeys(address2);
		driver.findElement(CityField).sendKeys(city);
		Select iSelect = new Select(driver.findElement(CountryDropdown));
		iSelect.selectByVisibleText(country);
		Select oSelect = new Select(driver.findElement(StateField));
		oSelect.selectByVisibleText("Maharashtra");
		driver.findElement(ZipField).sendKeys(zip);
	}

	// --------------Mix Panel Setting-------------------

	public void settingMixpanel(String api, String token, String secret) throws InterruptedException {
		driver.findElement(MixpanelSettingCheckbox).click();
		Thread.sleep(2000);
		driver.findElement(ApiKeyField).sendKeys(api);
		driver.findElement(TokenField).sendKeys(token);
		driver.findElement(SecretField).sendKeys(secret);
	}

	// --------------Filling User Details----------------

	public void enterFirstName() {
		String adminFirstNameObj = getTestData.firstName;
		driver.findElement(FirstNameField).sendKeys(adminFirstNameObj);
	}

	public void enterLastName() {
		String adminLastNameObj = getTestData.lastName;
		driver.findElement(LastNameField).sendKeys(adminLastNameObj);
	}

	public void enterUserPhoneNumber() {
		String phoneObj = " " + getTestData.phoneNum;
		driver.findElement(PhoneField_UD).sendKeys(phoneObj);
	}

	public void enterUserEmail() {
		String userEmail = getTestData.email;
		driver.findElement(EmailField_UD).sendKeys(userEmail);
	}

	public void enterUsersTeam() {
		String team = getTestData.team;
		driver.findElement(TeamField).sendKeys(team);
	}

	public void selectPromotionalEmail() {
		driver.findElement(PromotionalEmailDropdown).click();
		driver.findElement(PromotionalEmail_dd).click();
	}

	public void vendorConfiguration() {
		driver.findElement(PromotionalEmailDropdown).click();
		driver.findElement(PromotionalEmail_dd).click();
		driver.findElement(PromotionalSMSdropdown).click();
		driver.findElement(PromotionalSMS_dd).click();
		driver.findElement(TransactionalEmailDropdown).click();
		driver.findElement(TransactionalEmail_dd).click();
		driver.findElement(TransactionalSMSdropdown).click();
		driver.findElement(TransactionalSMS_dd).click();
	}

	public void selectMarketingDomain() {
		driver.findElement(MarketingDomainDropdown).click();
		driver.findElement(MarketingDomain_dd).click();
	}

	public void clickOnSaveButton() {
		driver.findElement(SaveButton).click();
	}

	public String getBusinessNameRequiredMessage() {
		String businessNameRequiredMessage = driver.findElement(BusinessNameRequiredMessage).getText();
		return businessNameRequiredMessage;
	}

	public String getShortNameRequiredMessage() {
		String shortNameRequiredMessage = driver.findElement(ShortNameRequiredMessage).getText();
		return shortNameRequiredMessage;
	}

	public String getWebsiteFieldRequiredMessage() {
		String websiteFieldRequiredMessage = driver.findElement(WebsiteFieldRequiredMessage).getText();
		return websiteFieldRequiredMessage;
	}

	public String getEmailFieldRequiredMessage() {
		String emailFieldRequiredMessage = driver.findElement(EmailFieldRequiredMessage).getText();
		return emailFieldRequiredMessage;
	}

	public String getEmailDomainRequiredMessage() {
		String emailDomainRequiredMessage = driver.findElement(EmailDomainRequiredMessage).getText();
		return emailDomainRequiredMessage;
	}

	public String getUploadLogoRequiredMessage() {
		String uploadLogoRequiredMessage = driver.findElement(UploadLogoRequiredMessage).getText();
		return uploadLogoRequiredMessage;
	}

	public String getClientPhoneRequiredMessage() {
		String clientPhoneRequiredMessage = driver.findElement(ClientPhoneRequiredMessage).getText();
		return clientPhoneRequiredMessage;
	}

	public String getCityForIndianPRIRequiredMessage() {
		String cityForIndianPRIRequiredMessage = driver.findElement(CityForIndianPRIRequiredMessage).getText();
		return cityForIndianPRIRequiredMessage;
	}

	public String getIndianPRIRequiredMessage() {
		String indianPRIRequiredMessageRequiredMessage = driver.findElement(IndianPRIRequiredMessage).getText();
		return indianPRIRequiredMessageRequiredMessage;
	}

	public String getSMSmaskFieldRequiredMessage() {
		String smsMaskFieldRequiredMessage = driver.findElement(SMSmaskFieldRequiredMessage).getText();
		return smsMaskFieldRequiredMessage;
	}

	public String getAddressFieldRequiredMessage() {
		String addressFieldRequiredMessage = driver.findElement(AddressFieldRequiredMessage).getText();
		return addressFieldRequiredMessage;
	}

	public String getCityFieldRequiredMessage() {
		String cityFiledRequiredMessage = driver.findElement(CityFieldRequiredMessage).getText();
		return cityFiledRequiredMessage;
	}

	public String getStateDropdownRequiredMessage() {
		String stateDropdownRequiredMessage = driver.findElement(StateDropdownRequiredMessage).getText();
		return stateDropdownRequiredMessage;
	}

	public String getCountryDropdownRequiredMessage() {
		String countryDropdownRequiredMessage = driver.findElement(CountryDropdownRequiredMessage).getText();
		return countryDropdownRequiredMessage;
	}

	public String getZipFieldRequiredMessage() {
		String zipFieldRequiredMessage = driver.findElement(ZipFieldRequiredMessage).getText();
		return zipFieldRequiredMessage;
	}

	public String getFirstNameRequiredMessage() {
		String firstNameRequiredMessage = driver.findElement(FirstNameRequiredMessage).getText();
		return firstNameRequiredMessage;
	}

	public String getLastNameRequiredMessage() {
		String lastNameRequiredMessage = driver.findElement(LastNameRequiredMessage).getText();
		return lastNameRequiredMessage;
	}

	public String getUserEmailRequiredMessage() {
		String userEmailRequiredMessage = driver.findElement(UserEmailRequiredMessage).getText();
		return userEmailRequiredMessage;
	}

	public String getTeamFieldRequiredMessage() {
		String teamFieldRequiredMessage = driver.findElement(TeamFieldRequiredMessage).getText();
		return teamFieldRequiredMessage;
	}

	public String getMarketingDomainRequiredMessage() {
		String marketingDomainRequiredMessage = driver.findElement(MarketingDomainRequiredMessage).getText();
		return marketingDomainRequiredMessage;
	}

}
